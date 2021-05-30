DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS(ID BIGSERIAL NOT NULL PRIMARY KEY, NAME VARCHAR(255) NOT NULL);

DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES(ID BIGSERIAL NOT NULL PRIMARY KEY, NAME VARCHAR(255) NOT NULL);

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(ID BIGSERIAL       NOT NULL PRIMARY KEY,
                   TITLE VARCHAR(255) NOT NULL,
                   AUTHOR_ID BIGINT   NOT NULL REFERENCES AUTHORS(ID),
                   GENRE_ID BIGINT    NOT NULL REFERENCES GENRES(ID)
                   );