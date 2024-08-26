Feature: API - Orbi

  @api @APIOrbi
  Scenario Outline: Login Successful
    When Resquest Method POST Access Token "<username>" "<password>"
    Then Validation JSON Response access_token - Access Token
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    Examples:
    | username | password |
    | admin    | password |


  @api @APIOrbi
  Scenario Outline: Login Failed
    When Resquest Method POST Access Token "<username>" "<password>" - Failed
    Then Validation Response Code 401
    Then Validation Response Time
    Then Validation Response Message - Access Token
    Then Validation Response Error - Access Token
    Examples:
      | username  | password |
      | fdrsts    | password |
      | admin     | njdbsjkn |
      | fdrsts    | njdbsjkn |
      |           |          |


  @api @APIOrbi
  Scenario Outline: Create User Failed / Without Access Token
    When Resquest Method POST Create User "<name>" "<email>" <age> - Without Access Token
    Then Validation Response Code 401
    Then Validation Response Time
    Then Validation Response Message - Create User

    Examples:
      | name       | email                | age  |
      | francisco  | francisco @gmail.com | 33   |

  @api @APIOrbi
  Scenario: Create User Failed - Do not capture name
    When Resquest Method POST Access Token "admin" "password"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "francisco@gmail.com" 33 - Name is not captured
    Then Validation Response Code 400
    Then Validation Response Error - Create User

  @api @APIOrbi
  Scenario: Create User Failed - Name with numeric field
    When Resquest Method POST Access Token "admin" "password"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User 33 "francisco@gmail.com" 33 - Name with numeric field
    Then Validation Response Code 400
    Then Validation Response Error - Create User


  @api @APIOrbi
  Scenario: Create User Failed - Do not capture email
    When Resquest Method POST Access Token "admin" "password"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "francisco" "" 33 - Without Id
    Then Validation Response Code 400
    Then Validation Response Error - Create User
    Then Validation Response Message - Create User - Email is not captured

  @api @APIOrbi
  Scenario: Create User Failed - Email with numeric field
    When Resquest Method POST Access Token "admin" "password"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "francisco" 33 33 - Email with numeric field
    Then Validation Response Code 400
    Then Validation Response Error - Create User


  @api @APIOrbi
  Scenario: Create User Failed - Email incorrect format
    When Resquest Method POST Access Token "admin" "password"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "francisco" "francisco.com" 33 - Without Id
    Then Validation Response Code 400
    Then Validation Response Error - Create User


  @api @APIOrbi
  Scenario: Create User Failed - age String
    When Resquest Method POST Access Token "admin" "password"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "francisco" "francisco@gmail.com" "33" - Age with string field
    Then Validation Response Code 400
    Then Validation Response Error - Create User

  @api @APIOrbi
  Scenario Outline: Validate Record registered in all records
    When Resquest Method POST Access Token "<username>" "<password>"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "<name>" "<email>" <age> - Return Id
    Then Validation Response Code 201
    Then Validation Response Time
    Then Validate JSON Response Create User "<name>" "<email>" <age>
    When Resquest Method GET All users
    Then Validate record within JSON Response list - All Users "<name>" "<email>" <age>


    Examples:
      | username  | password | name         | email                  | age  |
      | admin     | password | francisco99  | francisco99@gmail.com  | 33   |


  @api @APIOrbi
  Scenario Outline: Validate User by ID Successful
    When Resquest Method POST Access Token "<username>" "<password>"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "<name>" "<email>" <age> - Return Id
    Then Validation Response Code 201
    Then Validation Response Time
    Then Validate JSON Response Create User "<name>" "<email>" <age>
    When Resquest Method GET All users
    Then Validate record within JSON Response list - All Users "<name>" "<email>" <age>
    When Validate User by ID "<name>" "<email>" <age> "<isActive>" - User by ID
    Then Validation Response Code 200


    Examples:
      | username  | password | name         | email                  | age  | isActive  |
      | admin     | password | francisco    | francisco@gmail.com    | 33   | true      |


  @api @APIOrbi
  Scenario Outline: Validate User by ID - Does Not Exist
    When Resquest Method POST Access Token "<username>" "<password>"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "<name>" "<email>" <age> - Return Id
    Then Validation Response Code 201
    Then Validation Response Time
    Then Validate JSON Response Create User "<name>" "<email>" <age>
    When Resquest Method GET All users
    Then Validate record within JSON Response list - All Users "<name>" "<email>" <age>
    When Validate User by ID <id> - User by ID - Does Not Exist
    Then Validation Response Code 404
    Then Validation Response Error - Not Found

    Examples:
      | username  | password | name         | email                  | age  | id    |
      | admin     | password | francisco    | francisco@gmail.com    | 33   | 9999  |


  @api @APIOrbi
  Scenario Outline: Update User Successful
    When Resquest Method POST Access Token "<username>" "<password>"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "<name>" "<email>" <age> - Return Id
    Then Validation Response Code 201
    Then Validation Response Time
    Then Validate JSON Response Create User "<name>" "<email>" <age>
    When Resquest Method GET All users
    Then Validate record within JSON Response list - All Users "<name>" "<email>" <age>
    When Validate User by ID "<name>" "<email>" <age> "<isActive>" - User by ID
    Then Validation Response Code 200
    When Update user "<nameUpdate>" "<emailUpdate>" <ageUpdate>
    Then Validate JSON Response "<nameUpdate>" "<emailUpdate>" <ageUpdate> "<isActive>" - true
    Then Validation Response Code 200
    Examples:
      | username  | password | name         | email                  | age  | isActive  | nameUpdate      | emailUpdate               |  ageUpdate  |
      | admin     | password | francisco    | francisco@gmail.com    | 33   | true      | franciscoUpdate | franciscoupdate@gmail.com | 44          |


  @api @APIOrbi
  Scenario Outline: Update User - Non-Existing ID
    When Resquest Method POST Access Token "<username>" "<password>"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "<name>" "<email>" <age> - Return Id
    Then Validation Response Code 201
    Then Validation Response Time
    Then Validate JSON Response Create User "<name>" "<email>" <age>
    When Resquest Method GET All users
    Then Validate record within JSON Response list - All Users "<name>" "<email>" <age>
    When When Update user "<nameUpdate>" "<emailUpdate>" <ageUpdate> <id> - Non Existing ID
    Then Validation Response Code 404
    Then Validation Response Error - Not Found

    Examples:
      | username  | password | name         | email                  | age  | id     | nameUpdate      | emailUpdate               |  ageUpdate  |
      | admin     | password | francisco    | francisco@gmail.com    | 33   | 9999   | franciscoUpdate | franciscoupdate@gmail.com | 44          |


  @api @APIOrbi
  Scenario Outline: Update User - Name NULL
    When Resquest Method POST Access Token "<username>" "<password>"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "<name>" "<email>" <age> - Return Id
    Then Validation Response Code 201
    Then Validation Response Time
    Then Validate JSON Response Create User "<name>" "<email>" <age>
    When Resquest Method GET All users
    Then Validate record within JSON Response list - All Users "<name>" "<email>" <age>
    When Validate User by ID "<name>" "<email>" <age> "<isActive>" - User by ID
    Then Validation Response Code 200
    When Update user "<emailUpdate>" <ageUpdate> - name NULL
    Then Validation Response Code 400
    Then Validation Response Error - Update User

    Examples:
      | username  | password | name         | email                  | age  | isActive  | nameUpdate      | emailUpdate              |  ageUpdate  |
      | admin     | password | francisco    | francisco@gmail.com    | 33   | true      | franciscoUpdate | franciscoupdategmail.com | 44          |

  @api @APIOrbi
  Scenario Outline: Update User - Field Name Numeric
    When Resquest Method POST Access Token "<username>" "<password>"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "<name>" "<email>" <age> - Return Id
    Then Validation Response Code 201
    Then Validation Response Time
    Then Validate JSON Response Create User "<name>" "<email>" <age>
    When Resquest Method GET All users
    Then Validate record within JSON Response list - All Users "<name>" "<email>" <age>
    When Validate User by ID "<name>" "<email>" <age> "<isActive>" - User by ID
    Then Validation Response Code 200
    When Update user <nameUpdate> "<emailUpdate>" <ageUpdate> - Field Name Numeric
    Then Validation Response Code 400
    Then Validation Response Error - Update User

    Examples:
      | username  | password | name         | email                  | age  | isActive  | nameUpdate      | emailUpdate               |  ageUpdate  |
      | admin     | password | francisco    | francisco@gmail.com    | 33   | true      | 13              | franciscoupdate@gmail.com | 44          |

  @api @APIOrbi
  Scenario Outline: Update User - Email in incorrect format
    When Resquest Method POST Access Token "<username>" "<password>"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "<name>" "<email>" <age> - Return Id
    Then Validation Response Code 201
    Then Validation Response Time
    Then Validate JSON Response Create User "<name>" "<email>" <age>
    When Resquest Method GET All users
    Then Validate record within JSON Response list - All Users "<name>" "<email>" <age>
    When Validate User by ID "<name>" "<email>" <age> "<isActive>" - User by ID
    Then Validation Response Code 200
    When Update user "<nameUpdate>" "<emailUpdate>" <ageUpdate>
    Then Validation Response Code 400
    Then Validation Response Error - Update User
    Examples:
      | username  | password | name         | email                  | age  | isActive  | nameUpdate      | emailUpdate              |  ageUpdate  |
      | admin     | password | francisco    | francisco@gmail.com    | 33   | true      | franciscoUpdate | franciscoupdategmail.com | 44          |


  @api @APIOrbi
  Scenario Outline: Update User - Field Email Numeric
    When Resquest Method POST Access Token "<username>" "<password>"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "<name>" "<email>" <age> - Return Id
    Then Validation Response Code 201
    Then Validation Response Time
    Then Validate JSON Response Create User "<name>" "<email>" <age>
    When Resquest Method GET All users
    Then Validate record within JSON Response list - All Users "<name>" "<email>" <age>
    When Validate User by ID "<name>" "<email>" <age> "<isActive>" - User by ID
    Then Validation Response Code 200
    When Update user "<nameUpdate>" <emailUpdate> <ageUpdate> - Field Email Numeric
    Then Validation Response Code 400
    Then Validation Response Error - Update User
    Examples:
      | username  | password | name         | email                  | age  | isActive  | nameUpdate      | emailUpdate               |  ageUpdate  |
      | admin     | password | francisco    | francisco@gmail.com    | 33   | true      | franciscoUpdate | 13                        | 44          |


  @api @APIOrbi
  Scenario Outline: Update User - Email NULL
    When Resquest Method POST Access Token "<username>" "<password>"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "<name>" "<email>" <age> - Return Id
    Then Validation Response Code 201
    Then Validation Response Time
    Then Validate JSON Response Create User "<name>" "<email>" <age>
    When Resquest Method GET All users
    Then Validate record within JSON Response list - All Users "<name>" "<email>" <age>
    When Validate User by ID "<name>" "<email>" <age> "<isActive>" - User by ID
    Then Validation Response Code 200
    When Update user "<nameUpdate>" "<emailUpdate>" <ageUpdate>
    Then Validation Response Code 400
    Then Validation Response Error - Update User
    Examples:
      | username  | password | name         | email                  | age  | isActive  | nameUpdate      | emailUpdate               |  ageUpdate  |
      | admin     | password | francisco    | francisco@gmail.com    | 33   | true      | franciscoUpdate |                           | 44          |



  @api @APIOrbi
  Scenario Outline: Update User - Field Age String
    When Resquest Method POST Access Token "<username>" "<password>"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "<name>" "<email>" <age> - Return Id
    Then Validation Response Code 201
    Then Validation Response Time
    Then Validate JSON Response Create User "<name>" "<email>" <age>
    When Resquest Method GET All users
    Then Validate record within JSON Response list - All Users "<name>" "<email>" <age>
    When Validate User by ID "<name>" "<email>" <age> "<isActive>" - User by ID
    Then Validation Response Code 200
    When Update user "<nameUpdate>" "<emailUpdate>" "<ageUpdate>" - Age String
    Then Validation Response Code 400
    Then Validation Response Error - Update User

    Examples:
      | username  | password | name         | email                  | age  | isActive  | nameUpdate      | emailUpdate               |  ageUpdate  |
      | admin     | password | francisco    | francisco@gmail.com    | 33   | true      | franciscoUpdate | franciscoupdate@gmail.com | 44          |



  @api @APIOrbi
  Scenario Outline: Delete User Successful
    When Resquest Method POST Access Token "<username>" "<password>"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "<name>" "<email>" <age> - Return Id
    Then Validation Response Code 201
    Then Validation Response Time
    Then Validate JSON Response Create User "<name>" "<email>" <age>
    When Delete User
    Then Validate JSON Response "<name>" "<email>" <age> "<isActiveDelete>"
    Examples:
      | username  | password | name         | email                  | age  | isActive  | nameUpdate      | emailUpdate               |  ageUpdate  | isActiveDelete |
      | admin     | password | francisco    | francisco@gmail.com    | 33   | true      | franciscoUpdate | franciscoupdate@gmail.com | 44          | false          |


  @api @APIOrbi
  Scenario Outline: Delete User - Non-Existing ID
    When Resquest Method POST Access Token "<username>" "<password>"
    Then Validation Response Code 201
    Then Validation Response Time
    And Save access_token
    When Resquest Method POST Create User "<name>" "<email>" <age> - Return Id
    Then Validation Response Code 201
    Then Validation Response Time
    Then Validate JSON Response Create User "<name>" "<email>" <age>
    When Resquest Method GET All users
    When Validate User by ID "<name>" "<email>" <age> "<isActive>" - User by ID
    Then Validation Response Code 200
    When Update user "<nameUpdate>" "<emailUpdate>" <ageUpdate>
    Then Validation Response Code 200
    When Delete User <id> - Non Existing ID
    Then Validation Response Code 404
    Then Validation Response Error - Not Found
    Examples:
      | username  | password | name         | email                  | age  | isActive  | nameUpdate      | emailUpdate               |  ageUpdate  | id          |
      | admin     | password | francisco    | francisco@gmail.com    | 33   | true      | franciscoUpdate | franciscoupdate@gmail.com | 44          | 9999        |









