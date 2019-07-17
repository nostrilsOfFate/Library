drop table if exists data_genre.genre;
drop table if exists data_genre.authors;
drop table if exists data_genre.books;
CREATE TABLE IF NOT EXISTS data_genre.genre (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO data_genre.genre (id, name)
VALUES (1, 'Fiction');
INSERT INTO data_genre.genre (id, name)
VALUES (2, 'Fantasy');
INSERT INTO data_genre.genre (id, name)
VALUES (3, 'Drama');


CREATE TABLE IF NOT EXISTS data_genre.authors (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO data_genre.authors (id, name)
VALUES (1, 'Pushkin A.S.');
INSERT INTO data_genre.authors (id, name)
VALUES (2, 'George Orwell');
INSERT INTO data_genre.authors (id, name)
VALUES (3, 'John Ronald Reuel Tolkien');


CREATE TABLE IF NOT EXISTS data_genre.books (
  id          INT          NOT NULL AUTO_INCREMENT,
  name        VARCHAR(50)  NOT NULL,
  description VARCHAR(300) not null,
  writtenYear int          not null,
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