package com.kaden.iot.serviceapi;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String API_TITLE = "Service用のAPI";
    private static final String API_DESCRIPTION = "Service用のIF仕様書";

    /**
     * swagger ドキュメント
     *
     * @return Docket
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(newHashSet("application/json"))
                .apiInfo(apiInfo())
                .select()
                .paths(paths())
                .build()
                .protocols(newHashSet("https"))
                .enableUrlTemplating(false)
                .genericModelSubstitutes(ResponseEntity.class, Optional.class)
                .useDefaultResponseMessages(false)
                // メソッド毎の共通エラーレスポンスを定義
                .globalResponseMessage(RequestMethod.GET, getErrorResponses())
                .globalResponseMessage(RequestMethod.POST, getErrorResponses())
                .globalResponseMessage(RequestMethod.PUT, getErrorResponses())
                .globalResponseMessage(RequestMethod.DELETE, getErrorResponses())
                .globalResponseMessage(RequestMethod.PATCH, getErrorResponses());
    }

    /**
     * APIの基本情報を定義
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version("1.0.0")
                .build();
    }

    /**
     * swagger ドキュメント対象のパスを定義
     *
     * @return {@code Predicate<String>}
     */
    private Predicate paths() {
        // ExceptionHandler内も対象になるため、/error以外のAPIを対象にする
        // actuatorエンドポイントを追加したため除外対象に追加
        return Predicates.not(Predicates.contains(Pattern.compile("^/error|^/actuator/?.*")));
        // ant("/v1/**") や regex("^/(?!error)$")などでも定義可能
    }

    /**
     * 共通エラーレスポンスを定義
     *
     * @return {@code List<ResponseMessage>}
     */
    private List<ResponseMessage> getErrorResponses() {
        return newArrayList(
                createErrorResponseMessage(400, "パラメータエラー", "Swagger400ErrorResource"),
                createErrorResponseMessage(500, "システムエラー", "Swagger500ErrorResource")
        );
    }

    /**
     * ResponseMessageを生成する
     *
     * @param code          httpステータスコード
     * @param message       表示用テキスト
     * @param resourceClass レスポンスリソースクラス
     * @return ResponseMessage
     */
    private ResponseMessage createErrorResponseMessage(int code, String message, String resourceClass) {
        return new ResponseMessageBuilder()
                .code(code)
                .message(message)
                .responseModel(new ModelRef(resourceClass))
                .build();
    }
}
