package com.cooperevoto.application.service;


import com.cooperevoto.api.v1.dto.request.PautaRequest;
import com.cooperevoto.api.v1.dto.response.PautaResponse;
import com.cooperevoto.application.usecase.CadastrarPautaUseCase;
import com.cooperevoto.domain.repository.PautaRepository;
import com.cooperevoto.mapper.PautaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PautaService implements CadastrarPautaUseCase {

    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;

    @Override
    public PautaResponse executar(PautaRequest request) {
        var pauta = pautaMapper.toEntity(request);
        var salvo = pautaRepository.save(pauta);
        return pautaMapper.toResponse(salvo);
    }

    public PautaResponse cadastrar(PautaRequest request) {
        return executar(request);
    }
}

