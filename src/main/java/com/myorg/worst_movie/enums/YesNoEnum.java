package com.myorg.worst_movie.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YesNoEnum {

    YES("yes", "Yes", true),
    NO("no", "No", false);

    private final String value;
    private final String description;
    private final boolean isWinner;

    public static YesNoEnum of(String value) {
        for (YesNoEnum e : YesNoEnum.values()) {
            if (value.equals(e.getValue())) {
                return e;
            }
        }
        return null;
    }

    public static YesNoEnum of(Boolean value) {
        for (YesNoEnum e : YesNoEnum.values()) {
            if (value.equals(e.isWinner())) {
                return e;
            }
        }
        return null;
    }

}
