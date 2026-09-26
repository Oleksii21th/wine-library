package eu.babych.winelibrary.dto.winecalculator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.babych.winelibrary.domain.Occasion;
import eu.babych.winelibrary.domain.Pace;
import eu.babych.winelibrary.domain.RoleAtTable;

public record WineCalculationRequest(
        int guests,
        Occasion occasion,
        @JsonProperty("duration_hours")
        int durationHours,
        @JsonProperty("wine_role")
        RoleAtTable wineRole,
        Pace pace) {
}
