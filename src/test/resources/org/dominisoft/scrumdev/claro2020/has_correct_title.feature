Feature: Has correct title
  Index (login) page has the appropriate title.

  Scenario: Index page has correct title
    Given I navigate to index.html
    Then The title should be 'Agile Voting'
  Scenario: Rejects Null Value
    Given I navigate to index.html
    When I click on 'id-input'
    Then The input should not be empty
