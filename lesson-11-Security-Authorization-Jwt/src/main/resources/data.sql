--Пользователи
insert into users(name, password, role) values ('admin', '$2a$16$FMSODhQnsyHfuMEdKiMUu.46Qqmzr084FqHA8L0Fp8MIYmIiWBZUa', 'ADMIN');
insert into users(name, password, role) values ('reader', '$2a$16$FltGrygFMPyOoZG2P15lceoIRFmr1Rwq3MDtxouGY25sPtQnxDbzy', 'READER');

--Авторы книг
insert into authors(name) values('Nosov N. N.');
insert into authors(name) values('Kaverin V. A.');
insert into authors(name) values('Gaidar A. P.');

--Жанры книг
insert into genres(name) values('Story');
insert into genres(name) values('Library of the Soviet novel');
insert into genres(name) values('School library');

--Книга "Two captains"
insert into books(title, author_id, genre_id) values('Two captains', 2, 2);
--Отзыв о книге
insert into comments(book_id, comment) values(1L, 'This book has one drawback - it is impossible to tear yourself away from it.');
insert into comments(book_id, comment) values(1L, 'The language of the story is simple enough, but this is its plus.');

--Две книги одного автора в одном жанре:
--Книга "The Adventures of Dunno and His Friends"
insert into books(title, author_id, genre_id) values('The Adventures of Dunno and His Friends', 1, 1);
--Отзыв о книге
insert into comments(book_id, comment) values(2L, 'The book is informative, the child really liked it');
insert into comments(book_id, comment) values(2L, 'I recommend to first graders for reading!');
insert into comments(book_id, comment) values(2L, 'Format, convenient to take with you. Color illustrations available, hardcover');

--Книга "Dreamers"
insert into books(title, author_id, genre_id) values('Dreamers', 1, 2);
--Отзыв о книге
insert into comments(book_id, comment) values(3L, 'This book comes from childhood!');

-- Две книги одного автора в одном жанре:
--Книга "Timur and his team"
insert into books(title, author_id, genre_id) values('Timur and his team', 3, 3);
--Книга "School"
insert into books(title, author_id, genre_id) values('School', 3, 3);
--Отзыв о книге
insert into comments(book_id, comment) values(5L, 'A book written by a Red Army man about how he went to fight for Soviet power.');