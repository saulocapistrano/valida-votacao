package com.cooperevoto.infrastructure.client.cpf.service;


import com.cooperevoto.infrastructure.client.cpf.vo.CpfStatusResponse;

public interface CpfValidationService {
    CpfStatusResponse validar(String cpf);
}
