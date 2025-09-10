package com.myorg.worst_movie.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorstMovieDetailDTO {

    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

}

