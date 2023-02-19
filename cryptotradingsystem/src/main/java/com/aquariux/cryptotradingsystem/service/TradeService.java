package com.aquariux.cryptotradingsystem.service;

import java.math.BigDecimal;
import java.util.List;

import com.aquariux.cryptotradingsystem.dto.TradeDTO;

public interface TradeService {
	
	public TradeDTO makeTrade(Long userId, Long cryptoId, String tradeType, BigDecimal quantity);
	
	public List<TradeDTO> getTradeHistoryByUserId(Long userId);
	
}
