package org.nnnn.ddd.repository.specs;

import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nnnn.ddd.entity.*;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CaseSpecifications Tests")
class CaseSpecificationsTest {

    @Mock private Root<DluCase>     root;
    @Mock private CriteriaQuery<?>  query;
    @Mock private CriteriaBuilder   cb;
    @Mock private Predicate         predicate;
    @Mock private Path<Object>      path;
    @Mock private Fetch<?, ?>       fetch;

    // =========================================================================
    // fetchDependencies()
    // =========================================================================

    @Nested
    @DisplayName("fetchDependencies()")
    class FetchDependenciesTests {

        @Test
        @DisplayName("fetches all associations when result type is not Long (non-count query)")
        void fetchDependencies_nonCountQuery_fetchesAllAssociations() {
            when(query.getResultType()).thenReturn((Class) DluCase.class);
            // doReturn avoids the generic type mismatch that when().thenReturn(fetch) causes
            doReturn(fetch).when(root).fetch(anyString(), any(JoinType.class));
            when(root.get("id")).thenReturn(path);
            when(cb.isNotNull(path)).thenReturn(predicate);

            Predicate result = CaseSpecifications.fetchDependencies()
                    .toPredicate(root, query, cb);

            verify(root).fetch("tags",     JoinType.LEFT);
            verify(root).fetch("category", JoinType.LEFT);
            verify(root).fetch("ddd",      JoinType.LEFT);
            verify(root).fetch("status",   JoinType.LEFT);
            verify(root).fetch("ada",      JoinType.LEFT);
            assertThat(result).isEqualTo(predicate);
        }

        @Test
        @DisplayName("skips fetches when result type is Long (count query for pagination)")
        void fetchDependencies_countQuery_skipsFetches() {
            when(query.getResultType()).thenReturn((Class) Long.class);
            when(root.get("id")).thenReturn(path);
            when(cb.isNotNull(path)).thenReturn(predicate);

            Predicate result = CaseSpecifications.fetchDependencies()
                    .toPredicate(root, query, cb);

            verify(root, never()).fetch(anyString(), any(JoinType.class));
            assertThat(result).isEqualTo(predicate);
        }

        @Test
        @DisplayName("always returns isNotNull predicate on id regardless of query type")
        void fetchDependencies_alwaysReturnsIsNotNullPredicateOnId() {
            when(query.getResultType()).thenReturn((Class) DluCase.class);
            // doReturn avoids the generic type mismatch that when().thenReturn(fetch) causes
            doReturn(fetch).when(root).fetch(anyString(), any(JoinType.class));
            when(root.get("id")).thenReturn(path);
            when(cb.isNotNull(path)).thenReturn(predicate);

            Predicate result = CaseSpecifications.fetchDependencies()
                    .toPredicate(root, query, cb);

            verify(cb).isNotNull(path);
            assertThat(result).isEqualTo(predicate);
        }
    }

    // =========================================================================
    // hasProactiveFlg()
    // =========================================================================

    @Nested
    @DisplayName("hasProactiveFlg()")
    class HasProactiveFlgTests {

        @Test
        @DisplayName("returns cb.equal predicate on proactiveFlg")
        void hasProactiveFlg_returnsEqualPredicate() {
            Short flag = 1;
            when(root.get("proactiveFlg")).thenReturn(path);
            when(cb.equal(path, flag)).thenReturn(predicate);

            Predicate result = CaseSpecifications.hasProactiveFlg(flag)
                    .toPredicate(root, query, cb);

            verify(cb).equal(path, flag);
            assertThat(result).isEqualTo(predicate);
        }
    }

    // =========================================================================
    // hasAssignedNm()
    // =========================================================================

    @Nested
    @DisplayName("hasAssignedNm()")
    class HasAssignedNmTests {

        @Test
        @DisplayName("returns cb.equal predicate on assignedNm")
        void hasAssignedNm_returnsEqualPredicate() {
            String assigned = "jdoe";
            when(root.get("assignedNm")).thenReturn(path);
            when(cb.equal(path, assigned)).thenReturn(predicate);

            Predicate result = CaseSpecifications.hasAssignedNm(assigned)
                    .toPredicate(root, query, cb);

            verify(cb).equal(path, assigned);
            assertThat(result).isEqualTo(predicate);
        }
    }

    // =========================================================================
    // hasArrId()
    // =========================================================================

    @Nested
    @DisplayName("hasArrId()")
    class HasArrIdTests {

        @Test
        @DisplayName("returns cb.equal predicate on arrId")
        void hasArrId_returnsEqualPredicate() {
            String arrId = "ARR-001";
            when(root.get("arrId")).thenReturn(path);
            when(cb.equal(path, arrId)).thenReturn(predicate);

            Predicate result = CaseSpecifications.hasArrId(arrId)
                    .toPredicate(root, query, cb);

            verify(cb).equal(path, arrId);
            assertThat(result).isEqualTo(predicate);
        }
    }

