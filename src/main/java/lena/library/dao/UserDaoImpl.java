package lena.library.dao;

import lena.library.model.Role;
import lena.library.model.User;
import lombok.extern.slf4j.Slf4j;
import org.mockito.Mock;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //TODO СДЕЛАТЬ РОЛИ И ЮЗЕРЫ
    @Override
    public User create(User user) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into library.users (name) values(?,?,?,?,?,?,?,?)",
                        new String[]{"id","firstName" ,"lastName" ,"email","password", "enabled","lastLogin"});
                ps.setInt(1, user.getId());
                ps.setString(2, user.getFirstName());
                ps.setString(3, user.getLastName());
                ps.setString(4, user.getEmail());
                ps.setString(5, user.getPassword());
                ps.setObject(6, user.getRole());
                ps.setBoolean(7, user.isEnabled());
                ps.setObject(8, user.getLastLogin());
                return ps;
            }
        };
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, holder);
        try {
            user.setId(holder.getKey().intValue());
        } catch (InvalidDataAccessApiUsageException e) {
            log.info("Invalid value of key");
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        Object[] objects = new Object[]{email};
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("select * from library.users where email = ?",
                    objects, (rs, rowNum) -> fillUser(rs));
        } catch (EmptyResultDataAccessException e) {
            log.info("Empty result in getting by name");
        }
        return user;
    }

    @Override
    public User findById(Integer userId) {
        Object[] objects = new Object[]{userId};
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("select * from library.users where id = ?",
                    objects, (rs, rowNum) -> fillUser(rs));
        } catch (EmptyResultDataAccessException e) {
            log.info("Empty result of user in getting by id");
        }
        return user;
    }

    @Override
    public boolean existsById(String userId) {
        return jdbcTemplate.update("select from library.users where id = ?", userId) != 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        return jdbcTemplate.update("select from library.users where email = ?", email) != 0;
    }

    @Override
    public List<User> findAllByIdIn(List<String> ids) {
        return jdbcTemplate.query("SELECT * FROM library.users", new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return fillUser(rs);
            }
        });
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.execute("delete from library.users;");
        } catch (DataAccessException e) {
            log.info("Empty result in all deleting");
        }
    }

    @Override
    public Boolean deleteById(Integer id) {
        return jdbcTemplate.update("delete from library.users where id = ?", id)!=0;
    }

    @Override
    public Boolean deleteByName(String name) {
        return jdbcTemplate.update("delete from library.users where name = ?", name)!=0;
    }


    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM library.users", new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return fillUser(rs);
            }
        });
    }

    private User fillUser(ResultSet rs) throws SQLException {
        User user1 = new User();
        user1.setId(rs.getInt("id"));
        user1.setEmail(rs.getString("email"));
        user1.setFirstName(rs.getString("firstName"));
        user1.setLastName(rs.getString("lastName"));
        user1.setEnabled(rs.getBoolean("enabled"));
        user1.setLastLogin(rs.getDate("lastLogin").toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        user1.setPassword(rs.getString("password"));
        return user1;
    }
}
