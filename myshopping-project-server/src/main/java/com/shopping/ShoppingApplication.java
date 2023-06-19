package com.shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("com.shopping.mapper")
public class ShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer(){
            /**
             * 跨域配置
             * @param registry
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")//配置那些资源允许跨域访问
                        .allowedMethods("*")//允许访问的请求方式
                        .allowCredentials(true)//允许使用凭证，cookie
                        .allowedOrigins("http://localhost:8080")//允许访问当前系统的域
                    ;

            }
        };
    }

}
