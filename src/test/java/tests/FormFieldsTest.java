package tests;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FormFieldsPage;

public class FormFieldsTest {
    private WebDriver driver;
    private FormFieldsPage formFieldsPage;
    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://practice-automation.com/form-fields/");

        formFieldsPage = new FormFieldsPage(driver);
    }
    @Test
    public void testFormSubmission() {
        formFieldsPage.enterName("Danil");
        formFieldsPage.enterPassword("Password1");
        formFieldsPage.selectDrinks("Milk", "Coffee");
        formFieldsPage.selectColor("Yellow");
        formFieldsPage.selectAutomationTool("Yes");
        formFieldsPage.enterEmail("shushkovdanil@gmail.com");

        String message = formFieldsPage.getAutomationToolsMessage();
        formFieldsPage.enterMessage(message);

        formFieldsPage.clickSubmit();

        String alertText = driver.switchTo().alert().getText();
        Assert.assertEquals(alertText, "Message received!");
        driver.switchTo().alert();
    }
}