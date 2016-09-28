package ru.rosreestr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
@Configuration
@ComponentScan(basePackages = "ru.rosreestr")
//@EnableJpaRepositories(basePackages = "ru.rosreestr.repository")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

//    @Bean(destroyMethod = "close")
//    DataSource dataSource(Environment env) {
//        HikariConfig dataSourceConfig = new HikariConfig();
//        dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
//        dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
//        dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));
//        dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));
//
//        return new HikariDataSource(dataSourceConfig);
//    }

}
