# Test Automation Framework with Java, Appium and Maven

## Overview
[Startup Docs](docs/startup_guide.md)

## Organization

This repo contains an automated framework to run tests against Android Devices

    src/main    <- Contains all the framework Logic
    src/test    <- Contains all the Automated Tests
    pom.xml     <- Maven dependencies

# Writing Tests

## Test Classes

All Test Classes must inherit from BaseTest.

```Java
class MyTestClass extends BaseTest {
   @AfterClass
      public static void tearDownClass() {
          Appium.killServer(appiumProcess);
          Driver.freeUDID(UDID);
      }
}
```

In addition to that, all Test Classes must implement tearDownClass method
```Java
 class MyTestClass extends BaseTest {
    @AfterClass
    public static void tearDownClass() {
       Appium.killServer(appiumProcess);
       Driver.freeUDID(UDID);
   }
 }
```

The tearDownClass method will be invoked once all the Test Methods are executed,
it closes the Appium Process created and also frees the Android Device so further test
can using


## Test Methods
Test methods consists of a class method with the @Test annotation
```Java
   @Test
   public void myTestMethod() {
    
   }
```

Here we can use the PageObjects in the following form:
```Java
   @Test
   public void myTestMethod() {
    PageFactory.WelcomeOverlay().waitForViewToLoad(driver);
    PageFactory.WelcomeOverlay().skipButton.click();
   }
```

The previous test waits for the WelcomeOverlay to be present 
and then clicks on the skip button

