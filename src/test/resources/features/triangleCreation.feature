Feature: Create triangle by API

  Background:
    Given There are no any triangles

  Scenario Outline: Create different types of triangles
    When Creating new <description> triangle with sides: <input>
    Then There is\are 1 triangle\s in the response list
    And The triangle with position in list #1 has sides <firstSide>, <secondSide> and <thirdSide>
    And The triangle with position in list #1 has perimeter <perimeter>
    And The triangle with position in list #1 has area <area>

    Examples:
      |description      |input     |firstSide  |secondSide |thirdSide |perimeter| area |
      |Obtuse           |2;3;4     |2          |3          |4         |9        |2.9047375096555625|
      |Right            |3;4;5     |3          |4          |5         |12       |6                 |
      |Acute            |66; 67; 68|66         |67         |68        |201      |1942.927800382711 |
      |Isosceles        |3;3;5     |3          |3          |5         |11       |4.14578098794425  |
      |Equilateral      |6; 6; 6   |6          |6          |6         |18       |15.588457268119896|
#      TODO have to clarify the next steps expected behavior
      |with negative number sides|-2; -3; -4  |2       |3          |4         |9|2.9047375096555625|
      |with one negative number side|8; 8; -8  |8       |8          |8         |24|27.712812921102035|
      |with decimal numbered divided by dots sides|1.111;1.1;1.1 |1.111|1.1    |1.1       |3.311 |0.5274090744478497|


#     impossible to deserialize with Jackson (tested manually - passed)
#      |with sides value > int       |1                 |4294967295; 4294967295; 4294967295|4294967295|4294967295|4294967295|


  Scenario Outline: Try to create impossible triangle
    When Creating new <description> triangle with sides: <input>
    Then There is\are 0 triangle\s in the response list
    Examples:
      |description      | input |
      |with empty input |       |
      |impossible geometry |2; 3; 10|
      |with zero sides     |0; 0; 0 |
      |with non-digit sides|a; b; c |
      |with one non-digit side|2; @; 2|
      |without one side       |99;99; |


  Scenario Outline: Create triangles with non default separators in input
    When Creating new triangle with request data: separator: <separator>, input: <input>
    Then There is\are 1 triangle\s in the response list
    And The triangle with position in list #1 has sides 1, 1 and 1
    Examples:
  |separator | input |
#  TODO ask about the expected behavior with empty separator field sent
# |.              |1.1.1                               |
# |          |1; 1; 1  |
  |;         |1;1;1  |
  |????      |1????1????1????|
  |z         |1z 1z 1|
  |$         |1$ 1$ 1|
  |}         |1} 1} 1|
  |" "       |1" "1" "1|
  |заморские буквы|1заморские буквы 1 заморские буквы 1|
  |2              |121212                              |