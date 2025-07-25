package com.cooperevoto.application.usecase.pauta;

import com.cooperevoto.api.v1.dto.request.PautaRequest;
import com.cooperevoto.api.v1.dto.response.PautaResponse;
import com.cooperevoto.domain.model.Pauta;
import com.cooperevoto.domain.repository.PautaRepository;
import com.cooperevoto.mapper.PautaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarPautaUseCaseImpl implements CadastrarPautaUseCase {

    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;

    @Override
    public PautaResponse executar(PautaRequest request) {
        Pauta pauta = pautaMapper.toEntity(request);
        pauta = pautaRepository.save(pauta);
        return pautaMapper.toResponse(pauta);
    }
}
