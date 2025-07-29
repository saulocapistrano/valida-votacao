package com.cooperevoto.application.usecase.votacao;

import com.cooperevoto.api.v1.dto.response.ResultadoVotacaoResponse;
import com.cooperevoto.domain.enums.TipoVoto;
import com.cooperevoto.domain.repository.PautaRepository;
import com.cooperevoto.domain.repository.SessaoVotacaoRepository;
import com.cooperevoto.domain.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterResultadoVotacaoUseCaseImpl implements ObterResultadoVotacaoUseCase {

    private final PautaRepository pautaRepository;
    private final SessaoVotacaoRepository sessaoRepository;
    private final VotoRepository votoRepository;

    @Override
    public ResultadoVotacaoResponse executar(Long pautaId) {
        var pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new IllegalArgumentException("Pauta não encontrada"));

        var sessao = sessaoRepository.findByPautaId(pautaId)
                .orElseThrow(() -> new IllegalStateException("Sessão não encontrada"));

        if (sessao.getDataFechamento().isAfter(java.time.LocalDateTime.now())) {
            throw new IllegalStateException("Sessão ainda não foi encerrada");
        }

        long totalSim = votoRepository.countByPautaIdAndVoto(pautaId, TipoVoto.SIM);
        long totalNao = votoRepository.countByPautaIdAndVoto(pautaId, TipoVoto.NAO);
        long total = totalSim + totalNao;

        String resultado;
        if (totalSim > totalNao) {
            resultado = "SIM";
        } else if (totalNao > totalSim) {
            resultado = "NÃO";
        } else {
            resultado = "EMPATE";
        }

        pauta.setResultado(resultado);
        pautaRepository.save(pauta);

        return new ResultadoVotacaoResponse(pautaId, totalSim, totalNao, total, resultado);
    }
}
