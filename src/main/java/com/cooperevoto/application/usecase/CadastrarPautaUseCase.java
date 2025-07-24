package com.cooperevoto.application.usecase;

import com.cooperevoto.api.v1.dto.request.PautaRequest;
import com.cooperevoto.api.v1.dto.response.PautaResponse;

public interface CadastrarPautaUseCase {
    PautaResponse executar(PautaRequest request);
}
