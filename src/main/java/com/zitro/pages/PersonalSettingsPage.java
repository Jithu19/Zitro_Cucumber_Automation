package com.zitro.pages;

import com.zitro.common.GeneralUtils;
import io.cucumber.java.Scenario;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PersonalSettingsPage {

    GeneralUtils generalUtils;
    Scenario scn;
    WebDriver driver;
    UserHomePage userHomePage;
    ChangeSecurityQuestionPage changeSecurityQuestionPage;
    ChangeNewsLetterSettingsPage changeNewsLetterSettingsPage;

    public PersonalSettingsPage(WebDriver driver, Scenario scn) {
        this.driver = driver;
        this.generalUtils = new GeneralUtils();
        PageFactory.initElements(driver, this);
        this.scn = scn;
        this.userHomePage = new UserHomePage(driver, scn);
        this.changeSecurityQuestionPage = new ChangeSecurityQuestionPage(driver, scn);
        this.changeNewsLetterSettingsPage = new ChangeNewsLetterSettingsPage(driver, scn);
    }

    @FindBy(how = How.XPATH, xpath = "//h1[text()='Personal settings']")
    WebElement PersonalSettingsPage_Header;
    @FindBy(how = How.XPATH, xpath = "//button[text()='Change security question']")
    WebElement PersonalSettingsPage_ChangeSecurityQuestion_Button;
    @FindBy(how = How.XPATH, xpath = "//button[@class='c-btn c-btn--secondary c-btn--sm c-btn--block o-ellipsis js-newsletterModalBtn']")
    WebElement PersonalSettingsPage_ChangeNewsLetterConsent_Button;

    @Step("Navigate to Change security question")
    public void navigateToChangeSecurityQuestion()
    {
        generalUtils.clickElement(userHomePage.UserHomePage_userName, "User name icon");
        generalUtils.clickElement(userHomePage.UserHomePage_userName_PersonalData, "Personal Data icon");
        Assert.assertTrue(generalUtils.verifyObjectIsDisplayed(PersonalSettingsPage_Header, driver), "Personal Settings screen is not opened...!");
        generalUtils.clickElement(PersonalSettingsPage_ChangeSecurityQuestion_Button, "change security question button");
        Assert.assertTrue(generalUtils.verifyObjectIsDisplayed(changeSecurityQuestionPage.ChangeSecurityQuestionPage_Header, driver), "Change security question screen is not loaded..!");

    }

    @Step("Navigate to Change news letter")
    public void navigateToChangeNewsLetter()
    {
        generalUtils.clickElement(userHomePage.UserHomePage_userName, "User name icon");
        generalUtils.clickElement(userHomePage.UserHomePage_userName_PersonalData, "Personal Data icon");
        Assert.assertTrue(generalUtils.verifyObjectIsDisplayed(PersonalSettingsPage_Header, driver), "Personal Settings screen is not opened...!");
        generalUtils.scrollToViewElement(driver, PersonalSettingsPage_ChangeNewsLetterConsent_Button, "change security question button");
        generalUtils.clickElement(PersonalSettingsPage_ChangeNewsLetterConsent_Button, "change security question button");
        Assert.assertTrue(generalUtils.verifyObjectIsDisplayed(changeNewsLetterSettingsPage.ChangeNewsLetterSettingsPage_Header, driver), "Change news letter screen is not loaded..!");
    }
}
