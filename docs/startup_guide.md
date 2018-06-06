# Startup Guide

### Computer Prep

- Install [JDK 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
- Install [Maven](https://maven.apache.org/install.html)
- Install [NodeJs 8 and NPM](https://nodejs.org)
- Install Appium
```bash
npm install -g appium
```
- Install Maven dependencies 
```bash
mvn dependency:purge-local-repository clean install
```

### Running Tests
```bash
mvn test
```

Clean Run
```bash
mvn clean test
```