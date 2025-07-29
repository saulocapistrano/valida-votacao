package com.cooperevoto.api.v1.controller;

import com.cooperevoto.api.v1.dto.request.AbrirSessaoRequest;
import com.cooperevoto.api.v1.dto.response.SessaoVotacaoResponse;
import com.cooperevoto.application.usecase.votacao.AbrirSessaoUseCase;
import com.cooperevoto.domain.repository.SessaoVotacaoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SessaoVotacaoController {

    private final AbrirSessaoUseCase abrirSessaoUseCase;
    private final SessaoVotacaoRepository sessaoRepository;

    @PostMapping("/pautas/{id}/sessao")
    public ResponseEntity<SessaoVotacaoResponse> abrirSessao(
            @PathVariable Long id,
            @RequestBody(required = false) @Valid AbrirSessaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(abrirSessaoUseCase.executar(id, request));
    }

    @GetMapping("/sessoes")
    public ResponseEntity<List<SessaoVotacaoResponse>> listarSessoes() {
        var sessoes = sessaoRepository.findAll();
        var resposta = sessoes.stream()
                .map(sessao -> new SessaoVotacaoResponse(
                        sessao.getId(),
                        sessao.getPauta().getId(),
                        sessao.getDataAbertura(),
                        sessao.getDataFechamento()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/sessoes/{id}")
    public ResponseEntity<SessaoVotacaoResponse> buscarPorId(@PathVariable Long id) {
        var sessao = sessaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada")); // pode ajustar para @ControllerAdvice

        var resposta = new SessaoVotacaoResponse(
                sessao.getId(),
                sessao.getPauta().getId(),
                sessao.getDataAbertura(),
                sessao.getDataFechamento());

        return ResponseEntity.ok(resposta);
    }
}
