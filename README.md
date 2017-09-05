# Caffeinate Me

[![CircleCI](https://circleci.com/gh/serenity-bdd/serenity-cucumber-starter.svg?style=svg)](https://circleci.com/gh/serenity-bdd/serenity-cucumber-starter)


This project contains exercises for the [Serenity Dojo](https://serenitydojo.teachable.com) 
[BDD with Serenity and Cucumber](https://serenitydojo.teachable.com/p/bdd-with-cucumber-and-serenity) course.

There is a tag for the sample code for each lesson. To 

## Get the code

Git:

    git clone https://github.com/serenity-dojo/caffeinate-me.git
    cd caffeinate-me

## Use Maven

Open a command window and run:

    mvn clean verify

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(CucumberWithSerenity.class)` annotation on the `CucumberTestSuite`
class tells JUnit to kick off Cucumber.

## Use Gradle

Open a command window and run:

    gradlew test aggregate

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(CucumberWithSerenity.class)` annotation on the `CucumberTestSuite`
class tells JUnit to kick off Cucumber.

## Viewing the reports

Both of the commands provided above will produce a Serenity test report in the `target/site/serenity` directory. Go take a look!
