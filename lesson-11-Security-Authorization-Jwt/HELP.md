# Spring Security

### Домашнее задание
В CRUD Web-приложение добавить механизм аутентификации

Цель: 
- защитить Web-приложение аутентифкацией и простой авторизацией  

Результат:
- приложение с использованием Spring Security

Задание выполняется на основе нереактивного приложения Spring MVC!

Требования:
- Добавить в приложение новую сущность - пользователь;   
- Не обязательно реализовывать методы по созданию пользователей - допустимо добавить пользователей только через БД-скрипты;
- В существующее CRUD-приложение добавить механизм Form-based аутентификации;
- UsersServices реализовать самостоятельно;
- Авторизация на всех страницах - для всех аутентифицированных. Форма логина - доступна для всех;
- Написать тесты контроллеров, которые проверяют, что все необходимые ресурсы действительно защищены.

Команды для запуска в браузере (после запуска приложения):
http://localhost:8080/api/v1/books/
http://localhost:8080/api/v1/books?BookTitle=School
http://localhost:8080/api/v1/token/
для включенной аутентификации: 
http://localhost:8080/api/v1/books/ -u admin:password
http://localhost:8080/api/v1/books?BookTitle=School -u admin:password

Для выполнения команд в Terminal в IDEA нужно перед командой указать "curl":
curl localhost:8080/api/v1/books/ -u admin:password
curl localhost:8080/api/v1/books?BookTitle=School -u admin:password
curl http://localhost:8080/api/v1/token/ -u admin:password

If you POST to the /token endpoint with the user user/password:
curl -XPOST user:password@localhost:8080/api/v1/token
curl -XPOST admin:password@localhost:8080/api/v1/token
@RequestBody AccountDto:
curl --data '{"name":"admin", "password":"password"}' -v -X POST -H 'Content-Type:application/json' http://localhost:8080/api/v1/token

curl --data "{"name":"admin", "password":"password"}" -v -X POST -H "Content-Type: application/json" "http://localhost:8080/api/v1/token"

Итак, затем запросите токен и экспортируйте его:
export TOKEN=`curl -XPOST user:password@localhost:8080/token`

Finally, request /, including the bearer token for authentication:
curl -H "Authorization: Bearer $TOKEN" localhost:8080 && echo


#### Данная работа не засчитывает предыдущую!

* [JWT - JSON Web Tokens. Allows you to decode, verify and generate JWT.](https://jwt.io/)
* [Spring Security с помощью JWT токена](https://java-master.com/spring-security-%D1%81-%D0%BF%D0%BE%D0%BC%D0%BE%D1%89%D1%8C%D1%8E-jwt-%D1%82%D0%BE%D0%BA%D0%B5%D0%BD%D0%B0/)
* [Онлайн-генератор BCrypt](https://bcrypt-generator.com/)
* [Документация на SoapUI](https://www.soapui.org/docs/rest-testing/understanding-rest-parameters/)
* [JWT Login Sample](https://github.com/spring-projects/spring-security-samples/tree/main/servlet/spring-boot/java/jwt/login)
* [Basic Authentication Header Generator](https://www.blitter.se/utils/basic-authentication-header-generator/)
* [SoapUI - отправить регистрационные данные в JSON](https://coderoad.ru/57095052/SoapUI-%D0%BE%D1%82%D0%BF%D1%80%D0%B0%D0%B2%D0%B8%D1%82%D1%8C-%D1%80%D0%B5%D0%B3%D0%B8%D1%81%D1%82%D1%80%D0%B0%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5-%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D0%B5-%D0%B2-JSON)
* [Spring Boot + Spring Security + Thymeleaf example – Mkyong.com](https://mkyong.com/spring-boot/spring-boot-spring-security-thymeleaf-example/)
* [gitHub - @RequestMapping Content-Type error when a @RequestBody is used but Content-Type header is not given](https://github.com/spring-projects/spring-boot/issues/3313)
* [Enterprise Integration Patterns - Pipes and Filters](https://www.enterpriseintegrationpatterns.com/patterns/messaging/PipesAndFilters.html)
* [Spring REST + Spring Security Example – Mkyong.com](https://mkyong.com/spring-boot/spring-rest-spring-security-example/)
* [RFC 7617 - The 'Basic' HTTP Authentication Scheme](https://datatracker.ietf.org/doc/html/rfc7617)
* [RFC 1945 - Hypertext Transfer Protocol -- HTTP/1.0](https://datatracker.ietf.org/doc/html/rfc1945)
* [CSRF — Википедия](https://ru.wikipedia.org/wiki/%D0%9C%D0%B5%D0%B6%D1%81%D0%B0%D0%B9%D1%82%D0%BE%D0%B2%D0%B0%D1%8F_%D0%BF%D0%BE%D0%B4%D0%B4%D0%B5%D0%BB%D0%BA%D0%B0_%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81%D0%B0)
* [Spring Security Architecture](https://spring.io/guides/topicals/spring-security-architecture)
* [Сайт проекта Spring Security](https://spring.io/projects/spring-security)
* [Документация Spring Security Reference - примеры](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
* [Spring Security Tutorial](https://mkyong.com/tutorials/spring-security-tutorials/)
* [3. Java Configuration](https://docs.spring.io/spring-security/site/docs/4.0.4.RELEASE/reference/html/jc.html)
* [REST API с использованием Spring Security и JWT](https://itnan.ru/post.php?c=1&p=545610)
* [Testing the Web Layer](https://spring.io/guides/gs/testing-web/)
* [Spring Boot: интеграционное тестирование с Spring Testing](https://www.youtube.com/watch?v=Lnc3o8cCwZY)