    // =========================================================================
    // hasCategories()
    // =========================================================================

    @Nested
    @DisplayName("hasCategories()")
    class HasCategoriesTests {

        @Mock private Join<DluCase, CategoryList> categoryJoin;
        @Mock private Path<Object>                categoryIdPath;
        @Mock private CriteriaBuilder.In<Object>  inPredicate;

        @Test
        @DisplayName("inner joins category and applies in() predicate on id")
        void hasCategories_innerJoinAndInPredicate() {
            List<Integer> ids = List.of(1, 2, 3);
            doReturn(categoryJoin).when(root).join("category", JoinType.INNER);
            when(categoryJoin.get("id")).thenReturn(categoryIdPath);
            when(categoryIdPath.in(ids)).thenReturn((Predicate) inPredicate);

            Predicate result = CaseSpecifications.hasCategories(ids)
                    .toPredicate(root, query, cb);

            verify(root).join("category", JoinType.INNER);
            verify(categoryJoin).get("id");
            verify(categoryIdPath).in(ids);
            assertThat(result).isEqualTo(inPredicate);
        }
    }

    // =========================================================================
    // hasDluOfficesIn()
    // =========================================================================

    @Nested
    @DisplayName("hasDluOfficesIn()")
    class HasDluOfficesInTests {

        @Mock private Join<DluCase, DluOfficeList> dddJoin;
        @Mock private Path<Object>                 officeIdPath;
        @Mock private CriteriaBuilder.In<Object>   inPredicate;

        @Test
        @DisplayName("inner joins ddd and applies in() predicate on id")
        void hasDluOfficesIn_innerJoinAndInPredicate() {
            List<Integer> ids = List.of(10, 20);
            doReturn(dddJoin).when(root).join("ddd", JoinType.INNER);
            when(dddJoin.get("id")).thenReturn(officeIdPath);
            when(officeIdPath.in(ids)).thenReturn((Predicate) inPredicate);

            Predicate result = CaseSpecifications.hasDluOfficesIn(ids)
                    .toPredicate(root, query, cb);

            verify(root).join("ddd", JoinType.INNER);
            verify(dddJoin).get("id");
            verify(officeIdPath).in(ids);
            assertThat(result).isEqualTo(inPredicate);
        }
    }

    // =========================================================================
    // hasTagsIn()
    // =========================================================================

    @Nested
    @DisplayName("hasTagsIn()")
    class HasTagsInTests {

        @Mock private Join<DluCase, CaseTag>     caseTagJoin;
        @Mock private Join<CaseTag, TagList>     tagJoin;
        @Mock private Path<Object>               tagIdPath;
        @Mock private CriteriaBuilder.In<Object> inPredicate;

        @Test
        @DisplayName("inner joins tags then tag and applies in() predicate on id")
        void hasTagsIn_doubleInnerJoinAndInPredicate() {
            List<Integer> ids = List.of(5, 6, 7);
            doReturn(caseTagJoin).when(root).join("tags", JoinType.INNER);
            doReturn(tagJoin).when(caseTagJoin).join("tag", JoinType.INNER);
            when(tagJoin.get("id")).thenReturn(tagIdPath);
            when(tagIdPath.in(ids)).thenReturn((Predicate) inPredicate);

            Predicate result = CaseSpecifications.hasTagsIn(ids)
                    .toPredicate(root, query, cb);

            verify(root).join("tags", JoinType.INNER);
            verify(caseTagJoin).join("tag", JoinType.INNER);
            verify(tagJoin).get("id");
            verify(tagIdPath).in(ids);
            assertThat(result).isEqualTo(inPredicate);
        }
    }

    // =========================================================================
    // hasStatusesIn()
    // =========================================================================

    @Nested
    @DisplayName("hasStatusesIn()")
    class HasStatusesInTests {

        @Mock private Join<DluCase, StatusList>  statusJoin;
        @Mock private Path<Object>               statusIdPath;
        @Mock private CriteriaBuilder.In<Object> inPredicate;

        @Test
        @DisplayName("inner joins status and applies in() predicate on id")
        void hasStatusesIn_innerJoinAndInPredicate() {
            List<Integer> ids = List.of(100, 200);
            doReturn(statusJoin).when(root).join("status", JoinType.INNER);
            when(statusJoin.get("id")).thenReturn(statusIdPath);
            when(statusIdPath.in(ids)).thenReturn((Predicate) inPredicate);

            Predicate result = CaseSpecifications.hasStatusesIn(ids)
                    .toPredicate(root, query, cb);

            verify(root).join("status", JoinType.INNER);
            verify(statusJoin).get("id");
            verify(statusIdPath).in(ids);
            assertThat(result).isEqualTo(inPredicate);
        }
    }

