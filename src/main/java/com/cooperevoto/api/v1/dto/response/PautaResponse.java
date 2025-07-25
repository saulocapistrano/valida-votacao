package com.cooperevoto.api.v1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PautaResponse {
    private Long id;
    private String titulo;
    private String descricao;


}