package com.mvassoler.runner.runner.config;


import com.mvassoler.runner.runner.domain.Corrida;
import com.mvassoler.runner.runner.domain.ErrorDetailsDTO;
import com.mvassoler.runner.runner.domain.Prova;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@SecurityScheme(
        name = "Chave Bearer",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfiguration {

    private static final String BAD_REQUEST_RESPONSE = "BadRequestResponse";
    private static final String NOT_FOUND_RESPONSE = "NotFoundResponse";
    private static final String NOT_AUTHORIZED_RESPONSE = "NotAuthorizedResponse";
    private static final String NOT_ACCEPTABLE_RESPONSE = "NotAcceptableResponse";
    private static final String INTERNAL_SERVER_ERROR_RESPONSE = "InternalServerErrorResponse";

    @Bean
    GroupedOpenApi runners() {
        return GroupedOpenApi
                .builder()
                .group("Runners")
                .pathsToMatch(
                        "/corrida/**",
                        "/prova/**"
                )
                .addOpenApiCustomizer(openApi -> {
                    openApi.info(new Info()
                                    .title("Runner API")
                                    .description("Gestão dos treinos e provas para corredores de rua")
                                    .version("1.0")
                                    .license(
                                            new License()
                                                    .name("Apache 2.0")
                                                    .url("http://springdoc.com")
                                    )
                            ).tags(
                                    Arrays.asList(
                                            this.newTag("Provas", "API para a manutenção das provas oficiais da corrida de rua"),
                                            this.newTag("Corridas", "API para a manutenção dos treinos da corrida de rua")
                                    )
                            )

                            .components(new Components().schemas(this.gerarSchemas())
                                    .responses(this.gerarResponses()
                                    )
                            )
                            .getPaths()
                            .values()
                            .forEach(pathItem -> pathItem.readOperationsMap()
                                    .forEach(this::setListResponses)
                            );
                })
                .build();
    }


    private Tag newTag(String name, String description) {
        return new Tag().name(name).description(description);
    }


    private void setListResponses(PathItem.HttpMethod method, Operation operation) {
        ApiResponses responses = operation.getResponses();
        switch (method) {
            case GET -> {
                responses.addApiResponse("400", new ApiResponse().$ref(BAD_REQUEST_RESPONSE));
                responses.addApiResponse("401", new ApiResponse().$ref(NOT_AUTHORIZED_RESPONSE));
                responses.addApiResponse("404", new ApiResponse().$ref(NOT_FOUND_RESPONSE));
                responses.addApiResponse("406", new ApiResponse().$ref(NOT_ACCEPTABLE_RESPONSE));
                responses.addApiResponse("500", new ApiResponse().$ref(INTERNAL_SERVER_ERROR_RESPONSE));
            }
            case POST, PUT -> {
                responses.addApiResponse("400", new ApiResponse().$ref(BAD_REQUEST_RESPONSE));
                responses.addApiResponse("401", new ApiResponse().$ref(NOT_AUTHORIZED_RESPONSE));
                responses.addApiResponse("404", new ApiResponse().$ref(NOT_FOUND_RESPONSE));
                responses.addApiResponse("406", new ApiResponse().$ref(NOT_ACCEPTABLE_RESPONSE));
            }
            default -> {
                responses.addApiResponse("400", new ApiResponse().$ref(BAD_REQUEST_RESPONSE));
                responses.addApiResponse("401", new ApiResponse().$ref(NOT_AUTHORIZED_RESPONSE));
                responses.addApiResponse("404", new ApiResponse().$ref(NOT_FOUND_RESPONSE));
            }
        }
    }

    private Map<String, Schema> gerarSchemas() {
        final Map<String, Schema> schemaMap = new HashMap<>();

        Map<String, Schema> corrida = ModelConverters.getInstance().read(Corrida.class);
        Map<String, Schema> prova = ModelConverters.getInstance().read(Prova.class);
        Map<String, Schema> problemSchema = ModelConverters.getInstance().read(ErrorDetailsDTO.class);
        Map<String, Schema> problemObjectSchema = ModelConverters.getInstance().read(ErrorDetailsDTO.Object.class);

        schemaMap.putAll(corrida);
        schemaMap.putAll(prova);
        schemaMap.putAll(problemSchema);
        schemaMap.putAll(problemObjectSchema);

        return schemaMap;
    }

    private Map<String, ApiResponse> gerarResponses() {
        final Map<String, ApiResponse> apiResponseMap = new HashMap<>();

        Content content = new Content()
                .addMediaType(APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().schema(new Schema<ErrorDetailsDTO>().$ref("Problema")));

        apiResponseMap.put(BAD_REQUEST_RESPONSE, new ApiResponse()
                .description("Requisição inválida")
                .content(content));

        apiResponseMap.put(NOT_FOUND_RESPONSE, new ApiResponse()
                .description("Recurso não encontrado")
                .content(content));

        apiResponseMap.put(NOT_AUTHORIZED_RESPONSE, new ApiResponse()
                .description("Recurso não encontrado")
                .content(content));

        apiResponseMap.put(NOT_ACCEPTABLE_RESPONSE, new ApiResponse()
                .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .content(content));

        apiResponseMap.put(INTERNAL_SERVER_ERROR_RESPONSE, new ApiResponse()
                .description("Erro interno no servidor")
                .content(content));

        return apiResponseMap;
    }


}