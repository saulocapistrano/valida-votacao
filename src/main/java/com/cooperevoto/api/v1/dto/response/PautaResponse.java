package com.cooperevoto.api.v1.dto.response;

import lombok.Data;

@Data
public class PautaResponse {
    private Long id;
    private String titulo;
    private String descricao;
}