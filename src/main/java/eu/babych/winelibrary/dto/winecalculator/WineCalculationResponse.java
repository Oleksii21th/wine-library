package eu.babych.winelibrary.dto.winecalculator;

public record WineCalculationResponse(
        int bottles,
        double liters,
        int glasses,
        double glassesPerGuest) {
}
