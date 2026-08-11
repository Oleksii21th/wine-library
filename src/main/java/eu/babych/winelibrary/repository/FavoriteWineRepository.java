package eu.babych.winelibrary.repository;

import eu.babych.winelibrary.model.FavoriteWine;
import eu.babych.winelibrary.model.User;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteWineRepository extends JpaRepository<FavoriteWine, Long> {
    @EntityGraph(attributePaths = {"wine"})
    Page<FavoriteWine> findAllByUserId(Long userId, Pageable pageable);

    void deleteByUserAndWineId(User user, Long wineId);

    boolean existsByUserAndWineId(User user, Long wineId);

    long countByUserId(Long userId);

    @Query("SELECT fw.wine.id FROM FavoriteWine fw "
            + "WHERE fw.user.id = :userId AND fw.wine.id IN :wineIds")
    Set<Long> findFavoriteWineIds(@Param("userId") Long userId,
                                  @Param("wineIds") Collection<Long> wineIds);

    Page<FavoriteWine> findByUserIdAndAddedAtAfter(Long userId,
                                                   LocalDateTime date,
                                                   Pageable pageable);
}
