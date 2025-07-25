package com.cooperevoto.application.usecase.pauta;

import com.cooperevoto.api.v1.dto.request.PautaRequest;
import com.cooperevoto.api.v1.dto.response.PautaResponse;

public interface CadastrarPautaUseCase {
    PautaResponse executar(PautaRequest request);
}
