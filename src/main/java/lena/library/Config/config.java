package lena.library.Config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class config {

    @Autowired
    private DataSource dataSource;




   public DataSource dataSource() {
   DriverManagerDataSource dataSource = new DriverManagerDataSource();
       dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
       dataSource.setUrl("jdbc:mysql://localhost:3306?useSSL=false");
       dataSource.setUsername( "root" );
       dataSource.setPassword( "root" );
       return dataSource;
   }
}
