Feature: Ask for a receipt

  Background:
    Given Sarah has a Caffeinate-Me account
    And the following prices:
      | Product            | Price |
      | regular cappuccino | 1.90  |
      | large cappuccino   | 2.25  |
      | muffin             | 1.25  |

  Scenario: Order several items
    Given Sarah has ordered:
      | Quantity | Product          |
      | 1        | large cappuccino |
      | 1        | muffin           |
    When she asks for a receipt
    Then she should receive a receipt totalling:
      | Subtotal | Service Fee | Total |
      | 3.50     | 0.18        | 3.68  |
