package com.automation.utils;

import com.automation.framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class containing common methods for web automation
 * Provides reusable methods for element interactions, waits, and browser actions
 */
public class CommonMethods {
    
    // Get WebDriverWait instance dynamically
    private static WebDriverWait getWait() {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
    }
    
    // Get FluentWait instance dynamically
    private static FluentWait<org.openqa.selenium.WebDriver> getFluentWait() {
        return new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);
    }
    
    public static void click(By locator) {
        DriverManager.getDriver().findElement(locator).click();
    }
    
    // Wait for element to be clickable and then click
    public static void waitAndClick(By locator) {
        getWait().until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    
    public static void fluentWaitAndClick(By locator) {
        WebElement element = getFluentWait().until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
    
    public static void waitForLoad() {
        getWait().until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
    
    public static void jsClick(By locator) {
        WebElement element = DriverManager.getDriver().findElement(locator);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", element);
    }
    
    public static void waitAndJsClick(By locator) {
        WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", element);
    }
    
    public static void scrollToElement(By locator) {
        WebElement element = DriverManager.getDriver().findElement(locator);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    // Wait for element to be visible and send text
    public static void sendKeys(By locator, String text) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }
    
    public static boolean isDisplayed(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }
    
    public static List<String> getTextFromElements(By locator) {
        List<WebElement> elements = getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        List<String> textList = new ArrayList<>();
        for (WebElement element : elements) {
            textList.add(element.getText());
        }
        return textList;
    }
    
    // Hover over element using Actions class
    public static void hoverOnElement(By locator) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(element).perform();
    }
    
    public static String getText(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }
    
    public static boolean isElementPresent(By locator) {
        try {
            DriverManager.getDriver().findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static void goBack() {
        DriverManager.getDriver().navigate().back();
    }
    
    public static void refreshPage() {
        DriverManager.getDriver().navigate().refresh();
    }
    
    public static List<String> getTextFromElementsIfPresent(By locator) {
        List<WebElement> elements = getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        List<String> textList = new ArrayList<>();
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                textList.add(element.getText());
            }
        }
        return textList;
    }
    
    public static List<String> reverseList(List<String> originalList) {
        List<String> reversedList = new ArrayList<>();
        for (int i = originalList.size() - 1; i >= 0; i--) {
            reversedList.add(originalList.get(i));
        }
        return reversedList;
    }
    
    public static void waitForLoaderToDisappear() {
        By loaderLocator = By.xpath("//*[@class='loader-animation']");
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
    }
}