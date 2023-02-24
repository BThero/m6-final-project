# m6-final-project
My final project for M6 Java Spring. 
Creates an API that allows you to add/update/remove currency conversions and use them to convert a specific amount from one currency to another. 
For now only supports three currencies: Euro, Dollar and Tenge.

The project was bootstapped with https://start.spring.io/ using a Gradle-Groovy build system with the following dependencies:
- Spring Web
- Spring Data JPA
- H2 Database

## How to start
- Clone this Git repository with `git clone https://github.com/BThero/m6-final-project.git`
- The project uses Gradle (https://gradle.org/), so make sure to build the project using `gradlew build`
- The application uses a persisted H2 database with a user `root` and no password by default. If you want to customize these defaults, take a look at an `application.properties` file
- Run the project with `gradlew run`

## How to use
The API will be exposed at `localhost:8080`. You can interact with the API as follows:
<img width="654" alt="image" src="https://user-images.githubusercontent.com/45234955/221206821-1e53b87a-8430-466d-8e66-0e6cb8bff0e6.png">
<img width="654" alt="image" src="https://user-images.githubusercontent.com/45234955/221206987-5d3386f4-dc9e-4fc9-abd8-1cac43db94d1.png">
<img width="654" alt="image" src="https://user-images.githubusercontent.com/45234955/221207073-2fdf101f-24ba-4484-9427-0a1fb9362657.png">
<img width="654" alt="image" src="https://user-images.githubusercontent.com/45234955/221207093-45854f96-55b5-495a-9f36-d4c70e8fac77.png">

Keys `firstCurrency` and `secondCurrency` accept the names of currencies (only Euro, Dollar and Tenge for now). They are **case insensitive**.

Keys `rate` and `amount` accept a single floating point value.

The API will always return `200 OK` as a response. If there was an error, it will be provided in the response message.
