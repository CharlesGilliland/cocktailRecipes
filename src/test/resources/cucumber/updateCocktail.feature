Feature: Update a cocktail from in the database
  AS A customer
  I WANT TO update cocktail recipes
  SO THAT I can edit a recipe stored in the database

  Scenario: Successfully update a cocktail
    Given The cocktail already exists in the database
    And I supply an updated version of the cocktail
    When I submit a request to update the cocktail
    Then I receive a string to say the cocktail has been updated

  Scenario: Successfully update a cocktail without change
    Given The cocktail already exists in the database
    When I submit a request to update the cocktail
    Then I receive a string to say the cocktail has been updated

  Scenario: Fail to update a cocktail
    Given The cocktail to update does not exist in the database
    When I submit a request to update the cocktail
    Then I receive an error from the server and the cocktail is not updated

