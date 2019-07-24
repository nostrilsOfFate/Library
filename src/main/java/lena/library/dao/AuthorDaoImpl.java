package lena.library.dao;

        import lena.library.model.Author;
        import org.springframework.dao.DataAccessException;
        import org.springframework.jdbc.core.JdbcTemplate;
        import org.springframework.jdbc.core.RowMapper;
        import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Repository;

        import javax.persistence.EntityManager;
        import javax.persistence.Persistence;
        import javax.sql.DataSource;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {
//data source
//entity manager
public EntityManager em = Persistence.createEntityManagerFactory("LenaTest").createEntityManager();

   // private NamedParameterJdbcOperations namedParameterJdbcOperations;

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public int insert(Author author) {
      if (author.isNew()) {
          String SQL = "insert into data_genre.authors (name) values(?)";
          jdbcTemplate.update(SQL, author.getName());
          System.out.println("Author successfully created.\nName: " + author.getName() + ";\nId: " +
                  author.getId() + ";\nBooks: " + author.getBooks() + "\n");
         return 1;
//            return namedParameterJdbcOperations.getJdbcOperations().update("insert into data_genre.authors (name) values(?)", author.getName());
     } else {
          String SQL = "update data_genre.authors set name=? where id=?";
          jdbcTemplate.update(SQL,  author.getName(), author.getId());
          System.out.println("Author successfully created.\nName: " + author.getName() + ";\nId: " +
                  author.getId() + ";\nBooks: " + author.getBooks() + "\n");
          return 0;
//            return namedParameterJdbcOperations.getJdbcOperations().update("update data_genre.authors set name=? where id=?", author.getName(), author.getId());
       }

    }

    @Override
    public Author getById(int id) throws DataAccessException {
//        Map<String, Integer> params = Collections.singletonMap("id", id);
//        return namedParameterJdbcOperations.queryForObject("select * from data_genre.authors where id = :id", params, new AuthorMapper());
    return em.find(Author.class, id);
    }

    @Override
    public Author getByName(String name) throws DataAccessException {
//        Map<String, String> params = Collections.singletonMap("name", name);
//        return namedParameterJdbcOperations.queryForObject("select * from data_genre.authors where name = :name", params, new AuthorMapper());
  return null;
    }

    @Override
    public List<Author> getAllAuthors() {
//        return namedParameterJdbcOperations.query("select * from data_genre.authors", new AuthorMapper());
        return null;
    }

    @Override
    public int deleteById(int id) {
//        Map<String, Integer> params = Collections.singletonMap("name", id);
//////        return namedParameterJdbcOperations.update("delete * from data_genre.authors where name = :name", params);
        return 0;
    }

    @Override
    public int deleteByName(String name) {
//        Map<String, String> params = Collections.singletonMap("name", name);
//        return namedParameterJdbcOperations.update("DELETE * from data_genre.authors where name = :name", params);
        return 0;
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }

}
