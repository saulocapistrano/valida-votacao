package com.cooperevoto.domain.repository;

import com.cooperevoto.domain.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
    List<Pauta> findByTituloContainingIgnoreCase(String titulo);

}
