Feature: Housing sorting validation

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
