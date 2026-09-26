package eu.babych.winelibrary.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import eu.babych.winelibrary.exception.badrequest.InvalidEnumValueException;
import java.util.Arrays;

public enum RoleAtTable {
    MAIN_DRINK("main_drink"),
    ONE_OF_SEVERAL("one_of_several");

    private final String value;

    RoleAtTable(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static RoleAtTable fromValue(String value) {
        return Arrays.stream(values())
                .filter(role -> role.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() ->
                        new InvalidEnumValueException("wine_role", value));
    }
}
