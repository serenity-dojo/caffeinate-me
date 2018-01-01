Feature: View pending orders

  Barry needs to see the orders that his customers have placed.

  Background:
    Given Barry is a barrista
    And Sarah is a registered customer
    And Joe is a registered customer

  Scenario: Barry sees all the orders
    Given Sarah has ordered an espresso
    Given Joe has ordered a cappuccino
    When Barry reviews the pending orders
    Then Barry should see the orders:
      | customer | order      |
      | Sarah    | espresso   |
      | Joe      | cappuccino |


  @Pending
  Scenario: Barry sees the high priority orders first
