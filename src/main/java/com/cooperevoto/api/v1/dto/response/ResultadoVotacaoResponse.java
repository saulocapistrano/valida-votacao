package com.cooperevoto.api.v1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoVotacaoResponse {
    private Long pautaId;
    private long votosSim;
    private long votosNao;
    private long totalVotos;
    private String resultado;
}