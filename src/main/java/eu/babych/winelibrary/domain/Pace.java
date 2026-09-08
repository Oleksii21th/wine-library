package eu.babych.winelibrary.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import eu.babych.winelibrary.exception.badrequest.InvalidEnumValueException;
import java.util.Arrays;

public enum Pace {
    LIGHT("light"),
    MODERATE("moderate"),
    GENEROUS("generous");

    private final String value;

    Pace(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Pace fromValue(String value) {
        return Arrays.stream(values())
                .filter(pace -> pace.value.equals(value))
                .findFirst()
                .orElseThrow(() ->
                        new InvalidEnumValueException("pace", value));
    }
}
