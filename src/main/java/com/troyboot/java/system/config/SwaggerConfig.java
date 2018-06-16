package com.troyboot.java.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
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

    private static final String SWAGGER_SCAN_BASE_PACKAGE = "com.troyboot.java";
    private static final String VERSION = "0.0.1";

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("RESTful APIs for TroyBoot")
                .description("展示TroyBoot部分接口")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .contact("YangYang")
                .version(VERSION).build();
    }

}