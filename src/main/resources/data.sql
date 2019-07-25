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



INSERT INTO data_genre.books (id, name, description, writtenYear)
VALUES (1,
        'Ruslan and Lyudmila',
        'Первая законченная поэма Александра Сергеевича Пушкина; волшебная сказка, вдохновлённая древнерусскими былинами.',
        1820);
INSERT INTO data_genre.books (id, name, description, writtenYear)
VALUES (2,
        'Animal Farm: A Fairy Story',
        'Cатирическая повесть-притча, «All animals are equal, but some animals are more equal than others» ',
        1945);
INSERT INTO data_genre.books (id, name, description, writtenYear)
VALUES (3,
        'The Hobbit, or There and Back Again',
        'В основе сюжета — путешествие хоббита Бильбо Бэггинса, волшебника Гэндальфа и тринадцати гномов во главе с Торином Дубощитом.',
        1937);
INSERT INTO data_genre.books (id, name, description, writtenYear)
VALUES (4, 'Ленина Книга', 'В основе сюжета — приключения Лены.', 2019);



CREATE TABLE IF NOT EXISTS data_genre.authors (
  id   INTEGER     NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);


INSERT INTO data_genre.authors (id, name)
VALUES (1, 'Pushkin A.S.');
INSERT INTO data_genre.authors (id, name)
VALUES (2, 'George Orwell');
INSERT INTO data_genre.authors (id, name)
VALUES (3, 'John Ronald Ruel Tolkien');



CREATE TABLE IF NOT EXISTS data_genre.genres (
  id   INTEGER     NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO data_genre.genres (id, name)
VALUES (4, 'Fiction');
INSERT INTO data_genre.genres (id, name)
VALUES (5, 'Fantasy');
INSERT INTO data_genre.genres (id, name)
VALUES (6, 'Drama');



CREATE TABLE IF NOT EXISTS data_genre.authors_books (
  author_id INTEGER NOT NULL,
  book_id   INTEGER NOT NULL,
  PRIMARY KEY (author_id, book_id),
  CONSTRAINT fk_author_id FOREIGN KEY (author_id) references data_genre.authors (id),
  CONSTRAINT fk_book1_id foreign key (book_id) references data_genre.books (id)
);

INSERT INTO data_genre.authors_books (author_id, book_id)
VALUES (1, 1);
INSERT INTO data_genre.authors_books (author_id, book_id)
VALUES (1, 4);



CREATE TABLE IF NOT EXISTS data_genre.genres_books (
  genre_id INTEGER NOT NULL,
  book_id  INTEGER NOT NULL,
  PRIMARY KEY (genre_id, book_id),
  CONSTRAINT fk_genre_id FOREIGN KEY (genre_id) references data_genre.genres (id),
  CONSTRAINT fk_book_id foreign key (book_id) references data_genre.books (id)
);

INSERT INTO data_genre.genres_books (genre_id, book_id)
VALUES (4, 2);
INSERT INTO data_genre.genres_books (genre_id, book_id)
VALUES (4, 3);
INSERT INTO data_genre.genres_books (genre_id, book_id)
VALUES (5, 1);
INSERT INTO data_genre.genres_books (genre_id, book_id)
VALUES (5, 3);
INSERT INTO data_genre.genres_books (genre_id, book_id)
VALUES (6, 1);
