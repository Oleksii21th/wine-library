package eu.babych.winelibrary.service.calculator;

import eu.babych.winelibrary.domain.Occasion;
import eu.babych.winelibrary.domain.Pace;
import eu.babych.winelibrary.domain.RoleAtTable;
import eu.babych.winelibrary.dto.winecalculator.WineCalculationRequest;
import eu.babych.winelibrary.dto.winecalculator.WineCalculationResponse;
import eu.babych.winelibrary.exception.badrequest.InvalidDurationException;
import eu.babych.winelibrary.exception.badrequest.InvalidGuestsException;
import eu.babych.winelibrary.exception.notfound.StrategyNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WineCalculationServiceImpl implements WineCalculationService {
    private static final double LITERS_PER_BOTTLE = 0.75;
    private static final int GLASSES_PER_BOTTLE = 5;
    private static final double GLASSES_PER_GUEST_PER_HOUR = 1.0;
    private static final double RESERVE = 1.10;

    private final List<CalculationStrategy<Pace>> paceStrategies;
    private final List<CalculationStrategy<RoleAtTable>> roleStrategies;
    private final List<CalculationStrategy<Occasion>> occasionStrategies;

    public WineCalculationServiceImpl(List<CalculationStrategy<Pace>> paceStrategies,
                                      List<CalculationStrategy<RoleAtTable>> roleStrategies,
                                      List<CalculationStrategy<Occasion>> occasionStrategies) {
        this.paceStrategies = paceStrategies;
        this.roleStrategies = roleStrategies;
        this.occasionStrategies = occasionStrategies;
    }

    @Override
    public WineCalculationResponse calculate(WineCalculationRequest request) {
        validate(request);

        double glasses = calculateGlasses(request);
        int totalGlasses = calculateTotalGlasses(glasses);
        int bottles = calculateBottles(totalGlasses);
        double liters = calculateLiters(bottles);
        double glassesPerGuest = calculateGlassesPerGuest(totalGlasses, request.guests());

        return new WineCalculationResponse(bottles, liters, totalGlasses, round(glassesPerGuest));
    }

    private double calculateGlasses(WineCalculationRequest request) {
        double glasses = request.guests() * request.durationHours() * GLASSES_PER_GUEST_PER_HOUR;
        glasses *= findStrategy(paceStrategies, request.pace()).multiplier();
        glasses *= findStrategy(roleStrategies, request.wineRole()).multiplier();
        glasses *= findStrategy(occasionStrategies, request.occasion()).multiplier();

        return glasses * RESERVE;
    }

    private int calculateTotalGlasses(double glasses) {
        return (int) Math.ceil(glasses);
    }

    private int calculateBottles(int totalGlasses) {
        return (int) Math.ceil((double) totalGlasses / GLASSES_PER_BOTTLE);
    }

    private double calculateLiters(int bottles) {
        return bottles * LITERS_PER_BOTTLE;
    }

    private double calculateGlassesPerGuest(int totalGlasses, int guests) {
        return (double) totalGlasses / guests;
    }

    private <T> CalculationStrategy<T> findStrategy(List<CalculationStrategy<T>> strategies,
                                                    T type) {
        return strategies.stream()
                .filter(strategy -> strategy.getType() == type)
                .findFirst()
                .orElseThrow(() -> new StrategyNotFoundException("No strategy found for: "
                        + type));
    }

    private void validate(WineCalculationRequest request) {
        if (request.guests() < 1 || request.guests() > 100) {
            throw new InvalidGuestsException();
        }

        if (request.durationHours() < 1 || request.durationHours() > 12) {
            throw new InvalidDurationException();
        }
    }

    private double round(double value) {
        return Math.round(value * 10.0) / 10.0;
    }
}
