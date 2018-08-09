package com.knight.configdemo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

/**
 * Spring MVC的配置
 *
 * @author knight
 */
@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    @Value("${spring.thymeleaf.cache}")
    private boolean thymeleafCacheEnable = true;
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    /**
     * 模板资源解析器
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.thymeleaf") //配置前缀绑定
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(context);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(thymeleafCacheEnable);
        return templateResolver;
    }

    /**
     * Thymeleaf标准方言
     *
     * @return
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        // 配置支持SpringEL表达式
        templateEngine.setEnableSpringELCompiler(true);
        //支持SpringSecurity方言
        SpringSecurityDialect springSecurityDialect = new SpringSecurityDialect();
        templateEngine.addDialect(springSecurityDialect);
        return templateEngine;
    }

    /**
     * 配置视图解析器
     *
     * @return
     */
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }


    /**
     * 静态资源加载配置
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
