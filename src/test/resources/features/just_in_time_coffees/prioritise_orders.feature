@Backend:PrioritiseOrders
Feature: Prioritise orders

  Orders are prioritised by nearby customers.
  This lets the barista know when he or she needs to start preparing the next coffee.

  Background:
    Given Sarah has a Caffeinate-Me account

  Scenario Outline: Order urgency
    Given Sarah has ordered an espresso
    And Sarah is <ETA> minutes away from the shop
    When Barry reviews the pending orders
    Then Sarah's order should have an urgency of <Urgency>

    Examples:
      | Rule                      | ETA | Urgency |
      | More than 10 minutes away | 12  | Normal  |
      | Between 5 and 10 minutes  | 7   | High    |
      | Less than 5 minutes       | 3   | Urgent  |
