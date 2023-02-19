package com.aquariux.cryptotradingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aquariux.cryptotradingsystem.entity.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

}
