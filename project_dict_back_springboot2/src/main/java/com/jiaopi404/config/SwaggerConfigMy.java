package com.jiaopi404.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Swagger config my.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigMy {

    /**
     * 是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
     */
    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    /**
     * 创建 容器
     *
     * @return the docket
     */
    @Bean
    public Docket createRestApi () {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                // 是否开启
                .enable(swaggerEnabled)
                .select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.jiaopi404.controller"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }

    /**
     * 创建 ApiInfo
     *
     * @return the api info
     */
    public ApiInfo getApiInfo () {
        return new ApiInfoBuilder()
                .title("jiaopi dict swagger2")
                .description("jiaopi dict")
                .contact(new Contact("jiaopi", "https://github.com/jiaopi404", "jiaopi404@163.com"))
                .version("1.0.0")
                .build();
    }
}
