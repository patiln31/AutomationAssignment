# Automation Framework

## Prerequisites
- Java 21
- Maven 3.6+
- Chrome/Firefox browser

## Running Tests

### Via TestNG XML:
```bash
mvn test
```

### Via Command Line with parameters:
```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
```

### Generate Allure Report:
```bash
mvn allure:report
mvn allure:serve
```

## Framework Structure
- `src/main/java/com/automation/framework/` - Core framework classes
- `src/main/java/com/automation/tests/` - Test classes
- `src/main/java/com/automation/utils/` - Utility classes
- `test-data/` - Excel test data files
- `testng.xml` - TestNG configuration

## Test Data Format
Excel format in `test-data/testdata.xlsx`:
- Sheet Name: TestData
- Headers: url, expectedTitle
- Data rows with corresponding values