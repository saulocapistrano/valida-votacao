package com.cooperevoto.usecase;


import com.cooperevoto.application.usecase.pauta.CadastrarPautaUseCaseImpl;
import com.cooperevoto.domain.model.Pauta;
import com.cooperevoto.domain.repository.PautaRepository;
import com.cooperevoto.api.v1.dto.request.PautaRequest;
import com.cooperevoto.api.v1.dto.response.PautaResponse;
import com.cooperevoto.mapper.PautaMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastrarPautaUseCaseImplTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private PautaMapper pautaMapper;

    @InjectMocks
    private CadastrarPautaUseCaseImpl useCase;

    @Test
    void deveCadastrarNovaPauta() {
        PautaRequest request = new PautaRequest("titulo teste", "descricao teste");
        Pauta pautaEntity = new Pauta();
        Pauta pautaSalva = new Pauta();
        pautaSalva.setId(1L);
        PautaResponse responseEsperado = new PautaResponse(1L, "titulo teste", "descricao teste");

        when(pautaMapper.toEntity(request)).thenReturn(pautaEntity);
        when(pautaRepository.save(pautaEntity)).thenReturn(pautaSalva);
        when(pautaMapper.toResponse(pautaSalva)).thenReturn(responseEsperado);

        PautaResponse response = useCase.executar(request);

        assertThat(response.getId()).isEqualTo(1L);
        verify(pautaRepository).save(pautaEntity);
    }
}

