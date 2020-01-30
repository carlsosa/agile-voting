package org.dominisoft.scrumdev.claro2020;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public final class StepDefinitions {
    private static final String WEBDRIVER_CHROME_DRIVER_PROP_NAME = "webdriver.chrome.driver";
    private static final String WEBDRIVER_GECKO_DRIVER_PROP_NAME = "webdriver.gecko.driver";
    private static final String BASE_URL;

    static {
        BASE_URL = String.format("http://127.0.0.1:%d/index.html",
                App.DEFAULT_PORT);

        App.main(new String[]{});

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
        final String actualGeckoDriverPath = System
                .getProperty(WEBDRIVER_GECKO_DRIVER_PROP_NAME);
        if (actualGeckoDriverPath != null && actualGeckoDriverPath.length() > 0) {
            return;
        }

        final String geckoDriverFileName = "geckodriver"
                + (envInfo.isWindowsOs ? ".exe" : "");
        final String geckoDriverPath = Path
                .of(envInfo.homeDirectory, geckoDriverFileName).toString();
        System.setProperty(WEBDRIVER_GECKO_DRIVER_PROP_NAME, geckoDriverPath);
    }

    private static void setChromeDriverProperties(final EnvironmentInfo envInfo) {
        final String actualChromeDriverPath = System
                .getProperty(WEBDRIVER_CHROME_DRIVER_PROP_NAME);
        if (actualChromeDriverPath != null && actualChromeDriverPath.length() > 0) {
            return;
        }

        final String chromeDriverFileName = "chromedriver"
                + (envInfo.isWindowsOs ? ".exe" : "");
        final String chromeDriverPath = Path
                .of(envInfo.homeDirectory, chromeDriverFileName).toString();
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

    @Then("The input should not be empty")
    public void theInputShouldNotBeEmpty() {
        WebElement element = driver.findElement(By.id("id-input"));
        Assert.assertEquals("", element.getText());
    }
}
