package cn.wmyskxz.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private  String password;
    private static final Logger log = LoggerFactory.getLogger(AppConfig.class);
    private static final AppConfig instance = new AppConfig();

    public static AppConfig getInstance() {
        return instance;
    }

    public AppConfig() {
//        InputStream resourceAsStream = null;
//        try {
//            resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties");
//            Properties properties = new Properties();
            //                properties.load(resourceAsStream);
            this.jdbcUrl = "jdbc:mysql://127.0.0.1:3306/login?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT&useSSL=false";
            this.jdbcUsername = "root";
            this.jdbcPassword = "dd976789595";
//        } finally {
//            if (resourceAsStream != null) {
//                try {
//                    resourceAsStream.close();
//                } catch (IOException e) {
//                    log.info("", e);
//                }
//            }
//        }
    }

    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }
}
