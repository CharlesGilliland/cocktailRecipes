Feature: Store a cocktail recipe in the database
  AS A customer
  I WANT TO store cocktail recipes
  SO THAT I can use them at a later date

  Scenario: Successfully store a cocktail recipe
    Given I have supplied a name
    And I have supplied a set of instructions
    And I have supplied a description
    When I submit a request to add the cocktail
    Then the cocktail is stored in the database
    And I receive a message to say it has been saved
