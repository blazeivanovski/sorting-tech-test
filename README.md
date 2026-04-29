# Technical test

### Project URL
https://github.com/blazeivanovski/sorting-tech-test.git

### Tech stack for automated testing
**Core**
- Java (OpenJDK 21.0.10)
- Apache Maven (3.11.0)

**Testing frameworks**
- Playwright (1.49.0)
- Cucumber (7.15.0)
- JUnit (4.13.2)

**Reporting**
- Allure (2.25.0)

### Site under test
- https://madrid.craigslist.org/

### Page under test
- Housing

### Functionality to verify
- Sorting functionality (price ascending, price descending)
- By default, such sorting possibilities are available: price ascending, price descending, newest
- After using search such sorting possibilities are available: price ascending, price descending, newest, upcoming, relevance

### Test scenario
```
Scenario: Verify sorting options and price sorting behavior
  Given User visits the Craigslist Madrid site
    When User selects English language
    And User navigates to Housing section
    Then User is on Housing page

    When User sorts results by PRICE_ASC
    Then Results should be sorted by price in ascending order

    When User sorts results by PRICE_DESC
    Then Results should be sorted by price in descending order
    And Default sorting options should be available

    When User performs a search for "Traditional House"
    Then Extended sorting options should be available
```

### Command to run test
`mvn clean test`

### Command to run report
`mvn allure:serve`
