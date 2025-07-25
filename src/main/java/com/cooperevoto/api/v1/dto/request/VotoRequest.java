package com.cooperevoto.api.v1.dto.request;

import com.cooperevoto.domain.enums.TipoVoto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoRequest {
    @NotBlank
    private String cpf;

    @NotNull
    private Long pautaId;

    @NotNull
    private TipoVoto voto;
}
