package com.aquariux.cryptotradingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aquariux.cryptotradingsystem.entity.CryptoBalance;

@Repository
public interface CryptoBalanceRepository extends JpaRepository<CryptoBalance, Long> {

}
