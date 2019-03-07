Feature: Delete user

  Scenario: Delete user
    Given a login page open
    And logged in as "UserToBeDeleted" with password "Password"
    When deleting account
    Then the account is deleted