package com.cooperevoto.api.v1.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PautaRequest {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String descricao;
}
