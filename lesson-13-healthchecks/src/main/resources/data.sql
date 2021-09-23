insert into authors(name) values('Nosov N. N.');
insert into authors(name) values('Kaverin V. A.');
insert into authors(name) values('Gaidar A. P.');

insert into genres(name) values('Story');
insert into genres(name) values('Library of the Soviet novel');
insert into genres(name) values('School library');

insert into books(title, author_id, genre_id) values('Two captains', 2, 2);
insert into comments(book_id, comment) values(1L, 'This book has one drawback - it is impossible to tear yourself away from it.');
insert into comments(book_id, comment) values(1L, 'The language of the story is simple enough, but this is its plus.');

-- Две книги одного автора в одном жанре
insert into books(title, author_id, genre_id) values('The Adventures of Dunno and His Friends', 1, 1);
insert into comments(book_id, comment) values(2L, 'The book is informative, the child really liked it');
insert into comments(book_id, comment) values(2L, 'I recommend to first graders for reading!');
insert into comments(book_id, comment) values(2L, 'Format, convenient to take with you. Color illustrations available, hardcover');

insert into books(title, author_id, genre_id) values('Dreamers', 1, 2);
insert into comments(book_id, comment) values(3L, 'This book comes from childhood!');

-- Две книги одного автора в одном жанре
insert into books(title, author_id, genre_id) values('Timur and his team', 3, 3);
insert into books(title, author_id, genre_id) values('School', 3, 3);
insert into comments(book_id, comment) values(5L, 'A book written by a Red Army man about how he went to fight for Soviet power.');