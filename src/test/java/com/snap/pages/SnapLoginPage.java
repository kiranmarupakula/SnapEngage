package com.snap.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.snap.util.GenericFunctions;


public class SnapLoginPage extends GenericFunctions{

	 WebDriver driver;
	   GenericFunctions generic = new GenericFunctions();
		@SuppressWarnings("static-access")
		public SnapLoginPage(WebDriver driver) {
			this.driver = generic.driver;
		}
		
		@FindBy(id="email")WebElement emailField;
		public void typeInEmailField(String email) throws Exception {
			generic.sendkeys(emailField, email);
		}
		
		@FindBy(id="password")WebElement passwordField;
		public void typeInPasswordField(String password) throws Exception {
			generic.sendkeys(passwordField, password);
		}

		@FindBy(name="Submit")WebElement signInButton;
		public void clickLoginButton() throws Exception {
			generic.click(signInButton);
			
		}
		
		@FindBy(xpath="//*[contains(@class,'button_se Buttoncss')]")WebElement okButton;
		public void clickOkButton() throws Exception {
			generic.click(okButton);
			Thread.sleep(5000);

		}

}
