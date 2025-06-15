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

public class UserHomePage {

    GeneralUtils generalUtils;
    Scenario scn;
    WebDriver driver;
    HomePage homePage;

    public UserHomePage(WebDriver driver, Scenario scn) {
        this.driver = driver;
        this.generalUtils = new GeneralUtils();
        PageFactory.initElements(driver, this);
        this.scn = scn;
        this.homePage = new HomePage(driver, scn);
    }

    @FindBy(how = How.XPATH, xpath = "//a[contains(@class,'c-wheel__btn-close')]")
    WebElement UserHomePage_CloseAdwheel;
    @FindBy(how = How.XPATH, xpath = "//a[contains(text(),'Collect')]")
    WebElement UserHomePage_CollectAllButton;
    @FindBy(how = How.XPATH, xpath = "//div[@class='c-bar-status__username']")
    WebElement UserHomePage_userName;
    @FindBy(how = How.XPATH, xpath = "//a[contains(text(),'Personal data')]")
    WebElement UserHomePage_userName_PersonalData;
    @FindBy(how = How.XPATH, xpath = "//div[contains(text(),'Newsletter & Promotions')]")
    WebElement UserHomePage_NewsLetterAndPromotions_Header;
    @FindBy(how = How.XPATH, xpath = "//input[@name='receiveEmail' and @value='false']")
    WebElement UserHomePage_NewsLetterAndPromotions_recieveEmail_NO;
    @FindBy(how = How.XPATH, xpath = "//input[@name='marketingConsent' and @value='false']")
    WebElement UserHomePage_NewsLetterAndPromotions_marketingConsent_NO;
    @FindBy(how = How.XPATH, xpath = "//button[text()='Confirm']")
    WebElement UserHomePage_NewsLetterAndPromotions_ConfirmButton;
    @FindBy(how = How.XPATH, xpath = "//a[contains(text(),'Log out')]")
    WebElement UserHomePage_userName_Logout;


    @Step("validate that the login is successful")
    public void validateUserHomePageLoaded() {
        Assert.assertTrue(generalUtils.verifyObjectIsDisplayed(UserHomePage_userName, driver), "Login failed..!");

        while (isAnyAdElementDisplayed()) {
            manageAdInteractions();
        }
    }

    @Step("logout from Game twist application")
    public void logoutFromGameTwistApplication()
    {
        generalUtils.clickElement(UserHomePage_userName, "User name icon");
        generalUtils.clickElement(UserHomePage_userName_Logout, "Logout icon");
        Assert.assertTrue(generalUtils.verifyObjectIsDisplayed(homePage.Login_Button, driver), "Logout not successful...!");
        generalUtils.takeScreenSnap("GameTwist_HomeScreen_Logout", scn, driver);
        scn.log("Logged out successfully");
    }

    private boolean isAnyAdElementDisplayed() {
        return generalUtils.verifyObjectIsDisplayed(UserHomePage_CloseAdwheel, driver) ||
                generalUtils.verifyObjectIsDisplayed(UserHomePage_CollectAllButton, driver) ||
                generalUtils.verifyObjectIsDisplayed(UserHomePage_NewsLetterAndPromotions_Header, driver);
    }

    private void manageAdInteractions() {
        if (generalUtils.verifyObjectIsDisplayed(UserHomePage_CollectAllButton, driver)) {
            generalUtils.actionsClick(UserHomePage_CollectAllButton, driver, "ad wheel");
        }
        if (generalUtils.verifyObjectIsDisplayed(UserHomePage_CloseAdwheel, driver)) {
            generalUtils.actionsClick(UserHomePage_CloseAdwheel, driver, "ad wheel");
        }
        if (generalUtils.verifyObjectIsDisplayed(UserHomePage_NewsLetterAndPromotions_Header, driver)) {
            generalUtils.clickElement(UserHomePage_NewsLetterAndPromotions_recieveEmail_NO, "Receive email : No");
            generalUtils.clickElement(UserHomePage_NewsLetterAndPromotions_marketingConsent_NO, "Marketing consent : No");
            generalUtils.clickElement(UserHomePage_NewsLetterAndPromotions_ConfirmButton, "News letter consent and promotions confirm button");
        }

    }
}