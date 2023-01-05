# Spring MVC

### Домашнее задание
REST API приложения "Библиотека"

Цель: 
- научиться разрабатывать полноценные REST API на базе Spring MVC  

Результат:
- REST API на базе Spring MVC

Домашнее задание выполняется переписыванием предыдущего на Spring MVC.

Требования:
- Создать CRUD REST API приложения с хранением сущностей в БД;   
- В качестве предметной области нужно взять "библиотеку" из прошлых занятий (вместе с DAO / репозиториями / сервисами и проч.);
- При разработке API придерживаться ресурсного стиля URL и семантики HTTP методов;
- Для книг (главной сущности) в API должны быть доступные все CRUD операции. CRUD остальных сущностей по желанию/необходимости;
- Все функции апи проверить через интеграционные тесты контроллеров.

Команды для запуска в браузере (после запуска приложения) или в Terminal в IDEA:
http://localhost:8080/api/v1/books/
http://localhost:8080/api/v1/books?BookTitle=School

#### Данная работа не засчитывает предыдущую!

* [HTTP Methods for RESTful Services](https://www.restapitutorial.com/lessons/httpmethods.html)
* [JSON](http://www.json.org/json-ru.html)
* [Литература для изучения JS](https://www.dropbox.com/sh/zwnp4y4zpl2pe08/AAAGqfdn991CZUVOoMqIwGS_a?dl=0)
* [Web on Servlet Stack](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)

