package eu.babych.winelibrary.service.calculator.occassion;

import eu.babych.winelibrary.domain.Occasion;
import eu.babych.winelibrary.service.calculator.CalculationStrategy;
import org.springframework.stereotype.Component;

@Component
public class BirthdayOccasionStrategy implements CalculationStrategy<Occasion> {
    @Override
    public Occasion getType() {
        return Occasion.BIRTHDAY;
    }

    @Override
    public double multiplier() {
        return 1.00;
    }
}
