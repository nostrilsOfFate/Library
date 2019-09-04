import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppTest {
    @Autowired
    private EntityManager em;
//перед выполнением любого теста будет создана НОВАЯ EntityManagerFactory, что повлечёт за собой
// создание НОВОЙ БД, т.к. hibernate.hbm2ddl.auto имеет значение create. А из новой фабрики получим новый EntityManager.
    @BeforeEach
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LenaTest");
        em = emf.createEntityManager();
    }

    @AfterEach
    public void close() {
        em.getEntityManagerFactory().close();
        em.close();
    }
}
