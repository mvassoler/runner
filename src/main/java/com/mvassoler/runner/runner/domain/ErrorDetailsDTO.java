package com.mvassoler.runner.runner.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;


@Schema(name = "Problema", description = "Payload de representação de falha da requisição da API")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetailsDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7766453300222693652L;

    @Schema(name = "status", description = "Status HTTP", example = "400")
    @JsonProperty("status")
    private Integer status;

    @Schema(name = "time_stamp", description = "Data e hora", example = "2022-07-15T11:21:50.902245498Z")
    @JsonProperty("time_stamp")
    private OffsetDateTime timestamp;

    @Schema(name = "type", description = "Tipo da falha", example = "NULL")
    @JsonProperty("type")
    private String type;

    @Schema(name = "title", description = "Titulo da mensagem", example = "Dados inválidos")
    @JsonProperty("title")
    private String title;

    @Schema(name = "detail", description = "Detalhes da mensagem", example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    @JsonProperty("detail")
    private String detail;

    @Schema(name = "user_message", description = "Mensagem", example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    @JsonProperty("user_message")
    private String userMessage;

    @Schema(name = "object", description = "Objetos relacionados ao problema")
    @JsonProperty("object")
    private List<Object> objects;

    @Getter
    @Builder
    @Schema(name = "ObjetoProblema")
    public static class Object implements Serializable {

        @Serial
        private static final long serialVersionUID = -6695081971534944234L;

        @Schema(name = "name", description = "Nome do objeto", example = "descrição")
        @JsonProperty("name")
        private String name;

        @Schema(name = "user_message", description = "Mensagem do objeto", example = "Descrição obrigatória.")
        @JsonProperty("user_message")
        private String userMessage;

    }

}