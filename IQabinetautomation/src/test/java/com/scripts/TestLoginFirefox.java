package com.scripts;

import static org.junit.Assert.*;
import com.thoughtworks.selenium.Selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.CSS;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.internal.seleniumemulation.IsElementPresent;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import org.junit.Test;

public class TestLoginFirefox {
	  private Selenium selenium;
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		 driver = new FirefoxDriver();
		 String baseUrl = "http://iqabinet-web.herokuapp.com/";
		 selenium = new WebDriverBackedSelenium(driver, baseUrl);
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 //selenium.wait(2000);
		 selenium.setSpeed("200000");
	}

	@Test
	public void testvalidloginfirefox() throws Exception {
		selenium.waitForPageToLoad("50000");
		selenium.open("/index.html#/index.html");
		assertTrue("Check the sign in button should be displayed", selenium.isElementPresent("link=Sign-in"));
		
		selenium.click("link=Sign-in");
		selenium.waitForPageToLoad("30000");
		assertTrue("Check the login field should be displayed", selenium.isElementPresent("id=email"));
		
		driver.findElement(By.id("email")).sendKeys("heer.qa@gmail.com");
		assertTrue("Check the password button should be displayed", selenium.isElementPresent("id=password"));
		
		driver.findElement(By.id("password")).sendKeys("Aq12345678");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Login");
		selenium.waitForPageToLoad("30000");
		String getbutonname= driver.findElement(By.linkText("Personal Documents")).getText();
		assertEquals("Personal Documents", getbutonname);
	}
	@Test
	public void testinvalidloginfirefox() throws Exception{
		
		selenium.open("/index.html#/index.html");
		assertTrue("Check the sign in button should be displayed", selenium.isElementPresent("link=Sign-in"));
		
		selenium.click("link=Sign-in");
		selenium.waitForPageToLoad("30000");
		assertTrue("Check the login field should be displayed", selenium.isElementPresent("id=email"));
		
		driver.findElement(By.id("email")).sendKeys("asdadsd");
		assertTrue("Check the password button should be displayed", selenium.isElementPresent("id=password"));
		
		driver.findElement(By.id("password")).sendKeys("asdadsd");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Login");
		selenium.waitForPageToLoad("30000");
		assertTrue("Verify that error message is displayed for invalid username and password ", selenium.isElementPresent("id=error"));
		assertEquals("Invalid Username or Password", selenium.getText("id=error"));
	}



	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
