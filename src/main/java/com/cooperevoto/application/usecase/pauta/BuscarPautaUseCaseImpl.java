package com.cooperevoto.application.usecase.pauta;

import com.cooperevoto.api.v1.dto.response.PautaResponse;
import com.cooperevoto.domain.model.Pauta;
import com.cooperevoto.domain.repository.PautaRepository;
import com.cooperevoto.mapper.PautaMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class BuscarPautaUseCaseImpl implements BuscarPautaUseCase {

    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;

    @Override
    public PautaResponse executar(Long id) {
        Pauta pauta = pautaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada"));
        return pautaMapper.toResponse(pauta);
    }
}
