# Startup Guide

### Computer Prep

- Install [JDK 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
- Install [Maven](https://maven.apache.org/install.html)
- Install [NodeJs and NPM](https://nodejs.org) - *Stable Version, Node 6+*
- Install Appium
```bash
npm install -g appium
```
- Install Maven dependencies 
```bash
mvn dependency:purge-local-repository clean
```

### Running Tests
**Make sure to have the devices ready before running the tests**
```bash
mvn test
```

Clean Run
```bash
mvn clean test
```