    // =========================================================================
    // dueBetween()
    // =========================================================================

    @Nested
    @DisplayName("dueBetween()")
    class DueBetweenTests {

        @Mock private Path<LocalDate> dueDtPath;

        @BeforeEach
        void setUp() {
            lenient().when(root.<LocalDate>get("dueDt")).thenReturn(dueDtPath);
        }

        @Test
        @DisplayName("both null returns null predicate")
        void dueBetween_bothNull_returnsNull() {
            Predicate result = CaseSpecifications.dueBetween(null, null)
                    .toPredicate(root, query, cb);

            assertThat(result).isNull();
            verifyNoInteractions(cb);
        }

        @Test
        @DisplayName("start only returns greaterThanOrEqualTo predicate")
        void dueBetween_startOnly_returnsGreaterThanOrEqualTo() {
            LocalDate start = LocalDate.of(2024, 1, 1);
            when(cb.greaterThanOrEqualTo(dueDtPath, start)).thenReturn(predicate);

            Predicate result = CaseSpecifications.dueBetween(start, null)
                    .toPredicate(root, query, cb);

            verify(cb).greaterThanOrEqualTo(dueDtPath, start);
            assertThat(result).isEqualTo(predicate);
        }

        @Test
        @DisplayName("end only returns lessThanOrEqualTo predicate")
        void dueBetween_endOnly_returnsLessThanOrEqualTo() {
            LocalDate end = LocalDate.of(2024, 12, 31);
            when(cb.lessThanOrEqualTo(dueDtPath, end)).thenReturn(predicate);

            Predicate result = CaseSpecifications.dueBetween(null, end)
                    .toPredicate(root, query, cb);

            verify(cb).lessThanOrEqualTo(dueDtPath, end);
            assertThat(result).isEqualTo(predicate);
        }

        @Test
        @DisplayName("both start and end returns between predicate")
        void dueBetween_bothProvided_returnsBetween() {
            LocalDate start = LocalDate.of(2024, 1, 1);
            LocalDate end   = LocalDate.of(2024, 12, 31);
            when(cb.between(dueDtPath, start, end)).thenReturn(predicate);

            Predicate result = CaseSpecifications.dueBetween(start, end)
                    .toPredicate(root, query, cb);

            verify(cb).between(dueDtPath, start, end);
            assertThat(result).isEqualTo(predicate);
        }
    }

    // =========================================================================
    // requestBetween()
    // =========================================================================

    @Nested
    @DisplayName("requestBetween()")
    class RequestBetweenTests {

        @Mock private Path<LocalDate> requestDtPath;

        @BeforeEach
        void setUp() {
            lenient().when(root.<LocalDate>get("requestDt")).thenReturn(requestDtPath);
        }

        @Test
        @DisplayName("both null returns null predicate")
        void requestBetween_bothNull_returnsNull() {
            Predicate result = CaseSpecifications.requestBetween(null, null)
                    .toPredicate(root, query, cb);

            assertThat(result).isNull();
            verifyNoInteractions(cb);
        }

        @Test
        @DisplayName("start only returns greaterThanOrEqualTo predicate")
        void requestBetween_startOnly_returnsGreaterThanOrEqualTo() {
            LocalDate start = LocalDate.of(2024, 3, 1);
            when(cb.greaterThanOrEqualTo(requestDtPath, start)).thenReturn(predicate);

            Predicate result = CaseSpecifications.requestBetween(start, null)
                    .toPredicate(root, query, cb);

            verify(cb).greaterThanOrEqualTo(requestDtPath, start);
            assertThat(result).isEqualTo(predicate);
        }

        @Test
        @DisplayName("end only returns lessThanOrEqualTo predicate")
        void requestBetween_endOnly_returnsLessThanOrEqualTo() {
            LocalDate end = LocalDate.of(2024, 6, 30);
            when(cb.lessThanOrEqualTo(requestDtPath, end)).thenReturn(predicate);

            Predicate result = CaseSpecifications.requestBetween(null, end)
                    .toPredicate(root, query, cb);

            verify(cb).lessThanOrEqualTo(requestDtPath, end);
            assertThat(result).isEqualTo(predicate);
        }

        @Test
        @DisplayName("both start and end returns between predicate")
        void requestBetween_bothProvided_returnsBetween() {
            LocalDate start = LocalDate.of(2024, 3, 1);
            LocalDate end   = LocalDate.of(2024, 6, 30);
            when(cb.between(requestDtPath, start, end)).thenReturn(predicate);

            Predicate result = CaseSpecifications.requestBetween(start, end)
                    .toPredicate(root, query, cb);

            verify(cb).between(requestDtPath, start, end);
            assertThat(result).isEqualTo(predicate);
        }
    }
}
