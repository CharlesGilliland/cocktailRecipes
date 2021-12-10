Feature: Store cocktail recipes
    AS A customer
    I WANT TO save recipes
    SO THAT I can use them later

    Scenario: Successfully store a cocktail recipe
        Given I supply a name
        And I supply a set of instructions
        And I supply a description
        When I add the cocktail
        Then the cocktail recipe should be stored

