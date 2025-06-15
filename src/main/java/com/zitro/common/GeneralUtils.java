package com.zitro.common;

import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.NoSuchElementException;

public class GeneralUtils {

    /******************************************************************
     * Description : Opens the specified URL in the given WebDriver instance.
     * Arguments   : WebDriver driver - WebDriver instance
     *               String url - The URL to be opened
     * Return Value: NA
     ********************************************************************/
    public void openUrl(WebDriver driver, String url) {
        try {
            driver.get(url);
            System.out.println("Navigated to URL: " + url);
        } catch (WebDriverException e) {
            System.out.println("Failed to open URL: " + url + " | " + e.getMessage());
            Assert.fail("Unable to navigate to URL: " + url);
        }
    }

    private byte[] takeScreenSnap(WebDriver driver) {

        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);

    }

    /******************************************************************
     * Description : Takes a screenshot and attaches it to the given
     *               scenario with a descriptive name.
     * Arguments   : String imgDescription - Description for the screenshot
     *               Scenario scn - Cucumber scenario instance
     *               WebDriver driver - WebDriver instance
     * Return Value: NA
     ********************************************************************/
    public void takeScreenSnap(String imgDescription, Scenario scn, WebDriver driver) {
        try {
            String timestamp = GeneralUtils.generateTimeStamp();
            String screenshotName = String.format("%s___%s", imgDescription, timestamp);
            scn.attach(takeScreenSnap(driver), "image/png", screenshotName);
        } catch (JavascriptException e) {
            System.out.println("JavascriptException: Webpage not fully loaded or rendered properly");
            Assert.fail("Unable to take screenshot. Webpage not loaded!");
        } catch (Exception e) {
            System.out.println("Exception while capturing screenshot");
            Assert.fail("Unable to take screenshot due to an unexpected error!");
        }
    }

    /******************************************************************
     * Description : Halts execution for the specified number of seconds.
     * Arguments   : int secondsWait - Time to wait in seconds
     * Return Value: NA
     ********************************************************************/
    public void waitForSeconds(int secondsWait) {
        try {
            long milliseconds = (long) secondsWait * 1000;
            System.out.println("Waiting for seconds..." + secondsWait);
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted during sleep for seconds." + secondsWait);
            Thread.currentThread().interrupt();
            Assert.fail("Thread was interrupted during wait.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while waiting for seconds." + secondsWait);
            Assert.fail("Unexpected exception during wait.");
        }
    }

    /******************************************************************************
     * Description : Verifies if the WebElement is displayed on the webpage
     * Arguments   : WebElement wElement - The element to be verified
     *               WebDriver driver - WebDriver instance to interact with the page
     * Return Value: boolean - true if element is displayed, false otherwise
     ********************************************************************************/
    public boolean verifyObjectIsDisplayed(WebElement wElement, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            wait.until(ExpectedConditions.visibilityOf(wElement));
            boolean isDisplayed = wElement.isDisplayed();
            System.out.println("Element " + (isDisplayed ? "is" : "not") + " displayed: " + wElement);
            return isDisplayed;
        } catch (TimeoutException e) {
            System.out.println("Element not visible after 2 seconds: " + wElement);
            return false;
        } catch (WebDriverException e) {
            System.out.println("Element not displayed: " + wElement);
            return false;
        }
    }

    public boolean verifyObjectIsNotDisplayed(WebElement wElement, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            wait.until(ExpectedConditions.invisibilityOf(wElement));
            System.out.println("Element is not displayed (invisible): " + wElement);
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element is still visible after 2 seconds: " + wElement);
            return false;
        } catch (WebDriverException e) {
            System.out.println("WebDriverException while checking element visibility: " + wElement);
            return false;
        }
    }

    /******************************************************************************
     * Description : To click on an element using Actions class and WebDriver instance
     * Arguments   : WebElement wElement - Element to be clicked
     *               WebDriver driver - WebDriver instance to interact with the browser
     *               String elementInfo - Descriptive name/info for the element (for logging)
     * Return Value: NA
     ********************************************************************************/
    public void actionsClick(WebElement wElement, WebDriver driver, String elementInfo) {
        try {
            waitForSeconds(2);
            Actions action = new Actions(driver);
            action.moveToElement(wElement).click().perform();
            System.out.println("Successfully clicked on the element: " + elementInfo);
        } catch (NoSuchElementException e) {
            System.out.println("Element not found to click: " + elementInfo);
            Assert.fail("Element to click not found: " + elementInfo);

        }
    }

    /******************************************************************************
     * Description : To click on an element using WebDriver and WebElement
     * Arguments   : WebElement wElement - The element to be clicked
     *               String elementInfo - Descriptive name/info for the element (for logging)
     * Return Value: NA
     ********************************************************************************/
    public void clickElement(WebElement wElement, String elementInfo) {
        try {
            wElement.click();
            System.out.println("Clicked on :" + elementInfo);
        } catch (TimeoutException e) {
            System.out.println("Element not visible after 5 seconds: " + wElement);
            Assert.fail("Element not visible: " + elementInfo);
        } catch (NoSuchElementException e) {
            System.out.println("Click failed for :" + elementInfo);
            Assert.fail("Element not found: " + elementInfo);
        }
    }

    /******************************************************************************
     * Description : To scroll into view for the given WebElement using JavaScript
     * Arguments   : WebDriver driver - WebDriver instance to interact with the browser
     *               WebElement wElement - The element to scroll into view
     *               String elementInfo - Descriptive name/info for the element (for logging)
     * Return Value: WebElement - The element passed as argument (for chaining or further actions)
     ********************************************************************************/
    public WebElement scrollToViewElement(WebDriver driver, WebElement wElement, String elementInfo) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wElement);
            System.out.println("Scrolling to view the element: " + elementInfo);
            return wElement;

        } catch (NoSuchElementException e) {
            System.out.println("Failed to scroll to the element: " + elementInfo);
            return null;
        }
    }

    /******************************************************************************
     * Description : To set text in the input WebElement given
     * Arguments   : WebElement wElement - The input WebElement
     *               String valueToSet - The value to enter into the input field
     *               WebDriver driver - WebDriver instance to interact with the browser
     *               String elementInfo - Descriptive name/info for the element (for logging)
     * Return Value: NA
     ********************************************************************************/
    public void setText(WebDriver driver, WebElement wElement, String valueToSet, String elementInfo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(wElement));
            wElement.sendKeys(valueToSet);
            System.out.println("Entered Text: \"" + valueToSet + "\" to element: " + elementInfo);

        } catch (TimeoutException e) {
            System.out.println("Element not visible after 5 seconds: " + wElement);
            Assert.fail("Element not visible: " + elementInfo);
        } catch (NoSuchElementException e) {
            System.out.println("The element to set text doesn't exist: " + elementInfo);
            Assert.fail("The element to set text doesn't exist: " + elementInfo);
        } catch (ElementNotInteractableException e) {
            System.out.println("The element is not interactable: " + elementInfo);
            Assert.fail("The element is not interactable: " + elementInfo);
        }
    }

    /******************************************************************************
     * Description : To generate a 12-digit timestamp string
     *               formatted as yyMMddHHmmss (without any slashes).
     * Arguments   : NA
     * Return Value : String - 12-digit timestamp (format: yyMMddHHmmss)
     ********************************************************************************/
    public static String generateTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        return sdf.format(new java.util.Date()); // Format the current date into a string
    }

    /******************************************************************************
     * Description : To wait until the object loads (becomes visible and interactable)
     * Arguments   : WebDriver driver - WebDriver instance
     *               WebElement wElement - WebElement to wait for
     *               int waitTime - Maximum time to wait in seconds
     *               String elementInfo - Descriptive info about the element for logging purposes
     * Return Value : boolean - true if element is visible, false otherwise
     ********************************************************************************/
    public boolean waitUntilObjectLoads(WebDriver driver, WebElement wElement, int waitTime, String elementInfo) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        try {
            wait.until(ExpectedConditions.visibilityOf(wElement));
            waitForSeconds(2);
            if (!wElement.isDisplayed()) {
                waitForSeconds(2);
                System.out.println("Element hidden. Scrolling to view: " + elementInfo);
                scrollToViewElement(driver, wElement, elementInfo);
                wait.until(ExpectedConditions.visibilityOf(wElement));
            }

            System.out.println("Element is visible: " + elementInfo);
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element not found within timeout: " + elementInfo);
        } catch (NoSuchElementException e) {
            System.out.println("Element not found (No Such Element): " + elementInfo);
        } catch (StaleElementReferenceException e) {
            System.out.println("Element became stale during the process: " + elementInfo);
        } catch (WebDriverException e) {
            System.out.println("WebDriver exception encountered: " + elementInfo);
        }
        return false;
    }

    /******************************************************************************
     * Description : To select a value from a dropdown element.
     * Arguments   : WebElement wElement - WebElement for the dropdown
     *               String valueToSet - Value to be selected from the dropdown
     *               String ElementInfo - Description for logging purposes
     * Return Value : void - Logs success/failure
     ********************************************************************************/
    public void selectDropdownValue(WebElement wElement, String valueToSet, String elementInfo) {
        try {
            Select selectElement = new Select(wElement);
            selectElement.selectByValue(valueToSet);
            System.out.println("Selected value: " + valueToSet + " in dropdown: " + elementInfo);

        } catch (NoSuchElementException e) {
            System.out.println("Dropdown element not found: " + elementInfo);
            Assert.fail("Dropdown element not found: " + elementInfo);
        } catch (ElementNotInteractableException e) {
            System.out.println("Dropdown element not interactable: " + elementInfo);
            Assert.fail("Dropdown element not interactable: " + elementInfo);
        } catch (WebDriverException e) {
            System.out.println("WebDriver exception occurred while interacting with dropdown: " + elementInfo);
            Assert.fail("WebDriver exception occurred while interacting with dropdown: " + elementInfo);
        }
    }

    /******************************************************************************
     * Description : To select a value from a dropdown by visible text.
     * Arguments   : WebElement wElement - WebElement for the dropdown
     *               String valueToSet - The visible text to be selected from the dropdown
     *               String ElementInfo - Description for logging purposes
     * Return Value : void - Logs success/failure and interacts with the dropdown.
     ********************************************************************************/
    public void selectDropdownByText(WebElement wElement, String valueToSet, String elementInfo) {
        try {
            Select selectElement = new Select(wElement);
            selectElement.selectByVisibleText(valueToSet);
            System.out.println("Selected value: " + valueToSet + " from dropdown: " + elementInfo);

        } catch (NoSuchElementException e) {
            System.out.println("Dropdown element not found: " + elementInfo);
            Assert.fail("Dropdown element not found: " + elementInfo);

        } catch (ElementNotInteractableException e) {
            System.out.println("Dropdown element not interactable: " + elementInfo);
            Assert.fail("Dropdown element not interactable: " + elementInfo);

        } catch (WebDriverException e) {
            System.out.println("WebDriverException occurred while interacting with dropdown: " + elementInfo);
            Assert.fail("WebDriverException occurred while interacting with dropdown: " + elementInfo);
        }
    }
}