package com.cooperevoto.application.usecase.pauta;

import com.cooperevoto.api.v1.dto.response.PautaResponse;

import java.util.List;

public interface ListarPautasUseCase {
    List<PautaResponse> executar(String titulo);
}