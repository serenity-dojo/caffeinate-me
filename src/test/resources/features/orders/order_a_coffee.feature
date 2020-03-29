Feature: Order a coffee

  In order to save time when I pick up my morning coffee
  As a coffee love
  I want to be able to order my coffee in advance

  Scenario: Buyer orders a coffee when they are close to the coffee shop
    Given Cathy is 100 metre from the coffee shop
    When Cathy orders a large cappuccino
    Then Barry should receive the order
    And Barry should know that the order is Urgent

  Scenario Outline: Customer orders are treated as high priority when the customer is close to the shop
    Given Cathy is <distance> metres from the coffee shop
    When Cathy orders a large cappuccino
    Then Barry should receive the order
    And Barry should know that the order is <status>

    Examples:
      | distance | status |
      | 49.5     | Urgent |
      | 300      | Normal |

  Scenario: Customers should be informed when their drinks are ready
    Given Cathy has ordered a large cappuccino
    When her order is ready
    Then she should receive a message "Your large cappuccino is ready!"
