package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class FormFieldsPage {

    private WebDriver driver;

    public FormFieldsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String name) {
        scrollToElement(By.id("name-input"));
        driver.findElement(By.id("name-input")).sendKeys(name);
    }

    public void enterPassword(String password) {
        scrollToElement(By.xpath("//input[@type='password']"));
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
    }

    public void selectDrinks(String... drinks) {
        for (String drink : drinks) {
            WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox' and @value='" + drink + "']"));
            scrollToElement(checkbox);
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }
    public void selectColor(String colorValue) {
        WebElement radio = driver.findElement(By.xpath("//input[@name='fav_color' and @value='" + colorValue + "']"));
        scrollToElement(radio);
        if (!radio.isSelected()) {
            radio.click();
        }
    }
    public void selectAutomationTool(String option) {
        WebElement automationDropdown = driver.findElement(By.cssSelector("#automation"));
        Select dropdown = new Select(automationDropdown);
        dropdown.selectByVisibleText(option);
    }
    public void enterEmail(String email) {
        scrollToElement(By.id("email"));
        driver.findElement(By.id("email")).sendKeys(email);
    }
    public String getAutomationToolsMessage() {
        // Находим список инструментов
        List<WebElement> toolItems = driver.findElements(By.xpath("//label[text()='Automation tools']/following-sibling::ul/li"));

        int toolsCount = toolItems.size();
        String longestTool = "";
        int maxLength = 0;
        for (WebElement tool : toolItems) {
            String toolName = tool.getText();
            if (toolName.length() > maxLength) {
                maxLength = toolName.length();
                longestTool = toolName;
            }
        }
        return toolsCount + "\n" + longestTool;
    }
    public void enterMessage(String message) {
        scrollToElement(By.id("message"));
        driver.findElement(By.id("message")).sendKeys(message);
    }

    public void clickSubmit() {
        scrollToElement(By.xpath("//button[text()='Submit']"));
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }
    private void scrollToElement(By by) {
        WebElement element = driver.findElement(by);
        scrollToElement(element);
    }
    private void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

}