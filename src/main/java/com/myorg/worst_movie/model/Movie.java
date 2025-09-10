package com.myorg.worst_movie.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private Long id;

    @NotBlank(message = "É obrigatório informar o título")
    @Column(name = "title")
    private String title;

    @NotNull(message = "É obrigatório informar o ano")
    @Column(name = "year")
    private Integer year;

    @NotBlank(message = "É obrigatório informar o estúdio")
    @Column(name = "studios")
    private String studios;

    @NotBlank(message = "É obrigatório informar o Produtor")
    @Column(name = "producers")
    private String producers;

    @Column(name = "winner")
    private Boolean winner;

}
