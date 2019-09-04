package lena.library.dao;

import lena.library.model.Author;
import lena.library.model.Book;
import lena.library.model.Genre;
import lena.library.model.Role;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Repository
public class RoleDaoImpl implements RoleDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Role create(Role role) {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into test.roles (name) values(?)", new String[]{"id"});
                ps.setString(1, role.getName());
                return ps;
            }
        };
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, holder);
        try {
            role.setId(holder.getKey().toString());
        } catch (InvalidDataAccessApiUsageException e) {
            log.info("Invalid value of key");
        }
        return role;
    }

    @Override
    public Role findByName(String name) {
        Object[] objects = new Object[]{name};
        Role role = null;
        try {
            role = jdbcTemplate.queryForObject("select * from test.roles where name = ?", objects, (rs, rowNum) -> {
                Role role1 = new Role();
                role1.setId(rs.getString("id"));
                role1.setName(rs.getString("name"));
                return role1;
            });
        } catch (EmptyResultDataAccessException e) {
            log.info("Empty result in getting by name");
        }
        return role;
    }

    @Override
    public List<Role> findAllByNameIn(List<String> names) {
        List<Role> roles = new ArrayList<>();
        for (String name: names
             ) {
            log.info("Get book by name: {}", name);
            Object[] objects = new Object[]{name};
            List<Role> roles1 = new ArrayList<>();
            try {
                roles1 = jdbcTemplate.query("select * from test.roles where name = ?", objects, (rs, rowNum) ->  {
                    Role role1 = new Role();
                    role1.setId(rs.getString("id"));
                    role1.setName(rs.getString("name"));
                    return role1;
                });
            } catch (EmptyResultDataAccessException e) {
                log.info("Empty result in getting by name");
            }
            if (roles1 != null) {
                roles.addAll(roles1);
            }
        }
        return roles;
    }

    @Override
    public Boolean deleteByName(String name) {
        return jdbcTemplate.update("delete from test.roles where name = ?", name)!=0;
    }

    @Override
    public Boolean existsByName(String name) {
       return jdbcTemplate.update("select from test.roles where name = ?", name) != 0;
    }

    @Override
    public List<Role> getAllRoles() {
        return jdbcTemplate.query("SELECT * FROM test.roles", new RowMapper<Role>() {
            public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
                Role role1 = new Role();
                role1.setId(rs.getString("id"));
                role1.setName(rs.getString("name"));
                return role1;
            }
        });
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.execute("delete from test.roles;");
        } catch (DataAccessException e) {
            log.info("Empty result in all deleting");
        }
    }
}
