package eu.babych.winelibrary.service.winefilter;

import eu.babych.winelibrary.model.wine.Wine;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class WineSpecificationProviderManager {
    private final List<WineSpecificationProvider<Wine>> providers;

    public WineSpecificationProviderManager(
            List<WineSpecificationProvider<Wine>> providers) {
        this.providers = providers;
    }

}