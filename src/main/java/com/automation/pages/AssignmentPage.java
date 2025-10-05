package com.automation.pages;

import com.automation.framework.DriverManager;
import com.automation.utils.CommonMethods;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Page Object Model class for Assignment/Career functionality
 * Contains all elements and methods for job roles, users, and messaging
 */
public class AssignmentPage {
    
    // Web element locators for assignment page functionality
    private By navMenuItems = By.xpath("//div[@class='navListEl']");                                                    // Navigation menu items
    private By careerButton = By.xpath("//button[text()='Career']");                                                    // Career menu button
    private By userNames = By.xpath("//div[@class='ant-card job-card-item selected ']//div[@class='user-main']//span"); // User names in carousel
    private By nextButton = By.xpath("//div[@class='ant-card job-card-item selected ']//*[contains(@data-src,'cheveron-right')]"); // Next button for user carousel
    private By jobRoles = By.xpath("//div[@class='ant-card-body']//div[@class='job-role']");                           // Job role elements
    private By messageButton = By.xpath("//button[text()='Message']");                                                 // Message button
    private By messageTextArea = By.xpath("//div[@class='froala-editor']//div[@contenteditable='true']");             // Message text area
    private By sendButton = By.xpath("//button[contains(@class,'msg-btn-send')]");                                     // Send message button
    private By goToInboxButton = By.xpath("//button[text()='Go to Inbox']");                                          // Go to Inbox button
    private By lastMessage = By.xpath("(//div[@class='collapse-overflow-content'])[last()]//p");                      // Last message in inbox
    private By inspirationSection = By.xpath("//p[contains(text(),'Inspiration for')]");                              // Inspiration for you section
    private By occupationLinks = By.xpath("//div[@aria-label='Inspiration for ']//div[contains(@class,'careerCardV2Wrapper')]//a"); // Occupation links in inspiration section
    private By recommendedCareersSection = By.xpath("//p[contains(text(),'Recommended Careers based on your')]");           // Recommended Careers section
    private By recommendedCareerLinks = By.xpath("//p[contains(text(),'Recommended Careers based on your')]/../../../../../..//div[@class='career-path-block career-container-row']//a");        // Career links in recommended section
    
    /**
     * Verify navigation menu contains expected items
     * @return true if menu items match expected list, false otherwise
     */
    public boolean verifyNavigationMenu() {
        List<String> expectedMenuItems = Arrays.asList("Home", "Community", "Career", "Inbox");
        List<String> actualMenuItems = CommonMethods.getTextFromElements(navMenuItems);
        return actualMenuItems.equals(expectedMenuItems);
    }
    
    /**
     * Select item from Career dropdown menu
     * @param menuItem Menu item to select (Jobs, Projects, Career Paths)
     */
    public void selectCareerMenuItem(String menuItem) {
        CommonMethods.hoverOnElement(careerButton);                           // Hover over Career button
        By menuLocator = By.xpath("//p[text()='" + menuItem + "']");         // Create dynamic locator for menu item
        CommonMethods.waitAndClick(menuLocator);                             // Click on selected menu item
    }
    
    public int getTotalUserCount() {
        return DriverManager.getDriver().findElements(userNames).size();
    }
    
    public void selectUser(String userName) {
        int totalUsers = getTotalUserCount();
        int attempts = 0;
        
        while (attempts < totalUsers) {
            String currentUser = CommonMethods.getText(userNames);
            
            if (currentUser.equals(userName)) {
                CommonMethods.waitAndClick(userNames);
                return;
            }
            
            if (CommonMethods.isElementPresent(nextButton)) {
                CommonMethods.waitAndClick(nextButton);
                attempts++;
            } else {
                break;
            }
        }
        
        throw new RuntimeException("User '" + userName + "' not found after checking " + attempts + " out of " + totalUsers + " users");
    }
    
    public List<String> getAllUserNames() {
        List<String> allUserNames = new ArrayList<>();
        int totalUsers = getTotalUserCount();
        
        // Get all users by index
        for (int i = 1; i <= totalUsers; i++) {
            By userLocator = By.xpath("(//div[@class='ant-card job-card-item selected ']//div[@class='user-main']//span)[" + i + "]");
            String currentUser = CommonMethods.getText(userLocator);
            allUserNames.add(currentUser);
            
            // Click next button for all except last user
            if (i < totalUsers && CommonMethods.isElementPresent(nextButton)) {
                CommonMethods.waitAndClick(nextButton);
            }
        }
        
        System.out.println("Total Users Found: " + allUserNames.size());
        System.out.println("User Names: " + allUserNames);
        return allUserNames;
    }
    
    /**
     * Get all available job roles from the page
     * @return List of job role names
     */
    public List<String> getAllJobRoles() {
        List<String> allJobRoles = CommonMethods.getTextFromElements(jobRoles);
        return allJobRoles;
    }
    
