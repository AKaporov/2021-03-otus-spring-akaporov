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
- Необязательно реализовывать методы по созданию пользователей - допустимо добавить пользователей только через БД-скрипты;
- В существующее CRUD-приложение добавить механизм Form-based аутентификации;
- UsersServices реализовать самостоятельно;
- Авторизация на всех страницах - для всех аутентифицированных. Форма логина - доступна для всех;
- Написать тесты контроллеров, которые проверяют, что все необходимые ресурсы действительно защищены.

Команды для запуска в браузере (после запуска приложения) или в Terminal в IDEA:
http://localhost:8080/api/v1/books/
http://localhost:8080/api/v1/books?BookTitle=School
для включенной аутентификации: 
http://localhost:8080/api/v1/books/ -u admin:password
http://localhost:8080/api/v1/books?BookTitle=School -u admin:password


#### Данная работа не засчитывает предыдущую!

* [Enterprise Integration Patterns - Pipes and Filters](https://www.enterpriseintegrationpatterns.com/patterns/messaging/PipesAndFilters.html)
* [Spring Boot + Spring Security + Thymeleaf example – Mkyong.com](https://mkyong.com/spring-boot/spring-boot-spring-security-thymeleaf-example/)
* [RFC 7617 - The 'Basic' HTTP Authentication Scheme](https://datatracker.ietf.org/doc/html/rfc7617)
* [RFC 1945 - Hypertext Transfer Protocol -- HTTP/1.0](https://datatracker.ietf.org/doc/html/rfc1945)
* [CSRF — Википедия](https://ru.wikipedia.org/wiki/%D0%9C%D0%B5%D0%B6%D1%81%D0%B0%D0%B9%D1%82%D0%BE%D0%B2%D0%B0%D1%8F_%D0%BF%D0%BE%D0%B4%D0%B4%D0%B5%D0%BB%D0%BA%D0%B0_%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81%D0%B0)
* [Spring Security Architecture](https://spring.io/guides/topicals/spring-security-architecture)
* [Сайт проекта Spring Security](https://spring.io/projects/spring-security)
* [Документация Spring Security Reference - примеры](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
* [Документация](https://mkyong.com/tutorials/spring-security-tutorials/)
* [3. Java Configuration](https://docs.spring.io/spring-security/site/docs/4.0.4.RELEASE/reference/html/jc.html)
* [REST API с использованием Spring Security и JWT](https://itnan.ru/post.php?c=1&p=545610)
* [Testing the Web Layer](https://spring.io/guides/gs/testing-web/)
* [Spring Boot: интеграционное тестирование с Spring Testing](https://www.youtube.com/watch?v=Lnc3o8cCwZY)