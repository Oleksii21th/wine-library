package eu.babych.winelibrary.service.calculator.role;

import eu.babych.winelibrary.domain.RoleAtTable;
import eu.babych.winelibrary.service.calculator.CalculationStrategy;
import org.springframework.stereotype.Component;

@Component
public class OneOfSeveralRoleStrategy implements CalculationStrategy<RoleAtTable> {
    @Override
    public RoleAtTable getType() {
        return RoleAtTable.ONE_OF_SEVERAL;
    }

    @Override
    public double multiplier() {
        return 0.60;
    }
}