//package com.myhome.springCrudRest.configuration;
//
//import com.myhome.springCrudRest.converter.RoleConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//
//public class WebMvcAppConfig implements WebMvcConfigurer {
//
//    private final
//    RoleConverter roleConverter;
//
//    //@Autowired
//    public WebMvcAppConfig(RoleConverter roleConverter) {
//        this.roleConverter = roleConverter;
//    }
//
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        System.out.println("=== WebMvcAppConfig ===" + "=== addViewControllers ===");
//        registry.addViewController("/users");
//        registry.addViewController("/login");
//        registry.addViewController("/profile");
//        registry.addViewController("/api");
//    }
//
//
//    @Bean
//    public ViewResolver viewResolver() {
//        System.out.println("=== WebMvcAppConfig ===" + "=== viewResolver ===");
//
//        InternalResourceViewResolver bean = new InternalResourceViewResolver();
//
//        bean.setViewClass(JstlView.class);
//        bean.setPrefix("/jsp/");
//        bean.setSuffix(".jsp");
//
//        return bean;
//    }
//
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        System.out.println("=== WebMvcAppConfig ===" + "=== addFormatters ===");
//        registry.addConverter(roleConverter);
//    }
//}
