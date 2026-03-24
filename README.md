package org.nnnn.ddd.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.nnnn.ddd.entity.dddCase;
import org.nnnn.ddd.model.dddCase;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@Repository
public interface CaseRepository extends JpaRepository<dddCase, Integer>, JpaSpecificationExecutor<dddCase>{
    List<dddCase> findByArrIdAndParentIdIsNull(String arrId);

    List<dddCase> findByArrIdOrderByIdDesc(String arrId);

    @EntityGraph(attributePaths = {"category", "items", "tags", "ddd", "status", "notes"})
    Optional<dddCase> findById(int caseId);

    List<dddCase> findByDueDtBeforeAndStatus_IdNotAndAssignedNmNotNull(LocalDate dueDt, Integer statusId);

    List<dddCase> findByDueDtBetweenAndStatus_IdNotAndAssignedNmNotNull(LocalDate start, LocalDate end, Integer statusId);

    Integer countByDueDtBeforeAndStatus_IdNot(LocalDate dueDt, Integer statusId);

    Integer countByDueDtBetweenAndStatus_IdNot(LocalDate start, LocalDate end, Integer statusId);

    Integer countByDueDtBeforeAndAssignedNmAndStatus_IdNot(LocalDate dueDt, String assignedNm, Integer statusId);

    Integer countByDueDtBetweenAndAssignedNmAndStatus_IdNot(LocalDate start, LocalDate end, String assignedNm, Integer statusId);

    Integer countByDueDtBeforeAndddd_IdInAndStatus_IdNot(LocalDate dueDt, Collection<Integer> dddIds, Integer statusId);

    Integer countByDueDtBetweenAndddd_IdInAndStatus_IdNot(LocalDate start, LocalDate end, Collection<Integer> dddIds, Integer statusId);

}
