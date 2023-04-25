package com.selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reddit {
	WebDriver driver = null;
	WebDriverWait wait = null;
	ChromeOptions options = null;
	Reddit() {
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        
        driver.get("https://www.reddit.com/login");
	}
	
	public boolean login() throws InterruptedException {
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("rrajchal1234");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("rajesh1234");
        WebElement loginButton = driver.findElement(By.className("AnimatedForm__submitButton"));
        loginButton.click();
        
        Thread.sleep(5000);
        String pageTitle = driver.getTitle();
        if (pageTitle.contains("Dive into anything")) {
            System.out.println("Login Successful");
            return true;
        } else {
            System.out.println("Login Failed");
            return false;
        }
	}
	
	public boolean postABlog() throws InterruptedException {
		Thread.sleep(3000);
		WebElement createPostButton = driver.findElement(By.name("createPost"));
		createPostButton.click();
		
		Thread.sleep(3000);
        WebElement title = driver.findElement(By.cssSelector("textarea[placeholder='Title']"));
		title.sendKeys("Test");
		
		Thread.sleep(3000);
		WebElement text = driver.findElement(By.className("public-DraftEditor-content"));
        text.sendKeys("This is a selenium test");
        
        Thread.sleep(3000);
		WebElement saveDraft = driver.findElement(By.xpath("//button[text()='Save Draft']"));
		saveDraft.click();
		
		Thread.sleep(3000);
		try {
			WebElement updateDraft = driver.findElement(By.xpath("//button[text()='Update draft']"));
			if (updateDraft == null)
				return false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void close() {
		driver.quit();
	}
}
