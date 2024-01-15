Feature: Order a coffee

  In order to save time when I pick up my morning coffee
  As a coffee love
  I want to be able to order my coffee in advance

  Scenario: Buyers order a coffee when they are close to the coffee shop
    Given Cathy is 100 metres from the coffee shop
    When Cathy orders a "large cappuccino"
    Then Barry should receive the order
    And Barry should know that the order is Urgent

  Scenario: Buyers order a coffee when they are very close to the coffee shop
    Given Cathy is 50 metres from the coffee shop
    When Cathy orders a "large cappuccino"
    Then Barry should receive the order
    And Barry should know that the order is Urgent

  Scenario: Buyers order a coffee when they are far to the coffee shop
    Given Cathy is 300 metres from the coffee shop
    When Cathy orders a "large cappuccino"
    Then Barry should receive the order
    And Barry should know that the order is Normal

