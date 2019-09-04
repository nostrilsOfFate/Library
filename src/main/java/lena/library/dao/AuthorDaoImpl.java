package lena.library.dao;

import lena.library.model.Author;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Slf4j
@Repository
public class AuthorDaoImpl implements AuthorDao {
    //TODO 1) ДОбавить ко всем методам логи (см пример BookDaoImpl) - НЕ СДЕЛАНО
    //TODO 2) Удалить getter, setter, equals hash code. toString и тд - посмотреть на Data в инете(документация!!!) - готово
    //TODO 3) УДАЛИТЬ ВЕСЬ ЗАКОМЕНЧЕННЫЙ КОД!!! - почти все, кроме классов что еще не сделаны
    //TODO 4) Лить все в гит ( комитить чаще!!) - оке
    //TODO 5) Вынести конфигурацию в java класс либо удалить java классы конфига - ...
    //TODO 6) Переделать методы удаления - и тесты удаления
    //TODO 7) Удалить main - он нафиг не нужен
    //TODO 8) ПЕределать интерфейсы в сервис классах
    // Соответственно create, get, update - возвращает сущность

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Author insert(Author author) throws DataAccessException { //ставка
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into test.authors (name) values(?)", new String[]{"id"});
                ps.setString(1, author.getName());
                return ps;
            }
        };
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, holder);
        try {
            author.setId(holder.getKey().intValue());
        } catch (InvalidDataAccessApiUsageException e) {
            log.info("Invalid value of key");
        }
        return author;
    }

    @Override
    public Author update(Author author) { //обновление текущего пользователя
        int i;
        try {
            Object[] objects = new Object[]{
                    author.getName(),
                    author.getId(),};
            i = jdbcTemplate.update("UPDATE  test.authors SET name = ? where id = ?", objects);
        } catch (DataAccessException e) {
            i = 0;
            log.info("Empty result in updating");
        }
        return (i != 0) ? author : null;
    }

    @Override
    public Author getById(int id) throws DataAccessException {
        Object[] objects = new Object[]{id};
        Author author = null;
        try {
            author = jdbcTemplate.queryForObject("select * from test.authors where id= ?", objects, (rs, arg) -> {
                Author author1 = new Author();
                author1.setId(rs.getInt("id"));
                author1.setName(rs.getString("name"));
                return author1;
            });
        } catch (EmptyResultDataAccessException e) {
            log.info("Empty result in getting by id");
        }
        return author;
    }

    @Override
    public Author getByName(String name) throws DataAccessException {
        Object[] objects = new Object[]{name};
        Author author = null;
        try {
            author = jdbcTemplate.queryForObject("select * from test.authors where name = ?", objects, (rs, rowNum) -> {
                Author author1 = new Author();
                author1.setId(rs.getInt("id"));
                author1.setName(rs.getString("name"));
                return author1;
            });
        } catch (EmptyResultDataAccessException e) {
            log.info("Empty result in getting by name");
        }
        return author;
    }

    @Override
    public List<Author> getAllAuthors() {
        return jdbcTemplate.query("SELECT * FROM test.authors", (rs, rowNum) -> {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            return author;
        });
    }

    @Override
    public Boolean deleteById(int id) throws DataAccessException {
        return jdbcTemplate.update("delete from test.authors where id = ?", id) != 0;
    }

    @Override
    public Boolean deleteByName(String name) throws DataAccessException {
        return jdbcTemplate.update("delete from test.authors where name = ?", name) != 0;
    }

    public void deleteAll() {
        try {
            jdbcTemplate.execute("delete from test.authors;");
        } catch (DataAccessException e) {
            log.info("Empty result in all deleting");
        }
    }
}