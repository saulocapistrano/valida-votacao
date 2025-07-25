package com.cooperevoto.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pauta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;
}
