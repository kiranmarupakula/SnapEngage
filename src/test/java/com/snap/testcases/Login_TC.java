package com.snap.testcases;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.snap.pages.SnapLoginPage;
import com.snap.util.GenericFunctions;
public class Login_TC extends GenericFunctions{
	
	@Test
	public void verifylogin() throws Exception {
		
		  Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/test/java/com/snap/testdata/testData.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// object created for SnapLoginPage
		SnapLoginPage login = PageFactory.initElements(driver, SnapLoginPage.class);
		
		//Below step takes the screenshot of Login page
		screenshot("LoginPage");
		login.typeInEmailField(prop.getProperty("UserName"));
		login.typeInPasswordField(prop.getProperty("Password"));
		login.clickLoginButton();
		
		login.clickOkButton();
		//Thread.sleep(3000);
		
		// Below step takes the screenshot of Home page.
		screenshot("HomePage");
		
		//verifies the Home page after login
		assertEquals(driver.getTitle().equals(prop.getProperty("title")), true);
		
	}
}
