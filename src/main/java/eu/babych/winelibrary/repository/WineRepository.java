package eu.babych.winelibrary.repository;

import eu.babych.winelibrary.model.wine.Wine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<Wine, Long>, JpaSpecificationExecutor<Wine> {
    @EntityGraph(attributePaths = {"country", "region", "producer", "grapes", "foods"})
    Page<Wine> findAll(Specification<Wine> specification, Pageable pageable);
}
