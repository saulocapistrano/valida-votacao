package com.cooperevoto.application.usecase.votacao;

import com.cooperevoto.api.v1.dto.response.SessaoVotacaoResponse;
import java.util.List;

public interface ListarSessoesUseCase {
    List<SessaoVotacaoResponse> executar();
}