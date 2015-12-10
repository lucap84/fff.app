package it.fff.business.strategy.impl;

import it.fff.business.strategy.VerificationCodeStrategy;

public class VerificationCodeStategyHashing implements VerificationCodeStrategy {

	@Override
	public String generateVerificationCode(String email) {
		return String.valueOf(email.hashCode());
	}

}
