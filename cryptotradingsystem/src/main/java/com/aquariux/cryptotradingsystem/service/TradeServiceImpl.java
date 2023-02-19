package com.aquariux.cryptotradingsystem.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aquariux.cryptotradingsystem.dto.TradeDTO;
import com.aquariux.cryptotradingsystem.entity.Crypto;
import com.aquariux.cryptotradingsystem.entity.CryptoBalance;
import com.aquariux.cryptotradingsystem.entity.Trade;
import com.aquariux.cryptotradingsystem.entity.User;
import com.aquariux.cryptotradingsystem.exception.BalanceNotEnoughException;
import com.aquariux.cryptotradingsystem.repository.CryptoBalanceRepository;
import com.aquariux.cryptotradingsystem.repository.CryptoRepository;
import com.aquariux.cryptotradingsystem.repository.TradeRepository;
import com.aquariux.cryptotradingsystem.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private CryptoBalanceRepository cryptoBalanceRepository;
    
    @Autowired
    private CryptoRepository cryptoRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public TradeDTO makeTrade(Long userId, Long cryptoId, String tradeType, BigDecimal quantity) {

        User user = userRepository.findById(userId).orElse(null);
        Crypto crypto = cryptoRepository.findById(cryptoId).orElse(null);

        if (user == null || crypto == null) {
            throw new EntityNotFoundException("User or Crypto not found.");
        }

        BigDecimal price = tradeType.equalsIgnoreCase("BUY") ? crypto.getBestBuyingPrice() : crypto.getBestSellingPrice();

        BigDecimal tradeValue = price.multiply(quantity);

        if (tradeType.equalsIgnoreCase("BUY") && user.getWalletBalance().compareTo(tradeValue) < 0) {
        	throw new BalanceNotEnoughException("Wallet Balance not Enough.");
        }
        //get crypto balance if null new an object
        CryptoBalance cryptoBalance = user.getCryptoBalances()
        								  .stream()
        								  .filter(cb->cb.getSymbol().equalsIgnoreCase(crypto.getSymbol()))
        								  .findFirst()
        								  .orElse(null);
        
        if(Objects.isNull(cryptoBalance)) {
            if (tradeType.equalsIgnoreCase("SELL")) {
            	throw new BalanceNotEnoughException("Crypto Balance not Enough.");
            } else {
            	cryptoBalance = new CryptoBalance();
            	cryptoBalance.setUser(user);
            	cryptoBalance.setCryptoBalance(BigDecimal.ZERO);
            	cryptoBalance.setSymbol(crypto.getSymbol());
            }
        }else {
            if (tradeType.equalsIgnoreCase("SELL") && cryptoBalance.getCryptoBalance().compareTo(quantity) < 0) {
            	throw new BalanceNotEnoughException("Crypto Balance not Enough.");
            }
        }
        //save trade transaction
        Trade trade = new Trade();
        trade.setUser(user);
        trade.setCrypto(crypto);
        trade.setTradeType(tradeType);
        trade.setQuantity(quantity);
        trade.setPrice(price);
        trade.setTradeTime(LocalDateTime.now());
        tradeRepository.save(trade);
        
        // update wallet balance and crypto balance
        if (tradeType.equalsIgnoreCase("BUY")) {
            user.setWalletBalance(user.getWalletBalance().subtract(tradeValue));
            cryptoBalance.setCryptoBalance(cryptoBalance.getCryptoBalance().add(quantity));
        } else {
            user.setWalletBalance(user.getWalletBalance().add(tradeValue));
            cryptoBalance.setCryptoBalance(cryptoBalance.getCryptoBalance().subtract(quantity));
        }
        
        cryptoBalanceRepository.save(cryptoBalance);
        userRepository.save(user);

        return new TradeDTO(trade);
    }
    
    @Transactional
    public List<TradeDTO> getTradeHistoryByUserId(Long userId) {
        return Optional.of(
                    userRepository.findById(userId)
                        .orElseThrow(() -> new EntityNotFoundException("User with id: " + userId + " not found."))
                        .getTrades()
                )
                .filter(trades -> !trades.isEmpty())
                .map(trades -> trades.stream()
                        .map(TradeDTO::new)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
