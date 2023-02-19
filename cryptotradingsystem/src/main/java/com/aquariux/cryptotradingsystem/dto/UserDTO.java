package com.aquariux.cryptotradingsystem.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private String userName;
	private String email;
	private String password;
	private BigDecimal walletBalance;
	
}
