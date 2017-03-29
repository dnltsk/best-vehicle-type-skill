# Best Vehicle Type Skill

Alexa Skill to answer a question like "Womit fahre ich am besten nach {cityName}?".
A possible answer will be

* Fahre mit dem **Auto** weil es **extrem** regnen wird.
* Fahre mit dem **Auto** weil es **moderat** regnen wird.
* Fahre mit dem **Bus** weil es **leicht** regnen wird.
* Fahre mit dem **Fahrrad** weil es **nicht** regnen wird.


## build

`mvn clean package`

## start

`export OWM_API_KEY=YOUR_OPEN_WEATHER_MAP_API_KEY && java -jar target/best-vehicle-type-skill-jar-with-dependencies.jar Reading,UK`

## references

* OpenWeatherMap for the weather forecast<br>
http://openweathermap.org/

* List of 3000 largest cities<br>
http://data.mongabay.com/cities_pop_01.htm