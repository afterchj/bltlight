package com.tpadsz.after.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hongjian.chen on 2018/11/27.
 */
@Configuration
public class DBConfig {

//    private String driver = "com.mysql.jdbc.Driver";
//    private String url = "jdbc:mysql://10.10.11.80:3306/uic";
//    private String username = "ccy";
//    private String password = "ccy";
//    private int maxActive = 20;
//    private int maxIdel = 8;
//    private long maxWait = 100;


    private static String driverClass;
    private static String jdbc_url;
    private static String username;
    private static String password;
    private static int maxActive;
    private static int maxIdle;
    private static long maxWait;

    static {
        InputStream inputStream = DBConfig.class.getClassLoader().getResourceAsStream("setup.production.properties");
        Properties p = new Properties();
        try {
            p.load(inputStream);
            driverClass = (String) p.get("driverClass");
            jdbc_url = (String) p.get("jdbc.url");
            username = (String) p.get("jdbc.username");
            password = (String) p.get("jdbc.password");
            maxActive = Integer.parseInt((String) p.get("maxActive"));
            maxIdle = Integer.parseInt((String) p.get("maxIdle"));
            maxWait = Long.parseLong((String) p.get("maxWait"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Bean(name = "myDateSource")
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(jdbc_url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMaxWait(maxWait);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "mySqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setDataSource(dataSource());
        bean.setTypeAliasesPackage("com.tpadsz.after.entity");
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mybatis80/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "mySqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "myMapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //获取之前注入的beanName为sqlSessionFactory的对象
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("mySqlSessionFactory");
        //指定xml配置文件的路径
        mapperScannerConfigurer.setBasePackage("com.tpadsz.after.dao80");
        return mapperScannerConfigurer;
    }

}
