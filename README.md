# Selenium WebDriver Automation Framework

## Tech Stack
- **Java**: 21
- **Maven**: 3.6+
- **Selenium WebDriver**: 4.15.0
- **TestNG**: 7.8.0
- **Allure Reports**: 2.29.0
- **Design Pattern**: Page Object Model (POM)

## Prerequisites
- Java 21 JDK installed
- Maven 3.6+ installed
- Chrome/Firefox browser installed
- Git (for cloning repository)
- IDE (IntelliJ IDEA/Eclipse) - Optional

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
Update `src/main/resources/config.properties`:
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

## Test Cases Overview

### Test Case 1 (testAssignment1):
- User login and authentication
- Navigation menu verification
- Jobs section navigation
- Software Developer role selection
- User carousel interaction
- Message sending and inbox verification
- Job roles with users output

### Test Case 2 (testAssignment2):
- User login and authentication
- Career Paths section navigation
- Inspiration section occupation clicking (first 3)
- Page refresh and navigation
- Recommended careers extraction
- Reverse order validation assertion

## Generate Reports

### Allure Reports:
```bash
# Clean previous reports
mvn clean

# Generate fresh report
mvn allure:report

# Serve report (opens in browser)
mvn allure:serve

# One command for test + report
mvn clean test allure:serve
```

### TestNG Reports:
- HTML reports: `target/surefire-reports/index.html`
- XML reports: `target/surefire-reports/testng-results.xml`

## Framework Architecture

### Directory Structure:
```
src/main/java/com/automation/
├── framework/
│   ├── BaseTest.java          # Base test setup/teardown
│   └── DriverManager.java     # WebDriver management
├── pages/
│   ├── LoginPage.java         # Login page objects
│   └── AssignmentPage.java    # Main application pages
├── tests/
│   └── AssignmentTest.java    # Test cases
└── utils/
    ├── CommonMethods.java     # Reusable utility methods
    └── PropertyReader.java    # Configuration reader
```

### Key Features:
- **Page Object Model**: Separate page classes for maintainability
- **Dynamic Waits**: WebDriverWait with custom conditions
- **Cross-browser Support**: Chrome and Firefox
- **Loader Handling**: Custom wait for loader animations
- **Allure Integration**: Detailed test reporting
- **Configuration Management**: Properties file for test data

## Troubleshooting

### Common Issues:
1. **ChromeDriver Compatibility**: Update Chrome browser
2. **Test Data**: Verify credentials in config.properties
3. **Element Timeouts**: Check application loading times
4. **Session Issues**: Ensure proper driver cleanup

### Debug Commands:
```bash
# Verbose Maven output
mvn test -X

# Debug logging
mvn test -Dorg.slf4j.simpleLogger.defaultLogLevel=debug

# Skip tests (compile only)
mvn compile
```

## Contributing
1. Fork the repository
2. Create feature branch
3. Make changes with proper test coverage
4. Submit pull request

## Author
Nilesh Patil - Automation Test Engineer