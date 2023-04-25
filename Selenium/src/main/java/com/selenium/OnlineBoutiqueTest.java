package com.selenium;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OnlineBoutiqueTest {

	@Test
	void testPage() throws Exception {
		OnlineBoutique ob = new OnlineBoutique();
		String currentUrl = ob.getCurrentUrl();
		System.out.println("currentUrl: " + currentUrl);
		Assertions.assertEquals(ob.getCurrentUrl(), "https://onlineboutique.dev/");
		
		ob.closePage();
	}
	
	@Test
	void testCheckout() throws Exception {
		OnlineBoutique ob = new OnlineBoutique();
		ob.checkout();

		Assertions.assertEquals(ob.getCurrentUrl(), "https://onlineboutique.dev/cart/checkout");
		
		double totalPrice = ob.getTotalPrice();
		double actualPrice = getTotalPrice();
		Assertions.assertEquals(totalPrice, actualPrice);
		
		int quantity = ob.getQuantity();
		Assertions.assertEquals(quantity, 2);
		
		ob.closePage();
		
	}
	
	private double getTotalPrice() {
		double tankTopPrice = 18.99;
		int quantity = 2;
		double shipping = 8.99;
		return tankTopPrice * quantity + shipping;
	}
	

}
