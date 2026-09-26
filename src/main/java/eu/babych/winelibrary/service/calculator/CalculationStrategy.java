package eu.babych.winelibrary.service.calculator;

public interface CalculationStrategy<T> {
    T getType();

    double multiplier();
}
