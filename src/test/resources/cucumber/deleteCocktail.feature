Feature: Delete a cocktail from the database
  AS A customer
  I WANT TO delete cocktail recipes
  SO THAT If i make a mistake or no longer need to the cocktail I can remove it

  Scenario: Successfully delete a cocktail
    Given I have supplied a name for the ingredient
    And I have supplied a type for the ingredient
    And I have supplied an abv for the ingredient
    And I have supplied a storage for the ingredient
    And I have supplied a description for the ingredient
    When I submit a request to add the ingredient
    Then I receive the json of the saved ingredient