package eu.babych.winelibrary.service.impl;

import eu.babych.winelibrary.dto.wine.WineFilterRequestDto;
import eu.babych.winelibrary.dto.wine.WineResponseDto;
import eu.babych.winelibrary.dto.wine.WineSearchRequestDto;
import eu.babych.winelibrary.exception.notfound.WineNotFoundException;
import eu.babych.winelibrary.mapper.WineMapper;
import eu.babych.winelibrary.model.User;
import eu.babych.winelibrary.model.wine.Wine;
import eu.babych.winelibrary.repository.FavoriteWineRepository;
import eu.babych.winelibrary.repository.UserRepository;
import eu.babych.winelibrary.repository.WineRepository;
import eu.babych.winelibrary.service.WineService;
import eu.babych.winelibrary.service.winefilter.WineSearchSpecificationBuilder;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WineServiceImpl implements WineService {
    private final WineMapper mapper;
    private final WineRepository wineRepository;
    private final UserRepository userRepository;
    private final FavoriteWineRepository favoriteWineRepository;
    private final WineSearchSpecificationBuilder<Wine> specificationBuilder;

    public WineServiceImpl(WineMapper mapper,
                           WineRepository wineRepository,
                           UserRepository userRepository,
                           FavoriteWineRepository favoriteWineRepository,
                           WineSearchSpecificationBuilder<Wine> specificationBuilder) {
        this.mapper = mapper;
        this.wineRepository = wineRepository;
        this.userRepository = userRepository;
        this.favoriteWineRepository = favoriteWineRepository;
        this.specificationBuilder = specificationBuilder;
    }

    @Override
    public WineResponseDto findById(Long id, Authentication authentication) {
        Wine wine = wineRepository.findById(id)
                .orElseThrow(() -> new WineNotFoundException(id));

        User user = getCurrentUser(authentication);

        boolean favorite =
                favoriteWineRepository.existsByUserAndWineId(user, id);

        return mapper.toDto(wine, favorite);
    }

    @Override
    public Page<WineResponseDto> findAll(WineFilterRequestDto requestDto,
                                         Pageable pageable,
                                         Authentication authentication) {
        Specification<Wine> specification =
                specificationBuilder.buildFilter(requestDto);

        return findAllBySpecification(specification, pageable, authentication);
    }

    @Override
    public Page<WineResponseDto> search(WineSearchRequestDto searchDto,
                                        Pageable pageable,
                                        Authentication authentication) {
        Specification<Wine> specification =
                specificationBuilder.buildSearch(searchDto);

        return findAllBySpecification(specification, pageable, authentication);
    }

    private Page<WineResponseDto> findAllBySpecification(Specification<Wine> specification,
                                                         Pageable pageable,
                                                         Authentication authentication) {
        Page<Wine> wines = wineRepository.findAll(specification, pageable);
        Long userId = getCurrentUser(authentication).getId();

        List<Long> wineIds = wines.getContent()
                .stream()
                .map(Wine::getId)
                .toList();

        Set<Long> favoriteWineIds = wineIds.isEmpty()
                ? Collections.emptySet()
                : favoriteWineRepository.findFavoriteWineIds(userId, wineIds);

        return wines.map(wine ->
                mapper.toDto(wine, favoriteWineIds.contains(wine.getId())));
    }

    private User getCurrentUser(Authentication authentication) {
        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + email));
    }
}
