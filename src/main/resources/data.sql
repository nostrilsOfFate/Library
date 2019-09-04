drop table if exists library.genres_books;
drop table if exists library.authors_books;
drop table if exists library.genre;
drop table if exists library.authors;
drop table if exists library.books;

CREATE TABLE IF NOT EXISTS library.books (
  id          INTEGER      NOT NULL AUTO_INCREMENT,
  name        VARCHAR(50)  NOT NULL,
  description VARCHAR(300) NOT NULL,
  writtenYear INTEGER      NOT NULL,
  readed      boolean      NOT NULL default false,
  PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS library.authors (
  id   INTEGER     NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS library.genres (
  id   INTEGER     NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS library.roles (
  id   INTEGER     NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS library.users (
  id        INTEGER     NOT NULL AUTO_INCREMENT,
  email     VARCHAR(50) NOT NULL,
  password  VARCHAR(50) NOT NULL,
  firstName VARCHAR(50) NOT NULL,
  lastName  VARCHAR(50) NOT NULL,
  enabled   boolean     NOT NULL default true,
  lastLogin DATE        not null,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS library.roles_users (
  role_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  PRIMARY KEY (role_id, user_id),
  CONSTRAINT fk_role_id FOREIGN KEY (role_id) references library.roles (id),
  CONSTRAINT fk_user_id foreign key (user_id) references library.users (id)
);

CREATE TABLE IF NOT EXISTS library.authors_books (
  author_id INTEGER NOT NULL,
  book_id   INTEGER NOT NULL,
  PRIMARY KEY (author_id, book_id),
  CONSTRAINT fk_author_id FOREIGN KEY (author_id) references library.authors (id),
  CONSTRAINT fk_book1_id foreign key (book_id) references library.books (id)
);


CREATE TABLE IF NOT EXISTS library.genres_books (
  genre_id INTEGER NOT NULL,
  book_id  INTEGER NOT NULL,
  PRIMARY KEY (genre_id, book_id),
  CONSTRAINT fk_genre_id FOREIGN KEY (genre_id) references library.genres (id),
  CONSTRAINT fk_book_id foreign key (book_id) references library.books (id)
);
