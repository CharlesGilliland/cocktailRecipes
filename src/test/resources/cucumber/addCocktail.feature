Feature: Store a cocktail recipe in the database
  AS A customer
  I WANT TO store cocktail recipes
  SO THAT I can use them at a later date

  Scenario: Successfully store a cocktail recipe with a name instructions and descriptions
    Given I have supplied a name
    And I have supplied a set of instructions
    And I have supplied a description
    When I submit a request to add the cocktail
    Then I receive the json of the saved cocktail

  Scenario: Successfully store a cocktail with a name and instructions
    Given I have supplied a name
    And I have supplied a set of instructions
    And I have not supplied a description
    When I submit a request to add the cocktail
    Then I receive the json of the saved cocktail

  Scenario: Fail to store the cocktail by not supplying a name
    Given I have not supplied a name
    And I have supplied a set of instructions
    When I submit a request to add the cocktail
    Then I receive an error from the server and the cocktail is not stored

  Scenario: Fail to store the cocktail by not supplying instructions
    Given I have supplied a name
    And I have not supplied a set of instructions
    When I submit a request to add the cocktail
    Then I receive an error from the server and the cocktail is not stored