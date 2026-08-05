package eu.babych.winelibrary.service.impl;

import eu.babych.winelibrary.dto.favoritewine.FavoriteWineResponseDto;
import eu.babych.winelibrary.exception.badrequest.FavoriteWineAlreadyExistsException;
import eu.babych.winelibrary.exception.notfound.FavoriteWineNotFoundException;
import eu.babych.winelibrary.exception.notfound.WineNotFoundException;
import eu.babych.winelibrary.mapper.FavoriteWineMapper;
import eu.babych.winelibrary.model.FavoriteWine;
import eu.babych.winelibrary.model.User;
import eu.babych.winelibrary.model.wine.Wine;
import eu.babych.winelibrary.repository.FavoriteWineRepository;
import eu.babych.winelibrary.repository.UserRepository;
import eu.babych.winelibrary.repository.WineRepository;
import eu.babych.winelibrary.service.FavoriteWineService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FavoriteWineServiceImpl implements FavoriteWineService {
    private final FavoriteWineRepository favoriteWineRepository;
    private final UserRepository userRepository;
    private final WineRepository wineRepository;
    private final FavoriteWineMapper favoriteWineMapper;

    public FavoriteWineServiceImpl(FavoriteWineRepository favoriteWineRepository,
                                   UserRepository userRepository,
                                   WineRepository wineRepository,
                                   FavoriteWineMapper favoriteWineMapper) {
        this.favoriteWineRepository = favoriteWineRepository;
        this.userRepository = userRepository;
        this.wineRepository = wineRepository;
        this.favoriteWineMapper = favoriteWineMapper;
    }

    @Override
    @Transactional
    public void delete(Authentication authentication, Long wineId) {
        User user = getCurrentUser(authentication);

        if (!favoriteWineRepository.existsByUserAndWineId(user, wineId)) {
            throw new FavoriteWineNotFoundException();
        }

        favoriteWineRepository.deleteByUserAndWineId(user, wineId);
    }

    @Override
    public Page<FavoriteWineResponseDto> findAllByUser(Authentication authentication,
                                                       Pageable pageable) {
        User user = getCurrentUser(authentication);

        return favoriteWineRepository
                .findAllByUserId(user.getId(), pageable)
                .map(favoriteWineMapper::toDto);
    }

    @Override
    public FavoriteWineResponseDto save(Authentication authentication, Long wineId) {
        User user = getCurrentUser(authentication);

        if (favoriteWineRepository.existsByUserAndWineId(user, wineId)) {
            throw new FavoriteWineAlreadyExistsException(wineId);
        }

        Wine wine = wineRepository.findById(wineId)
                .orElseThrow(() -> new WineNotFoundException(wineId));

        FavoriteWine favoriteWine = new FavoriteWine();
        favoriteWine.setUser(user);
        favoriteWine.setWine(wine);
        favoriteWine.setAddedAt(LocalDateTime.now());

        FavoriteWine saved = favoriteWineRepository.save(favoriteWine);

        return favoriteWineMapper.toDto(saved);
    }

    @Override
    public boolean isFavorite(Authentication authentication, Long wineId) {
        User user = getCurrentUser(authentication);

        return favoriteWineRepository.existsByUserAndWineId(user, wineId);
    }

    @Override
    public long countFavoriteWines(Authentication authentication) {
        Long userId = getCurrentUser(authentication).getId();

        return favoriteWineRepository.countByUserId(userId);
    }

    @Override
    public Page<FavoriteWineResponseDto> findRecentFavoriteWines(Authentication authentication,
                                                                 Pageable pageable) {
        Long userId = getCurrentUser(authentication).getId();
        LocalDateTime dateFrom = LocalDateTime.now().minusDays(30);

        return favoriteWineRepository.findByUserIdAndAddedAtAfter(userId, dateFrom, pageable)
                .map(favoriteWineMapper::toDto);
    }

    private User getCurrentUser(Authentication authentication) {
        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + email));
    }
}
