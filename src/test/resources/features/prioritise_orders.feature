Feature: Prioritise orders

  Scenario Outline: Order urgency
    Given Sarah has ordered an espresso
    And Sarah is <ETA> minutes away from the shop
    When Barry reviews the pending orders
    Then Sarah's order should have an urgency of <Urgency>

    Examples:
      | ETA | Rule                      | Urgency |
      | 12  | More than 10 minutes away | Normal  |
      | 7   | Between 5 and 10 minutes  | High    |
      | 3   | Less than 5 minutes       | Urgent  |
