package com.cooperevoto.usecase;


import com.cooperevoto.api.v1.dto.response.PautaResponse;
import com.cooperevoto.application.usecase.pauta.ListarPautasUseCaseImpl;
import com.cooperevoto.domain.model.Pauta;
import com.cooperevoto.domain.repository.PautaRepository;
import com.cooperevoto.mapper.PautaMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarPautasUseCaseImplTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private PautaMapper pautaMapper;

    @InjectMocks
    private ListarPautasUseCaseImpl useCase;

    @Test
    void deveListarPautasPorTitulo() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        when(pautaRepository.findByTituloContainingIgnoreCase("consulta"))
                .thenReturn(List.of(pauta));
        when(pautaMapper.toResponse(any())).thenReturn(new PautaResponse(1L, "consulta", "descricao"));

        List<PautaResponse> result = useCase.executar("consulta");

        assertThat(result).hasSize(1);
        verify(pautaRepository).findByTituloContainingIgnoreCase("consulta");
    }

    @Test
    void deveListarTodasSeTituloForNull() {
        when(pautaRepository.findByTituloContainingIgnoreCase(anyString()))
                .thenReturn(Collections.emptyList());

        List<PautaResponse> result = useCase.executar(null);
        assertThat(result).isEmpty();
    }
}
