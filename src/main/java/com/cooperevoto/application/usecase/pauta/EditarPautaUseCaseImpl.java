package com.cooperevoto.application.usecase.pauta;

import com.cooperevoto.api.v1.dto.request.PautaRequest;
import com.cooperevoto.api.v1.dto.response.PautaResponse;
import com.cooperevoto.domain.model.Pauta;
import com.cooperevoto.domain.repository.PautaRepository;
import com.cooperevoto.mapper.PautaMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditarPautaUseCaseImpl implements EditarPautaUseCase {

    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;

    @Override
    public PautaResponse executar(Long id, PautaRequest request) {
        Pauta pauta = pautaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada"));
        pauta.setTitulo(request.getTitulo());
        pauta.setDescricao(request.getDescricao());
        pauta = pautaRepository.save(pauta);
        return pautaMapper.toResponse(pauta);
    }
}