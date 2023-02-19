package com.aquariux.cryptotradingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aquariux.cryptotradingsystem.entity.Crypto;

import java.util.Optional;

@Repository
public interface CryptoRepository extends JpaRepository<Crypto, Long> {
	
	Optional<Crypto> findBySymbol(String symbol);
	
}
