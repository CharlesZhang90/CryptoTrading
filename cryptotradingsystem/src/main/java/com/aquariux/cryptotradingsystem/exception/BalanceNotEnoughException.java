package com.aquariux.cryptotradingsystem.exception;

public class BalanceNotEnoughException extends RuntimeException {
	
	private static final long serialVersionUID = 6678168045380529281L;

	public BalanceNotEnoughException(String message) {
		super(message);
	}


}
