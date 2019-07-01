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
        webDriver.manage().window().setSize(new Dimension(1200, 3800));
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
    public void topNavLinks() throws InterruptedException {
        webDriver.get("https://www.ringcentral.com/solutions/small-business.html");


        WebElement Products = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[1]/a"));
        assertEquals("https://www.ringcentral.com/all-products.html", Products.getAttribute("href"));

        Actions builder = new Actions(webDriver);
        builder.moveToElement(Products).build().perform();
        Thread.sleep(5000);

        // Products
        WebElement ProductsTitle = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div[2]/div/div[1]/div/span"));
        assertEquals("Products", ProductsTitle.getText());
        WebElement ProductsSubtitle = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div[2]/div/div[2]/div"));
        assertEquals("Get the leading all-in-one solution or just what you need.", ProductsSubtitle.getText());
        WebElement SeeAllProducts = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div[2]/div/div[3]/div/a"));
        assertEquals("https://www.ringcentral.com/all-products.html", SeeAllProducts.getAttribute("href"));

        // Products - Office
        WebElement OfficeLink = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[2]/div/div/div/a"));
        assertEquals("https://www.ringcentral.com/office/how-it-works.html", OfficeLink.getAttribute("href"));
        WebElement OfficeSubtitle = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[2]/div/div/div/a/span/span[2]"));
        assertEquals("All-in-one phone, meetings and messaging.", OfficeSubtitle.getText());
        WebElement HowItWorks = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[2]/div/div/div/ul/li[1]/a"));
        assertEquals("https://www.ringcentral.com/office/small-business/voip-phone-system-how-it-works.html", HowItWorks.getAttribute("href"));
        WebElement Features = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[2]/div/div/div/ul/li[2]/a"));
        assertEquals("https://www.ringcentral.com/office/phone-system-features.html", Features.getAttribute("href"));
        WebElement PhonesAndHeadsets = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[2]/div/div/div/ul/li[3]/a"));
        assertEquals("https://www.ringcentral.com/office/voip-phone.html", PhonesAndHeadsets.getAttribute("href"));
        WebElement DesktopApp = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[2]/div/div/div/ul/li[4]/a"));
        assertEquals("https://www.ringcentral.com/desktop-app.html", DesktopApp.getAttribute("href"));
        WebElement MobileApp = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[2]/div/div/div/ul/li[5]/a"));
        assertEquals("https://www.ringcentral.com/mobile-app.html", MobileApp.getAttribute("href"));
        WebElement OfficePricing = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[2]/div/div/div/ul/li[6]/a"));
        assertEquals("https://www.ringcentral.com/office/plansandpricing.html", OfficePricing.getAttribute("href"));

        //Products - Meetings
        WebElement Meetings = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[3]/div/div/div[1]/a"));
        assertEquals("https://www.ringcentral.com/online-meetings/overview.html", Meetings.getAttribute("href"));
        WebElement MeetingsSubtitle = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[3]/div/div/div[1]/a/span/span[2]"));
        assertEquals("The complete video meetings solution.", MeetingsSubtitle.getText());

        // Products - CC
        WebElement CC = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[3]/div/div/div[2]/a"));
        assertEquals("https://www.ringcentral.com/contact-center/overview.html", CC.getAttribute("href"));
        WebElement CCSubtitle = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[3]/div/div/div[2]/a/span/span[2]"));
        assertEquals("Omnichannel collaborative contact center.", CCSubtitle.getText());

        // Products - Fax
        WebElement Fax = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[3]/div/div/div[3]/a"));
        assertEquals("https://www.ringcentral.com/fax/features/how-it-works.html", Fax.getAttribute("href"));
        WebElement FaxSubtitle = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[3]/div/div/div[3]/a/span/span[2]"));
        assertEquals("Fax from any device connected to the internet.", FaxSubtitle.getText());

        // Products - Glip
        WebElement Glip = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[4]/div/div/div[1]/a"));
        assertEquals("https://www.ringcentral.com/teams/overview.html", Glip.getAttribute("href"));
        WebElement GlipSubtitle = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[4]/div/div/div[1]/a/span/span[2]"));
        assertEquals("Team messaging & collaboration.", GlipSubtitle.getText());

        //Products - Pro
        WebElement Pro = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[4]/div/div/div[2]/a"));
        assertEquals("https://www.ringcentral.com/pro/features/how-it-works.html", Pro.getAttribute("href"));
        WebElement ProSubtitle = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/div/div[4]/div/div/div[2]/a/span/span[2]"));
        assertEquals("Inbound call management and toll-free numbers.", ProSubtitle.getText());


        // Pricing
        WebElement Pricing = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[3]/div/a"));
        assertEquals("https://www.ringcentral.com/office/plansandpricing.html", Pricing.getAttribute("href"));

        // Resources
        WebElement Resources = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[4]/div[1]/a"));
        assertEquals("https://www.ringcentral.com/resources/learning-center.html", Resources.getAttribute("href"));

        // About US
        WebElement AboutUs = webDriver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div/div[19]/div/div[1]/div[5]/div[1]/a"));
        assertEquals("https://www.ringcentral.com/whyringcentral/company.html", AboutUs.getAttribute("href"));
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
