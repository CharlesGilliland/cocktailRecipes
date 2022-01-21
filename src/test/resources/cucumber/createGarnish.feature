Feature: Store a garnish on the web app
  AS A customer
  I WANT TO store glasses
  SO THAT I can use them in any recipes I want to store

  Scenario: Successfully save a garnish from the web
    Given I have navigated to the home page to create a garnish
    When I click on the navigation link for the create garnish page
    Then I am redirected to the create garnish page
    When I enter the information for a new garnish
    And I click on the create garnish button
    Then I am redirected to the view garnish page
    And I am able to see the newly created garnish