insert into books(`title`) values('Golden Key');

insert into authors(`surname`, `name`, `patronymic`) values('Tolstoy', 'Alexey', 'Nikolaevich');

insert into genres(`name`) values('Story');

insert into bookstoauthorstogenreslink(book_id, author_id, genre_id) values(1, 1, 1);

