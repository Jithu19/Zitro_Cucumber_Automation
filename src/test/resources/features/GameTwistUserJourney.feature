@SmokeTest
Feature: Sanity Tests for GameTwist Application

  Scenario: Register a new user on GameTwist
    Given The GameTwist application is launched
    When The GameTwist home screen is displayed
    Then Register a new user and record the user credentials for future use

  Scenario: Login and update the security question in GameTwist
    Given The GameTwist application is launched
    When The GameTwist home screen is displayed
    Then Log in using the previously created user credentials
    And Navigate from Personal Data section to update the security question
    And Provide a new answer to the security question and save the changes
    Then Verify that the security question has been successfully updated

  Scenario: Login and update the newsletter consent in GameTwist
    Given The GameTwist application is launched
    When The GameTwist home screen is displayed
    Then Log in using the previously created user credentials
    And Navigate from Personal Data section to update the newsletter consent
    And Save the changes to the newsletter consent
    And Verify that the newsletter consent has been successfully updated
    Then Log out the user