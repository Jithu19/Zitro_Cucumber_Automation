package com.zitro.pages;

import com.zitro.common.EmailUtils;
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

public class RegistrationPage {

    GeneralUtils generalUtils;
    Scenario scn;
    WebDriver driver;
    HomePage homePage;

    String emailCreated;
    String userNameCreated;

    public RegistrationPage(WebDriver driver, Scenario scn) {
        this.driver = driver;
        this.scn = scn;
        this.generalUtils = new GeneralUtils();
        PageFactory.initElements(driver, this);
        this.homePage = new HomePage(driver, scn);
    }

    @FindBy(how = How.XPATH, xpath = "//header//div[text()='Registration']")
    WebElement Registration_Page_Header;
    @FindBy(how = How.XPATH, xpath = "//input[@name='email']")
    WebElement Registration_Email;
    @FindBy(how = How.XPATH, xpath = "//input[@name='nickname']")
    WebElement Registration_nickName;
    @FindBy(how = How.XPATH, xpath = "//input[@name='password']")
    WebElement Registration_Password;
    @FindBy(how = How.XPATH, xpath = "//select[@name='day']")
    WebElement Registration_DOB_Day;
    @FindBy(how = How.XPATH, xpath = "//select[@name='month']")
    WebElement Registration_DOB_month;
    @FindBy(how = How.XPATH, xpath = "//select[@name='year']")
    WebElement Registration_DOB_year;
    @FindBy(how = How.XPATH, xpath = "//div[@class='recaptcha-checkbox-border']")
    WebElement Registration_Captcha_ImNotARobot;
    @FindBy(how = How.XPATH, xpath = "//input[@id='termsAccept']")
    WebElement Registration_TermsAgreement_Checkbox;
    @FindBy(how = How.XPATH, xpath = "//button[text()='Begin adventure']")
    WebElement Registration_Register_BeginAdventure_Button;
    @FindBy(how = How.XPATH, xpath = "//header/div[text()='Confirm your e-mail address']")
    WebElement Registration_Confirmation_Email_Header;

    @Step("Register new user")
    public void RegisterUser() {
        emailCreated = EmailUtils.generateEmail();
        userNameCreated = EmailUtils.generateUserID();
        generalUtils.clickElement(homePage.Register_Button, "Register button");
        generalUtils.waitUntilObjectLoads(driver, Registration_Page_Header, 15, "Register new user screen");
        generalUtils.waitForSeconds(5);
        generalUtils.setText(driver, Registration_Email, emailCreated, "Email id");
        generalUtils.setText(driver, Registration_nickName, userNameCreated, "Nick Name");
        generalUtils.setText(driver, Registration_Password, PropertyUtils.readGolbalData("password"), "Password");
        generalUtils.selectDropdownValue(Registration_DOB_Day, "19", "DOB Day");
        generalUtils.selectDropdownByText(Registration_DOB_month, "June", "DOB Month");
        generalUtils.selectDropdownValue(Registration_DOB_year, "1995", "DOB Year");
        //CaptchaClick - Fail
        //generalUtils.actionsClick(Registration_Captcha_ImNotARobot,driver,"Captcha");
        System.out.println("Validate captcha manually... Waiting for 15 seconds..");
        generalUtils.waitForSeconds(15);
        generalUtils.clickElement(Registration_TermsAgreement_Checkbox, "Agree to terms");
        generalUtils.waitForSeconds(4);
        generalUtils.takeScreenSnap("registration_UserDataFilled", scn, driver);
        generalUtils.waitForSeconds(4);
        generalUtils.clickElement(Registration_Register_BeginAdventure_Button, "Complete registration button");
        generalUtils.waitUntilObjectLoads(driver, Registration_Confirmation_Email_Header, 10, "Registration confirmation screen");
        Assert.assertTrue(generalUtils.verifyObjectIsDisplayed(Registration_Confirmation_Email_Header, driver), "Registration confirmation screen is not loaded..!");
        generalUtils.takeScreenSnap("registration_Confirmation", scn, driver);
        generalUtils.waitForSeconds(2);
        EmailUtils.storeCreatedCredentials();
    }

}