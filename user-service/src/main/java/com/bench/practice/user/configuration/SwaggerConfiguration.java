package com.bench.practice.user.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                                                      .apis(RequestHandlerSelectors.basePackage("com.bench.practice.user.controller"))
                                                      .paths(PathSelectors.ant("/api/v1/user/*"))
                                                      .build()
                                                      .apiInfo(apiInfo());
//                                                      .useDefaultResponseMessages(false);
                                  /*                    .globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder().code(500)
                                                                                                                                         .message("500 message")
                                                                                                                                         .responseModel(new ModelRef("Error"))
                                                                                                                                         .build(),
                                                                                                             new ResponseMessageBuilder().code(403)
                                                                                                                                         .message("Forbidden!!!!!")
                                                                                                                                         .build()));*/
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("My REST API", "Some custom description of API.", "API TOS", "Terms of service", new Contact("Thirdy Fetalvero", "www.example.com", "botsot.felix@gmail.com"), "License of API", "API license URL", Collections.emptyList());
        return apiInfo;
    }
}
