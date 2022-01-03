Feature: Retrieving all cocktail recipes from database
  AS A customer
  I WANT TO retrieve stored cocktails
  SO THAT I can see all the available recipes

  Scenario: Retrieve all cocktails
    Given there are cocktails stored in the database
    When I submit a request to get all cocktails
    Then I should receive information on all the stored cocktails
