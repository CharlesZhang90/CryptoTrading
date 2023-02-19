package com.aquariux.cryptotradingsystem.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aquariux.cryptotradingsystem.dto.TradeDTO;
import com.aquariux.cryptotradingsystem.service.TradeService;

@RestController
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/{userId}/{cryptoId}")
    public ResponseEntity<TradeDTO> makeTrade(
            @PathVariable Long userId,
            @PathVariable Long cryptoId,
            @RequestParam String tradeType,
            @RequestParam BigDecimal quantity
    ) {
    	TradeDTO tradeDto = null;
    	try {
    		tradeDto = tradeService.makeTrade(userId, cryptoId, tradeType, quantity);
    	} catch (Exception e){
    		return ResponseEntity.badRequest().body(null);
    	}
		return ResponseEntity.ok().body(tradeDto);	
    }
    
    @GetMapping("/{userId}/trades")
    public ResponseEntity<List<TradeDTO>> getTradeHistoryByUserId(@PathVariable Long userId) {
    	List<TradeDTO> list = new ArrayList<>();
    	try {
    		list = tradeService.getTradeHistoryByUserId(userId);
    	} catch (Exception e) {
    		return ResponseEntity.badRequest().body(null);
    	}
        return ResponseEntity.ok().body(list);
    }
}
