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

public class LoginPage {

    GeneralUtils generalUtils;
    Scenario scn;
    WebDriver driver;
    HomePage homePage;

    public LoginPage(WebDriver driver, Scenario scn) {
        this.driver = driver;
        this.scn = scn;
        this.generalUtils = new GeneralUtils();
        PageFactory.initElements(driver, this);
        this.homePage = new HomePage(driver, scn);
    }

    @FindBy(how = How.XPATH, xpath = "//header/div/div/div[contains(text(),'Login')]")
    WebElement Login_Header;
    @FindBy(how = How.XPATH, xpath = "//input[@name='username']")
    WebElement Login_Username;
    @FindBy(how = How.XPATH, xpath = "//input[@name='password']")
    WebElement Login_Password;
    @FindBy(how = How.XPATH, xpath = "//button[contains(text(),'Log in')]")
    WebElement Login_Button;

    @Step("login to Game twist application using valid credentials")
    public void loginToGameTwistApplication() {
        generalUtils.clickElement(homePage.Login_Button, "Login Button");
        generalUtils.waitUntilObjectLoads(driver, Login_Header, 5, "Login page screen");
        Assert.assertTrue(generalUtils.verifyObjectIsDisplayed(Login_Header, driver), "Login page is not loaded..!");
        generalUtils.setText(driver, Login_Username, PropertyUtils.readGolbalData("username"), "Username");
        generalUtils.setText(driver, Login_Password, PropertyUtils.readGolbalData("password"), "Password");
        generalUtils.clickElement(Login_Button, "login_btn");
        scn.log("Logged in user credentials : " + PropertyUtils.readGolbalData("username") + " / " + PropertyUtils.readGolbalData("password"));

    }

}