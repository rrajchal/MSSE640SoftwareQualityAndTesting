package com.selenium;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Travels {
	private static WebDriver driver = null;
	private String[] formFields = new String[4]; // First Name, Last Name, Business Name, Email
	private Integer num1 = null;
	private Integer num2 = null;
	private String alertMsg = null;
	private Alert alert = null;

	Travels() {
		driver = new ChromeDriver();
		driver.get("https://phptravels.com/demo/");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
	}
	
	public String[] fillForm() throws InterruptedException {
		Thread.sleep(1000);
		WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys("Rajesh");
        formFields[0] = firstNameInput.getAttribute("value");
        
        Thread.sleep(1000);
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys("Rajchal");
        formFields[1] = lastNameInput.getAttribute("value");
        
        Thread.sleep(1000);
        WebElement businessNameInput = driver.findElement(By.name("business_name"));
        businessNameInput.sendKeys("Amazing Business");
        formFields[2] = businessNameInput.getAttribute("value");
        
        Thread.sleep(1000);
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("rrajchal@gmail.com");
        formFields[3] = emailInput.getAttribute("value");
        
        Thread.sleep(1000);
        WebElement numb1 = driver.findElement(By.id("numb1"));
        int num1 = Integer.parseInt(numb1.getText());
        setNum1(num1);
        WebElement numb2 = driver.findElement(By.id("numb2"));
        int num2 = Integer.parseInt(numb2.getText());
        setNum2(num2);
        int total = getNum1() + getNum2();
        WebElement result = driver.findElement(By.id("number"));
        result.clear();
        result.sendKeys(String.valueOf(total));
        
        Thread.sleep(1000);
        WebElement submitButton = driver.findElement(By.id("demo"));
        submitButton.click();

		
		return formFields;
	}
	
	public String[] incompleteFillForm() throws InterruptedException {
		Thread.sleep(1000);
		WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys("Rajesh");
        formFields[0] = firstNameInput.getAttribute("value");
        
        Thread.sleep(1000);
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys("Rajchal");
        formFields[1] = lastNameInput.getAttribute("value");
        
        Thread.sleep(1000);
        WebElement businessNameInput = driver.findElement(By.name("business_name"));
        businessNameInput.sendKeys("Amazing Business");
        formFields[2] = businessNameInput.getAttribute("value");
        
        Thread.sleep(1000);
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("rrajchal@gmail.com");
        formFields[3] = emailInput.getAttribute("value");
        
        Thread.sleep(1000);
        WebElement submitButton = driver.findElement(By.id("demo"));
        submitButton.click();
        
        this.alert = driver.switchTo().alert();
        setAlertMsg(this.alert.getText());
		
		return formFields;
	}
	
	public void acceptAlertAndCompleteForm() throws InterruptedException {
		try {
		    alert.accept();
		} catch (NoAlertPresentException e) {
		}

		driver.switchTo().defaultContent();
		
		Thread.sleep(1000);
        WebElement numb1 = driver.findElement(By.id("numb1"));
        int num1 = Integer.parseInt(numb1.getText());
        setNum1(num1);
        WebElement numb2 = driver.findElement(By.id("numb2"));
        int num2 = Integer.parseInt(numb2.getText());
        setNum2(num2);
        int total = getNum1() + getNum2();
        WebElement result = driver.findElement(By.id("number"));
        result.clear();
        result.sendKeys(String.valueOf(total));
        
        Thread.sleep(1000);
        WebElement submitButton = driver.findElement(By.id("demo"));
        submitButton.click();
		
	}
	
	public boolean isSuccessful() {
		List<WebElement> circleElements = new ArrayList<>();
		try {
			circleElements = driver.findElements(By.tagName("circle"));
		} catch (Exception e) {
			return false;
		}
		return circleElements.size() > 0;
	}
	
	public int validateResult() throws InterruptedException {
		Thread.sleep(1000);
        WebElement numb1 = driver.findElement(By.id("numb1"));
        int num1 = Integer.parseInt(numb1.getText());
        setNum1(num1);
        WebElement numb2 = driver.findElement(By.id("numb2"));
        int num2 = Integer.parseInt(numb2.getText());
        setNum2(num2);
        int total = getNum1() + getNum2();
        WebElement result = driver.findElement(By.id("number"));
        result.clear();
        result.sendKeys(String.valueOf(total));
        
		return total;
	}
	
	public void closePage() {
		driver.quit();
	}
	
	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}
	
	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}


}
