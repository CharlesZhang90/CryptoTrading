package com.aquariux.cryptotradingsystem.service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aquariux.cryptotradingsystem.dto.UserDTO;
import com.aquariux.cryptotradingsystem.entity.User;
import com.aquariux.cryptotradingsystem.repository.CryptoRepository;
import com.aquariux.cryptotradingsystem.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CryptoRepository cryptoRepository;
    
    @Override
    @Transactional
    public BigDecimal getWalletBalance(Long userId) {
        return Optional.of(
					userRepository.findById(userId)
						.orElseThrow(()->new EntityNotFoundException("User with id: " + userId + " not found.")
				)
        		.getWalletBalance())
				.orElse(BigDecimal.ZERO);
    }

	@Override
	@Transactional
	public BigDecimal getCryptoBalance(Long userId) {
		// crypto balance is based on number of crypto multiply by current best selling price
		return Optional.of(
					userRepository.findById(userId)
							.orElseThrow(()->new EntityNotFoundException("User with id: " + userId + " not found."))
							.getCryptoBalances()
				)
				.filter(list -> !list.isEmpty())
				.map(list -> list.stream()
					.map(e -> e.getCryptoBalance().multiply(cryptoRepository.findBySymbol(e.getSymbol()).get().getBestSellingPrice()))
					.reduce(BigDecimal.ZERO, BigDecimal::add))
				.orElse(BigDecimal.ZERO);
	}

	@Override
	@Transactional
	public User createUser(UserDTO userDto) throws Exception {
		return userRepository.save(userRepository.findByUserName(userDto.getUserName())
				  			 .map(user -> new User(userDto))
				  			 .orElseThrow(() -> new Exception("User already exists.")));
	}
}
