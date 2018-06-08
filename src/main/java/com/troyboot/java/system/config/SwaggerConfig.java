package com.troyboot.java.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @Authour YangYang
 * @Date 2018/6/5 17:01
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @RequestMapping(value = "/demo2.do")
    String getDemo() {
        return "Hello World \n" + "Spring boot3";
    }

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        ApiInfo apiInfo = new ApiInfo(
                "troy of springboot",
                "troy of springboot",
                "0.0.1",
                null,
                null,
                null,
                null);
        return new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/user/.*")).build()
                .apiInfo(apiInfo).useDefaultResponseMessages(false);
    }

}