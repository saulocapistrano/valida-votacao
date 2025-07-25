package com.cooperevoto.api.v1.controller;

import com.cooperevoto.api.v1.dto.request.VotoRequest;
import com.cooperevoto.api.v1.dto.response.VotoResponse;
import com.cooperevoto.application.usecase.votacao.CadastrarVotoUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/votos")
@RequiredArgsConstructor
public class VotoController {

    private final CadastrarVotoUseCase cadastrarVotoUseCase;

    @PostMapping
    public ResponseEntity<VotoResponse> votar(@RequestBody @Valid VotoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarVotoUseCase.executar(request));
    }
}
