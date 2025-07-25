package com.cooperevoto.api.v1.controller;

import com.cooperevoto.api.v1.dto.request.AbrirSessaoRequest;
import com.cooperevoto.api.v1.dto.response.SessaoVotacaoResponse;
import com.cooperevoto.application.usecase.votacao.AbrirSessaoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pautas")
@RequiredArgsConstructor
public class SessaoVotacaoController {

    private final AbrirSessaoUseCase abrirSessaoUseCase;

    @PostMapping("/{id}/sessao")
    public ResponseEntity<SessaoVotacaoResponse> abrirSessao(
            @PathVariable Long id,
            @RequestBody(required = false) AbrirSessaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                abrirSessaoUseCase.executar(id, request));
    }
}