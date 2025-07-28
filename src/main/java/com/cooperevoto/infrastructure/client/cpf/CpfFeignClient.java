package com.cooperevoto.infrastructure.client.cpf;

import com.cooperevoto.infrastructure.client.cpf.vo.CpfStatusResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cpfClient", url = "${cpf.client.url}")
public interface CpfFeignClient {

    @GetMapping("/cpf/{cpf}")
    CpfStatusResponse validarCpf(@PathVariable("cpf") String cpf);

}
