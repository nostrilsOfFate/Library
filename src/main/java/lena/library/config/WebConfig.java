package lena.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration //сообщает Spring что данный класс является конфигурационным
// и содержит определения и зависимости bean-компонентов
@EnableWebMvc //позволяет импортировать конфигурацию Spring MVC из класса WebMvcConfigurationSupport.
// Можно также реализовать, например, интерфейс WebMvcConfigurer, у которого есть целая куча методов..
@ComponentScan(basePackages =  "lena.library") //сообщает Spring где искать компоненты, которыми он
// должен управлять, т.е. классы, помеченные аннотацией @Component или ее производными,
// такими как @controller, @Repository, @service. Эти аннотации автоматически определяют бин класса.
public class WebConfig {

    @Bean
    ViewResolver viewResolver() {
        //ViewResolver необходим для нахождения представления по имени
        //мы создаем его реализацию и определяем где именно искать представления в webapp
        //представление найдется как "/pages/books.jsp"
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
      //  viewResolver.setPrefix("/pages/");
   //     viewResolver.setPrefix("/pages/");
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;

    }
}
