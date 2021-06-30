# JPQL, Spring ORM, DAO на основе Spring ORM + JPA

### Домашнее задание
Переписать приложение для хранения книг на ORM

Цель: 
- полноценно работать с JPA + Hibernate для подключения к реляционным БД посредством ORM-фреймворка 

Результат:
- Высокоуровневое приложение с JPA-маппингом сущностей

Домашнее задание выполняется переписыванием предыдущего на JPA.

Требования:
- Переписать приложение для хранения книг на ORM;   
- Использовать JPA, Hibernate только в качестве JPA-провайдера;
- Для решения проблемы N+1 можно использовать специфические для Hibernate аннотации @Fetch и @BatchSize;
- Добавить сущность "комментария к книге", реализовать CRUD для новой сущности;
- Покрыть репозитории тестами, используя H2 базу данных и соответствующий H2 Hibernate-диалект для тестов;
- Не забудьте отключить DDL через Hibernate;
- @Transactional рекомендуется ставить только на методы сервиса.


#### Это домашнее задание будет использоваться в качестве основы для других ДЗ. Данная работа не засчитывает предыдущую!

### Команды
- ib или insertBook - Добавьте книгу, укажите ее "Название" "Фамилия Имя Отчество" "Жанр";
- fb или findBook - Получите информацию о книге по ее "Названию";
- db или deleteBook - Удалить книгу со всей связанной информацией по "Названию";
- fab или findAllBook - Найдите все книги в библиотеке;
- fbr или findBookReviews - Найти все отзывы о книге по её "Названию";
- sbr или saveBookReview - Добавить отыз о книге по её названию, укажите ее "Название" "Отзыв".

* [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)
* [H2 Database Engine](http://www.h2database.com/html/main.html)
* [Data Access](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html)
* [Документация Spring ORM](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#orm)

