package com.automation.framework;

import io.qameta.allure.Allure;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

/**
 * Base test class providing setup and teardown functionality
 * All test classes should extend this class for browser management
 */
public class BaseTest {
    
    /**
     * Setup method executed before each test method
     * Initializes browser driver and maximizes window
     * @param browser Browser name (defaults to chrome if not provided)
     */
    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        DriverManager.setDriver(browser);
        DriverManager.getDriver().manage().window().maximize();
        Allure.addAttachment("Browser", browser);
    }
    
    /**
     * Teardown method executed after each test method
     * Closes browser and cleans up driver instance
     */
    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}