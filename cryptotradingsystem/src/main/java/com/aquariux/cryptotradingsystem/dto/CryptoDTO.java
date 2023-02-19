package com.aquariux.cryptotradingsystem.dto;

import java.math.BigDecimal;

import com.aquariux.cryptotradingsystem.entity.Crypto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CryptoDTO {
	
	private String symbol;
	private BigDecimal bestBuyingPrice;
	private BigDecimal bestSellingPrice;
	
	public CryptoDTO(Crypto crypto){
		this.setSymbol(crypto.getSymbol());
		this.setBestBuyingPrice(crypto.getBestBuyingPrice());
		this.setBestSellingPrice(crypto.getBestSellingPrice());
	}
}
