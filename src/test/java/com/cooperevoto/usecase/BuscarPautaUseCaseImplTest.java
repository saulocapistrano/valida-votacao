package com.cooperevoto.usecase;


import com.cooperevoto.api.v1.dto.response.PautaResponse;
import com.cooperevoto.application.usecase.pauta.BuscarPautaUseCaseImpl;
import com.cooperevoto.domain.model.Pauta;
import com.cooperevoto.domain.repository.PautaRepository;
import com.cooperevoto.mapper.PautaMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class BuscarPautaUseCaseImplTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private PautaMapper pautaMapper;

    @InjectMocks
    private BuscarPautaUseCaseImpl useCase;

    @Test
    void deveBuscarPautaPorIdExistente() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);

        PautaResponse esperado = new PautaResponse(1L, "titulo", "descricao");

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(pautaMapper.toResponse(pauta)).thenReturn(esperado);

        PautaResponse result = useCase.executar(1L);

        assertThat(result).isEqualTo(esperado);
        verify(pautaRepository).findById(1L);
    }


    @Test
    void deveLancarExcecaoSeIdNaoExiste() {
        when(pautaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> useCase.executar(999L));

        verify(pautaRepository).findById(999L);
    }
}

