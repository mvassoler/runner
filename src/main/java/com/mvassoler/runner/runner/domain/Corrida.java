package com.mvassoler.runner.runner.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Schema(name = "Corrida Input", description = "Payload de representação de uma prova")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Corrida implements Serializable {

    @Serial
    private static final long serialVersionUID = 8682261978453018236L;

    private UUID id;
    private String titulo;
    private String data;
    private String tempo;
    private Integer distancia;

}
