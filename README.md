# Best Vehicle Type Skill

Alexa Skill to answer a question like "Womit fahre ich am besten nach {cityName}?".
A possible answer will be

* Fahre mit einem **Auto** weil es **extrem** regnen wird.
* Fahre mit einem **Auto** weil es **moderat** regnen wird.
* Fahre mit einem **Bus** weil es **leicht** regnen wird.
* Fahre mit einem **Fahrrad** weil es **nicht** regnen wird.


## build

`mvn assembly:assembly -DdescriptorId=jar-with-dependencies -DskipTests=true package`

## start

`java -jar target/best-vehicle-type-skill-jar-with-dependencies.jar Berlin` (not working yet)

## references

* OpenWeatherMap for the weather forecast<br>
http://openweathermap.org/

* List of 3000 largest cities<br>
http://data.mongabay.com/cities_pop_01.htm