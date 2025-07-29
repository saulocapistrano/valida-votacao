package com.cooperevoto.api.controller;

import com.cooperevoto.api.v1.dto.request.PautaRequest;
import com.cooperevoto.api.v1.dto.response.PautaResponse;
import com.cooperevoto.application.usecase.pauta.*;
import com.cooperevoto.mapper.PautaMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pautas")
@RequiredArgsConstructor
@Tag(name = "Pautas", description = "Gerenciamento de pautas da cooperativa")
public class PautaController {

    private final CadastrarPautaUseCase cadastrarUseCase;
    private final ListarPautasUseCase listarUseCase;
    private final BuscarPautaUseCase buscarUseCase;
    private final EditarPautaUseCase editarUseCase;
    private final ExcluirPautaUseCase excluirUseCase;
    private final PautaMapper pautaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar uma nova pauta")
    public PautaResponse cadastrar(@Valid @RequestBody PautaRequest request) {
        return cadastrarUseCase.executar(request);
    }

    @GetMapping
    @Operation(summary = "Listar pautas", description = "Lista todas as pautas cadastradas com filtro opcional por título")
    public List<PautaResponse> listar(@RequestParam(required = false) String titulo) {
        return listarUseCase.executar(titulo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pauta por ID")
    public ResponseEntity<PautaResponse> buscarPorId(
            @Parameter(description = "ID da pauta") @PathVariable Long id) {
        return ResponseEntity.ok(buscarUseCase.executar(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma pauta")
    public ResponseEntity<PautaResponse> atualizar(@PathVariable Long id,
                                                   @Valid @RequestBody PautaRequest request) {
        return ResponseEntity.ok(editarUseCase.executar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir uma pauta")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
