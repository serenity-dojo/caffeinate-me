Feature: Prioritising orders

  In order to organise my team
  As head barista preparing orders in a large coffee shop
  I need to have an idea of how many drink orders are coming

  Scenario Outline: Prioritise orders according to client ETA
    Given Sarah has ordered 1 espresso
    And Sarah is <ETA> minutes away from the shop
    When Barry reviews the pending orders
    Then Sarah's order should have an priority of <Priority>

    Examples:
      | Rule                      | ETA | Priority |
      | More than 10 minutes away | 12  | Normal   |
      | Between 5 and 10 minutes  | 7   | High     |
      | Less than 5 minutes       | 3   | Urgent   |


