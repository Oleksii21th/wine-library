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
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    @Transactional
    @Override
    public WineResponseDto findById(Long id, Authentication authentication) {
        Wine wine = wineRepository.findById(id)
                .orElseThrow(() -> new WineNotFoundException(id));

        User user = getCurrentUser(authentication);

        boolean favorite =
                favoriteWineRepository.existsByUserAndWineId(user, id);

        return mapper.toDto(wine, favorite);
    }

    @Transactional
    @Override
    public Page<WineResponseDto> findAll(WineFilterRequestDto requestDto,
                                         Pageable pageable,
                                         Authentication authentication) {
        Specification<Wine> specification =
                specificationBuilder.buildFilter(requestDto);

        return findAllBySpecification(specification, pageable, authentication);
    }

    @Transactional
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
        Page<Wine> page = wineRepository.findAll(specification, pageable);

        if (page.isEmpty()) {
            return Page.empty(pageable);
        }

        Long userId = getCurrentUser(authentication).getId();

        List<Long> wineIds = page.getContent()
                .stream()
                .map(Wine::getId)
                .toList();

        Set<Long> favoriteWineIds =
                favoriteWineRepository.findFavoriteWineIds(userId, wineIds);

        List<Wine> wines =
                wineRepository.findAllWithFoodsAndGrapesByIdIn(wineIds);

        List<WineResponseDto> dtos = page.getContent()
                .stream()
                .map(wine -> {
                    Wine fullWine = wines.stream()
                            .filter(w -> w.getId().equals(wine.getId()))
                            .findFirst()
                            .orElseThrow(() -> new WineNotFoundException(wine.getId()));

                    return mapper.toDto(
                            fullWine,
                            favoriteWineIds.contains(wine.getId()));
                })
                .toList();

        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    private User getCurrentUser(Authentication authentication) {
        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + email));
    }
}
