package eu.babych.winelibrary.service.calculator.occassion;

import eu.babych.winelibrary.domain.Occasion;
import eu.babych.winelibrary.service.calculator.CalculationStrategy;
import org.springframework.stereotype.Component;

@Component
public class TastingOccasionStrategy
        implements CalculationStrategy<Occasion> {

    @Override
    public Occasion getType() {
        return Occasion.TASTING;
    }

    @Override
    public double multiplier() {
        return 0.60;
    }
}
