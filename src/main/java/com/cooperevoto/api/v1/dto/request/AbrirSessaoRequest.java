package com.cooperevoto.api.v1.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AbrirSessaoRequest {

    @NotNull(message = "O ID da pauta é obrigatório")
    private Long pautaId;

    @Min(value = 1, message = "A duração mínima da sessão deve ser de 1 minuto")
    private Integer duracaoMinutos;


}
