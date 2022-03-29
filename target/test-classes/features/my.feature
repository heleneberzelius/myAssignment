Feature: infoFields

  Background:
  Create and login to email account with at least two different browsers

  Scenario Outline: Create and login to emailaccount
    Given I have open "<browser>" browser
    Given enter emailaddress "<email>"
    And enter username "<username>"
    And enter password "<password>"
    When click Sign Up
    Then account created "<result>" with "<status>"


    Examples:
      | browser | email             | username                                                                                                       | password    | result                                                                             | status |
      | chrome  | newEmail          | newUser                                                                                                        | Hejsvejs1!  | Check your email                                                                   | ok     |
      | edge    | minmail@gmail.com | Julius9090Julius9090Julius9090Julius9090Julius9090Julius9090Julius9090Julius9090Julius9090Julius9090Julius9090 | GoBananas1! | Enter a value less than 100 characters long                                        | failed |
      | chrome  | minmail@mail.se   | Julius9090                                                                                                     | Hejsvejs1!  | Another user with this username already exists. Maybe it's your evil twin. Spooky. | failed |
      | edge    |                   | Aladdin99                                                                                                      | Aladdin99!  | Please enter a value                                                               | failed |


