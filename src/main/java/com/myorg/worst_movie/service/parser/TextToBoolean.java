package com.myorg.worst_movie.service.parser;

import com.myorg.worst_movie.enums.YesNoEnum;
import com.opencsv.bean.AbstractBeanField;

import java.util.Optional;

public class TextToBoolean extends AbstractBeanField {

    @Override
    protected Object convert(String s) {
        return Optional.ofNullable(YesNoEnum.of(s)).map(YesNoEnum::isWinner).orElse(false);
    }

    @Override
    public String convertToWrite(Object value) {
        return Optional.ofNullable(YesNoEnum.of((Boolean) value)).map(YesNoEnum::getValue).orElse("");
    }

}
