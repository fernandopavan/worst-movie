package com.myorg.worst_movie.dto;


import com.myorg.worst_movie.service.validator.RequiredInput;
import com.myorg.worst_movie.service.parser.TextToBoolean;
import com.opencsv.bean.*;
import com.opencsv.bean.validators.PreAssignmentValidator;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieUploadDTO {

    @CsvNumber("####")
    @PreAssignmentValidator(validator = RequiredInput.class)
    @CsvBindByPosition(position = 0)
    private Integer year;

    @PreAssignmentValidator(validator = RequiredInput.class)
    @CsvBindByPosition(position = 1, required = true)
    private String title;

    @PreAssignmentValidator(validator = RequiredInput.class)
    @CsvBindByPosition(position = 2)
    private String studios;

    @PreAssignmentValidator(validator = RequiredInput.class)
    @CsvBindByPosition(position = 3)
    private String producers;

    @CsvCustomBindByPosition(position = 4, converter = TextToBoolean.class)
    private Boolean winner;

}
