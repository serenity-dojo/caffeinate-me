@Frontend:PlaceAnOrder
@Backend:PlaceAnOrder
Feature: Place an order

  In order to save time when I pick up my morning coffee
  As a coffee lover
  I want to be able to order my coffee in advance

  Caffeinate-Me customers like their coffee hot

  Scenario: Buyer orders a coffee
    Given Cathy has a Caffeinate-Me account
    When she orders a large cappuccino
    Then Barry should receive the order


