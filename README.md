rank-interactive-app

run the app:
- mvn clean install
- mvn spring-boot:run

The app is running on port 8081:
I have a swagger page: http://localhost:8081/swagger-ui.html

H2 console: http://localhost:8081/h2
username: sam
url: jdbc:h2:mem:testdb

========================================================================================
I was not sure whether to create a controller for adding a new the player and transaction?
so I have create a Demodata to load data on startup for player and transaction.
