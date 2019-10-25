package com.snap.util;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class GenericFunctions {

protected static  WebDriver driver;

	@BeforeClass
	public static void browserLaunch() {
		 System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.get("https://snapengage-qa.appspot.com/signin?to=hub");
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
	}
	
    public WebDriverWait wait;
    public boolean isElementPresent(WebElement element) {
		boolean status = false;
		try {	
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
			status = driver.findElements((By) element).size() > 0;
			return status;
		} catch (Exception e) {
			return status;
		}
	}
    public boolean isElementDisplayed(WebElement element) {
		boolean status = false;
		try {
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(element));
			status = element.isDisplayed();
			return status;
		} catch (Exception e) {
			return status;
		}
	}   
    
   public boolean isElementEnabled(WebElement element) {
		boolean status = false;
		try {
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			status = element.isEnabled();
			return status;
		} catch (Exception e) {
			return status;
		}
	}

	public void click(WebElement element) throws Exception {
		if (isElementDisplayed(element)) {
			if (isElementEnabled(element)) {
				try {
					Thread.sleep(1000);
					element.click();
					System.out.println(" clicking on   " + element);
				} catch (Exception e) {
					System.out.println("Unable to click ");
					driver.quit();Assert.fail();
				}

			} else {
				System.out.println("Unable to click since element is disabled " + element);
				driver.quit();	Assert.fail();
			}
		} else {
			System.out.println("Unable to click since element is not displayed " + element);
			driver.quit();Assert.fail();
		}
	}
    public void sendkeys(WebElement element, String text) throws Exception {    	
			if (isElementDisplayed(element)) {
				if (isElementEnabled(element)) {
					try {
						element.clear();
						element.sendKeys(text);
						System.out.println(" entering value in   "+element+"  "+text);						
					}catch (Exception e) {						
						System.out.println("Unable to enter text ");	
					}
					}else {
						System.out.println("Unable to enter text since element is disabled "+element+" "+text);	
						driver.quit();Assert.fail();
				}
				} else {
					System.out.println("Unable to enter text since element is not displayed "+element);
					driver.quit();Assert.fail();
			}
	}
	
	
	
	public void screenshot(String name) throws Exception{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("resources/screenshot/"+name+".png"));	
	}
	
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}

}
