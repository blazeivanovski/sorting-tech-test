Feature: Housing sorting validation

  Scenario: Verify sorting options and price sorting behavior
    Given User opens Craigslist Madrid
    When User selects English language
    And User navigates to Housing section
    Then User is on Housing page

    When User sorts results by PRICE_ASC
    Then Results should be sorted by price ascending

    When User sorts results by PRICE_DESC
    Then Results should be sorted by price descending
    And Default sorting options should be available

    When User performs a search for "Traditional House"
    Then Extended sorting options should be available
