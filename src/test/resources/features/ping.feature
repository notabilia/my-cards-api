Feature: Ping
  A user wants the ability to know if the service is up or not

  Scenario Outline: A user can interact with the API to know if it is up or not
    Given the API is "<status>"
    When a user checks the status of the API
    Then the ping API endpoint should return a status code of <code>
    And the API should return the time the call was made
    And the API should return the current running version

    Examples:
      | status | code |
      | up     | 200  |
