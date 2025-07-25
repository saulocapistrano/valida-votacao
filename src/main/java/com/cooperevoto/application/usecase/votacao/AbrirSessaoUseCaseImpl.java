package com.cooperevoto.application.usecase.votacao;


import com.cooperevoto.api.v1.dto.request.AbrirSessaoRequest;
import com.cooperevoto.api.v1.dto.response.SessaoVotacaoResponse;
import com.cooperevoto.domain.model.SessaoVotacao;
import com.cooperevoto.domain.repository.PautaRepository;
import com.cooperevoto.domain.repository.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AbrirSessaoUseCaseImpl implements AbrirSessaoUseCase {

    private final SessaoVotacaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;

    @Override
    public SessaoVotacaoResponse executar(Long pautaId, AbrirSessaoRequest request) {
        var pauta = pautaRepository.findById(pautaId)
                .orElseThrow();

        if (sessaoRepository.existsByPautaId(pautaId)) {
            throw new IllegalStateException("Sessão já aberta para esta pauta");
        }

        int duracao = request != null && request.getDuracaoMinutos() != null
                ? request.getDuracaoMinutos()
                : 1;


        var abertura = LocalDateTime.now();
        var fechamento = abertura.plusMinutes(duracao);

        var sessao = SessaoVotacao.builder()
                .pauta(pauta)
                .dataAbertura(abertura)
                .dataFechamento(fechamento)
                .build();

        var salvo = sessaoRepository.save(sessao);

        return new SessaoVotacaoResponse(salvo.getId(), pautaId, abertura, fechamento);
    }
}