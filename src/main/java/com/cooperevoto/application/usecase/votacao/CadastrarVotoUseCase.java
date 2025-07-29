package com.cooperevoto.application.usecase.votacao;

import com.cooperevoto.api.v1.dto.request.VotoRequest;
import com.cooperevoto.api.v1.dto.response.VotoResponse;

public interface CadastrarVotoUseCase {
    VotoResponse executar(VotoRequest request);
}