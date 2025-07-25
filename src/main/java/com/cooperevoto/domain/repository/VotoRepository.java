package com.cooperevoto.domain.repository;

import com.cooperevoto.domain.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByCpfAndPautaId(String cpf, Long pautaId);
}
