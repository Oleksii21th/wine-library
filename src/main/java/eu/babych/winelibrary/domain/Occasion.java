package eu.babych.winelibrary.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import eu.babych.winelibrary.exception.badrequest.InvalidEnumValueException;
import java.util.Arrays;

public enum Occasion {
    DINNER("dinner"),
    BIRTHDAY("birthday"),
    WEDDING("wedding"),
    PARTY("party"),
    CORPORATE_EVENT("corporate_event"),
    CASUAL_GATHERING("casual_gathering"),
    TASTING("tasting"),
    OTHER("other");

    private final String value;

    Occasion(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Occasion fromValue(String value) {
        return Arrays.stream(values())
                .filter(occasion -> occasion.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() ->
                        new InvalidEnumValueException("occasion", value));
    }
}
