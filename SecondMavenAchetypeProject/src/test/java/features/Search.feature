Feature: Search For Product

  Scenario: Search for a Product
    Given the user is on the website 'https://www.amazon.co.uk' using 'chrome'
    When  the user searches for a product 'watch'
    Then the search results should be displayed
    When I click on the product 'samsung'
    Then the PDP should be displayed