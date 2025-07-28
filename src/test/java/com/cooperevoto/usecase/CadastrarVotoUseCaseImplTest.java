package com.cooperevoto.usecase;

import com.cooperevoto.api.v1.dto.request.VotoRequest;
import com.cooperevoto.application.usecase.votacao.CadastrarVotoUseCaseImpl;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class CadastrarVotoUseCaseImplTest {

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private SessaoVotacaoRepository sessaoRepository;

    @InjectMocks
    private CadastrarVotoUseCaseImpl cadastrarVotoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveLancarExcecao_QuandoAssociadoJaVotou() {
        var request = new VotoRequest("12345678901", 1L, TipoVoto.SIM);

        when(votoRepository.existsByCpfAndPautaId(request.getCpf(), request.getPautaId()))
                .thenReturn(true);

        assertThrows(IllegalArgumentException.class, () ->
                cadastrarVotoUseCase.executar(request));
    }

    @Test
    void deveLancarExcecao_QuandoPautaNaoExiste() {
        var request = new VotoRequest("12345678901", 1L, TipoVoto.SIM);


        when(votoRepository.existsByCpfAndPautaId(request.getCpf(), request.getPautaId()))
                .thenReturn(false);

        when(pautaRepository.findById(request.getPautaId()))
                .thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> cadastrarVotoUseCase.executar(request));
    }

    @Test
    void deveLancarExcecao_QuandoSessaoNaoExiste() {
        var request = new VotoRequest("12345678901", 1L, TipoVoto.SIM);

        var pauta = new Pauta(1L, "titulo", "descricao", null);

        when(votoRepository.existsByCpfAndPautaId(request.getCpf(), request.getPautaId()))
                .thenReturn(false);

        when(pautaRepository.findById(1L))
                .thenReturn(Optional.of(pauta));

        when(sessaoRepository.findByPautaId(1L))
                .thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () ->
                cadastrarVotoUseCase.executar(request));
    }

    @Test
    void deveLancarExcecao_QuandoSessaoFechada() {
        var request = new VotoRequest("12345678901", 1L, TipoVoto.SIM);

        var pauta = new Pauta(1L, "titulo", "descricao", null);
        var sessao = SessaoVotacao.builder()
                .id(1L)
                .pauta(pauta)
                .dataAbertura(LocalDateTime.now().minusMinutes(10))
                .dataFechamento(LocalDateTime.now().minusMinutes(5))
                .build();

        when(votoRepository.existsByCpfAndPautaId(request.getCpf(), request.getPautaId()))
                .thenReturn(false);

        when(pautaRepository.findById(1L))
                .thenReturn(Optional.of(pauta));

        when(sessaoRepository.findByPautaId(1L))
                .thenReturn(Optional.of(sessao));

        assertThrows(IllegalStateException.class, () ->
                cadastrarVotoUseCase.executar(request));
    }
}
