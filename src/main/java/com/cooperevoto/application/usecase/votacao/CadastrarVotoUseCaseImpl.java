package com.cooperevoto.application.usecase.votacao;

import com.cooperevoto.api.v1.dto.request.VotoRequest;
import com.cooperevoto.api.v1.dto.response.VotoResponse;
import com.cooperevoto.domain.model.Voto;
import com.cooperevoto.domain.repository.PautaRepository;
import com.cooperevoto.domain.repository.SessaoVotacaoRepository;
import com.cooperevoto.domain.repository.VotoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CadastrarVotoUseCaseImpl implements CadastrarVotoUseCase {

    private final VotoRepository votoRepository;
    private final PautaRepository pautaRepository;
    private final SessaoVotacaoRepository sessaoRepository;

    @Override
    public VotoResponse executar(VotoRequest request) {
        if (votoRepository.existsByCpfAndPautaId(request.getCpf(), request.getPautaId())) {
            throw new IllegalArgumentException("Associado já votou nesta pauta.");
        }

        var pauta = pautaRepository.findById(request.getPautaId())
                .orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada"));

        var sessao = sessaoRepository.findByPautaId(request.getPautaId())
                .orElseThrow(() -> new IllegalStateException("Sessão não encontrada"));

        var agora = LocalDateTime.now();
        if (agora.isAfter(sessao.getDataFechamento())) {
            throw new IllegalStateException("Sessão de votação encerrada.");
        }

        var voto = Voto.builder()
                .cpf(request.getCpf())
                .voto(request.getVoto())
                .pauta(pauta)
                .sessao(sessao)
                .dataVoto(agora)
                .build();

        var salvo = votoRepository.save(voto);

        return new VotoResponse(
                salvo.getId(),
                salvo.getCpf(),
                salvo.getVoto(),
                salvo.getPauta().getId(),
                salvo.getDataVoto()
        );
    }
}
