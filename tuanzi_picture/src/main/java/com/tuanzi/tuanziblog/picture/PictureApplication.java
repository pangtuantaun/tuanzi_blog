package com.tuanzi.tuanziblog.picture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;


@EnableTransactionManagement
@SpringBootApplication
@EnableOpenApi
@EnableDiscoveryClient
@EnableFeignClients("com.tuanzi.tuanziblog.commons.feign")
@ComponentScan(basePackages = {
        "com.tuanzi.tuanziblog.commons.config.feign",
        "com.tuanzi.tuanziblog.commons.handler",
        "com.tuanzi.tuanziblog.commons.config.redis",
        "com.tuanzi.tuanziblog.utils",
        "com.tuanzi.tuanziblog.picture",
        "com.tuanzi.tuanziblog.xo.utils",
        "com.tuanzi.tuanziblog.xo.service",
        "com.tuanzi.tuanziblog.xo.mapper"})
public class PictureApplication {

    public static void main(String[] args) {
        SpringApplication.run(PictureApplication.class, args);
    }
}
