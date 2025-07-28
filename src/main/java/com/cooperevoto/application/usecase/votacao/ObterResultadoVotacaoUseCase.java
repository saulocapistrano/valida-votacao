package com.cooperevoto.application.usecase.votacao;


import com.cooperevoto.api.v1.dto.response.ResultadoVotacaoResponse;

public interface ObterResultadoVotacaoUseCase {
    ResultadoVotacaoResponse executar(Long pautaId);
}
