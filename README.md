# Test Automation Framework with Java, Appium and Maven

## Overview
- [Startup Docs](docs/startup_guide.md)
- [Reports](docs/reports_guide.md)
- [Troubleshooting](#troubleshooting)

## Organization

This repo contains an automated framework to run tests against Android Devices

    src/main      <- Contains all the framework Logic
    src/test      <- Contains all the Automated Tests
    pom.xml       <- Maven dependencies
    testng.xml    <- Testng config file

# Writing Tests

## Test Classes

All Test Classes must inherit from BaseTest.

```Java
class MyTestClass extends BaseTest {
}
```

In addition to that, all Test Classes must implement tearDownClass method
```Java
 class MyTestClass extends BaseTest {
    @AfterClass(description = "Stopping Appium Server and Freeing Device")
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
    @Test(description = "How the test is going to be named on the Report")
    @Description("a brief description of the test")
    public void myTestMethod() {}
```

Here we can use the PageObjects just like:
```Java
    @Test(description = "How the test is going to be named on the Report")
    @Description("a brief description of the test")
    public void myTestMethod() {
        PageFactory.WelcomeOverlay().waitForViewToLoad(driver);
        PageFactory.WelcomeOverlay().skipButton.click();
    }
```

The previous test waits for the WelcomeOverlay to be present 
and then clicks on the skip button

# Troubleshooting

## Specific Errors

- If  Running 
```
allure:report
``` 
or 
```
allure:serve
```
Throws this error:
```java
Exception in thread "main" java.lang.NoClassDefFoundError: javax/xml/bind/annotation/XmlElement
	at com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector.<init>(JaxbAnnotationIntrospector.java:139)
	at com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector.<init>(JaxbAnnotationIntrospector.java:126)
	at io.qameta.allure.context.JacksonContext.<init>(JacksonContext.java:24)
	at io.qameta.allure.ConfigurationBuilder.useDefault(ConfigurationBuilder.java:50)
	at io.qameta.allure.Commands.createReportConfiguration(Commands.java:158)
	at io.qameta.allure.Commands.generate(Commands.java:63)
	at io.qameta.allure.CommandLine.run(CommandLine.java:129)
	at java.base/java.util.Optional.orElseGet(Optional.java:361)
	at io.qameta.allure.CommandLine.main(CommandLine.java:72)
Caused by: java.lang.ClassNotFoundException: javax.xml.bind.annotation.XmlElement
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:582)
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:185)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:496)
	... 9 more
[ERROR] Can't generate allure report data
org.apache.commons.exec.ExecuteException: Process exited with an error: 1 (Exit value: 1)
    at org.apache.commons.exec.DefaultExecutor.executeInternal (DefaultExecutor.java:404)
    at org.apache.commons.exec.DefaultExecutor.execute (DefaultExecutor.java:166)
    at org.apache.commons.exec.DefaultExecutor.execute (DefaultExecutor.java:153)
    at io.qameta.allure.maven.AllureCommandline.execute (AllureCommandline.java:79)
    at io.qameta.allure.maven.AllureCommandline.generateReport (AllureCommandline.java:49)
    at io.qameta.allure.maven.AllureGenerateMojo.generateReport (AllureGenerateMojo.java:189)
    at io.qameta.allure.maven.AllureGenerateMojo.executeReport (AllureGenerateMojo.java:128)
    at org.apache.maven.reporting.AbstractMavenReport.generate (AbstractMavenReport.java:255)
    at org.apache.maven.reporting.AbstractMavenReport.execute (AbstractMavenReport.java:143)
    at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo (DefaultBuildPluginManager.java:137)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:208)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:154)
    at org.apache.maven.lifecycle.internal.MojoExecutor.execute (MojoExecutor.java:146)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:117)
    at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject (LifecycleModuleBuilder.java:81)
    at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build (SingleThreadedBuilder.java:56)
    at org.apache.maven.lifecycle.internal.LifecycleStarter.execute (LifecycleStarter.java:128)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:305)
    at org.apache.maven.DefaultMaven.doExecute (DefaultMaven.java:192)
    at org.apache.maven.DefaultMaven.execute (DefaultMaven.java:105)
    at org.apache.maven.cli.MavenCli.execute (MavenCli.java:956)
    at org.apache.maven.cli.MavenCli.doMain (MavenCli.java:290)
    at org.apache.maven.cli.MavenCli.main (MavenCli.java:194)
    at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0 (Native Method)
    at jdk.internal.reflect.NativeMethodAccessorImpl.invoke (NativeMethodAccessorImpl.java:62)
    at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke (DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke (Method.java:564)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced (Launcher.java:289)
    at org.codehaus.plexus.classworlds.launcher.Launcher.launch (Launcher.java:229)
    at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode (Launcher.java:415)
    at org.codehaus.plexus.classworlds.launcher.Launcher.main (Launcher.java:356)
```

Check your Java Version, allure is not compatible with Java 9, if you installed
Java 8 make sure to set it as your current version by running:
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)
```