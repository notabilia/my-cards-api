Feature: Create cards
  A user wants the ability to create cards

  Scenario: A user can create a single card with no attributes
    When a user creates a card with no attributes
    Then a card with no attributes should be returned

  Scenario Outline: A user can create a single card with a description attribute
    When a user creates a card with a description attribute of "<description>"
    Then the cards description should be equal to "<description>"

    Examples:
      | description                            |
      | A card I bought in a shop for a person |

  Scenario Outline: A user can create a single card with a purchase date attribute
    When a user creates a card with a purchase date attribute of "<date>"
    Then the cards purchase day should be equal to <day>
    And the cards purchase month should be equal to <month>
    And the cards purchase year should be equal to <year>

    Examples:
      | date         | day   | month | year |
      | 1973-11-29   | 29    | 11    | 1973 |

  Scenario Outline: A user can create a single card with a merchant attribute
    When a user creates a card with a merchant attribute of "<merchant>"
    Then the cards merchant should be equal to "<merchant>"

    Examples:
      | merchant           |
      | A random card shop |

  Scenario Outline: A user can create a single card with a price attribute
    When a user creates a card with a price attribute of <price>
    Then the cards price should be equal to <price>

    Examples:
      | price |
      | 12.99 |

  Scenario Outline: A user can interact with the API to create a card
    Given the cards existence is "<exists>"
    When a user creates the card
    Then the API should return a status code of <code>

    Examples:
      | exists | code |
      | false  | 201  |
      | true   | 400  |
