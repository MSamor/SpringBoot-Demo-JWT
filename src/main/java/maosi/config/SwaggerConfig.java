package maosi.config;

import com.google.common.base.Predicates;
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

@Configuration
@EnableSwagger2 // 启用 Swagger2
public class SwaggerConfig {

    @Value("V1.0")
    private String systemPublish;

    /**
     * swagger2的配置文件
     * @return Docket
     */
    @Bean
    public Docket hotelApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("酒店管理系统API")
                .pathMapping("/")
                .select()
                    .apis(RequestHandlerSelectors.basePackage("maosi"))
                    //不监控 测试api
                    .paths(Predicates.not(PathSelectors.regex("/Ex.*")))
                    //过滤的接口
                    .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(hotelApiInfo());
    }

    /**
     * 设置学生api信息
     * @return Api构建
     */
    private ApiInfo hotelApiInfo() {
        return new ApiInfoBuilder()
                // 设置页面标题
                .title("酒店管理系统")
                // 定义版本号
                .version(systemPublish)
                // 设置联系人
                .contact(
                        new Contact("魏鹏  ->个人主页", "http://www.maosi.vip", "seemingly98@qq.com"))
                // 描述
                .description("酒店管理系统-API")
                .build();
    }

//    /**
//     * swagger2的配置文件
//     * 设置教师Api组
//     * @return Docket
//     */
//    @Bean
//    public Docket teacherApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("TeacherAPI")
//                .pathMapping("/")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com"))
//                //不监控 测试api
//                .paths(Predicates.not(PathSelectors.regex("/Ex.*")))
//                //过滤的接口
//                .paths(PathSelectors.regex("/.*"))
//                .build()
//                .apiInfo(studentApiInfo());
//    }
//
//    /**
//     * 设置老师api信息
//     * @return Api构建
//     */
//    private ApiInfo teacherApiInfo() {
//        return new ApiInfoBuilder()
//                // 设置页面标题
//                .title("学生就业信息管理系统")
//                // 定义版本号
//                .version(systemPublish)
//                // 设置联系人
//                .contact(
//                        new Contact("Liyang  ->GitHub", "https://github.com/MyGentleLife", "1519365158@qq.com"))
//                // 描述
//                .description("学生个人信息管理-教师API")
//                .build();
//    }

}
