package com.myorg.worst_movie.service;

import com.myorg.worst_movie.dto.MovieUploadDTO;
import com.myorg.worst_movie.model.Movie;
import com.myorg.worst_movie.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Service
public class WorstMovieUploadService {

    private final MovieRepository repository;

    @Transactional
    public void uploadCsv(MultipartFile file) {
        List<MovieUploadDTO> movieUploadDTOS = WorstMovieCsvFileReader.reader(file);

        for (MovieUploadDTO dto : movieUploadDTOS) {
            Movie movie = Movie.builder()
                    .year(dto.getYear())
                    .title(dto.getTitle())
                    .studios(dto.getStudios())
                    .producers(dto.getProducers())
                    .winner(dto.getWinner())
                    .build();

            repository.save(movie);
        }
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

}
