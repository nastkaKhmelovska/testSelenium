package com.anastasiia.learning.selenium;

import com.google.common.base.Function;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static WebDriver webDriver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\devTools\\chromedriver.exe");
        final ChromeOptions chromeOptions = new ChromeOptions();
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        webDriver = new ChromeDriver(capabilities);
    }

    @Test
    public void sendForm() throws InterruptedException {
        webDriver.get("https://www.ringcentral.com/solutions/small-business.html");
        final String timeMs = String.valueOf(System.currentTimeMillis());

        waitForPageLoading("//*[@id=\"leadform-1539372701-form\"]/div/div[7]/button");

        WebElement formTitle = webDriver.findElement(By.xpath("//*[@id=\"leadform-1539372701\"]/div/div/div/div/div[1]/p[1]"));
        assertEquals("Sign up for a free personal demo", formTitle.getText());

        WebElement formSubTitle = webDriver.findElement(By.xpath("//*[@id=\"leadform-1539372701\"]/div/div/div/div/div[1]/p[2]"));
        assertEquals("A sales advisor will contact you for a personalized, informative demo on the key features and " +
                "benefits of a cloud phone system.", formSubTitle.getText());

        WebElement firstName = webDriver.findElement(By.xpath("//*[@id=\"leadform-1539372701-form\"]/div/div[1]/input"));
        firstName.sendKeys("RingCentral");

        WebElement lasttName = webDriver.findElement(By.xpath("//*[@id=\"leadform-1539372701-form\"]/div/div[2]/input"));
        lasttName.sendKeys("RingCentral-Test");

        WebElement workEmail = webDriver.findElement(By.xpath("//*[@id=\"email-1539372701\"]"));
        workEmail.sendKeys("gwtestn" + timeMs + "@a66.com");

        WebElement phoneNumber = webDriver.findElement(By.xpath("//*[@id=\"leadform-1539372701-form\"]/div/div[4]/input"));
        phoneNumber.sendKeys("6502486333");

        WebElement companyName = webDriver.findElement(By.xpath("//*[@id=\"leadform-1539372701companyid\"]"));
        companyName.sendKeys("RingCentral");
        Thread.sleep(2000);
        WebElement RingCentral = webDriver.findElement(By.xpath("//*[@id=\"demandbase-company-autocomplete-widget\"]/ul/li[1]/a"));
        RingCentral.click();

        WebElement numberOfEmployees = webDriver.findElement(By.xpath("//*[@id=\"leadform-1539372701-form\"]/div/div[6]/div/div[1]"));
        numberOfEmployees.click();

        WebElement justMe = webDriver.findElement(By.xpath("//*[@id=\"leadform-1539372701-form\"]/div/div[6]/div/div[2]/div/div/div[2]"));
        justMe.click();

        waitForPageLoading("//*[@id=\"demandbase-company-autocomplete-widget\"]/ul");

        ((JavascriptExecutor) webDriver).executeScript("window.focus();");
        Thread.sleep(2000);
        Actions action = new Actions(webDriver);
        action.sendKeys(Keys.ESCAPE);

        WebElement submit = webDriver.findElement(By.xpath("//*[@id=\"leadform-1539372701-form\"]/div/div[7]/button"));
        submit.click();

        Thread.sleep(2000);
        WebElement thankYouMessage = webDriver.findElement(By.xpath("//*[@id=\"leadform-1539372701\"]/div/div/div/div/div[2]/p[1]"));
        assertEquals("Thank you for your interest in RingCentral", thankYouMessage.getText());


    }

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @AfterClass
    public static void shutDown() {
        webDriver.quit();

    }


    public WebElement waitForPageLoading(final String xpath) {
        final WebDriverWait wait = new WebDriverWait(webDriver, 60);
        return wait.until((Function<WebDriver, WebElement>) webDriver -> webDriver.findElement(By.xpath(xpath)));
    }
}
