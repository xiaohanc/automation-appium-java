# Reports Guide


Each action invoked upon the elements of a PageObjects will be logged to the
report's Test Body in the following format:

-*The Action*- followed by -*The Element*- and an Screenshot of the device

I.E.:
 ```java
PageFactory.WelcomeOverlay().skipButton.click();
```
 Will generate 2 Outputs:
 1)  Attempting to click on id: com.vice.viceforandroid:id/textview_skip_btn
 2)  Clicked successful on id: com.vice.viceforandroid:id/textview_skip_btn
 
 Each of these logs will have its own screenshot
 
 
After Running 
```bash
mvn clean test
```
To  view the report
```bash
mvn allure:serve
```

To generate the report
```bash
mvn allure:report
```
The report will be generated under:
```bash
target/allure-report
```

