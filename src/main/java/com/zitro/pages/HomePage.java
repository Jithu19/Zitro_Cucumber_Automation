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
public class HomePage {

    GeneralUtils generalUtils;
    Scenario scn;
    WebDriver driver;

    public HomePage(WebDriver driver, Scenario scn) {
        this.driver = driver;
        this.generalUtils = new GeneralUtils();
        PageFactory.initElements(driver, this);
        this.scn = scn;
    }

    @FindBy(how = How.XPATH, xpath = "//a[text()='Login']")
    WebElement Login_Button;
    @FindBy(how = How.XPATH, xpath = "//a[text()='Register']")
    WebElement Register_Button;
    @FindBy(how = How.XPATH, xpath = "//a/img[@title='GameTwist Online Casino']")
    WebElement GameTwist_logo;
    @FindBy(how = How.XPATH, xpath = "//button[contains(text(),'Accept All Cookies')]")
    WebElement GameTwistAcceptAllCookies;

    @Step("validate that the Game Twist home screen is loaded...!")
    public void validateHomeScreenLoadedSuccessfully() {
        Assert.assertTrue(generalUtils.verifyObjectIsDisplayed(GameTwist_logo, driver), "Failed to load the home page of Game Twist");
        generalUtils.takeScreenSnap("GameTwist_HomeScreen", scn, driver);
        scn.log("Home screen loaded successfully");
        acceptAllCookies();
    }

    public void acceptAllCookies() {
        if (generalUtils.verifyObjectIsDisplayed(GameTwistAcceptAllCookies, driver)) {
            generalUtils.clickElement(GameTwistAcceptAllCookies, "Accept all cookies");
            generalUtils.waitForSeconds(5);
            System.out.println("Accepted all cookies.");
        }
    }
}