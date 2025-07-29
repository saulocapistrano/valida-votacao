package com.cooperevoto.usecase;


import com.cooperevoto.api.v1.dto.request.PautaRequest;
import com.cooperevoto.api.v1.dto.response.PautaResponse;
import com.cooperevoto.application.usecase.pauta.EditarPautaUseCaseImpl;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class EditarPautaUseCaseImplTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private PautaMapper pautaMapper;

    @InjectMocks
    private EditarPautaUseCaseImpl useCase;

    @Test
    void deveEditarPautaExistente() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);

        PautaRequest request = new PautaRequest("novo titulo", "nova desc");
        Pauta atualizada = new Pauta();
        atualizada.setId(1L);
        atualizada.setTitulo("novo titulo");
        atualizada.setDescricao("nova desc");

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(pautaRepository.save(any())).thenReturn(atualizada);
        when(pautaMapper.toResponse(atualizada)).thenReturn(new PautaResponse(1L, "novo titulo", "nova desc"));

        PautaResponse result = useCase.executar(1L, request);

        assertThat(result).isNotNull();
        assertThat(result.getTitulo()).isEqualTo("novo titulo");

    }

    @Test
    void deveLancarExcecaoSePautaNaoExiste() {
        when(pautaRepository.findById(42L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                useCase.executar(42L, new PautaRequest("x", "y"))
        );
    }
}
