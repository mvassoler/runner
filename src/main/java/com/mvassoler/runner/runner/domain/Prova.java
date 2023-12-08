package com.mvassoler.runner.runner.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Schema(name = "Prova Input", description = "Payload de representação de uma prova")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Prova implements Serializable {

    @Serial
    private static final long serialVersionUID = -7226030265997019817L;

    private UUID id;
    private String titulo;
    private String cidade;
    private String data;
    private String distancia;

}
