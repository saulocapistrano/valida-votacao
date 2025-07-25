package com.cooperevoto.integration;


import com.cooperevoto.api.v1.dto.request.AbrirSessaoRequest;
import com.cooperevoto.domain.model.Pauta;
import com.cooperevoto.domain.repository.PautaRepository;
import com.cooperevoto.domain.repository.SessaoVotacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SessaoVotacaoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private SessaoVotacaoRepository sessaoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Pauta pauta;

    @BeforeEach
    void setUp() {
        sessaoRepository.deleteAll();
        pautaRepository.deleteAll();
        pauta = pautaRepository.save(new Pauta(null, "Sessão Teste", "Pauta com sessão"));
    }

    @Test
    void deveAbrirSessaoParaPauta() throws Exception {
        AbrirSessaoRequest request = new AbrirSessaoRequest(pauta.getId(), 2);

        mockMvc.perform(post("/api/v1/pautas/{id}/sessao", pauta.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.pautaId").value(pauta.getId()))
                .andExpect(jsonPath("$.dataAbertura").exists())
                .andExpect(jsonPath("$.dataFechamento").exists());
    }

    @Test
    void deveBuscarSessaoPorId() throws Exception {
        var sessao = sessaoRepository.save(new com.cooperevoto.domain.model.SessaoVotacao(null, pauta, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5)));

        mockMvc.perform(get("/api/v1/sessoes/{id}", sessao.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sessao.getId()))
                .andExpect(jsonPath("$.pautaId").value(pauta.getId()));
    }

    @Test
    void deveListarTodasSessoes() throws Exception {
        sessaoRepository.save(new com.cooperevoto.domain.model.SessaoVotacao(null, pauta, LocalDateTime.now(), LocalDateTime.now().plusMinutes(1)));

        mockMvc.perform(get("/api/v1/sessoes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }
}
