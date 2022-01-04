Feature: Store ingredients in the database
  AS A customer
  I WANT TO store ingredients
  SO THAT I can use them in any recipes I want to store

  Scenario: Successfully store ingredient
    Given I have supplied a name for the ingredient
    And I have supplied a type for the ingredient
    And I have supplied an abv for the ingredient
    And I have supplied a storage for the ingredient
    And I have supplied a description for the ingredient
    When I submit a request to add the ingredient
    Then I receive the json of the saved ingredient

  Scenario: Successfully store ingredient
    Given I have supplied a name for the ingredient
    And I have supplied a type for the ingredient
    And I have supplied an abv for the ingredient
    And I have supplied a storage for the ingredient
    And I have not supplied a description for the ingredient
    When I submit a request to add the ingredient
    Then I receive the json of the saved ingredient

  Scenario: Successfully store ingredient
    Given I have supplied a name for the ingredient
    And I have supplied a type for the ingredient
    And I have supplied an abv for the ingredient
    And I have not supplied a storage for the ingredient
    And I have not supplied a description for the ingredient
    When I submit a request to add the ingredient
    Then I receive the json of the saved ingredient

  Scenario: Successfully store ingredient
    Given I have supplied a name for the ingredient
    And I have supplied a type for the ingredient
    And I have not supplied an abv for the ingredient
    And I have not supplied a storage for the ingredient
    And I have not supplied a description for the ingredient
    When I submit a request to add the ingredient
    Then I receive the json of the saved ingredient

  Scenario: Failed to store ingredient
    Given I have not supplied a name for the ingredient
    And I have supplied a type for the ingredient
    And I have supplied an abv for the ingredient
    And I have supplied a storage for the ingredient
    And I have supplied a description for the ingredient
    When I submit a request to add the ingredient
    Then I receive an error from the server and the ingredient is not stored

  Scenario: Failed to store ingredient
    Given I have supplied a name for the ingredient
    And I have not supplied a type for the ingredient
    And I have supplied an abv for the ingredient
    And I have supplied a storage for the ingredient
    And I have supplied a description for the ingredient
    When I submit a request to add the ingredient
    Then I receive an error from the server and the ingredient is not stored

  Scenario: Failed to store ingredient
    Given I have supplied a name for the ingredient
    And I have supplied a type for the ingredient
    And I have supplied an incorrect abv for the ingredient
    And I have supplied a storage for the ingredient
    And I have supplied a description for the ingredient
    When I submit a request to add the ingredient
    Then I receive an error from the server and the ingredient is not stored
