package com.zsrc.face.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jianjiawen
 * @date 2021-2-15 21:29
 */
@Configuration
@EnableSwagger2
public class Knife4jConfiguration {
    @Bean(value = "ZsrcFaceAPIs")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description("# 人脸识别 RESTful APIs")
                        .termsOfServiceUrl("http://www.zsrc.face.com/")
                        .contact(new Contact("jianjiawen","http://jjw.com","93108@qq.com"))
                        .version("v1.0")
                        .build())
                //分组名称
                .groupName("v1.0")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.zsrc.face.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
