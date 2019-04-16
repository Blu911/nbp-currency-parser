## PL Parser NBP
Celem projektu jest przeanalizowanie plików XML zawierających kursy wymiany walut ze strony Narodowego Banku Polskiego; następnie na podstawie parametrów wpisanych przez użytkownika do aplikacji:

* kod waluty
* okres czasu

aplikacja powinna obliczyć i wydrukować:

* średnią cenę zakupu
* odchylenie standardowe kursów sprzedaży

### Zbudowanie programu
mvn clean package

### Uruchomienie
java -jar target/nbp-currency-parser-1.0-SNAPSHOT-jar-with-dependencies.jar **{kod waluty} {data początkowa} {data końcowa}**

Przykład:
java -jar target/nbp-currency-parser-1.0-SNAPSHOT-jar-with-dependencies.jar **EUR 2016-10-01 2016-10-14**

## EN Parser NBP
The goal is to parse XML files with currency exchange rates from the website of National Bank of Poland; then based on the application parameters provided by the user:

* currency code
* time period

the application should calculate and print out:

* average buy price
* sell price standard deviance

### Build
mvn clean package

### Run
java -jar target/nbp-currency-parser-1.0-SNAPSHOT-jar-with-dependencies.jar **{currency code} {start date} {end date}**

Example:
java -jar target/nbp-currency-parser-1.0-SNAPSHOT-jar-with-dependencies.jar **EUR 2016-10-01 2016-10-14**

### Technologie/Technologies:
* Maven
* JUnit
* JavaHamcrest
* jcabi-http
* Lombok
* SLF4J
