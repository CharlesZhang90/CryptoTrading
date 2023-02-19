package com.aquariux.cryptotradingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aquariux.cryptotradingsystem.dto.CryptoDTO;
import com.aquariux.cryptotradingsystem.service.CryptoPriceService;

@RestController
@RequestMapping("/crypto")
public class CryptoController {
	
    @Autowired
    private CryptoPriceService cryptoPriceService;

    @GetMapping("/bestAggregatedPrice")
    public ResponseEntity<List<CryptoDTO>> getBestAggregatedPrice() {
        return ResponseEntity.ok().body(cryptoPriceService.getBestAggregatedPrice());
    }
    
}
