Feature: Is Valid Admin
  User is admin.

  Scenario: User introduces username and password
    Given I navigate to index.html and I type username equals to admin and password equals admin
    When The User clicks login button
    Then Should display successful message

  Scenario: User introduces wrong username or password
    Given I navigate to index.html and I type wrong admin username or password
    When The User clicks login button
    Then Should display Invalid Loggin message
