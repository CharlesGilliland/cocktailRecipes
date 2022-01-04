Feature: Store a glass in the database
  AS A customer
  I WANT TO store glasses
  SO THAT I can use them in any recipes I want to store

  Scenario: Successfully store a glass
    Given I have supplied a type for the glass
    And I have supplied a volume for the glass
    When I submit a request to add the glass
    Then I receive the json of the saved glass

  Scenario: Fail to store a glass
    Given I have not supplied a type for the glass
    And I have supplied a volume for the glass
    When I submit a request to add the glass
    Then I receive an error from the server and the glass is not stored

  Scenario: Fail to store a glass
    Given I have supplied a type for the glass
    And I have not supplied a volume for the glass
    When I submit a request to add the glass
    Then I receive an error from the server and the glass is not stored