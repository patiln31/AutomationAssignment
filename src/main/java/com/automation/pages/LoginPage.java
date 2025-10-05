package com.automation.pages;

import com.automation.framework.DriverManager;
import com.automation.utils.PropertyReader;
import com.automation.utils.CommonMethods;
import org.openqa.selenium.By;
import java.util.Arrays;
import java.util.List;

/**
 * Page Object Model class for Login functionality
 * Contains all elements and methods related to login process
 */
public class LoginPage {
    
    // Web element locators for login page
    private By signInButton = By.xpath("//button[text()='Sign In']");        // Sign In button on homepage
    private By welcomeHeader = By.xpath("//h1[text()='Welcome Back!']");     // Welcome header on login form
    private By emailField = By.id("email");                                 // Email input field
    private By passwordField = By.id("password");                           // Password input field
    private By loginButton = By.xpath("//span[text()='Login']");            // Login submit button

    
    /**
     * Navigate to application URL from properties file
     * Waits for page to load completely
     */
    public void navigateToUrl() {
        DriverManager.getDriver().get(PropertyReader.getProperty("main.url"));
        CommonMethods.waitForLoad();
    }
    
    /**
     * Click on Sign In button to open login form
     * Waits for page to load after click
     */
    public void clickSignIn() {
        CommonMethods.waitAndClick(signInButton);
        CommonMethods.waitForLoad();
    }
    
    /**
     * Verify if Welcome Back header is displayed on login page
     * @return true if header is visible, false otherwise
     */
    public boolean isWelcomeHeaderDisplayed() {
        return CommonMethods.isDisplayed(welcomeHeader);
    }
    
    /**
     * Enter email address in email field
     * @param email Email address to enter
     */
    public void enterEmail(String email) {
        CommonMethods.sendKeys(emailField, email);
    }
    
    /**
     * Enter password in password field
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        CommonMethods.sendKeys(passwordField, password);
    }
    
    /**
     * Click login button to submit credentials
     * Uses JavaScript click and waits for page load
     */
    public void clickLogin() {
        CommonMethods.waitAndJsClick(loginButton);
        CommonMethods.waitForLoad();
    }
    
    /**
     * Complete login process using credentials from properties file
     * Enters email, password and clicks login button
     */
    public void performLogin() {
        enterEmail(PropertyReader.getProperty("email"));    // Enter email from config
        enterPassword(PropertyReader.getProperty("password")); // Enter password from config
        clickLogin();                                        // Submit login form
    }
    

}