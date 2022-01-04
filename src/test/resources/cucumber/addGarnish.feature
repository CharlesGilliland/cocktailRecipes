Feature: Store a garnish in the database
  AS A customer
  I WANT TO store garnishes
  SO THAT I can use them in any recipes I want to store

  Scenario: Successfully store a garnish
    Given I have supplied a type for the garnish
    And I have supplied storage for the garnish
    When I submit a request to add the garnish
    Then I receive the json of the saved garnish

  Scenario: Successfully store a garnish
    Given I have supplied a type for the garnish
    And I have not supplied storage for the garnish
    When I submit a request to add the garnish
    Then I receive the json of the saved garnish

  Scenario: Fail to store a garnish
    Given I have not supplied a type for the garnish
    And I have supplied storage for the garnish
    When I submit a request to add the garnish
    Then I receive an error from the server and the garnish is not stored