package com.mvassoler.runner.runner.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(name = "Vo2 Input", description = "Payload de representação de um teste de Vo2")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vo2Test implements Serializable {

    @Serial
    private static final long serialVersionUID = -413379050923787965L;

    private UUID id;
    private OffsetDateTime data;
    private BigDecimal result;
    private Corrida corrida;


}
