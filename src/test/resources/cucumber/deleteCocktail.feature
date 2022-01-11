Feature: Delete a cocktail from the database
  AS A customer
  I WANT TO delete cocktail recipes
  SO THAT If i make a mistake or no longer need to the cocktail I can remove it

  Scenario: Successfully delete a cocktail
    Given I have supplied an id for the cocktail
    And The cocktail with that id exists within the database
    When I submit a request to delete the cocktail
    Then I receive the json of the saved cocktail