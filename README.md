# Zitro_Cucumber_Automation

**Run Guide for the Automation Framework**

**System Requirements**
* Operating System: Windows 10 or 11
* Java Development Kit: JDK 21
* Apache Maven: Version 3.9.10 or higher
* Browser: Mozilla Firefox (required for viewing Allure reports)

**Execution**

Clone this automation framework project in your folder.
1. Navigate to your project directory and open a terminal or Command Prompt window and navigate to the root folder of your automation project.
2. Execute the test command Run the following Maven command to execute your tests with specific tags
	eg: mvn clean test -Dcucumber.filter.tags="@SmokeTest" && D:\Workspace\Git\Projects\Zitro_Cucumber_Automation\allure-commandline\bin\allure.bat generate target\allure-results --clean -o allure-report
	This command will run the 3 automated Smoke test cases in the Chrome browser
3. Once the execution is complete, Allure and Cucumber reports wll be generated.
	Allure report path: D:\Workspace\Git\Projects\Zitro_Cucumber_Automation\allure-report\index.html
	Cucumber report path: D:\Workspace\Git\Zitro_Automation\target\cucumber-reports\Html.html
4. View the reports using firefox.

***Firefox Configuration for Allure Report Access***

To view Allure reports locally in Firefox, you need to disable the strict origin policy:
1. Open Firefox.
2. In the address bar, type: about:config
![image](https://github.com/user-attachments/assets/6efb103c-9cba-4fc5-b8e2-ef854bd7b42e)

3. Click "Accept the Risk and Continue" when prompted.
4. Search for the preference: security.fileuri.strict_origin_policy
![image](https://github.com/user-attachments/assets/22b84744-3e71-4437-9943-dc778bf2221e)

5. Set its value to False.

**Screen shots**

![image](https://github.com/user-attachments/assets/115c6850-2361-416d-8db0-e0a59a345422)
![image](https://github.com/user-attachments/assets/6749e62d-be76-435d-b2b9-5f442ffb54b6)
![image](https://github.com/user-attachments/assets/ccc6b8df-6aed-46c9-98fc-c6694876f0fe)
![image](https://github.com/user-attachments/assets/c8cc994e-8728-44dc-b686-ccf88ea6fc94)
![image](https://github.com/user-attachments/assets/a40e3190-ae55-4cbe-b0e7-1870b5781c0d)
![image](https://github.com/user-attachments/assets/eaee75c5-e66c-46dd-9a7d-bd30035a6f3b)
![image](https://github.com/user-attachments/assets/c5b429a4-aff4-4289-bd8e-0b68b0e10683)
![image](https://github.com/user-attachments/assets/63576cfd-8667-448c-bc02-060bf1504627)

