package com.aquariux.cryptotradingsystem.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.aquariux.cryptotradingsystem.client.BinanceClient;
import com.aquariux.cryptotradingsystem.client.HuobiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.aquariux.cryptotradingsystem.dto.CryptoDTO;
import com.aquariux.cryptotradingsystem.entity.Crypto;
import com.aquariux.cryptotradingsystem.repository.CryptoRepository;

import jakarta.transaction.Transactional;

@Service
public class CryptoPriceServiceImpl implements CryptoPriceService {

    @Autowired
    private CryptoRepository cryptoRepository;

    @Autowired
    private BinanceClient binanceClient;

    @Autowired
    private HuobiClient huobiClient;
    
    @Scheduled(fixedDelay = 10000) // run every 10 seconds
    @Transactional
    public void updateCryptoPrices() {
        // Fetch prices from Binance API
        binanceClient.fetchTickerData()
                // Update Crypto table with the best aggregated prices
                .forEach(node -> updateSingleCryptoPrice(node.get("symbol").asText(),
                            new BigDecimal(node.get("bidPrice").asText()),
                            new BigDecimal(node.get("askPrice").asText()))
                );
        // Fetch prices from Huobi API
        huobiClient.fetchTickerData()
                .forEach(node -> updateSingleCryptoPrice(node.get("symbol").asText(),
                        new BigDecimal(node.get("bid").asText()),
                        new BigDecimal(node.get("ask").asText()))
                );
    }

    private void updateSingleCryptoPrice(String symbol, BigDecimal bidPrice, BigDecimal askPrice) {
        if (symbol.equalsIgnoreCase("ETHUSDT") || symbol.equalsIgnoreCase("BTCUSDT")) {
            Crypto updatedCrypto = cryptoRepository.findBySymbol(symbol.toUpperCase())
                    .map(crypto -> {
                        crypto.setBestBuyingPrice(crypto.getBestBuyingPrice().compareTo(askPrice) < 0 ? crypto.getBestBuyingPrice() : askPrice);
                        crypto.setBestSellingPrice(crypto.getBestSellingPrice().compareTo(bidPrice) >=0 ? crypto.getBestSellingPrice() : bidPrice);
                        crypto.setLastUpdate(LocalDateTime.now());
                        return crypto;
                    }).orElse(new Crypto(symbol, askPrice, bidPrice));
            cryptoRepository.save(updatedCrypto);
        }
    }

    @Transactional
    public List<CryptoDTO> getBestAggregatedPrice() {
    	// find all cryptos and get best buying and selling price and add to crypto dto for return
        return cryptoRepository.findAll()
        					   .stream()
        					   .map(CryptoDTO::new)
        					   .collect(Collectors.toList());
    }
}
