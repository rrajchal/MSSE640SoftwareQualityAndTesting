package com.selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EcommerceSystem {

	WebDriver driver = null;
	WebDriverWait wait = null;
	EcommerceSystem() {
		driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        driver.get("https://demo.nopcommerce.com/"); // start with this website
	}
	
	public boolean createAccount() throws InterruptedException {
		Thread.sleep(1000);
		WebElement registerLink = driver.findElement(By.cssSelector("a.ico-register"));
        registerLink.click();  // click on Register button
        
        Thread.sleep(1000);
        WebElement maleRadioButton = driver.findElement(By.id("gender-male"));
        maleRadioButton.click();
        
        Thread.sleep(1000);
		WebElement firstNameInput = driver.findElement(By.name("FirstName"));
        firstNameInput.sendKeys("Rajesh");
        
        Thread.sleep(1000);
        WebElement lastNameInput = driver.findElement(By.name("LastName"));
        lastNameInput.sendKeys("Rajchal");

        Thread.sleep(1000);
        Select selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
        selectDay.selectByValue("5");
        Select selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        selectMonth.selectByValue("10");
        Select selectYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
        selectYear.selectByValue("1990");
        
        Thread.sleep(1000);
        WebElement email = driver.findElement(By.id("Email"));
        email.sendKeys("rrajchal@gmail.com");
        
        Thread.sleep(1000);
        WebElement checkBox = driver.findElement(By.id("Newsletter"));
        if (checkBox.isSelected()) {
            checkBox.click();
        }
        
        Thread.sleep(1000);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("test1Test2");
        WebElement confirmPassword = driver.findElement(By.id("ConfirmPassword"));
        confirmPassword.sendKeys("test1Test2");

        Thread.sleep(1000);
		WebElement register = driver.findElement(By.id("register-button"));
		register.click();  // click on Register button
		
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("registerresult/1")) {
            System.out.println("Account created successfully!");
            return true;
        } else {
            WebElement successMessage = driver.findElement(By.cssSelector(".result"));
            if (successMessage.isDisplayed() && successMessage.getText().contains("Your registration completed")) {
                System.out.println("Account created successfully!");
                return true;
            } else {
                System.out.println("Account creation failed.");
                return false;
            }
        }
	}
	
	public boolean login() throws InterruptedException {
		Thread.sleep(1000);
		WebElement loginLink = driver.findElement(By.cssSelector("a.ico-login"));
		//WebElement loginLink = driver.findElement(By.xpath("//a[@class='ico-login']"));
		loginLink.click();
		
		Thread.sleep(1000);
        WebElement email = driver.findElement(By.id("Email"));
        email.sendKeys("rrajchal@gmail.com");
           
        Thread.sleep(1000);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("test1Test2");
        
        Thread.sleep(1000);
        driver.findElement(By.className("login-button")).click();
        
        try {
        	WebElement logoutButton = driver.findElement(By.cssSelector("a.ico-logout"));
            if (logoutButton.isDisplayed()) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Login failed.");
                return false;
            }
        } catch (Exception e) {
        	return false;
        }
	}

	public boolean addToCart() throws InterruptedException {
		driver.findElement(By.linkText("$25 Virtual Gift Card")).click();
		WebElement recipientName = driver.findElement(By.id("giftcard_43_RecipientName"));
		recipientName.sendKeys("Rajesh");
		
		WebElement recipientEmail = driver.findElement(By.id("giftcard_43_RecipientEmail"));
		recipientEmail.sendKeys("rrajchal@gmail.com");
		
		WebElement message = driver.findElement(By.id("giftcard_43_Message"));
		message.sendKeys("Congratulation!");
		
		int cartCountBefore = Integer.parseInt(driver.findElement(By.className("cart-qty")).getText().replaceAll("\\D+", ""));
		System.out.println("# of items in cart before addition: " + cartCountBefore);
		
		driver.findElement(By.className("add-to-cart-button")).click();
		Thread.sleep(1000);
		WebElement cart = driver.findElement(By.className("cart-qty"));
		System.out.println(cart.getText().replaceAll("\\D+", ""));
		
		int cartCountAfter = Integer.parseInt(driver.findElement(By.className("cart-qty")).getText().replaceAll("\\D+", ""));
		System.out.println("# of items in cart after addition:  " + cartCountAfter);
		
		return cartCountAfter - cartCountBefore == 1;
	}
	
	public boolean removeFromCart() throws InterruptedException {
		Thread.sleep(5000);
		WebElement shoppingCart = driver.findElement(By.cssSelector("a.ico-cart"));
		shoppingCart.click();
		
		Thread.sleep(5000);
		WebElement checkBox = driver.findElement(By.className("remove-btn"));
		checkBox.click();
		
		Thread.sleep(5000);
		int cartItems = Integer.parseInt(driver.findElement(By.className("cart-qty")).getText().replaceAll("\\D+", ""));
		
		return cartItems == 0;
	}
	
	public void close() {
		driver.quit();
	}
}
