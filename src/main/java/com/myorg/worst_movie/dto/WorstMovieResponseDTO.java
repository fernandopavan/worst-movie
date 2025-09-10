package com.myorg.worst_movie.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorstMovieResponseDTO {

    private List<WorstMovieDetailDTO> min;
    private List<WorstMovieDetailDTO> max;

}

