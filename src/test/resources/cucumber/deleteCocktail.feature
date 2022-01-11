Feature: Delete a cocktail from the database
  AS A customer
  I WANT TO delete cocktail recipes
  SO THAT If i make a mistake or no longer need to the cocktail I can remove it

  Scenario: Successfully delete a cocktail
    Given The cocktail exists in the database
    And I supply the correct id
    When I submit a request to delete the cocktail
    Then I receive a string to say the cocktail has been deleted

  Scenario: Failed to delete the cocktail
    Given The cocktail exists in the database
    And I supply an incorrect id
    When I submit a request to delete the cocktail
    Then I receive an error from the server and the cocktail is not deleted

  Scenario: Failed to delete the cocktail
    Given The cocktail does not exist in the database
    And I supply an id
    When I submit a request to delete the cocktail
    Then I receive an error from the server and the cocktail is not deleted