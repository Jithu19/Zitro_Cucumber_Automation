package com.zitro.stepDefinitions;

import com.zitro.common.GeneralUtils;
import com.zitro.common.PropertyUtils;
import com.zitro.pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.BasePage;

public class UserJourneyStepDef{

    private final BasePage basePage;

    public UserJourneyStepDef(BasePage basePage) {
        this.basePage = basePage;
    }

    GeneralUtils generalUtils;
    HomePage homePage;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    UserHomePage userHomePage;
    PersonalSettingsPage personalSettingsPage;
    ChangeSecurityQuestionPage changeSecurityQuestionPage;
    ChangeNewsLetterSettingsPage changeNewsLetterSettingsPage;

    @Given("The GameTwist application is launched")
    public void theGameTwistApplicationIsLaunched() {
        System.out.println("Driver: " + basePage.driver);
        System.out.println("Scenario: " + basePage.scn);
        generalUtils = new GeneralUtils();
        generalUtils.openUrl(basePage.driver, PropertyUtils.readGolbalData("GameTwistURL"));
    }

    @When("The GameTwist home screen is displayed")
    public void theGameTwistHomeScreenIsDisplayed() {
        homePage = new HomePage(basePage.driver,basePage.scn);
        homePage.validateHomeScreenLoadedSuccessfully();
    }

    @Then("Register a new user and record the user credentials for future use")
    public void registerANewUserAndRecordTheUserCredentials() {
        registrationPage = new RegistrationPage(basePage.driver,basePage.scn);
        registrationPage.RegisterUser();
    }

    @Then("Log in using the previously created user credentials")
    public void loginUsingThePreviouslyCreatedUserCredentials() {
        loginPage = new LoginPage(basePage.driver, basePage.scn);
        userHomePage = new UserHomePage(basePage.driver, basePage.scn);
        loginPage.loginToGameTwistApplication();
        userHomePage.validateUserHomePageLoaded();
    }

    @And("Navigate from Personal Data section to update the security question")
    public void navigateToPersonalDataSectionToUpdateSecurityQuestion() {
        personalSettingsPage = new PersonalSettingsPage(basePage.driver, basePage.scn);
        personalSettingsPage.navigateToChangeSecurityQuestion();
    }

    @And("Provide a new answer to the security question and save the changes")
    public void provideNewAnswerAndSaveTheChanges() {
        changeSecurityQuestionPage = new ChangeSecurityQuestionPage(basePage.driver, basePage.scn);
        changeSecurityQuestionPage.changeSecurityQuestionAndSaveChanges();
    }

    @Then("Verify that the security question has been successfully updated")
    public void verifySecurityQuestionUpdated() {
        changeSecurityQuestionPage.validateSecurityQuestionChangeSuccessful();
    }

    @And("Navigate from Personal Data section to update the newsletter consent")
    public void navigateToPersonalDataSectionToUpdateNewsletterConsent() {
        personalSettingsPage = new PersonalSettingsPage(basePage.driver, basePage.scn);
        personalSettingsPage.navigateToChangeNewsLetter();
    }

    @And("Save the changes to the newsletter consent")
    public void saveChangesToNewsletterConsent() {
        changeNewsLetterSettingsPage = new ChangeNewsLetterSettingsPage(basePage.driver, basePage.scn);
        changeNewsLetterSettingsPage.change_NewsLetterConsent();
    }

    @Then("Verify that the newsletter consent has been successfully updated")
    public void verifyNewsletterConsentUpdated() {
        changeNewsLetterSettingsPage.newsLetterConsentUpdateSuccessValidation();
    }

    @Then("Log out the user")
    public void logOutUser() {
        changeNewsLetterSettingsPage.newsLetterConsentUpdateSuccessValidation();
        userHomePage.logoutFromGameTwistApplication();
    }
}