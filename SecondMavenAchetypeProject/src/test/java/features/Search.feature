Feature: Search For Product

  Scenario: Search for a Product
    Given the user is on the website 'https://www.argos.co.uk' using 'chrome'
    When  the user searches for a product 'watch'
    Then the search results should be displayed