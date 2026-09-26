package eu.babych.winelibrary.service.calculator.role;

import eu.babych.winelibrary.domain.RoleAtTable;
import eu.babych.winelibrary.service.calculator.CalculationStrategy;
import org.springframework.stereotype.Component;

@Component
public class MainDrinkRoleStrategy implements CalculationStrategy<RoleAtTable> {
    @Override
    public RoleAtTable getType() {
        return RoleAtTable.MAIN_DRINK;
    }

    @Override
    public double multiplier() {
        return 1.00;
    }
}
