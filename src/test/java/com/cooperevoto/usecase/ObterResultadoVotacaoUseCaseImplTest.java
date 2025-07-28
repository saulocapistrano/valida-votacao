package com.cooperevoto.usecase;

import com.cooperevoto.application.usecase.votacao.ObterResultadoVotacaoUseCaseImpl;
import com.cooperevoto.domain.enums.TipoVoto;
import com.cooperevoto.domain.model.Pauta;
import com.cooperevoto.domain.model.SessaoVotacao;
import com.cooperevoto.domain.repository.PautaRepository;
import com.cooperevoto.domain.repository.SessaoVotacaoRepository;
import com.cooperevoto.domain.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ActiveProfiles("test")
class ObterResultadoVotacaoUseCaseImplTest {

    @Mock
    private SessaoVotacaoRepository sessaoRepository;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private PautaRepository pautaRepository;
    @InjectMocks
    private ObterResultadoVotacaoUseCaseImpl useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveLancarExcecao_QuandoSessaoNaoEncontrada() {
        when(pautaRepository.findById(1L)).thenReturn(Optional.of(new Pauta(1L, "titulo", "descricao", null)));
        when(sessaoRepository.findByPautaId(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> useCase.executar(1L));
    }

    @Test
    void deveLancarExcecao_QuandoSessaoAindaAberta() {
        var pauta = new Pauta(1L, "titulo", "descricao", null);
        SessaoVotacao sessao = SessaoVotacao.builder()
                .id(1L)
                .pauta(pauta)
                .dataAbertura(LocalDateTime.now().minusMinutes(1))
                .dataFechamento(LocalDateTime.now().plusMinutes(5))
                .build();

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(sessaoRepository.findByPautaId(1L)).thenReturn(Optional.of(sessao));

        assertThrows(IllegalStateException.class, () -> useCase.executar(1L));
    }

    @Test
    void deveRetornarResultado_QuandoSessaoFechada() {
        var pauta = new Pauta(1L, "titulo", "descricao", null);
        SessaoVotacao sessao = SessaoVotacao.builder()
                .id(1L)
                .pauta(pauta)
                .dataAbertura(LocalDateTime.now().minusMinutes(10))
                .dataFechamento(LocalDateTime.now().minusMinutes(1))
                .build();

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(sessaoRepository.findByPautaId(1L)).thenReturn(Optional.of(sessao));
        when(votoRepository.countByPautaIdAndVoto(1L, TipoVoto.SIM)).thenReturn(5L);
        when(votoRepository.countByPautaIdAndVoto(1L, TipoVoto.NAO)).thenReturn(2L);

        var response = useCase.executar(1L);

        assertEquals(5L, response.getVotosSim());
        assertEquals(2L, response.getVotosNao());
    }
}
