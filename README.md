# Data-Driven Automation Framework

## Prerequisites
- Java 21
- Maven 3.6+
- Chrome/Firefox browser
- Git (for cloning repository)

## Setup Instructions

### 1. Clone Repository
```bash
git clone <repository-url>
cd AutomationAssignment
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Configure Test Data
- Update `src/main/resources/config.properties` with your credentials:
  ```properties
  url=https://your-application-url.com
  email=your-email@example.com
  password=your-password
  ```

## Running Tests

### Via Maven Command Line:
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=AssignmentTest

# Run specific test method
mvn test -Dtest=AssignmentTest#testAssignment1
mvn test -Dtest=AssignmentTest#testAssignment2

# Run with specific browser
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
```

### Via TestNG XML:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Via IDE:
1. Right-click on `testng.xml` → Run As → TestNG Suite
2. Right-click on `AssignmentTest.java` → Run As → TestNG Test

## Test Cases

### Test Case 1 (testAssignment1):
- Login to application
- Navigate to Jobs section
- Select Software Developer role
- Get all user names
- Send message and verify in inbox
- Print job roles with users

### Test Case 2 (testAssignment2):
- Login to application
- Navigate to Career Paths section
- Click first 3 occupations from inspiration section
- Get first 3 recommended careers
- Verify recommended careers are in reverse order of clicked occupations

## Generate Reports

### Allure Reports:
```bash
# Generate report
mvn allure:report

# Serve report (opens in browser)
mvn allure:serve
```

### TestNG Reports:
- HTML reports available in `target/surefire-reports/`
- Open `index.html` in browser

## Framework Structure
- `src/main/java/com/automation/framework/` - Core framework classes (BaseTest, DriverManager)
- `src/main/java/com/automation/pages/` - Page Object Model classes
- `src/main/java/com/automation/tests/` - Test classes
- `src/main/java/com/automation/utils/` - Utility classes (CommonMethods, PropertyReader)
- `src/main/resources/` - Configuration files
- `testng.xml` - TestNG suite configuration

## Troubleshooting

### Common Issues:
1. **ChromeDriver version mismatch**: Update Chrome browser or use compatible ChromeDriver
2. **Test failures**: Check application URL and credentials in config.properties
3. **Element not found**: Verify application is loaded and elements are visible
4. **Session timeout**: Increase wait times in CommonMethods if needed

### Debug Mode:
```bash
# Run with debug logs
mvn test -X

# Run with specific log level
mvn test -Dorg.slf4j.simpleLogger.defaultLogLevel=debug
```