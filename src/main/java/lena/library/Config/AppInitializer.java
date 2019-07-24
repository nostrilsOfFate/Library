package lena.library.Config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {//регистрация классов конфигурации
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { //регистрация классов конфигурации
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() { //регистрируются адреса
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() { // фильтр для предварительной обработки кодировки
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }
}
