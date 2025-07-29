package com.cooperevoto.api.v1.dto.request;

import com.cooperevoto.domain.enums.TipoVoto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoRequest {
    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
    private String cpf;

    @NotNull(message = "O ID da pauta é obrigatório")
    private Long pautaId;

    @NotNull(message = "O tipo de voto é obrigatório")
    private TipoVoto voto;
}
