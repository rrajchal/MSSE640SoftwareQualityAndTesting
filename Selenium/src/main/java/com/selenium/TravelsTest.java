package com.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TravelsTest {

	@Test
	void testCompleteInputFields() throws InterruptedException {
		Travels t = new Travels();
		String[] formFields = t.fillForm();

		assertEquals(formFields[0], "Rajesh");
		assertEquals(formFields[1], "Rajchal");
		assertEquals(formFields[2], "Amazing Business");
		assertEquals(formFields[3], "rrajchal@gmail.com");
		
		assertTrue(t.isSuccessful());
		
		t.closePage();
	}
	
	@Test
	void testIncompleteInputFields() throws InterruptedException {
		Travels t = new Travels();
		String[] formFields = t.incompleteFillForm();

		assertEquals(formFields[0], "Rajesh");
		assertEquals(formFields[1], "Rajchal");
		assertEquals(formFields[2], "Amazing Business");
		assertEquals(formFields[3], "rrajchal@gmail.com");
		assertEquals(t.getAlertMsg(), "Please input result number");
		
		assertFalse(t.isSuccessful());
		
		t.acceptAlertAndCompleteForm();
		
		assertTrue(t.isSuccessful());
		
		t.closePage();
		
	}
	
	@Test
	void testValidateResult() throws InterruptedException {
		Travels t = new Travels();
		int total = t.validateResult();
		assertEquals(total, t.getNum1() + t.getNum2());
		
		t.closePage();
	}

}
