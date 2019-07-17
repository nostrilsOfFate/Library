import lena.library.dao.AuthorDao;
import lena.library.model.Author;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import static org.junit.Assert.assertEquals;


//@RunWith(JUnit4ClassRunner.class)
public class AuthorDaoImplTest {
@Autowired
    private AuthorDao authorDao;

    @Test
   public void insertTest() {
        Author expected = new Author(null, "Вася Пупкин");
        authorDao.insert(expected);
        Author actual = authorDao.getByName("Вася Пупкин");
        assertEquals(expected.getName(), actual.getName());
    }


// @Test
//    public void getById(int id) throws DataAccessException {
//        // Map<String, Integer> params = Collections.singletonMap("id", id);
//     //        return namedParameterJdbcOperations.queryForObject("select * from data_genre.authors where id = :id", params, new AuthorMapper());
//   //  Author expected =
// }

}
