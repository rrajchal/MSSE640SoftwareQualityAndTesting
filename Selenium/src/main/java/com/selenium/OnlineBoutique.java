package com.selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class OnlineBoutique {
	private static WebDriver driver = null;
	private double totalPrice = 0.0;
	private int quantity = 0;

	OnlineBoutique()
	{
		driver = new ChromeDriver();
		driver.get("https://onlineboutique.dev");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
	}

	public void checkout() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("currentUrl: " + driver.getCurrentUrl());
		WebElement tankTop = driver.findElement(By.cssSelector("a[href='/product/66VCHSJNUP']"));
		WebElement item = driver.findElement(By.xpath("//*[contains(text(), 'Tank')]"));
		System.out.println("Selected Item: " + item.getText());
		tankTop.click();
		
		System.out.println("\nAfter clicking, new currentUrl: " + driver.getCurrentUrl());
		Thread.sleep(1000);
		WebElement quantityDrowdown = driver.findElement(By.id("quantity"));
		Select select = new Select(quantityDrowdown);
		select.selectByIndex(1);
		setQuantity(Integer.parseInt(select.getFirstSelectedOption().getAttribute("value")));
		System.out.println("Selected quantity: " + getQuantity());
		WebElement submitButton = driver.findElement(By.cssSelector("button"));
		submitButton.click();
		
		System.out.println("Clicked on Add To Cart");
		Thread.sleep(1000);
		System.out.println("\nNew currentUrl: " + driver.getCurrentUrl());
		WebElement total = driver.findElement(By.xpath("//*[contains(text(), 'Total')]/following-sibling::div"));
		setTotalPrice(Double.parseDouble(total.getText().substring(1)));
		System.out.println("Total Price: " + getTotalPrice());
		WebElement placeOrderButton = driver.findElement(By.cssSelector("button.cymbal-button-primary[type='submit']"));
		placeOrderButton.click();
		
		System.out.println("Clicked on Place Order");
		Thread.sleep(1000);
		System.out.println("\nNew currentUrl: " + driver.getCurrentUrl());
	}
	
	public void closePage() {
		driver.quit();
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

}
