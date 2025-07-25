package com.cooperevoto.application.usecase.pauta;


import com.cooperevoto.domain.repository.PautaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirPautaUseCaseImpl implements ExcluirPautaUseCase {

    private final PautaRepository pautaRepository;

    @Override
    public void executar(Long id) {
        if (!pautaRepository.existsById(id)) {
            throw new EntityNotFoundException("Pauta não encontrada");
        }
        pautaRepository.deleteById(id);
    }
}