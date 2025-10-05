package com.automation.tests;

import com.automation.framework.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.pages.AssignmentPage;
import com.automation.utils.CommonMethods;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Test class containing automation test cases for the assignment
 * Extends BaseTest for browser setup and teardown functionality
 */
public class AssignmentTest extends BaseTest {
    
    // Initialize page objects for test execution
    private LoginPage loginPage = new LoginPage();
    private AssignmentPage assignmentPage = new AssignmentPage();
    
    /**
     * Test Case 1: Complete end-to-end workflow
     * - Login to application
     * - Navigate to Jobs section and verify Career menu selection
     * - Send message and verify in inbox
     * - Print formatted job roles with users
     */
    @Test(enabled = true)
    @Description("Assignment Test Case 1")
    public void testAssignment1() {
        // Step 1: Navigate to the application URL
        loginPage.navigateToUrl();
        
        // Step 2: Click on Sign In button to open login form
        loginPage.clickSignIn();
        
        // Step 3: Verify that Welcome Back header is displayed on login page
        Assert.assertTrue(loginPage.isWelcomeHeaderDisplayed(), "Welcome header not displayed");
        
        // Step 4: Enter credentials and perform login
        loginPage.performLogin();
        
        // Step 5: Verify navigation menu contains expected items (Home, Community, Career, Inbox)
        Assert.assertTrue(assignmentPage.verifyNavigationMenu(), "Navigation menu items don't match expected values");
        
        // Step 6: Hover over Career menu and select Jobs option
        assignmentPage.selectCareerMenuItem("Jobs");
        
        // Step 7: Verify Career menu is selected/active
        Assert.assertTrue(assignmentPage.isCareerMenuSelected(), "Career menu is not selected");
        
        // Step 8: Get and display all available job roles
        assignmentPage.getAllJobRoles();
        
        // Step 9: Select specific job role from the list
        assignmentPage.selectJobRole("Software Developer");
        
        // Step 10: Navigate to inbox section
        assignmentPage.clickGoToInbox();
        
        // Step 11: Create timestamped message for unique verification
        String currentTime = LocalDateTime.now().toString();
        String sentMessage = "Hello! I'm interested in this Software Developer position. Time: " + currentTime;
        
        // Step 12: Send the message
        assignmentPage.sendMessage(sentMessage);
        
        // Step 13: Verify the sent message appears correctly in inbox
        Assert.assertTrue(assignmentPage.verifyLastMessage(sentMessage), "Last message doesn't match the sent message");
        
        // Step 14: Navigate back to previous page
        CommonMethods.goBack();
        
        // Step 15: Print formatted output of all job roles with their associated users
        assignmentPage.printJobRolesWithUsers();
    }
    
    /**
     * Test Case 2: Career Paths navigation test
     * - Login to application
     * - Navigate to Career Paths section
     */
    @Test(enabled = true)
    @Description("Assignment Test Case 2")
    public void testAssignment2() {
        // Step 1: Navigate to the application URL
        loginPage.navigateToUrl();
        
        // Step 2: Click on Sign In button to open login form
        loginPage.clickSignIn();
        
        // Step 3: Verify that Welcome Back header is displayed on login page
        Assert.assertTrue(loginPage.isWelcomeHeaderDisplayed(), "Welcome header not displayed");
        
        // Step 4: Enter credentials and perform login
        loginPage.performLogin();
        
        // Step 5: Verify navigation menu contains expected items (Home, Community, Career, Inbox)
        Assert.assertTrue(assignmentPage.verifyNavigationMenu(), "Navigation menu items don't match expected values");
        
        // Step 6: Hover over Career menu and select Career Paths option
        assignmentPage.selectCareerMenuItem("Career Paths");
        
        // Step 7: Verify Career menu is selected/active
        Assert.assertTrue(assignmentPage.isCareerMenuSelected(), "Career menu is not selected");
        
        // Step 8: Scroll to Inspiration for you section
        assignmentPage.scrollToInspirationSection();
        
        // Step 9: Click on first 3 occupations and navigate back for each
        List<String> clickedOccupations = assignmentPage.clickFirst3Occupations();
        
        // Step 10: Refresh the page
        CommonMethods.refreshPage();
        
        // Step 11: Scroll to Recommended Careers based on your section
        assignmentPage.scrollToRecommendedCareersSection();
        
        // Step 12: Get first 3 recommended career names
        List<String> recommendedCareers = assignmentPage.getFirst3RecommendedCareers();
        
        // Step 13: Verify that recommended careers are in reverse order of clicked occupations
        List<String> reversedClickedOccupations = CommonMethods.reverseList(clickedOccupations);
        Assert.assertEquals(recommendedCareers, reversedClickedOccupations, "Recommended careers should be in reverse order of clicked occupations");
    }
}