package com.myorg.worst_movie.service.validator;

import com.opencsv.bean.BeanField;
import com.opencsv.bean.validators.StringValidator;
import com.opencsv.exceptions.CsvValidationException;

import java.util.Objects;

public class RequiredInput implements StringValidator {

    @Override
    public boolean isValid(String s) {
        return Objects.nonNull(s) && !s.isEmpty();
    }

    @Override
    public void validate(String s, BeanField beanField) throws CsvValidationException {
        if (!isValid(s)) {
            throw new CsvValidationException(String.format("coluna %s possui um valor inválido", beanField.getField().getName()));
        }
    }

    @Override
    public void setParameterString(String s) {
    }
}
