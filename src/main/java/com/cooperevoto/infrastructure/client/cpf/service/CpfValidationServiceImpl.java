package com.cooperevoto.infrastructure.client.cpf.service;

import com.cooperevoto.infrastructure.client.cpf.CpfFeignClient;
import com.cooperevoto.infrastructure.client.cpf.vo.CpfStatusResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;


@Service
@Primary
public class CpfValidationServiceImpl implements CpfValidationService {

    private final CpfFeignClient cpfFeignClient;

    public CpfValidationServiceImpl(CpfFeignClient cpfFeignClient) {
        this.cpfFeignClient = cpfFeignClient;
    }

    @Override
    @Cacheable(value = "cpfStatus", key = "#cpf")
    public CpfStatusResponse validar(String cpf) {
        return cpfFeignClient.validarCpf(cpf);
    }
}
