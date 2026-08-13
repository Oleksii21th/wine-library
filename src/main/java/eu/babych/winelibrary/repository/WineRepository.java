package eu.babych.winelibrary.repository;

import eu.babych.winelibrary.model.wine.Wine;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<Wine, Long>, JpaSpecificationExecutor<Wine> {
    @EntityGraph(attributePaths = {"country", "region", "producer"})
    Page<Wine> findAll(Specification<Wine> specification, Pageable pageable);

    @Query("SELECT DISTINCT w FROM Wine w "
            + "LEFT JOIN FETCH w.foods "
            + "LEFT JOIN FETCH w.grapes "
            + "WHERE w.id IN :ids")
    List<Wine> findAllWithFoodsAndGrapesByIdIn(@Param("ids") Collection<Long> ids);
}
