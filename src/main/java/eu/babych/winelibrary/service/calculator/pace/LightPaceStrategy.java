package eu.babych.winelibrary.service.calculator.pace;

import eu.babych.winelibrary.domain.Pace;
import eu.babych.winelibrary.service.calculator.CalculationStrategy;
import org.springframework.stereotype.Component;

@Component
public class LightPaceStrategy implements CalculationStrategy<Pace> {
    @Override
    public Pace getType() {
        return Pace.LIGHT;
    }

    @Override
    public double multiplier() {
        return 0.70;
    }
}
