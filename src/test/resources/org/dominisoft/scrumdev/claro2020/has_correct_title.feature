Feature: Has correct title
  Index (login) page has the appropriate title.

  Scenario: Index page has correct title
    Given I navigate to index.html
    Then The title should be 'Agile Voting'
  Scenario: Rejects Null Value
    Given I navigate to index.html
    When I click on 'id-input'
    Then The input 'id-input' should not be ''
  Scenario: Rejects 01234567891 Value
    Given I navigate to index.html
    When I type '01234567891' on 'id-input'
    Then The input 'id-input' should not be '01234567891'
  Scenario: Rejects when length is not 11 characters
    Given I navigate to index.html
    When I type '1234' on 'id-input'
    Then The label 'error-msg' should have text 'La cedula debe tener 11 caracteres'
  Scenario: Rejects when not numeric chars
    Given I navigate to index.html
    When I type '0123456789a' on 'id-input'
    Then The label 'error-msg' should have text 'La cedula debe tener solo caracteres numericos'
