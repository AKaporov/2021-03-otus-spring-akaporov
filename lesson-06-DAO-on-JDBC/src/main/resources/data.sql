insert into authors(`surname`, `name`, `patronymic`) values('Nosov', 'Nikolay', 'Nikolaevich');

insert into genres(`name`) values('A satirical fairy tale novel with elements of science fiction');

insert into books(`title`) values('The Adventures of Dunno and His Friends');

insert into bookstoauthorstogenreslink(book_id, author_id, genre_id) values(1, 1, 1);