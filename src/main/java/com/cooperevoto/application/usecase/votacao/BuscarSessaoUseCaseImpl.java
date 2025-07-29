package com.cooperevoto.application.usecase.votacao;

import com.cooperevoto.api.v1.dto.response.SessaoVotacaoResponse;
import com.cooperevoto.domain.repository.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarSessaoUseCaseImpl implements BuscarSessaoUseCase {

    private final SessaoVotacaoRepository repository;

    @Override
    public SessaoVotacaoResponse executar(Long id) {
        var sessao = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

        return new SessaoVotacaoResponse(
                sessao.getId(),
                sessao.getPauta().getId(),
                sessao.getDataAbertura(),
                sessao.getDataFechamento()
        );
    }
}