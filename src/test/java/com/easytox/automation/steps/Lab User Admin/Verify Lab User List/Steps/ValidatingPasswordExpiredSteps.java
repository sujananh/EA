package com.easytox.automation.steps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.easytox.automation.driver.DriverBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ValidatingPasswordExpiredSteps {
	private WebDriver driver;
	
	public ValidatingPasswordExpiredSteps() {
		DriverBase.instantiateDriverObject();
		driver = DriverBase.getDriver();
	}
	
	@Given("^User should be login to lab$")
	public void user_should_be_login_to_lab() throws Throwable {
		login("superadmin","admin");
		driver.manage().window().maximize();
	}

	@When("^Click on password not expired icon$")
	public void click_on_password_not_expired_icon() throws Throwable {
		Thread.sleep(2000);
		List<WebElement> rows = driver.findElements(By.cssSelector("#lablist > tbody > tr"));
		
		for (WebElement row : rows) {
			if(row.findElement(By.cssSelector("td:nth-child(2)")).getText().equals("RLab")) {
				row.findElement(By.cssSelector(".fa-users")).click();
				break;
			}
		}
		
		Thread.sleep(2000);
		
		rows = driver.findElements(By.cssSelector("#example > tbody > tr"));
		
		for (WebElement row : rows) {
			if(row.findElement(By.cssSelector("td:nth-child(2)")).getText().equals("zanfina123")) {
				row.findElement(By.cssSelector("td:nth-child(12) .fa-ban")).click();
				break;
			}
		}
	}

	@Then("^'User password expired now' message should be populated$")
	public void user_password_expired_now_message_should_be_populated() throws Throwable {
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.cssSelector(".alert-success")).getText(), "User zanfina123 password expired now");
	}

	@When("^Logout and attempt logging in with the above lab user credentials$")
	public void logout_and_attempt_logging_in_with_the_above_lab_user_credentials() throws Throwable {
		Thread.sleep(2000);
		logout();
		
		Thread.sleep(2000);
		login("zanfina123", "Welcome@123");
		
	}

	@Then("^Password Expired message should be populated and user should not be able to login to application$")
	public void password_Expired_message_should_be_populated_and_user_should_not_be_able_to_login_to_application() throws Throwable {
		Thread.sleep(2000);
		Assert.assertEquals(driver.getCurrentUrl(), "http://bmtechsol.com:8080/easytox/login/changePassword");
	}

	@When("^Click on password expired icon$")
	public void click_on_password_expired_icon() throws Throwable {
		login("superadmin", "admin");
		
		Thread.sleep(2000);
		List<WebElement> rows = driver.findElements(By.cssSelector("#lablist > tbody > tr"));
		
		for (WebElement row : rows) {
			if(row.findElement(By.cssSelector("td:nth-child(2)")).getText().equals("RLab")) {
				row.findElement(By.cssSelector(".fa-users")).click();
				break;
			}
		}
		
		Thread.sleep(2000);
		
		rows = driver.findElements(By.cssSelector("#example > tbody > tr"));
		
		for (WebElement row : rows) {
			if(row.findElement(By.cssSelector("td:nth-child(2)")).getText().equals("zanfina123")) {
				row.findElement(By.cssSelector("td:nth-child(12) .fa-check")).click();
				break;
			}
		}
	}

	@Then("^'User password activated now' message should be populated$")
	public void user_password_activated_now_message_should_be_populated() throws Throwable {
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.cssSelector(".alert-success")).getText(), "User zanfina123 password activated now");
	}

	@Then("^User login should be successful$")
	public void user_login_should_be_successful() throws Throwable {
		Assert.assertEquals(driver.getCurrentUrl(), "http://bmtechsol.com:8080/easytox/lab/payment");
	}
	
	private void login(String user, String pass) {
		String FIND_USERNAME = "j_username";
		String FIND_PASSWORD = "j_password";
		String URL = "http://bmtechsol.com:8080/easytox";
		
		driver.navigate().to(URL);
		
		WebElement username = driver.findElement(By.name(FIND_USERNAME));
		username.clear();
		username.sendKeys(user);
		
		WebElement password = driver.findElement(By.name(FIND_PASSWORD));
		password.clear();
		password.sendKeys(pass);
		
		driver.findElement(By.cssSelector(".btn")).click();
	}
	
	private void logout() {
		driver.findElement(By.cssSelector(".account-area > li:nth-child(2)")).click();
		driver.findElement(By.cssSelector(".dropdown-menu > li > a")).click();
	}
}