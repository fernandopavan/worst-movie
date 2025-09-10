package com.myorg.worst_movie.controller;

import com.myorg.worst_movie.dto.WorstMovieResponseDTO;
import com.myorg.worst_movie.service.WorstMovieListService;
import com.myorg.worst_movie.service.WorstMovieUploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/worst-movie")
public class WorstMovieController {

    private final WorstMovieUploadService uploadService;
    private final WorstMovieListService listService;

    @GetMapping
    private ResponseEntity<WorstMovieResponseDTO> getWorstProducersWin() {
        return ResponseEntity.ok().body(listService.getWorstProducersWin());
    }

    @PostMapping("/upload")
    private ResponseEntity<Void> upload(@RequestParam("file") MultipartFile file) {
        uploadService.uploadCsv(file);
        return ResponseEntity.ok().build();
    }

}
