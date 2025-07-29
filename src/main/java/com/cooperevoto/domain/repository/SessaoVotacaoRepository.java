package com.cooperevoto.domain.repository;

import com.cooperevoto.domain.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {
    boolean existsByPautaId(Long pautaId);
    Optional<SessaoVotacao> findByPautaId(Long pautaId);

}