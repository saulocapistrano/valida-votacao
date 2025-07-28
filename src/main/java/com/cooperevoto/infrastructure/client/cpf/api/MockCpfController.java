package com.cooperevoto.infrastructure.client.cpf.api;


import com.cooperevoto.infrastructure.client.cpf.vo.CpfStatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/mock-cpf")
public class MockCpfController {

    private final Random random = new Random();

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<CpfStatusResponse> validarCpf(@PathVariable String cpf) {
        int chance = random.nextInt(100);

        if (chance < 10) {
            return ResponseEntity.notFound().build();
        } else if (chance < 40) {
            return ResponseEntity.ok(new CpfStatusResponse("UNABLE_TO_VOTE"));
        } else {
            return ResponseEntity.ok(new CpfStatusResponse("ABLE_TO_VOTE"));
        }
    }
}