    /**
     * Select specific job role from available list
     * @param jobRoleName Name of job role to select
     * @throws RuntimeException if job role not found
     */
    public void selectJobRole(String jobRoleName) {
        List<String> allJobRoles = CommonMethods.getTextFromElements(jobRoles);
        
        // Search for matching job role and click
        for (int i = 0; i < allJobRoles.size(); i++) {
            if (allJobRoles.get(i).equals(jobRoleName)) {
                By jobRoleLocator = By.xpath("(//div[@class='ant-card-body']//div[@class='job-role'])[" + (i + 1) + "]");
                CommonMethods.waitAndClick(jobRoleLocator);
                return;
            }
        }
        
        // Throw exception if job role not found
        throw new RuntimeException("Job role '" + jobRoleName + "' not found in the available job roles: " + allJobRoles);
    }
    
    public void clickMessageButton() {
        CommonMethods.waitAndClick(messageButton);
    }
    
    /**
     * Send message in chat interface
     * @param messageText Text message to send
     */
    public void sendMessage(String messageText) {
        CommonMethods.sendKeys(messageTextArea, messageText);  // Enter message text
        CommonMethods.waitAndClick(sendButton);                // Click send button
    }
    
    public void clickGoToInbox() {
        CommonMethods.waitAndClick(goToInboxButton);
    }
    
    /**
     * Verify last message in inbox matches expected text
     * @param expectedMessage Expected message text
     * @return true if messages match, false otherwise
     */
    public boolean verifyLastMessage(String expectedMessage) {
        String actualMessage = CommonMethods.getText(lastMessage);
        return actualMessage.equals(expectedMessage);
    }
    
    /**
     * Check if Career menu is currently selected/active
     * @return true if Career menu is selected, false otherwise
     */
    public boolean isCareerMenuSelected() {
        By selectedCareerButton = By.xpath("//button[text()='Career' and @aria-current='page']");
        return CommonMethods.isElementPresent(selectedCareerButton);
    }
    
    /**
     * Scroll to Inspiration for you section
     */
    public void scrollToInspirationSection() {
        CommonMethods.isDisplayed(inspirationSection);  // Wait for element to be visible
        CommonMethods.scrollToElement(inspirationSection);
    }
    
    /**
     * Click on first 3 occupations from inspiration section
     * Clicks each occupation, navigates back, and stores occupation names in order
     * @return List of clicked occupation names in order
     */
    public List<String> clickFirst3Occupations() {
        List<String> clickedOccupations = new ArrayList<>();
        List<String> allOccupations = CommonMethods.getTextFromElements(occupationLinks);
        
        // Click first 3 occupations
        for (int i = 0; i < 3 && i < allOccupations.size(); i++) {
            String occupationName = allOccupations.get(i);
            clickedOccupations.add(occupationName);
            
            // Create dynamic locator for specific occupation
            By occupationLocator = By.xpath("//div[@aria-label='Inspiration for ']//div[contains(@class,'careerCardV2Wrapper')]//a[text()='" + occupationName + "']");
            
            // Click on occupation
            CommonMethods.waitAndClick(occupationLocator);
            
            // Wait for loader animation to disappear
            CommonMethods.waitForLoaderToDisappear();
            
            // Additional small wait for page stability as the page is still not loading even after loader is disappeared
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Navigate back
            CommonMethods.goBack();
        }
        
        System.out.println("Clicked Occupations in order: " + clickedOccupations);
        return clickedOccupations;
    }
    
    /**
     * Scroll to Recommended Careers based on your section
     */
    public void scrollToRecommendedCareersSection() {
        CommonMethods.isDisplayed(recommendedCareersSection);  // Wait for element to be visible
        CommonMethods.scrollToElement(recommendedCareersSection);
    }
    
    /**
     * Get first 3 recommended career names from the carousel
     * @return List of first 3 career names
     */
    public List<String> getFirst3RecommendedCareers() {
        try {
            List<String> allCareers = CommonMethods.getTextFromElementsIfPresent(recommendedCareerLinks);
            List<String> first3Careers = new ArrayList<>();
            
            // Get first 3 careers
            for (int i = 0; i < 3 && i < allCareers.size(); i++) {
                first3Careers.add(allCareers.get(i));
            }
            
            System.out.println("First 3 Recommended Careers: " + first3Careers);
            return first3Careers;
        } catch (Exception e) {
            System.out.println("Could not find recommended career elements. Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public void printJobRolesWithUsers() {
        List<String> allJobRoles = CommonMethods.getTextFromElements(jobRoles);
        System.out.println("\n=== Job Roles with Users ===");
        
        for (String jobRole : allJobRoles) {
            selectJobRole(jobRole);
            List<String> users = getAllUserNamesQuiet();
            
            System.out.print(jobRole + " -- ");
            for (int i = 0; i < users.size(); i++) {
                System.out.print(users.get(i));
                if (i < users.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
    
    private List<String> getAllUserNamesQuiet() {
        List<String> allUserNames = new ArrayList<>();
        int totalUsers = getTotalUserCount();
        
        for (int i = 1; i <= totalUsers; i++) {
            By userLocator = By.xpath("(//div[@class='ant-card job-card-item selected ']//div[@class='user-main']//span)[" + i + "]");
            String currentUser = CommonMethods.getText(userLocator);
            allUserNames.add(currentUser);
            
            if (i < totalUsers && CommonMethods.isElementPresent(nextButton)) {
                CommonMethods.waitAndClick(nextButton);
            }
        }
        
        return allUserNames;
    }
}