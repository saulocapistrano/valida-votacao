package com.cooperevoto.api.v1.controller;


import com.cooperevoto.api.v1.dto.response.ResultadoVotacaoResponse;
import com.cooperevoto.application.usecase.votacao.ObterResultadoVotacaoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pautas")
@RequiredArgsConstructor
public class ResultadoVotacaoController {

    private final ObterResultadoVotacaoUseCase obterResultadoVotacaoUseCase;

    @GetMapping("/{pautaId}/resultado")
    public ResponseEntity<ResultadoVotacaoResponse> obterResultado(@PathVariable Long pautaId) {
        var resultado = obterResultadoVotacaoUseCase.executar(pautaId);
        return ResponseEntity.ok(resultado);
    }
}
