package com.cooperevoto.application.usecase.pauta;


import com.cooperevoto.api.v1.dto.response.PautaResponse;

import com.cooperevoto.domain.repository.PautaRepository;
import com.cooperevoto.mapper.PautaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarPautasUseCaseImpl implements ListarPautasUseCase {

    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;

    @Override
    public List<PautaResponse> executar(String titulo) {
        return pautaRepository.findByTituloContainingIgnoreCase(titulo == null ? "" : titulo)
                .stream()
                .map(pautaMapper::toResponse)
                .collect(Collectors.toList());
    }
}