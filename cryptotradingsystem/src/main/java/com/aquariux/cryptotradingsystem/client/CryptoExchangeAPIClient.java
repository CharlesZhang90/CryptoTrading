package com.aquariux.cryptotradingsystem.client;

import com.fasterxml.jackson.databind.JsonNode;

public interface CryptoExchangeAPIClient {
	
    JsonNode fetchTickerData();
}
