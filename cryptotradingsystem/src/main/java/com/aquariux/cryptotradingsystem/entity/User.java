package com.aquariux.cryptotradingsystem.entity;

import java.math.BigDecimal;
import java.util.List;

import com.aquariux.cryptotradingsystem.dto.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "user_name", nullable = false)
    private String userName;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "wallet_balance", nullable = false)
    private BigDecimal walletBalance;
    
    @OneToMany(mappedBy="user")
    private List<CryptoBalance> cryptoBalances;
    
    @OneToMany(mappedBy="user")
    private List<Trade> trades;

    public User(UserDTO userDto){
        this.setUserName(userDto.getUserName());
        this.setEmail(userDto.getEmail());
        this.setPassword(userDto.getPassword());
        this.setWalletBalance(userDto.getWalletBalance());
    }
}
