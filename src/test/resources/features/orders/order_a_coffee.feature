Feature: Order a coffee

  In order to save time when I pick up my morning coffee
  As a coffee love
  I want to be able to order my coffee in advance

  Background:
    Given Cathy is a CaffeinateMe customer

  Scenario: Buyer orders a coffee when they are close to the coffee shop
    Given Cathy is 100 metres from the coffee shop
    When Cathy orders a "large cappuccino"
    Then Barry should receive the order
    And Barry should know that the order is Urgent


  Scenario: Buyer orders a coffee when they are 50 metres away from the shop
    Given Cathy is 50 metres from the coffee shop
    When Cathy orders a "large cappuccino"
    Then Barry should receive the order
    And Barry should know that the order is Urgent

  Scenario: Buyer orders a coffee when they are far to the shop
    Given Cathy is 300 metres from the coffee shop
    When Cathy orders a "large Cappuccino"
    Then Barry should receive the order
    And Barry should know that the order is Normal


  Scenario: Buyers can add a comment with their order
    Given Cathy orders a "large cappuccino" with a comment "Double sugar"
    Then Barry should receive the order
    And the order should have the comment "Double sugar"