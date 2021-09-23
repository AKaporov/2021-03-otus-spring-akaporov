# Использовать метрики, healthchecks и logfile

### Домашнее задание
Использовать метрики, healthchecks и logfile

Цель: 
- использовать изобилие возможностей Spring Boot Actuator для создания production-grade приложений и микросервисов  

Результат:
- приложение на Spring Boot Actuator.

Требования:
- Подключить Spring Boot Actuator в приложение%
- Включить метрики, healthchecks и logfile;
- Реализовать свой собственный HealthCheck индикатор;
- UI для данных от Spring Boot Actuator реализовывать не нужно;
- Опционально: переписать приложение на HATEOAS принципах с помощью Spring Data REST Repository.

#### Данное задание выполняется на основе одного из реализованных Web-приложений (Spring MVC).

Команды для запуска в браузере (после запуска приложения):
- http://localhost:8080/api/v1/books/
- http://localhost:8080/api/v1/books?BookTitle=School

#### Actuator Endpoints:
Список всех health-индикаторов:
- http://localhost:8080/actuator/health/
Доступ к моему health-индикатору (FillPercentageHealthIndicator):
- http://localhost:8080/actuator/health/fillpercentage  

Для выполнения команд в Terminal в IDEA нужно перед командой указать "curl":
- curl localhost:8080/api/v1/books/ -u admin:password
- curl localhost:8080/api/v1/books?BookTitle=School -u admin:password

If you POST to the /token endpoint with the user user/password:
- curl -XPOST user:password@localhost:8080/api/v1/token
- curl -XPOST admin:password@localhost:8080/api/v1/token
@RequestBody AccountDto:
- curl --data '{"name":"admin", "password":"password"}' -v -X POST -H 'Content-Type:application/json' http://localhost:8080/api/v1/token

Указать файл конфигурации JCL можно следующим образом:
- java -Djava.util.logging.config.file=/absolute/path/to/your/config/file/commons-logging.properties -jar /absolute/path/to/your/jar/file/MyClass.jar

* [Java Logging APIs Tutorial](https://mkyong.com/logging/java-logging-apis-tutorial/)
* [Java Logging Best Practices](https://sematext.com/blog/java-logging-best-practices/#toc-1-use-a-standard-logging-library-0)
* [Java logging. Hello World](https://habr.com/ru/post/247647/)
* [Логирование с JUL(java.util.Logging)](https://urvanov.ru/2019/07/03/%D0%BB%D0%BE%D0%B3%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5-%D1%81-jul-java-util-logging/)
* [Логирование в Java / quick start](https://habr.com/ru/post/130195/)
* [Health Indicators in Spring Boot (Baeldung)](https://www.baeldung.com/spring-boot-health-indicators)
* [Actuator Endpoints](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints)
* [Spring Data REST Reference Guide](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#reference)
* [draft-kelly-json-hal-08 - JSON Hypertext Application Language](https://datatracker.ietf.org/doc/html/draft-kelly-json-hal-08)
* [Spring Data REST](https://spring.io/projects/spring-data-rest)
* [Понимание HATEOAS (Spring по-русски!)](http://spring-projects.ru/understanding/hateoas/)
* [Spring REST and HAL Browser (Baeldung)](https://www.baeldung.com/spring-rest-hal)
* [JSON Formatter](https://chrome.google.com/webstore/detail/json-formatter/bcjindcccaagfpapjjmafapmmgkkhgoa?hl=ru)
* [HAL Specification](https://stateless.group/hal_specification.html)
* [Profiling tools - Help | IntelliJ IDEA](https://www.jetbrains.com/help/idea/cpu-profiler.html)

