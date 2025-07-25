package com.cooperevoto.api.v1;

import com.cooperevoto.api.v1.dto.request.PautaRequest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PautaControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        pautaRepository.deleteAll();
    }

    @Test
    void deveCadastrarNovaPauta() throws Exception {
        PautaRequest request = new PautaRequest("Título Teste", "Descrição Teste");

        mockMvc.perform(post("/api/v1/pautas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Título Teste"));
    }

    @Test
    void deveBuscarPautaPorId() throws Exception {
        Pauta pauta = new Pauta();
        pauta.setTitulo("Titulo");
        pauta.setDescricao("Descricao");
        pauta = pautaRepository.save(pauta);

        mockMvc.perform(get("/api/v1/pautas/{id}", pauta.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Titulo"));
    }


    @Test
    void deveAtualizarPauta() throws Exception {
        var pauta = new Pauta();
        pauta.setTitulo("Velho");
        pauta.setDescricao("Desc");
        pauta = pautaRepository.save(pauta);

        PautaRequest request = new PautaRequest("Novo", "Nova desc");

        mockMvc.perform(put("/api/v1/pautas/{id}", pauta.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Novo"));
    }


    @Test
    void deveExcluirPauta() throws Exception {
        var pauta = new Pauta();
        pauta.setTitulo("Excluir");
        pauta.setDescricao("algo");
        pauta = pautaRepository.save(pauta);

        mockMvc.perform(delete("/api/v1/pautas/{id}", pauta.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveListarPautas() throws Exception {
        Pauta pauta = new Pauta();
        pauta.setTitulo("Uma");
        pauta.setDescricao("desc");
        pautaRepository.save(pauta);

        mockMvc.perform(get("/api/v1/pautas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

}
