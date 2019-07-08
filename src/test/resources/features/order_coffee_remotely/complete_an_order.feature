@Frontend:CompletingOrder
Feature: Completing orders

  Background:
    Given Barry is a barista
    And Sarah is a registered customer

  @sprint-2
  Scenario: Barry delivers a completed order
    Given Sarah has ordered an espresso
    When Barry marks the order as complete
    Then the order should no longer appear in the pending orders
    And Sarah should see the order in her order history

