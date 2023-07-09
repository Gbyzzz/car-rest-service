# Car REST Service

#### Build and run

To use this app you should install and launch Docker first.
Then execute:

'docker compose up' in project's main folder

Or install PostgreSQL, add database with name 'car_db'
and set password for user 'postgres' as 'postgres'. Then execute:
1) gradle build
2) java -jar build/libs/car-rest-service-0.0.1-SNAPSHOT.jar

API docs: http://localhost:8080/v3/api-docs

To test API go to http://localhost:8080/swagger-ui/index.html

To authorize press on Authorize button and input login and password:

Login:<br/>
admin@car.com<br/>
admin1@car.com<br/>
staff@car.com<br/>
customer@car.com<br/>

Password for all:
132435

Admin and Admin1 have all permissions, Staff can make crud operations to cars.
Customer can only read cars.