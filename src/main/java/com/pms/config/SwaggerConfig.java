package com.pms.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author liangrj
 * @since 2023/02/16
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                //配置网站的基本信息
                .apiInfo(new ApiInfoBuilder()
                        //网站标题
                        .title("PMS接口文档")
                        //标题后面的版本号
                        .version("v1.0")
                        .description("PMS在线接口文档")
                        //联系人信息
                        .contact(new Contact("liangrj", "", "757419344@qq.com"))
                        .build())
                .select()
                .build();
    }
}
