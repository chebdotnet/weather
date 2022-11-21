# Weather service

## Running the project

1.  On local workstation
* run command 'docker-compose up' to launch mongo database instance, the db instance will be bound to port 27017
* run gradle command 'gradlew clean build'
* start the application with active profile -- local: java -jar weather-0.0.1-SNAPSHOT.jar
  --spring.profiles.active=local
* call the endpoint http://localhost:9091/weather from browser, curl or postman either

2.  On remote server

* confirm mongo db instance localhost:27017 is working on the server and confirms credentials provided in
  application.yaml
* deploy jar file depending on your server settings or execute a command: java -jar weather-0.0.1-SNAPSHOT.jar
* call the endpoint http://[your-server-address]:9091/weather from browser, curl or postman either on your client
  machine

## Technology stack
1. Java 17 -- Eclipse Adoptium jdk-17.0.2.8-hotspot
2. Application framework - SpringBoot 2.7.5
3. Package principle - Package by Feature
4. Api Layer - WebReactive
5. DB Layer - Mongo DB
6. Libraries - testcontainers, masptruct, lombok
7. Dependency management and build automation tool - Gradle
8. Testing - @WebFluxTest for api layer, @DataMongoTest for repositories layer, unit tests for dedicated classes

## 3-d party integrations
1. [KeyCdn service](https://tools.keycdn.com/) - to get geo data by ip address
2. [OpenWeatherMap](https://openweathermap.org/api) - to get weather conditions by latitude and longitude
3. [AmazonAws](https://checkip.amazonaws.com) - to get public ip address in case of application launching on local workstation

## Functional requirements
Implement a RESTful web service that would handle GET requests to path “weather” by returning the
weather data determined by IP of the request originator.
Upon receiving a request, the service should perform a geolocation search using a non-commercial, 3rd party
IP to location provider.
Having performed the reverse geo search service should use another non-commercial, 3rd party service to
determine current weather conditions using the coordinates of the IP.

## Non-functional requirements
1. Test coverage should be not less than 80%
2. Implemented web service should be resilient to 3rd party service unavailability
3. Data from 3rd party providers should be stored in a database
4. An in-memory cache should be used as the first layer in data retrieval
5. DB schema should allow a historical analysis of both queries from a specific IP and of weather
conditions for specific coordinates
6. DB schema versioning should be implemented
