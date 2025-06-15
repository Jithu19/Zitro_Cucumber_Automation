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

public class ChangeNewsLetterSettingsPage {

    GeneralUtils generalUtils;
    Scenario scn;
    WebDriver driver;
    HomePage homePage;

    public ChangeNewsLetterSettingsPage(WebDriver driver, Scenario scn) {
        this.driver = driver;
        this.scn = scn;
        this.generalUtils = new GeneralUtils();
        PageFactory.initElements(driver, this);
        this.homePage = new HomePage(driver, scn);
    }

    @FindBy(how = How.XPATH, xpath = "//div[contains(text(),'Change newsletter settings')]")
    WebElement ChangeNewsLetterSettingsPage_Header;
    @FindBy(how = How.XPATH, xpath = "//input[@name='receiveEmail' and @value='true']")
    WebElement ChangeNewsLetterSettingsPage_enable;
    @FindBy(how = How.XPATH, xpath = "//input[@name='receiveEmail' and @value='false']")
    WebElement ChangeNewsLetterSettingsPage_disable;
    @FindBy(how = How.XPATH, xpath = "//button[contains(text(),'Confirm')]")
    WebElement ChangeNewsLetterSettingsPage_ConfirmButton;
    @FindBy(how = How.XPATH, xpath = "//i[@class='o-titled-icon__icon o-titled-icon_" +
            "_icon--start c-icon c-icon--38 c-icon--cross-red']")
    WebElement NotSubscribedIndicator;

    @Step("change news letter consent and save")
    public void change_NewsLetterConsent() {
        generalUtils.waitUntilObjectLoads(driver, NotSubscribedIndicator, 10, "Register new user screen");
        generalUtils.waitUntilObjectLoads(driver, ChangeNewsLetterSettingsPage_ConfirmButton, 5, "Register new user screen");
        if (generalUtils.verifyObjectIsDisplayed(NotSubscribedIndicator, driver)){
            generalUtils.clickElement(ChangeNewsLetterSettingsPage_enable, "Enable news letter icon");
            System.out.println("news letters are enabled..!");
        } else {
            generalUtils.clickElement(ChangeNewsLetterSettingsPage_disable, "Disable news letter icon");
            System.out.println("news letters are disabled..!");
        }
        generalUtils.takeScreenSnap("changed_NewsLetter_Settings", scn, driver);
        generalUtils.clickElement(ChangeNewsLetterSettingsPage_ConfirmButton, "confirm button");
        System.out.println("News letter consent change confirmed by clicking on the button..!");
        generalUtils.waitForSeconds(3);
    }

    @Step("validation of change news letter is successful")
    public void newsLetterConsentUpdateSuccessValidation() {
        Assert.assertFalse(generalUtils.verifyObjectIsDisplayed(ChangeNewsLetterSettingsPage_Header, driver), "Change news letter is not successful.");
    }
}