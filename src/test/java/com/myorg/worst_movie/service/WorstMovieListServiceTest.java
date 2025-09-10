package com.myorg.worst_movie.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.nio.file.Files;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@WebAppConfiguration
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class WorstMovieListServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WorstMovieListService moviesService;

    @Autowired
    WorstMovieUploadService uploadService;

    @Test
    public void withWinnerTest() throws Exception {
        uploadService.deleteAll();

        File file = new File("src/test/resources/movielist-original.csv");
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        MockMultipartFile csvFile = new MockMultipartFile("file", "movielist-original.csv", MediaType.MULTIPART_FORM_DATA_VALUE, fileBytes);

        uploadService.uploadCsv(csvFile);

        mockMvc.perform(MockMvcRequestBuilders.get("/worst-movie").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.min", notNullValue()))
                .andExpect(jsonPath("$.max", notNullValue()));
    }

    @Test
    public void withoutWinnerTest() throws Exception {
        uploadService.deleteAll();

        File file = new File("src/test/resources/movielist.csv");
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        MockMultipartFile csvFile = new MockMultipartFile("file", "movielist.csv", MediaType.MULTIPART_FORM_DATA_VALUE, fileBytes);

        uploadService.uploadCsv(csvFile);

        mockMvc.perform(MockMvcRequestBuilders.get("/worst-movie").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.min").isEmpty())
                .andExpect(jsonPath("$.max").isEmpty());
    }

}