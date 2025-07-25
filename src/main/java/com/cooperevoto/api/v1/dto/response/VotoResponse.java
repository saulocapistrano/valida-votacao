package com.cooperevoto.api.v1.dto.response;

import com.cooperevoto.domain.enums.TipoVoto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoResponse {
    private Long id;
    private String cpf;
    private TipoVoto voto;
    private Long pautaId;
    private LocalDateTime dataVoto;
}
