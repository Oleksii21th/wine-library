package eu.babych.winelibrary.service.calculator.pace;

import eu.babych.winelibrary.domain.Pace;
import eu.babych.winelibrary.service.calculator.CalculationStrategy;
import org.springframework.stereotype.Component;

@Component
public class ModeratePaceStrategy implements CalculationStrategy<Pace> {
    @Override
    public Pace getType() {
        return Pace.MODERATE;
    }

    @Override
    public double multiplier() {
        return 1.00;
    }
}
