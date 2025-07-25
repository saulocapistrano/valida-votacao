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

    @NotNull
    private Long pautaId;

    @Min(1)
    private Integer duracaoMinutos;


}
