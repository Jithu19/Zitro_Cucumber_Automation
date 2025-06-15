Zitro_Cucumber_Automation

Run Guide for the Automation Framework
System Requirements
* Operating System: Windows 10 or 11
* Java Development Kit: JDK 21
* Apache Maven: Version 3.9.10 or higher
* Browser: Mozilla Firefox (required for viewing Allure reports)
Execution
Clone this automation framework project in your folder.
1. Navigate to your project directory Open a terminal or Command Prompt window and navigate to the root folder of your automation project.
2. Execute the test command Run the following Maven command to execute your tests with specific tags
	eg: mvn clean test -Dcucumber.filter.tags="@SmokeTest" && D:\Workspace\Git\Projects\Zitro_Cucumber_Automation\allure-commandline\bin\allure.bat generate target\allure-results --clean -o allure-report
	This command will run the 3 automated Smoke test cases in the Chrome browser
3. Once the execution is complete, Allure and Cucumber reports wll be generated.
	Allure report path: D:\Workspace\Git\Projects\Zitro_Cucumber_Automation\allure-report\index.html
	Cucumber report path: D:\Workspace\Git\Zitro_Automation\target\cucumber-reports\Html.html
4. View the reports using firefox.
Firefox Configuration for Allure Report Access
To view Allure reports locally in Firefox, you need to disable the strict origin policy:
1. Open Firefox.
2. In the address bar, type: about:config

3. Click "Accept the Risk and Continue" when prompted.
4. Search for the preference: security.fileuri.strict_origin_policy

5. Set its value to False.
Screen shots








