package eu.babych.winelibrary.service.calculator.pace;

import eu.babych.winelibrary.domain.Pace;
import eu.babych.winelibrary.service.calculator.CalculationStrategy;
import org.springframework.stereotype.Component;

@Component
public class GenerousPaceStrategy implements CalculationStrategy<Pace> {
    @Override
    public Pace getType() {
        return Pace.GENEROUS;
    }

    @Override
    public double multiplier() {
        return 1.30;
    }
}
