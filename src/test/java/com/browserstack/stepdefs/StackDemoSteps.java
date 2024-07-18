package com.browserstack.stepdefs;

import com.browserstack.AppPercySDK;
import com.browserstack.pageobjects.HomePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StackDemoSteps {
    private IOSDriver driver;
    private HomePage homePage;

    @Before
    public void setUp() throws MalformedURLException {
//        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, String> bstackOptions = new HashMap<>();
//        bstackOptions.putIfAbsent("source", "cucumber-java:sample-master:v1.2");
//        capabilities.setCapability("bstack:options", bstackOptions);
//        driver = new RemoteWebDriver(
//                new URL("https://hub.browserstack.com/wd/hub"), capabilities);
//        homePage = new HomePage(driver);

        DesiredCapabilities capabilities = new DesiredCapabilities();

        //running with legacy on one device
        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        // capabilities.setCapability("user",username);
        // capabilities.setCapability("key",accessKey);
        // capabilities.setCapability("platformName","ANDROID");
//         capabilities.setCapability("automationName","UIAutomator2");
        // capabilities.setCapability("automationName","Flutter");
        // capabilities.setCapability("app","bs://c8dfaeddbba525c5c81786c0744c34b2522b756a");// for Sample APK
        // capabilities.setCapability("deviceName","Samsung Galaxy S22 Ultra");
        // capabilities.setCapability("platformVersion","12.0");
        // capabilities.setCapability("buildName","Browserstack Legacy");

        //for local
        // l = new Local();
        // Map<String, String> options = new HashMap<String, String>();
        // options.put("key", accessKey);
        // l.start(options);
        // capabilities.setCapability("app","bs://1ef868da294fa39e94311376a07bc1b58f54eb82");// for Local APK


        // driver = new AndroidDriver(new URL("https://"+username+":"+accessKey+"@hub.browserstack.com/wd/hub"), capabilities);
        //else
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

    }

    @Given("^I am on the website '(.+)'$")
    public void I_am_on_the_website(String url) throws Throwable {
        WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Search Wikipedia")));

        searchElement.click();

    }

    @When("^I select a product and click on 'Add to cart' button")
    public void I_select_a_product_and_add_to_cart() throws Throwable {
        WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.sendKeys("BrowserStack");
        Thread.sleep(5000);

        List<WebElement> allProductsName = driver.findElements(AppiumBy.className("android.widget.TextView"));
        //   jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"marking test case failed\"}}");
//        AppPercySDK.screenshot(driver, "abcd");
        Assert.assertTrue(allProductsName.size() >0);
    }

    @Then("the product should be added to cart")
    public void product_should_be_added_to_cart() {
//        homePage.waitForCartToOpen();
//        Assert.assertEquals(homePage.getSelectedProductName(), homePage.getProductCartText());
    }

    @Then("the page title should contain '(.+)'$")
    public void page_title_should_contain(String expectedTitle) {
//        Assert.assertTrue(driver.getTitle().contains(expectedTitle));
    }

    @After
    public void teardown(Scenario scenario) throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
