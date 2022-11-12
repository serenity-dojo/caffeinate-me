Feature: Ask for a receipt

  Background:
    Given the following prices:
      | Product            | Price |
      | regular cappuccino | 1.90  |
      | large cappuccino   | 2.25  |
      | muffin             | 1.25  |
    And Cathy is a CaffeinateMe customer

  Scenario: Order several items
    Given Cathy has placed an order for the following items:
      | Quantity | Product          |
      | 1        | large cappuccino |
      | 2        | muffin           |
    When she asks for a receipt
    Then she should receive a receipt totalling:
      | Subtotal | Service Fee | Total |
      | 4.75     | 0.24        | 4.99  |
    And the receipt should contain the line items:
      | Product          | Quantity | Price |
      | large cappuccino | 1        | 2.25  |
      | muffin           | 2        | 2.50  |
