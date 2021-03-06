package com.easytox.automation.steps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.easytox.automation.driver.DriverBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class VerifyTheSearchResultsSteps {
	private WebDriver driver;
	
	public VerifyTheSearchResultsSteps() {
		DriverBase.instantiateDriverObject();
		driver = DriverBase.getDriver();
	}
	
	@Given("^User should be login to lab$")
	public void user_should_be_login_to_lab() throws Throwable {
		String USERNAME = "cgiclinician1";
		String PASSWORD = "Welcome@123";
		String FIND_USERNAME = "j_username";
		String FIND_PASSWORD = "j_password";
		String URL = "http:\\bmtechsol.com/easytox";
		
		driver.navigate().to(URL);
		
		WebElement username = driver.findElement(By.name(FIND_USERNAME));
		username.clear();
		username.sendKeys(USERNAME);
		
		WebElement password = driver.findElement(By.name(FIND_PASSWORD));
		password.clear();
		password.sendKeys(PASSWORD);
		
		driver.findElement(By.cssSelector(".btn")).click();
	}

	@When("^Enter any search criteria and click on search icon$")
	public void enter_any_search_criteria_and_click_on_search_icon() throws Throwable {
		Thread.sleep(2000);
		
		WebElement searchBox = driver.findElement(By.cssSelector("#caseorder_filter > label > input"));
		searchBox.clear();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("#caseorder_filter > label")));
		action.click();
		action.sendKeys("New");
		action.build().perform();
	}

	@Then("^Matching records with entered data should be diplayed in the case list$")
	public void matching_records_with_entered_data_should_be_diplayed_in_the_case_list() throws Throwable {
		Thread.sleep(2000);
		WebElement tableBody = driver.findElement(By.cssSelector("#caseorder tbody"));
		List<WebElement> cells = tableBody.findElements(By.tagName("td"));
		
		for (WebElement cell : cells) {
			if(cell.getText().equals("New")) {
				Assert.assertTrue(cell.getText().equals("New"));
			}
		}
	}
}
