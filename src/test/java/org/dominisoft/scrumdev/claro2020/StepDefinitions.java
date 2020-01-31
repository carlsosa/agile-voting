package org.dominisoft.scrumdev.claro2020;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;

import org.dominisoft.scrumdev.claro2020.App;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.Keys;

public final class StepDefinitions {
	private static final String WEBDRIVER_CHROME_DRIVER_PROP_NAME = "webdriver.chrome.driver";
	private static final String WEBDRIVER_GECKO_DRIVER_PROP_NAME = "webdriver.gecko.driver";
	private static final String BASE_URL;

	static {
		BASE_URL = String.format("http://127.0.0.1:%d/index.html", App.DEFAULT_PORT);

		App.main(new String[] {});

		EnsureWebDriversPaths();
	}

	private static void EnsureWebDriversPaths() {
		final EnvironmentInfo envInfo = getEnvironmentInfo();

		setGeckoDriverProperties(envInfo);

		setChromeDriverProperties(envInfo);
	}

	private static EnvironmentInfo getEnvironmentInfo() {
		final String osName = System.getProperty("os.name");
		final String userHome;
		final boolean isWindows;
		if (osName.startsWith("Windows")) {
			userHome = System.getenv("UserProfile");
			isWindows = true;
		} else {
			userHome = System.getenv("~");
			isWindows = false;
		}

		final EnvironmentInfo envInfo = new EnvironmentInfo(userHome, isWindows);
		return envInfo;
	}

	private static void setGeckoDriverProperties(final EnvironmentInfo envInfo) {
		final String actualGeckoDriverPath = System.getProperty(WEBDRIVER_GECKO_DRIVER_PROP_NAME);
		if (actualGeckoDriverPath != null && actualGeckoDriverPath.length() > 0) {
			return;
		}

		final String geckoDriverFileName = "geckodriver" + (envInfo.isWindowsOs ? ".exe" : "");
		final String geckoDriverPath = Path.of(envInfo.homeDirectory, geckoDriverFileName).toString();
		System.setProperty(WEBDRIVER_GECKO_DRIVER_PROP_NAME, geckoDriverPath);
	}

	private static void setChromeDriverProperties(final EnvironmentInfo envInfo) {
		final String actualChromeDriverPath = System.getProperty(WEBDRIVER_CHROME_DRIVER_PROP_NAME);
		if (actualChromeDriverPath != null && actualChromeDriverPath.length() > 0) {
			return;
		}

		final String chromeDriverFileName = "chromedriver" + (envInfo.isWindowsOs ? ".exe" : "");
		final String chromeDriverPath = Path.of(envInfo.homeDirectory, chromeDriverFileName).toString();
		System.setProperty(WEBDRIVER_CHROME_DRIVER_PROP_NAME, chromeDriverPath);
	}

	private static WebDriver driver;

	@Before
	public void beforeEach() {
		final FirefoxOptions options = new FirefoxOptions().setHeadless(false);
		driver = new FirefoxDriver(options);

//		ChromeOptions options = new ChromeOptions().setHeadless(true);
//		driver = new ChromeDriver(options); dummy change to test scm
	}

	@After
	public void afterEach() {
		driver.close();
	}

	private String actualTitle;

	@Given("I navigate to index.html")
	public void i_navigate_to_index_html() {
		driver.get(BASE_URL);
		actualTitle = driver.getTitle();
	}

	@Then("The title should be {string}")
	public void the_title_should_be(final String expectedTitle) {
		assertEquals(expectedTitle, actualTitle);
	}

	@When("I click on {string}")
	public void iClickOnElement(String el) {
		WebElement element = driver.findElement(By.id(el));
		element.click();
	}

	@When("I type {string} on {string}")
	public void iTypeValueOnElement(String value, String el) throws InterruptedException {
		WebElement element = driver.findElement(By.id(el));
		element.sendKeys(value);
		element.sendKeys(Keys.TAB);
		Thread.sleep(1000);
	}

	@Then("The input {string} should not be {string}")
	public void theInputXShouldNotBeY(String input, String value) {
		WebElement element = driver.findElement(By.id(input));
		Assert.assertEquals(value, element.getAttribute("value"));
	}

	@Then("The label {string} should have text {string}")
	public void theLabelXShouldHaveTextY(String label, String text) {
		WebElement element = driver.findElement(By.id(label));
		Assert.assertEquals(text, element.getAttribute("innerText"));
	}

	@Given("I navigate to index.html and I type username equals to admin and password equals admin")
	public void i_navigate_to_index_html_and_I_type_username_equals_to_admin_and_password_equals_admin() {
		driver.get(BASE_URL);
		driver.findElement(By.id("user-input-username")).sendKeys("admin");
		driver.findElement(By.id("user-input-password")).sendKeys("admin");
	}

	@When("The User clicks login button")
	public void the_User_clicks_login_button() {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id("login-button")).click();
	}

	@Then("Should display successful message")
	public void should_display_successful_message() {
		driver.findElement(By.id("user_label_login_result")).sendKeys("Success.!");
	}

	@Given("I navigate to index.html and I type wrong admin username or password")
	public void i_navigate_to_index_html_and_I_type_wrong_admin_username_or_password() {
		// Write code here that turns the phrase above into concrete actions
		driver.get(BASE_URL);
		driver.findElement(By.id("user-input-username")).sendKeys("admin");
		driver.findElement(By.id("user-input-password")).sendKeys("55565");
	}

	@Then("Should display Invalid Loggin message")
	public void should_display_Invalid_Loggin_message() {
		String value = driver.findElement(By.id("error-msg")).getAttribute("innerText");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals("Login invalido", value);
	}
}
