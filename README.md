# takehome

## Java Web Service using Spring Boot, Gradle and Docker
REST API Service using Spring Boot and interacting with graphql 

### Build and run
#### Configurations

Open the `Dockerfile` file and set the port to use in the container by modifying the EXPOSE field( 8080 by default ) .

#### Prerequisites

- Java 17
- Gradle
- Maven Docker

#### From terminal

Go on the project's root folder, then build the project using gradle. The following line works on mac :

    $ ./gradlew build 
    
Create a docker image

    $ docker build -t <image-name> .
    
Create and run a container for the image

    $ docker run --name <container-name> -p <local-desired-port>:8080 <image-name>

#### From Eclipse (Spring Tool Suite)

Import as *Existing Gradle Project* and run it as *Spring Boot App*.

### Run

- Run the application and go to http://localhost:[local-desired-port]/continent-api/continents
- Use the following urls to invoke controllers methods and see the interactions
  with graphql service:
    
    * `http://localhost:[local-desired-port]/continent-api/continents?countryCodes=US`: get a person by id.
    
    * `http://localhost:[local-desired-port]/continent-api/continents?countryCodes=US&countryCodes=BE&countryCodes=CA&countryCodes=BE&countryCodes=CN&countryCodes=KH`:  ex. you can add more country codes to the request.
   
