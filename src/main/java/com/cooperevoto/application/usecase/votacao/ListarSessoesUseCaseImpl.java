package com.cooperevoto.application.usecase.votacao;


import com.cooperevoto.api.v1.dto.response.SessaoVotacaoResponse;
import com.cooperevoto.domain.repository.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarSessoesUseCaseImpl implements ListarSessoesUseCase {

    private final SessaoVotacaoRepository repository;

    @Override
    public List<SessaoVotacaoResponse> executar() {
        return repository.findAll().stream()
                .map(sessao -> new SessaoVotacaoResponse(
                        sessao.getId(),
                        sessao.getPauta().getId(),
                        sessao.getDataAbertura(),
                        sessao.getDataFechamento()))
                .collect(Collectors.toList());
    }
}