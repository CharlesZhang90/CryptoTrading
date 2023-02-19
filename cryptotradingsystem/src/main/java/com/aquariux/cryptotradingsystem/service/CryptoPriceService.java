package com.aquariux.cryptotradingsystem.service;

import java.util.List;

import com.aquariux.cryptotradingsystem.dto.CryptoDTO;

public interface CryptoPriceService {
	
	public void updateCryptoPrices();
	
	public List<CryptoDTO> getBestAggregatedPrice();
	
}
