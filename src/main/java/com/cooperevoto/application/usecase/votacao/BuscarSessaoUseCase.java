package com.cooperevoto.application.usecase.votacao;

import com.cooperevoto.api.v1.dto.response.SessaoVotacaoResponse;

public interface BuscarSessaoUseCase {
    SessaoVotacaoResponse executar(Long id);
}
