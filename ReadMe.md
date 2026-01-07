# Selenium Automation Framework (UI + Future API Ready)

## Overview

This project is a scalable Selenium automation framework built using:

- Java
- Selenium 4
- TestNG
- Maven
- Selenium Grid
- Extent Reports

The framework supports:
- Parallel execution
- Selenium Grid execution
- Environment-based execution (QA / Staging / Prod)
- Retry mechanism for flaky tests
- Clean reporting with author, steps, screenshots
- Future expansion for API and Mobile automation
- Data-driven testing using Excel and JSON (no hardcoded test data)

This is designed as a real-world, production-style automation framework.

---

## Key Features

- Parallel test execution using TestNG
- Selenium Grid support (local / grid switch)
- Environment-based configuration
- Thread-safe WebDriver and reporting
- Retry Analyzer for flaky tests
- Extent Reports with:
    - Author
    - Feature
    - Story
    - Categories (Smoke / Regression)
    - Step logging
    - Screenshots on failure
- Maven-based execution
- CI/CD ready structure
- Data-driven testing with Excel and JSON support for test data 

This is designed as a real-world, production-style automation framework, not a demo project. It is also a **data-driven testing project** that leverages Excel and JSON files for flexible test data management, allowing easy parameterization of test cases without code changes.

---

## Execution Flow

1. Maven starts test execution
2. TestNG reads `testng.xml`
3. Browser is selected (Local or Grid)
4. DriverFactory creates WebDriver using ThreadLocal
5. Listener creates Extent test entry
6. Steps are logged via ExtentLogger
7. Screenshot is captured on failure
8. Extent report is flushed after execution

---

## Environment Configuration

The framework supports multiple environments without changing test code.

### Supported Environments

- qa (default)
- staging
- prod

### Configuration Files
- config.properties           → common config 
- config-qa.properties        → QA environment
- config-staging.properties   → Staging environment
- config-prod.properties      → Production environment

### Example: config-qa.properties
- env=qa
- base.url=https://qa.myapp.com
- username=qa_user
- password=qa_password

## How to Run Tests

### Run on QA (default)
mvn clean test -Denv=qa

### Run on Staging
mvn clean test -Denv=staging

### Run on Production
mvn clean test -Denv=prod

## Parallel Execution
Parallel execution is controlled by TestNG, not Maven profiles.

### In testng.xml:
    XML<suite parallel="classes" thread-count="4">

## Rules:
- Maximum parallel browsers = min(thread-count, number of test classes)
- Each test runs in its own browser session
- ThreadLocal is used for WebDriver and ExtentTest

---

## Selenium Grid Execution

1. **Start Selenium Grid**:
    `java -jar selenium-server-4.x.x.jar standalone`

2. **Grid UI**: http://localhost:4444/ui

3. **Switch to Grid**: In `config.properties`:
   runMode=grid
   gridUrl=http://localhost:4444

4. **To switch back to local execution**: In `config.properties`:
   `runMode=local`
   textNo code change required.

---

## Retry Mechanism
### Flaky tests are handled using a Retry Analyzer.
    RetryAnalyzer: RetryAnalyzer implements IRetryAnalyzer

### Usage:
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void unstableTest() {
    // Test logic
    }
- Failed tests are retried automatically
- Retry count is configurable
- Reporting remains clean and readable

---

## Reporting (Extent Reports)
Report Location
    '/reports/index.html'

### What the Report Contains
Per test:
  - Test name
  - Status (Pass / Fail / Skip)
  - Author
  - Feature
  - Story
  - Categories (Smoke / Regression)
  - Steps
  - Screenshot on failure
  - Execution time

### Report level:
  - Environment
  - Browser
  - OS
  - Java version
  - Run mode (local / grid)

---

## Annotations
Author
    `@Author("Bhargav")`

Feature
    `@Feature("Login")`

Story
    `@Story("Valid login")`
### Example
`@Test(groups = {"Smoke"})
 @Author("Bhargav")
 @Feature("Login")
 @Story("Valid user login")
 public void validLoginTest() {
}`

---

## Step Logging
Steps are logged using ExtentLogger.

Example:
`ExtentLogger.info("Entered username");
 ExtentLogger.info("Clicked login button");`

### Best practices:
- Log meaningful actions only
- Avoid logging every Selenium command
- Capture screenshots only on failure

---

## Design Principles
- Separation of concerns
- Thread safety
- Environment independence
- CI/CD friendly
- Easy future expansion

---

## Future Enhancements
- API automation module
- Severity / Priority annotations
- Dockerized Selenium Grid
- CI/CD integration
- Database validations
- Final Notes

## This framework is designed to be:
- Easy to understand
- Easy to run
- Easy to extend
- Interview-ready

This README should be enough to understand the entire project without opening the code.

---

## How to Run
`mvn test -Denv=qa` | Run all tests on QA (default). | Full suite execution.

`mvn test -Denv=qa -Dgroups=Smoke` | Smoke tests on QA. | Quick validation run.

`mvn test -Denv=staging` | All tests on Staging. | Environment-specific.

`mvn test -Denv=prod -Dgroups=Smoke` | Regression on Prod. | Thorough checks.

`mvn clean test -Denv=qa -Dgroups=Smoke,Sanity` | Smoke and Sanity tests on QA. | Quick validation run.



## Start Selenium Server
`cd C:\selenium-grid`
`java -jar selenium-server-4.39.0.jar standalone`

##### Groups
- Smoke
- Sanity
- Regression
- Critical