package com.aquariux.cryptotradingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceDTO {
	
	private String walletBalance;
	private String cryptoBalance;
	
}
