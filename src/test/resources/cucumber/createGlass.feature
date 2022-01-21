Feature: Store a glass on the web app
  AS A customer
  I WANT TO store glasses
  SO THAT I can use them in any recipes I want to store

  Scenario: Successfully save a glass from the web
    Given I have navigated to the home page to create a glass
    When I click on the navigation link for the create glass page
    Then I am redirected to the create glass page
    When I enter the information for a new glass
    And I click on the create glass button
    Then I am redirected to the view glass page
    And I am able to see the newly created glass