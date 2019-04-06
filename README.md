# My Cards - API

[![CircleCI](https://circleci.com/gh/notabilia/my-cards-api.svg?style=svg)](https://circleci.com/gh/notabilia/my-cards-api)

Java Spring application which allows users to:
 
 - Perform health checks on the service
 - Create and manage their account
 - Authenticate with the service
 - Create and manage their cards
 
 ## Build
 
 `mvn clean verify -Dversion=<X>`
 
 ## Test & Reports
 
 ```
 mvn test -Dversion=<X>
 mvn surefire-report:report-only -Dversion=<X>
 mvn site -DgenerateReports=false -Dversion=<X>
 ```
 
 ## Run
 
 ### Stand-alone
 
 `mvn spring-boot:run`
 
 ### Packaged
 
 `java -jar ${projectDir}/target/my-cards-backend-<X>.jar`
 
 ## Interaction
 
 ### Local
 
 [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html#/)