
[![CircleCI](https://circleci.com/gh/Vignesh-Durairaj/Spartez.svg?style=svg)](https://circleci.com/gh/Vignesh-Durairaj/Spartez) 
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/26351e02f2c5401e975281183b73387b)](https://app.codacy.com/app/Vignesh-Durairaj/Spartez?utm_source=github.com&utm_medium=referral&utm_content=Vignesh-Durairaj/Spartez&utm_campaign=Badge_Grade_Dashboard)

**Coding challenges for SPARTEZ interview**

Below are the set of challenges thrown at me as a part of interview process for Spartez @ Poland. The challenge consists of three coding challenges as below

1. A warm-up program to get familiarize with the check-out process and the IDE set-up (I used Eclipse Oxygen in my case)

2. A program to find the *index of the last occurrence* of a sub-array from a main array and to return *-1* for other failing cases

3. A program to decode a string into a MAP where each key value pair is separated by a *&* symbol and the key and value themselves got separated by a *=* symbol.

The solutions for all the three were provided using `Java 8`.

* For challenge 1 & 2 `Java 8 Streams` are heavily used to arrive at a solution
* For the 3rd challenge `conventional Java loops` are used.
* The application is configured for Maven and the below commands will be used to perform the individual tasks

For Compiling

```
cd <path to the source>
mvn clean
```

For testing

```
cd <path to the source>
mvn test
```

For building the JAR

```
cd <path to the source>
mvn install
```