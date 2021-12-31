Feature: Retrieve glasses
  AS A customer
  I WANT TO retrieve stored glasses
  SO THAT I can chose which one to use

  Scenario: Retrieve all glasses
    When I submit a request to get all glasses
    Then All the glasses should be returned


