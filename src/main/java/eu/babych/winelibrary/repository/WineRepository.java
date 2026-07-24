package eu.babych.winelibrary.repository;

import eu.babych.winelibrary.model.wine.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<Wine, Long> {
}
