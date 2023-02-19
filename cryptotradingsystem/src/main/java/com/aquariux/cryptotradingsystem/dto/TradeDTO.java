package com.aquariux.cryptotradingsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.aquariux.cryptotradingsystem.entity.Trade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeDTO {
	
	private String cryptoName;
	private String tradeType;
	private BigDecimal quantity;
	private BigDecimal price;
	private LocalDateTime tradeTime;
	
	public TradeDTO(Trade trade){
		this.setCryptoName(trade.getCrypto().getSymbol());
		this.setTradeType(trade.getTradeType());
		this.setQuantity(trade.getQuantity());
		this.setPrice(trade.getPrice());
		this.setTradeTime(trade.getTradeTime());
	}
	
}
