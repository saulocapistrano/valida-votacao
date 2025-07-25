package com.cooperevoto.application.usecase.pauta;


import com.cooperevoto.domain.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirPautaUseCaseImpl implements ExcluirPautaUseCase {

    private final PautaRepository pautaRepository;

    @Override
    public boolean executar(Long id) {
        if (!pautaRepository.existsById(id)) {
            return false;
        }
        pautaRepository.deleteById(id);
        return true;
    }


}