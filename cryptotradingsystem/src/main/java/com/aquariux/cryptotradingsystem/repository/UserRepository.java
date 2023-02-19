package com.aquariux.cryptotradingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aquariux.cryptotradingsystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
