package com.myhome.springCrudRest.configuration;

import com.myhome.springCrudRest.converter.RoleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@ComponentScan(basePackages = "com.myhome.springCrudRest")
@Import({SecurityConfig.class, JpaConfiguration.class})
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

    private final
    RoleConverter roleConverter;

    @Autowired
    public AppConfig(RoleConverter roleConverter) {
        this.roleConverter = roleConverter;
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("=== AppConfig ===" + "=== addViewControllers ===");
        registry.addViewController("/users");
        registry.addViewController("/login");
        registry.addViewController("/profile");
        registry.addViewController("/api");
    }


    @Bean
    public ViewResolver viewResolver() {
        System.out.println("=== AppConfig ===" + "=== viewResolver ===");

        InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/jsp/");
        bean.setSuffix(".jsp");

        return bean;
    }


    @Override
    public void addFormatters(FormatterRegistry registry) {
        System.out.println("=== AppConfig ===" + "=== addFormatters ===");
        registry.addConverter(roleConverter);
    }
}
