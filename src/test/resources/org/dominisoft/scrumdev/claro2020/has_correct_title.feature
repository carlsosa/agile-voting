Feature: Has correct title
  Index (login) page has the appropriate title.

  Scenario: Index page has correct title
    Given I navigate to index.html
    Then The title should be 'Agile Voting'