package com.cooperevoto.domain.model;

import com.cooperevoto.domain.enums.TipoVoto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "voto", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cpf", "pauta_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVoto voto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sessao_id")
    private SessaoVotacao sessao;

    private LocalDateTime dataVoto;
}
