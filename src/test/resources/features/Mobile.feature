Feature: Mobile - Orbi

  #@mobile @androidLocal @Mobile
  Scenario Outline: Login Successful
    Given Open mobile application - Orbi
    When Capture credentials "<username>" "<password>" and click on the Login button
    Then Title is displayed - Users List

    Examples:
    | username    | password |
    | admin       | password |


  #@mobile @androidLocal @Mobile
  Scenario Outline: Login - Do not capture username field
    Given Open mobile application - Orbi
    When Capture credentials "<username>" "<password>" and click on the Login button
    Then Error message is displayed "<message>" - Login username

    Examples:
      | username    | password | message                    |
      |             | password | Please enter your username |

  #@mobile @androidLocal @Mobile
  Scenario Outline: Login - Do not capture password field
    Given Open mobile application - Orbi
    When Capture credentials "<username>" "<password>" and click on the Login button
    Then Error message is displayed "<message>" - Login password

    Examples:
      | username    | password | message                    |
      | admin       |          | Please enter your password |


  #@mobile @androidLocal @Mobile
  Scenario Outline: Add new user - Do not capture Name field
    Given Open mobile application - Orbi
    When Capture credentials "<username>" "<password>" and click on the Login button
    And Click New User
    And Add new user "<Name>" "<Email>" "<Age>" - New User
    Then Error message "<message>" displayed Name - New User

    Examples:
      | username    | password | Name           | Email               | Age     | message                |
      | admin       | password |                | francisco@gmail.com | 33      | Please enter the name  |


  #@mobile @androidLocal @Mobile
  Scenario Outline: Add new user - Do not capture Email field
    Given Open mobile application - Orbi
    When Capture credentials "<username>" "<password>" and click on the Login button
    And Click New User
    And Add new user "<Name>" "<Email>" "<Age>" - New User
    Then Error message "<message>" displayed Email - New User

    Examples:
      | username    | password | Name           | Email               | Age     | message                 |
      | admin       | password | francisco      |                     | 33      | Please enter the email  |

  #@mobile @androidLocal @Mobile
  Scenario Outline: Add new user - Do not capture Age field
    Given Open mobile application - Orbi
    When Capture credentials "<username>" "<password>" and click on the Login button
    And Click New User
    And Add new user "<Name>" "<Email>" "<Age>" - New User
    Then Error message "<message>" displayed Age - New User

    Examples:
      | username    | password | Name           | Email               | Age     | message                 |
      | admin       | password | francisco      | francisco@gmail.com |         | Please enter the age    |

  #@mobile @androidLocal @Mobile
  Scenario Outline: Add new user - Invalid email
    Given Open mobile application - Orbi
    When Capture credentials "<username>" "<password>" and click on the Login button
    And Click New User
    And Add new user "<Name>" "<Email>" "<Age>" - New User
    Then Error message "<message>" displayed Invalid email - New User

    Examples:
      | username    | password | Name           | Email               | Age     | message                               |
      | admin       | password | francisco      | francisco.com       | 33      | Please enter a valid email address    |


  #@mobile @androidLocal @Mobile
  Scenario Outline: Add new user - age String
    Given Open mobile application - Orbi
    When Capture credentials "<username>" "<password>" and click on the Login button
    And Click New User
    And Add new user "<Name>" "<Email>" "<Age>" - New User
    Then Error message "<message>" displayed Invalid age - New User

    Examples:
      | username    | password | Name           | Email                | Age         | message                       |
      | admin       | password | francisco      | francisco@gmail.com  | fsghsj      | Please enter a valid number   |

  @mobile @androidLocal @Mobile
  Scenario Outline: Add new user Successful
    Given Open mobile application - Orbi
    When Capture credentials "<username>" "<password>" and click on the Login button
    And Click New User
    And Add new user "<Name>" "<Email>" "<Age>" - New User

    Examples:
      | username    | password | Name           | Email                | Age         |
      | admin       | password | francisco      | francisco@gmail.com  | 33          |