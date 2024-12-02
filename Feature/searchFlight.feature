@tag
Feature: To Validate Flight Search page

  @tag2
  Scenario Outline: To validate Flight for One Trip
    Given Launch Site and verify the title
    When Click on "<TravelMode>" and "<TravelType>"
    And Select  "<fromlocation>" and "<toLocation>"
    And Select "<Date>"
    And Select Travellers "<Adults>" & "<childs>" & "<Infants>"
    And Click on Search Button
    Then Verify Flight List should be  Display for above input
    And verify Flight Ticket price List Displayed Low to Hight
    And Click on select Flight Button
    And verify Flight Review Page

    Examples: 
      | TravelMode | TravelType | fromlocation | toLocation | Date      | Adults | childs | Infants |
      | One Way    | Economy    | LHE          | IST        | 4-12-2024 |      5 |      0 |       0 |
