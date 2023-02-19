package com.aquariux.cryptotradingsystem.controller;

import java.math.BigDecimal;

import com.aquariux.cryptotradingsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aquariux.cryptotradingsystem.dto.BalanceDTO;
import com.aquariux.cryptotradingsystem.dto.UserDTO;
import com.aquariux.cryptotradingsystem.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/balance")
    public ResponseEntity<BalanceDTO> getWalletBalance(@PathVariable Long userId) {
    	BalanceDTO balanceDto = new BalanceDTO();
    	try {
        	balanceDto.setWalletBalance(userService.getWalletBalance(userId).toString());
        	balanceDto.setCryptoBalance(userService.getCryptoBalance(userId).toString());
    	} catch (Exception e) {
    		return ResponseEntity.badRequest().body(null);
    	}
        return ResponseEntity.ok(balanceDto);
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDto){
    	User user = null;
    	try {
    		user = userService.createUser(userDto);
    	} catch (Exception e) {
    		return ResponseEntity.badRequest().body(null);
    	}
    	return ResponseEntity.ok(user);
    }

}
