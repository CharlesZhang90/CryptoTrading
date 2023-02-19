package com.aquariux.cryptotradingsystem.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.aquariux.cryptotradingsystem.dto.UserDTO;
import com.aquariux.cryptotradingsystem.entity.User;


public interface UserService {
	
	BigDecimal getWalletBalance(Long userId);
	
	BigDecimal getCryptoBalance(Long userId);
	
	User createUser(UserDTO userDto);
	
}
