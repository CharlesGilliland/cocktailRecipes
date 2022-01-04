Feature: Store equipment in the database
  AS A customer
  I WANT TO store equipment
  SO THAT I can use them in any recipes I want to store

  Scenario: Successfully store equipment
    Given I have supplied a name for the equipment
    And I have supplied a value for if it is powered
    When I submit a request to add the equipment
    Then I receive the json of the saved equipment

  Scenario: Successfully store equipment
    Given I have supplied a name for the equipment
    And I have not supplied a value for if it is powered
    When I submit a request to add the equipment
    Then I receive the json of the saved equipment

  Scenario: Fail to store equipment
    Given I have not supplied a name for the equipment
    And I have supplied a value for if it is powered
    When I submit a request to add the equipment
    Then I receive an error from the server and the equipment is not stored