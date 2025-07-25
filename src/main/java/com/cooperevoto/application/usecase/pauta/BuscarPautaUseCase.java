package com.cooperevoto.application.usecase.pauta;

import com.cooperevoto.api.v1.dto.response.PautaResponse;

public interface BuscarPautaUseCase {
    PautaResponse executar(Long id);
}