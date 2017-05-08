package com.fashionmart.prodcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fashionmart.common.FashionMartCommonConfiguration;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@Configuration
@EnableTransactionManagement(proxyTargetClass=true)
@EntityScan(basePackages = "com.fashionmart.prodcat.entity")
@EnableJpaRepositories(basePackages = "com.fashionmart.prodcat.repository")
@Import({ FashionMartCommonConfiguration.class })
public class ProductCatalogueApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogueApplication.class, args);
    }

}

