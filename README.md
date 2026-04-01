package org.nnnn.ddd.repository.specs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.nnnn.ddd.entity.DluCase;
import org.nnnn.ddd.entity.DluOfficeList;
import org.nnnn.ddd.entity.StatusList;
import org.nnnn.ddd.entity.TagList;
import org.nnnn.ddd.entity.CaseTag;
import org.nnnn.ddd.entity.CategoryList;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class CaseSpecifications {

    public static Specification<DluCase> fetchDependencies() {
        return (root, query, criteriaBuilder) -> {
            // Check the query type to avoid issues with count queries for pagination
            if (Long.class != query.getResultType()) {
                root.fetch("tags", JoinType.LEFT);
                root.fetch("category", JoinType.LEFT);
                root.fetch("ddd", JoinType.LEFT);
                root.fetch("status", JoinType.LEFT);
                root.fetch("ada", JoinType.LEFT);
            }
            // Return null or a simple predicate if no additional criteria are needed
            return criteriaBuilder.isNotNull(root.get("id"));
        };
    }

    public static Specification<DluCase> hasProactiveFlg(Short proactiveFlg) {
        return (root, query, cb) -> cb.equal(root.get("proactiveFlg"), proactiveFlg);
    }

    public static Specification<DluCase> hasAssignedNm(String assigned) {
        return (root, query, cb) -> cb.equal(root.get("assignedNm"), assigned);
    }

    public static Specification<DluCase> hasArrId(String arrId) {
        return (root, query, cb) -> cb.equal(root.get("arrId"), arrId);
    }

    public static Specification<DluCase> hasCategories(List<Integer> categoryIds) {
        return (root, query, cb) -> {
            Join<DluCase, CategoryList> categoryJoin = root.join("category", JoinType.INNER);
            return categoryJoin.get("id").in(categoryIds);
        };
    }

    public static Specification<DluCase> hasDluOfficesIn(List<Integer> officeIds) {
        return (root, query, cb) -> {
            Join<DluCase, DluOfficeList> dddJoin = root.join("ddd", JoinType.INNER);
            return dddJoin.get("id").in(officeIds);
        };
    }

    public static Specification<DluCase> hasTagsIn(List<Integer> tagIds) {
        return (root, query, cb) -> {
            Join<DluCase, CaseTag> caseTagJoin = root.join("tags", JoinType.INNER);
            Join<CaseTag, TagList> tagJoin = caseTagJoin.join("tag", JoinType.INNER);
            return tagJoin.get("id").in(tagIds);
        };
    }

    public static Specification<DluCase> hasStatusesIn(List<Integer> statusIds) {
        return (root, query, cb) -> {
            Join<DluCase, StatusList> statusJoin = root.join("status", JoinType.INNER);
            return statusJoin.get("id").in(statusIds);
        };
    }

    public static Specification<DluCase> dueBetween(LocalDate start, LocalDate end) {
        return (root, query, cb) -> {
            if (start == null && end == null)
                return null;
            if (start != null && end == null)
                return cb.greaterThanOrEqualTo(root.get("dueDt"), start);
            if (start == null && end != null)
                return cb.lessThanOrEqualTo(root.get("dueDt"), end);
            return cb.between(root.get("dueDt"), start, end);
        };
    }

    public static Specification<DluCase> requestBetween(LocalDate start, LocalDate end) {
        return (root, query, cb) -> {
            if (start == null && end == null)
                return null;
            if (start != null && end == null)
                return cb.greaterThanOrEqualTo(root.get("requestDt"), start);
            if (start == null && end != null)
                return cb.lessThanOrEqualTo(root.get("requestDt"), end);
            return cb.between(root.get("requestDt"), start, end);
        };
    }
}
