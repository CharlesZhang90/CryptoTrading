package com.aquariux.cryptotradingsystem.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cryptos")
public class Crypto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crypto_id")
    private Long cryptoId;
    
    @Column(name = "symbol", nullable = false)
    private String symbol;
    
    @Column(name = "best_selling_price", nullable = false)
    private BigDecimal bestSellingPrice;
    
    @Column(name = "best_buying_price", nullable = false)
    private BigDecimal bestBuyingPrice;
    
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;


    public Crypto(String symbol, BigDecimal bestSellingPrice, BigDecimal bestBuyingPrice) {
        this.symbol = symbol;
        this.bestSellingPrice = bestSellingPrice;
        this.bestBuyingPrice = bestBuyingPrice;
        this.lastUpdate = LocalDateTime.now();
    }
}
