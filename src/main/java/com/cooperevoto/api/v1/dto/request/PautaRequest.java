package com.cooperevoto.api.v1.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PautaRequest {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String descricao;
}
