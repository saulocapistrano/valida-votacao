package com.cooperevoto.application.usecase.votacao;

import com.cooperevoto.api.v1.dto.request.AbrirSessaoRequest;
import com.cooperevoto.api.v1.dto.response.SessaoVotacaoResponse;

public interface AbrirSessaoUseCase {
    SessaoVotacaoResponse executar(Long pautaId, AbrirSessaoRequest request);
}