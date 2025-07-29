package com.cooperevoto.integration;

import com.cooperevoto.api.v1.dto.request.AbrirSessaoRequest;
import com.cooperevoto.api.v1.dto.request.VotoRequest;
import com.cooperevoto.domain.enums.TipoVoto;
import com.cooperevoto.domain.model.Pauta;
import com.cooperevoto.domain.repository.PautaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class VotoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PautaRepository pautaRepository;

    private Long pautaId;

    @BeforeEach
    void setUp() throws Exception {
        Pauta pauta = pautaRepository.save(new Pauta(1L, "titulo", "descricao", null));
        pautaId = pauta.getId();

        AbrirSessaoRequest abrirSessao = new AbrirSessaoRequest(pautaId, 5);

        mockMvc.perform(post("/api/v1/pautas/" + pautaId + "/sessao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(abrirSessao)))
                .andExpect(status().isCreated());
    }

    @Test
    void deveRegistrarVotoComSucesso() throws Exception {
        VotoRequest voto = new VotoRequest("12345678900", pautaId, TipoVoto.SIM);

        mockMvc.perform(post("/api/v1/votos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(voto)))
                .andExpect(status().isCreated());
    }

    @Test
    void naoDevePermitirVotoDuplicadoPorCpf() throws Exception {
        VotoRequest voto = new VotoRequest("98765432100", pautaId, TipoVoto.NAO);

        mockMvc.perform(post("/api/v1/votos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(voto)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/votos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(voto)))
                .andExpect(status().isConflict());
    }

    @Test
    void naoDevePermitirVotoForaDaSessao() throws Exception {
        // Aqui poderíamos fechar a sessão manualmente caso a lógica permitisse
        // ou simular com mock uma data futura
        // Esse caso está como placeholder se precisar avançar o clock
    }
}

