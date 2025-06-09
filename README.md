# Java Template for Web Server

## Project Info
<img src="https://i.imgur.com/nPRpD0l.png" />

## Initial Setup
1. Remove .git folder
2. Connect new Repository
3. DB Set <br/>
-> this project use test local DB.
if not set DB in local, cannot build & run this project. <br/>
-> if not use DB, remove DB dependency & db connection

## Convention
1. Java version -> JDK 17
2. Library tool -> Maven
3. DB Tool -> JPA & MySQL

## How To Use
1. Install JDK version 17
2. Install Maven version 3.9.x
3. Install MySQL version 8.0
4. Modify Project Folder Name
5. Add Libraries in pom.xml
6. Set env in application.properties

## Add Libraries with Maven
1. Add Library in pom.xml
```
<dependencies>
  <dependency>
  ...
  </dependency>

  ...

  <!-- ex -->
  <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.3</version>
  </dependency>
</dependencies>
```

2. Install Maven Wrapper
```
mvn wrapper:wrapper
```

3. Install Library
```
./mvnw clean install
```
## Set DB
Database: user
Table: user
```
-- `user`.`user` definition

CREATE TABLE `user` (

`id` bigint NOT NULL AUTO_INCREMENT,

`name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,

`age` int unsigned DEFAULT NULL,

`updatedAt` datetime DEFAULT CURRENT_TIMESTAMP,

`createdAt` datetime DEFAULT CURRENT_TIMESTAMP,

`created_at` datetime(6) DEFAULT NULL,

`updated_at` datetime(6) DEFAULT NULL,

PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

## Run Spring Boot
```
./mvnw spring-boot:run
```

or use tasks.json in VSCode <br/>
Ctrl + Shift + B


## Set Project & Foleder Naming Rules
```
src
└── main
    └── java
        └── com
            └── notepad
                └── notepadjavavsc
                    ├── common
                    │   └── dto
                    │       └── MessagePayloadDto.java
                    │
                    ├── global
                    │   ├── config
                    │   │   └── WebSocketConfig.java
                    │   ├── exception
                    │   │   └── GlobalExceptionHandler.java
                    │   ├── filter
                    │   │   └── RequestLoggingFilter.java
                    │   ├── jwt
                    │   │
                    │   └── logger
                    │       ├── CustomLogger.java
                    │       └── DotenvInitializer.java
                    │
                    ├── hello
                    │   └── controller
                    │       └── HelloController.java
                    │
                    ├── user
                    │   ├── controller
                    │   │   └── UserController.java
                    │   ├── domain
                    │   │   └── User.java
                    │   ├── dto
                    │   │   └── UserDto.java
                    │   ├── repository
                    │   │   └── UserRepository.java
                    │   └── service
                    │       └── UserService.java
                    │
                    └── NotepadJavaVscApplication.java

```
1. General convention
src/java/com

2. Change main project name
ex) /notepadjavavsc

3. Change Main File
ex) NotepadJavaVscApplication.java

4. Folder Name Convention <br/>
global -> global object <br/>
common -> global util object <br/>
user, hello, ... -> local object <br/>

5. Object Folder Name Convention <br/>
controller -> Accept APIs <br/>
service -> Main Logic without DB <br/>
repository -> DB Logic with Query <br/>
domain -> entities with DB <br/>
dto -> interface with Body <br/>

## Change Project Name in pom.xml
```
<artifactId>MAIN_PROJECFT_NAME</artifactId>
<name>MAIN_PROJECFT_NAME</name>
```

## Change Project Name in application.properties
```
spring.application.name=notepad-java-vsc
```

## Change Package from Folder Naming
```
package com.name1.name2;
```

## Compile in IDE
1. Cleanup Project <br/>
<img src="https://i.imgur.com/Dgf0ckU.png" />

2. Compile Project
```
./mvnw compile
```