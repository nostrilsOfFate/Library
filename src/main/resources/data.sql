drop table if exists data_genre.genres_books;

drop table if exists data_genre.authors_books;
drop table if exists data_genre.genre;
drop table if exists data_genre.authors;
drop table if exists data_genre.books;

CREATE TABLE IF NOT EXISTS data_genre.books (
  id          INTEGER      NOT NULL AUTO_INCREMENT,
  name        VARCHAR(50)  NOT NULL,
  description VARCHAR(300) NOT NULL,
  writtenYear INTEGER         NOT NULL,
  readed      boolean      NOT NULL default false,
  PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS data_genre.authors (
  id   INTEGER     NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS data_genre.genres (
  id   INTEGER     NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS data_genre.authors_books (
  author_id INTEGER NOT NULL,
  book_id   INTEGER NOT NULL,
  PRIMARY KEY (author_id, book_id),
  CONSTRAINT fk_author_id FOREIGN KEY (author_id) references data_genre.authors (id),
  CONSTRAINT fk_book1_id foreign key (book_id) references data_genre.books (id)
);


CREATE TABLE IF NOT EXISTS data_genre.genres_books (
  genre_id INTEGER NOT NULL,
  book_id  INTEGER NOT NULL,
  PRIMARY KEY (genre_id, book_id),
  CONSTRAINT fk_genre_id FOREIGN KEY (genre_id) references data_genre.genres (id),
  CONSTRAINT fk_book_id foreign key (book_id) references data_genre.books (id)
);
