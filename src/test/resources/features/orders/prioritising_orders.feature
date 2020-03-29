Feature: Prioritising orders

  In order to organise my team
  As head barista preparing orders in a large coffee shop
  I need to have an idea of how many drink orders are coming

  Background:
    Given Cathy has a Caffeinate-me account

  Scenario Outline: Prioritise orders according to client ETA
    Given Cathy has ordered an expresso
    And Cathy is <ETA> minutes away
    When Barry reviews his pending orders
    Then Cathy's order should have an urgency of <Urgency>
    Examples:
      | Rule                      | ETA | Urgency |
      | More than 10 minutes away | 12  | Normal  |
      | Between 5 and 10 minutes  | 7   | High    |
      | Less than 5 minutes away  | 3   | Urgent  |
