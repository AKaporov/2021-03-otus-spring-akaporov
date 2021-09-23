insert into authors(name) values('Tolstoy A. N.');
insert into authors(name) values('Nosov N.');
insert into authors(name) values('Gaidar A. P.');

insert into genres(name) values('Story');
insert into genres(name) values('Library of the Soviet novel');
insert into genres(name) values('School library');

insert into books(title, author_id, genre_id) values('The Golden Key, or the Adventures of Pinocchio', 1, 1);
insert into comments(book_id, comment) values(1L, 'The book is informative, the child really liked it');
insert into comments(book_id, comment) values(1L, 'I recommend to first graders for reading!');
insert into comments(book_id, comment) values(1L, 'Format, convenient to take with you. Color illustrations available, hardcover');

insert into books(title, author_id, genre_id) values('Dreamers', 2, 2);
insert into comments(book_id, comment) values(2L, 'This book comes from childhood!');

-- Две книги одного автора в одном жанре
insert into books(title, author_id, genre_id) values('Timur and his team', 3, 3);
insert into books(title, author_id, genre_id) values('School', 3, 3);
insert into comments(book_id, comment) values(4L, 'A book written by a Red Army man about how he went to fight for Soviet power.');