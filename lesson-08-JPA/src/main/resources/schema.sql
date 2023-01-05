DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS GENRES;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS COMMENTS;

CREATE TABLE AUTHORS(ID BIGSERIAL NOT NULL PRIMARY KEY, NAME VARCHAR(255) NOT NULL UNIQUE);

CREATE TABLE GENRES(ID BIGSERIAL NOT NULL PRIMARY KEY, NAME VARCHAR(255) NOT NULL UNIQUE);

CREATE TABLE BOOKS(ID BIGSERIAL       NOT NULL PRIMARY KEY,
                   TITLE VARCHAR(255) NOT NULL UNIQUE,
                   AUTHOR_ID BIGINT   NOT NULL REFERENCES AUTHORS(ID),
                   GENRE_ID BIGINT    NOT NULL REFERENCES GENRES(ID)
                   );

CREATE TABLE COMMENTS(ID BIGSERIAL NOT NULL PRIMARY KEY,
                      BOOK_ID BIGINT REFERENCES BOOKS(ID),
                      COMMENT VARCHAR(255) NOT NULL
                      );
