Feature: Smoke
  As a student
  I aim at demonstrating my skills of writing automation tests
  By checking that Lorem Ipsum site works correctly

  Scenario Outline: Check the word presence in the first paragraph
    Given User opens '<homePage>' page
    And User switches to Russian language
    Then User checks the first paragraph contains the '<word>'

    Examples:
      | homePage            | word |
      | https://lipsum.com/ | рыба |

  Scenario Outline: Check that default setting result in text starts with Lorem ipsum
    Given User opens '<homePage>' page
    And User clicks on 'Generate Lorem Ipsum'
    Then User checks the first paragraph starts with the '<sentence>'

    Examples:
      | homePage            | sentence                                                |
      | https://lipsum.com/ | Lorem ipsum dolor sit amet, consectetur adipiscing elit |

  Scenario Outline: Check that Lorem Ipsum is generated with correct size
    Given User opens '<homePage>' page
    And User clicks on 'Words' button
    And User inputs '<number>' into the number field
    When User clicks on 'Generate Lorem Ipsum'
    And User checks the text contains expected '<amountOfWords>' amount of words
    And User opens '<homePage>' page
    And User clicks on 'Bytes' button
    And User inputs '<number>' into the number field
    When User clicks on 'Generate Lorem Ipsum'
    Then User checks the text contains expected '<amountOfBytes>' amount of words of bytes


    Examples:
      | homePage            | number | amountOfWords | amountOfBytes |
      | https://lipsum.com/ | 10     | 10            | 10            |
      | https://lipsum.com/ | 1      | 2             | 3             |
      | https://lipsum.com/ | 0      | 5             | 5             |
      | https://lipsum.com/ | 5      | 5             | 5             |
      | https://lipsum.com/ | 20     | 20            | 20            |

  Scenario Outline: Check that unchecking "start with Lorem Ipsum" checkbox works correctly
    Given User opens '<homePage>' page
    And User unchecks 'Start with Lorem Ipsum' checkbox
    When User clicks on 'Generate Lorem Ipsum'
    Then User checks that the first paragraph does not contain '<sentence>'

    Examples:
      | homePage            | sentence                                                |
      | https://lipsum.com/ | Lorem ipsum dolor sit amet, consectetur adipiscing elit |

  Scenario Outline: Check that randomly generated text paragraphs contain the word with probability of more than 40%
    Given User opens '<homePage>' page
    And User clicks on 'Generate Lorem Ipsum'
    And User counts number of paragraphs containing the '<word>'
    Then User generates text 10 times and checks the probability
    Examples:
      | homePage            | word |
      | https://lipsum.com/ | lorem |








