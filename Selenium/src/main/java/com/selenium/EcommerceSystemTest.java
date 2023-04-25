package com.selenium;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EcommerceSystemTest {

	@Test
	void testCreateAccount() throws InterruptedException {
		EcommerceSystem e = new EcommerceSystem();
		
		assertTrue(e.createAccount());
		e.close();
	}
	
	@Test
	void testLogin() throws InterruptedException {
		EcommerceSystem e = new EcommerceSystem();
		assertTrue(e.login());
		e.close();
	}
	
	@Test
	void addToCart() throws InterruptedException {
		EcommerceSystem e = new EcommerceSystem();
		assertTrue(e.login());
		assertTrue(e.addToCart());
		assertTrue(e.removeFromCart());
		
		e.close();
	}

}
