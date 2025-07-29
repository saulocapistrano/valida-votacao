package com.cooperevoto.infrastructure.client.cpf;


import com.cooperevoto.exception.BusinessException;
import com.cooperevoto.infrastructure.client.cpf.service.CpfValidationService;
import com.cooperevoto.infrastructure.client.cpf.vo.CpfStatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakeCpfValidationService implements CpfValidationService {

    private static final Random random = new Random();

    @Override
    public CpfStatusResponse validar(String cpf) {
        int chance = random.nextInt(100);

        if (chance < 10) {
            throw new BusinessException("CPF inválido", HttpStatus.NOT_FOUND);
        }
        else if (chance < 40) {
            return new CpfStatusResponse("UNABLE_TO_VOTE");
        } else {
            return new CpfStatusResponse("ABLE_TO_VOTE");
        }
    }
}
