package com.aquariux.cryptotradingsystem.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class HuobiClient implements CryptoExchangeAPIClient{

    @Value("${binanceUrl}")
    private String binanceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JsonNode fetchTickerData() {
        return Optional.of(
                    restTemplate.getForEntity(binanceUrl, JsonNode.class)
                )
                .map(ResponseEntity::getBody)
                .map(node -> node.get("data"))
                .orElse(JsonNodeFactory.instance.objectNode());
    }
}
