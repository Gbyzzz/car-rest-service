# Car REST Service

### 4<br/>


#### Build and run

To use this app you should install and launch Docker first.
Then execute:

'docker compose up' in project's main folder

Or install PostgreSQL, add database with name 'car_db'
and set password for user 'postgres' as 'postgres'. Then execute:
1) gradle build
2) java -jar build/libs/car-rest-service-0.0.1-SNAPSHOT.jar


To login send this as json to the address http://localhost:8080/login

{
"username": "login",
"password": "password"
}

You will get a jwt token, which you can use to get access to secured endpoints

Login:<br/>
admin@car.com<br/>
staff@car.com<br/>
antonynewman@gmail.com<br/>
kimberlyfoster@gmail.com<br/>

Password for all:
132435

