package com.zitro.pages;

import com.zitro.common.GeneralUtils;
import com.zitro.common.PropertyUtils;
import io.cucumber.java.Scenario;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ChangeSecurityQuestionPage {

    GeneralUtils generalUtils;
    Scenario scn;
    WebDriver driver;
    HomePage homePage;

    public ChangeSecurityQuestionPage(WebDriver driver, Scenario scn) {
        this.driver = driver;
        this.scn = scn;
        this.generalUtils = new GeneralUtils();
        PageFactory.initElements(driver, this);
        this.homePage = new HomePage(driver, scn);
    }

    @FindBy(how = How.XPATH, xpath = "//div[contains(text(),'Change security question')]")
    WebElement ChangeSecurityQuestionPage_Header;
    @FindBy(how = How.XPATH, xpath = "//select[@name='security-question']")
    WebElement ChangeSecurityQuestionPage_SecurityQuestion_dropDown;
    @FindBy(how = How.XPATH, xpath = "//input[@name='security-answer']")
    WebElement ChangeSecurityQuestionPage_SecurityAnswer;
    @FindBy(how = How.XPATH, xpath = "//input[@name='new-password']")
    WebElement ChangeSecurityQuestionPage_password;
    @FindBy(how = How.XPATH, xpath = "//button[text()='Save changes']")
    WebElement ChangeSecurityQuestionPage_SaveChanges_Button;
    @FindBy(how = How.XPATH, xpath = "//div[text()='The security question and answer have been changed.']")
    WebElement ChangeSecurityQuestionPage_saveConfirmation;

    @Step("change security question and save changes")
    public void changeSecurityQuestionAndSaveChanges() {
        Assert.assertTrue(generalUtils.verifyObjectIsDisplayed(ChangeSecurityQuestionPage_Header, driver), "Change security question screen is not loaded..!");
        generalUtils.selectDropdownByText(ChangeSecurityQuestionPage_SecurityQuestion_dropDown, "What is the make of your first car?", "Drop down: Security question");
        generalUtils.setText(driver, ChangeSecurityQuestionPage_SecurityAnswer, "Suzuki", "Security answer");
        generalUtils.setText(driver, ChangeSecurityQuestionPage_password, PropertyUtils.readGolbalData("password"), "Security question: Password");
        generalUtils.clickElement(ChangeSecurityQuestionPage_SaveChanges_Button, "Save changes button");

    }

    @Step("validate security question confirmation")
    public void validateSecurityQuestionChangeSuccessful() {
        Assert.assertTrue(generalUtils.verifyObjectIsDisplayed(ChangeSecurityQuestionPage_saveConfirmation, driver), "Security question saved confirmation message not displayed..!");
        generalUtils.takeScreenSnap("saveSecurityQuestionConfirmation", scn, driver);
    }
}
