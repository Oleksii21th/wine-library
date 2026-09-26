package eu.babych.winelibrary.service.calculator;

import eu.babych.winelibrary.dto.winecalculator.WineCalculationRequest;
import eu.babych.winelibrary.dto.winecalculator.WineCalculationResponse;

public interface WineCalculationService {
    WineCalculationResponse calculate(WineCalculationRequest request);
}
