Feature: Create triangle

  Scenario: Create triangle by API with default separator
    Given There are no any triangles
    When Creating new triangle with input "2; 3; 4"
    Then There is 1 triangle(s) in the response list
    Then The triangle #1 in the response list has sides 2 (first), 3 (second) and 4 (third)