INSERT INTO library.books (id, name, description, writtenYear)
VALUES (1,
        'Ruslan and Lyudmila',
        'Первая законченная поэма Александра Сергеевича Пушкина; волшебная сказка, вдохновлённая древнерусскими былинами.',
        1820);
INSERT INTO library.books (id, name, description, writtenYear)
VALUES (2,
        'Animal Farm: A Fairy Story',
        'Cатирическая повесть-притча, «All animals are equal, but some animals are more equal than others» ',
        1945);
INSERT INTO library.books (id, name, description, writtenYear)
VALUES (3,
        'The Hobbit, or There and Back Again',
        'В основе сюжета — путешествие хоббита Бильбо Бэггинса, волшебника Гэндальфа и тринадцати гномов во главе с Торином Дубощитом.',
        1937);
INSERT INTO library.books (id, name, description, writtenYear)
VALUES (4, 'Ленина Книга', 'В основе сюжета — приключения Лены.', 2019);


INSERT INTO library.authors (id, name)
VALUES (1, 'Pushkin A.S.');
INSERT INTO library.authors (id, name)
VALUES (2, 'George Orwell');
INSERT INTO library.authors (id, name)
VALUES (3, 'John Ronald Ruel Tolkien');


INSERT INTO library.genres (id, name)
VALUES (4, 'Fiction');
INSERT INTO library.genres (id, name)
VALUES (5, 'Fantasy');
INSERT INTO library.genres (id, name)
VALUES (6, 'Drama');


INSERT INTO library.authors_books (author_id, book_id)
VALUES (1, 1);
INSERT INTO library.authors_books (author_id, book_id)
VALUES (1, 4);


INSERT INTO library.genres_books (genre_id, book_id)
VALUES (4, 2);
INSERT INTO library.genres_books (genre_id, book_id)
VALUES (4, 3);
INSERT INTO library.genres_books (genre_id, book_id)
VALUES (5, 1);
INSERT INTO library.genres_books (genre_id, book_id)
VALUES (5, 3);
INSERT INTO library.genres_books (genre_id, book_id)
VALUES (6, 1);

INSERT INTO library.roles (id, name)
VALUES (7, 'admin');
INSERT INTO library.roles (id, name)
VALUES (8, 'member');

INSERT INTO library.users (id, email, password, firstName, lastName, enabled, lastLogin)
VALUES ('roleChangeSet', 'admin@gmail.com', 'password', 'admin', 'admin', true, NOW()  );
INSERT INTO library.users (id, email, password, firstName, lastName, enabled, lastLogin)
VALUES ('roleChangeSet1', 'member@gmail.com', 'password', 'member', 'member',true ,NOW() );
