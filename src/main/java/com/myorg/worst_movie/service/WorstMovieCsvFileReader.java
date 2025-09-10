package com.myorg.worst_movie.service;

import com.myorg.worst_movie.dto.MovieUploadDTO;
import com.myorg.worst_movie.exception.exceptions.FileException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class WorstMovieCsvFileReader {

    public static List<MovieUploadDTO> reader(MultipartFile file) {
        try (Reader reader = new InputStreamReader(file.getInputStream())) {

            CsvToBean<MovieUploadDTO> csvToBean = new CsvToBeanBuilder<MovieUploadDTO>(reader)
                    .withType(MovieUploadDTO.class)
                    .withSeparator(';')
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .withThrowExceptions(false)
                    .build();

            List<MovieUploadDTO> records = csvToBean.parse();

            List<CsvException> capturedExceptions = csvToBean.getCapturedExceptions();

            if (!capturedExceptions.isEmpty()) {
                throw new FileException(String.format("Erro na linha %s: %s ",
                        capturedExceptions.getFirst().getLineNumber(), capturedExceptions.getFirst().getMessage()));
            }

            return records;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
