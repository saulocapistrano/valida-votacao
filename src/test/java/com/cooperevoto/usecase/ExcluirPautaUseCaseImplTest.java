package com.cooperevoto.usecase;


import com.cooperevoto.application.usecase.pauta.ExcluirPautaUseCaseImpl;
import com.cooperevoto.domain.repository.PautaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExcluirPautaUseCaseImplTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private ExcluirPautaUseCaseImpl useCase;

    @Test
    void deveExcluirSePautaExistir() {
        when(pautaRepository.existsById(1L)).thenReturn(true);

        boolean excluido = useCase.executar(1L);

        assertThat(excluido).isTrue();
        verify(pautaRepository).deleteById(1L);
    }

    @Test
    void naoDeveExcluirSePautaNaoExistir() {
        when(pautaRepository.existsById(99L)).thenReturn(false);

        boolean excluido = useCase.executar(99L);

        assertThat(excluido).isFalse();
        verify(pautaRepository, never()).deleteById(anyLong());
    }
}
