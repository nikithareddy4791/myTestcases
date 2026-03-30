package org.nnnn.ddd.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nnnn.ddd.model.ArrestInfo;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CDWRepository Tests")
class CDWRepositoryTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @InjectMocks
    private CDWRepository cdwRepository;

    // Reusable test data
    private static final String ARR_ID        = "ARR123456";
    private static final String TOP_CHARGE    = "ASSAULT";
    private static final Date   ARR_DT        = Date.valueOf("2024-01-15");
    private static final String DEFT_FRST_NM  = "JOHN";
    private static final String DEFT_LAST_NM  = "DOE";
    private static final String DEFT_NYSID    = "NYSID001";
    private static final Character SEALED_FLG = 'N';

    // -------------------------------------------------------------------------
    // getArrestInfo(String arrId)
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("getArrestInfo(String arrId)")
    class GetArrestInfoTests {

        private ArrestInfo mockArrestInfo;

        @BeforeEach
        void setUp() {
            mockArrestInfo = mock(ArrestInfo.class);
            when(entityManager.createNativeQuery(anyString(), eq(ArrestInfo.class)))
                    .thenReturn(query);
            when(query.setParameter(anyInt(), any())).thenReturn(query);
            when(query.getSingleResult()).thenReturn(mockArrestInfo);
        }

        @Test
        @DisplayName("Should call createNativeQuery with ArrestInfo result class")
        void shouldCallCreateNativeQueryWithArrestInfoClass() {
            cdwRepository.getArrestInfo(ARR_ID);

            verify(entityManager).createNativeQuery(anyString(), eq(ArrestInfo.class));
        }

        @Test
        @DisplayName("Should bind arrId to parameter position 1")
        void shouldBindArrIdToParameterPosition1() {
            cdwRepository.getArrestInfo(ARR_ID);

            verify(query).setParameter(1, ARR_ID);
        }

        @Test
        @DisplayName("Should call getSingleResult exactly once")
        void shouldCallGetSingleResultOnce() {
            cdwRepository.getArrestInfo(ARR_ID);

            verify(query, times(1)).getSingleResult();
        }

        @Test
        @DisplayName("Should return the ArrestInfo returned by getSingleResult")
        void shouldReturnArrestInfoFromQuery() {
            ArrestInfo result = cdwRepository.getArrestInfo(ARR_ID);

            assertNotNull(result);
            assertSame(mockArrestInfo, result);
        }

        @Test
        @DisplayName("Should propagate exception when getSingleResult throws")
        void shouldPropagateExceptionWhenNoResultFound() {
            when(query.getSingleResult())
                    .thenThrow(new jakarta.persistence.NoResultException("no result"));

            assertThrows(jakarta.persistence.NoResultException.class,
                    () -> cdwRepository.getArrestInfo(ARR_ID));
        }

        @Test
        @DisplayName("Should not interact with query beyond setParameter and getSingleResult")
        void shouldNotHaveUnexpectedQueryInteractions() {
            cdwRepository.getArrestInfo(ARR_ID);

            verify(query).setParameter(1, ARR_ID);
            verify(query).getSingleResult();
            verifyNoMoreInteractions(query);
        }
    }

    // -------------------------------------------------------------------------
    // getArrestInfoSummary(String arrId)
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("getArrestInfoSummary(String arrId)")
    class GetArrestInfoSummaryByIdTests {

        private ArrestInfo mockArrestInfo;

        @BeforeEach
        void setUp() {
            mockArrestInfo = mock(ArrestInfo.class);
            when(entityManager.createNativeQuery(anyString(), eq(ArrestInfo.class)))
                    .thenReturn(query);
            when(query.setParameter(anyInt(), any())).thenReturn(query);
            when(query.getSingleResult()).thenReturn(mockArrestInfo);
        }

        @Test
        @DisplayName("Should call createNativeQuery with ArrestInfo result class")
        void shouldCallCreateNativeQueryWithArrestInfoClass() {
            cdwRepository.getArrestInfoSummary(ARR_ID);

            verify(entityManager).createNativeQuery(anyString(), eq(ArrestInfo.class));
        }

        @Test
        @DisplayName("Should bind arrId to parameter position 1")
        void shouldBindArrIdToParameterPosition1() {
            cdwRepository.getArrestInfoSummary(ARR_ID);

            verify(query).setParameter(1, ARR_ID);
        }

        @Test
        @DisplayName("Should call getSingleResult exactly once")
        void shouldCallGetSingleResultOnce() {
            cdwRepository.getArrestInfoSummary(ARR_ID);

            verify(query, times(1)).getSingleResult();
        }

        @Test
        @DisplayName("Should return the ArrestInfo returned by getSingleResult")
        void shouldReturnArrestInfoFromQuery() {
            ArrestInfo result = cdwRepository.getArrestInfoSummary(ARR_ID);

            assertNotNull(result);
            assertSame(mockArrestInfo, result);
        }

        @Test
        @DisplayName("Should propagate NoResultException when record is not found")
        void shouldPropagateNoResultException() {
            when(query.getSingleResult())
                    .thenThrow(new jakarta.persistence.NoResultException("no result"));

            assertThrows(jakarta.persistence.NoResultException.class,
                    () -> cdwRepository.getArrestInfoSummary(ARR_ID));
        }

        @Test
        @DisplayName("Should use a different (summary) SQL than getArrestInfo")
        void shouldUseSummarySql() {
            // Capture the SQL strings passed for both methods and assert they differ,
            // confirming the correct overload is dispatched.
            ArgumentCaptor<String> sqlCaptor = ArgumentCaptor.forClass(String.class);
            when(entityManager.createNativeQuery(sqlCaptor.capture(), eq(ArrestInfo.class)))
                    .thenReturn(query);

            cdwRepository.getArrestInfoSummary(ARR_ID);
            String summarySql = sqlCaptor.getValue();

            // The summary query only selects ARR_ID and TOP_CHARGE
            assertTrue(summarySql.contains("ARR.ARR_ID"),
                    "Summary SQL should reference ARR_ID");
            assertFalse(summarySql.contains("LISTAGG"),
                    "Summary SQL should NOT contain the LISTAGG aggregation used in the full query");
        }
    }

    // -------------------------------------------------------------------------
    // getArrestInfoSummary(List<String> arrIds) — unmasked
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("getArrestInfoSummary(List<String> arrIds)")
    class GetArrestInfoSummaryByListTests {

        @BeforeEach
        void setUp() {
            when(entityManager.createNativeQuery(anyString())).thenReturn(query);
            when(query.setParameter(anyInt(), any())).thenReturn(query);
        }

        @Test
        @DisplayName("Should call createNativeQuery without a result-class (raw query)")
        void shouldCallCreateNativeQueryWithoutResultClass() {
            when(query.getResultList()).thenReturn(Collections.emptyList());

            cdwRepository.getArrestInfoSummary(List.of(ARR_ID));

            verify(entityManager).createNativeQuery(anyString());
            verify(entityManager, never()).createNativeQuery(anyString(), any(Class.class));
        }

        @Test
        @DisplayName("Should bind the arrIds list to parameter position 1")
        void shouldBindArrIdsListToParameterPosition1() {
            List<String> arrIds = List.of(ARR_ID, "ARR999");
            when(query.getResultList()).thenReturn(Collections.emptyList());

            cdwRepository.getArrestInfoSummary(arrIds);

            verify(query).setParameter(1, arrIds);
        }

        @Test
        @DisplayName("Should return empty list when query returns no rows")
        void shouldReturnEmptyListWhenNoResults() {
            when(query.getResultList()).thenReturn(Collections.emptyList());

            List<ArrestInfo> result = cdwRepository.getArrestInfoSummary(List.of(ARR_ID));

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should map each Object[] row to ArrestInfo with masked=false")
        void shouldMapRowsToArrestInfoUnmasked() {
            Object[] row = { ARR_ID, TOP_CHARGE, ARR_DT, DEFT_FRST_NM, DEFT_LAST_NM, DEFT_NYSID, SEALED_FLG };
            when(query.getResultList()).thenReturn(List.of(row));

            List<ArrestInfo> result = cdwRepository.getArrestInfoSummary(List.of(ARR_ID));

            assertEquals(1, result.size());
            ArrestInfo info = result.get(0);
            assertEquals(ARR_ID,       info.getArrId());
            assertEquals(TOP_CHARGE,   info.getTopCharge());
            assertEquals(ARR_DT,       info.getArrDt());
            assertEquals(DEFT_FRST_NM, info.getDeftFrstNm());
            assertEquals(DEFT_LAST_NM, info.getDeftLastNm());
            assertEquals(DEFT_NYSID,   info.getDeftNysid());
            assertEquals(SEALED_FLG,   info.getArrSealedFlg());
            assertFalse(info.isMasked(),
                    "getArrestInfoSummary(List) should always build ArrestInfo with masked=false");
        }

        @Test
        @DisplayName("Should return a list with one entry per result row")
        void shouldReturnOneEntryPerRow() {
            Object[] row1 = { "ARR001", TOP_CHARGE, ARR_DT, DEFT_FRST_NM, DEFT_LAST_NM, DEFT_NYSID, SEALED_FLG };
            Object[] row2 = { "ARR002", "ROBBERY",  ARR_DT, "JANE", "SMITH", "NYSID002", 'Y' };
            when(query.getResultList()).thenReturn(List.of(row1, row2));

            List<ArrestInfo> result = cdwRepository.getArrestInfoSummary(List.of("ARR001", "ARR002"));

            assertEquals(2, result.size());
            assertEquals("ARR001", result.get(0).getArrId());
            assertEquals("ARR002", result.get(1).getArrId());
        }

        @Test
        @DisplayName("Should propagate exception thrown by getResultList")
        void shouldPropagateExceptionFromGetResultList() {
            when(query.getResultList())
                    .thenThrow(new jakarta.persistence.PersistenceException("DB error"));

            assertThrows(jakarta.persistence.PersistenceException.class,
                    () -> cdwRepository.getArrestInfoSummary(List.of(ARR_ID)));
        }
    }

    // -------------------------------------------------------------------------
    // getMaskedArrestInfoSummary(List<String> arrIds)
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("getMaskedArrestInfoSummary(List<String> arrIds)")
    class GetMaskedArrestInfoSummaryTests {

        @BeforeEach
        void setUp() {
            when(entityManager.createNativeQuery(anyString())).thenReturn(query);
            when(query.setParameter(anyInt(), any())).thenReturn(query);
        }

        @Test
        @DisplayName("Should call createNativeQuery without a result-class (raw query)")
        void shouldCallCreateNativeQueryWithoutResultClass() {
            when(query.getResultList()).thenReturn(Collections.emptyList());

            cdwRepository.getMaskedArrestInfoSummary(List.of(ARR_ID));

            verify(entityManager).createNativeQuery(anyString());
            verify(entityManager, never()).createNativeQuery(anyString(), any(Class.class));
        }

        @Test
        @DisplayName("Should bind the arrIds list to parameter position 1")
        void shouldBindArrIdsListToParameterPosition1() {
            List<String> arrIds = List.of(ARR_ID, "ARR999");
            when(query.getResultList()).thenReturn(Collections.emptyList());

            cdwRepository.getMaskedArrestInfoSummary(arrIds);

            verify(query).setParameter(1, arrIds);
        }

        @Test
        @DisplayName("Should return empty list when query returns no rows")
        void shouldReturnEmptyListWhenNoResults() {
            when(query.getResultList()).thenReturn(Collections.emptyList());

            List<ArrestInfo> result = cdwRepository.getMaskedArrestInfoSummary(List.of(ARR_ID));

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should map each Object[] row to ArrestInfo with masked=true")
        void shouldMapRowsToArrestInfoMasked() {
            Object[] row = { ARR_ID, TOP_CHARGE, ARR_DT, DEFT_FRST_NM, DEFT_LAST_NM, DEFT_NYSID, SEALED_FLG };
            when(query.getResultList()).thenReturn(List.of(row));

            List<ArrestInfo> result = cdwRepository.getMaskedArrestInfoSummary(List.of(ARR_ID));

            assertEquals(1, result.size());
            ArrestInfo info = result.get(0);
            assertEquals(ARR_ID,       info.getArrId());
            assertEquals(TOP_CHARGE,   info.getTopCharge());
            assertEquals(ARR_DT,       info.getArrDt());
            assertEquals(DEFT_FRST_NM, info.getDeftFrstNm());
            assertEquals(DEFT_LAST_NM, info.getDeftLastNm());
            assertEquals(DEFT_NYSID,   info.getDeftNysid());
            assertEquals(SEALED_FLG,   info.getArrSealedFlg());
            assertTrue(info.isMasked(),
                    "getMaskedArrestInfoSummary should always build ArrestInfo with masked=true");
        }

        @Test
        @DisplayName("Should return one entry per result row")
        void shouldReturnOneEntryPerRow() {
            Object[] row1 = { "ARR001", TOP_CHARGE, ARR_DT, DEFT_FRST_NM, DEFT_LAST_NM, DEFT_NYSID, SEALED_FLG };
            Object[] row2 = { "ARR002", "ROBBERY",  ARR_DT, "JANE", "SMITH", "NYSID002", 'Y' };
            when(query.getResultList()).thenReturn(List.of(row1, row2));

            List<ArrestInfo> result = cdwRepository.getMaskedArrestInfoSummary(List.of("ARR001", "ARR002"));

            assertEquals(2, result.size());
        }

        @Test
        @DisplayName("Masked and unmasked list queries should use the same SQL")
        void maskedAndUnmaskedShouldUseSameSql() {
            // Both methods share identical SQL — only the masked flag passed to the
            // ArrestInfo constructor differs. Capture and compare the SQL strings.
            ArgumentCaptor<String> sqlCaptor = ArgumentCaptor.forClass(String.class);
            when(entityManager.createNativeQuery(sqlCaptor.capture())).thenReturn(query);
            when(query.getResultList()).thenReturn(Collections.emptyList());

            cdwRepository.getArrestInfoSummary(List.of(ARR_ID));
            String unmaskedSql = sqlCaptor.getValue();

            cdwRepository.getMaskedArrestInfoSummary(List.of(ARR_ID));
            String maskedSql = sqlCaptor.getValue();

            assertEquals(unmaskedSql, maskedSql,
                    "Both list methods should execute identical SQL; only the masked flag differs");
        }

        @Test
        @DisplayName("Should propagate exception thrown by getResultList")
        void shouldPropagateExceptionFromGetResultList() {
            when(query.getResultList())
                    .thenThrow(new jakarta.persistence.PersistenceException("DB error"));

            assertThrows(jakarta.persistence.PersistenceException.class,
                    () -> cdwRepository.getMaskedArrestInfoSummary(List.of(ARR_ID)));
        }
    }

    // -------------------------------------------------------------------------
    // Cross-method: SQL distinctness
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("SQL Distinctness Across Methods")
    class SqlDistinctnessTests {

        @Test
        @DisplayName("getArrestInfo SQL should be distinct from getArrestInfoSummary(String) SQL")
        void fullQuerySqlShouldDifferFromSummarySql() {
            ArgumentCaptor<String> sqlCaptor = ArgumentCaptor.forClass(String.class);
            when(entityManager.createNativeQuery(sqlCaptor.capture(), eq(ArrestInfo.class)))
                    .thenReturn(query);
            when(query.setParameter(anyInt(), any())).thenReturn(query);
            when(query.getSingleResult()).thenReturn(mock(ArrestInfo.class));

            cdwRepository.getArrestInfo(ARR_ID);
            String fullSql = sqlCaptor.getValue();

            cdwRepository.getArrestInfoSummary(ARR_ID);
            String summarySql = sqlCaptor.getValue();

            assertNotEquals(fullSql, summarySql,
                    "Full-detail query SQL must differ from summary query SQL");
        }

        @Test
        @DisplayName("getArrestInfo SQL should contain LISTAGG for complaint IDs aggregation")
        void fullQueryShouldContainListagg() {
            ArgumentCaptor<String> sqlCaptor = ArgumentCaptor.forClass(String.class);
            when(entityManager.createNativeQuery(sqlCaptor.capture(), eq(ArrestInfo.class)))
                    .thenReturn(query);
            when(query.setParameter(anyInt(), any())).thenReturn(query);
            when(query.getSingleResult()).thenReturn(mock(ArrestInfo.class));

            cdwRepository.getArrestInfo(ARR_ID);

            assertTrue(sqlCaptor.getValue().contains("LISTAGG"),
                    "Full query must include LISTAGG for aggregating complaint IDs");
        }

        @Test
        @DisplayName("getArrestInfoSummary(String) SQL should contain WHERE ARR_ID filter")
        void summaryQueryShouldContainWhereArrIdFilter() {
            ArgumentCaptor<String> sqlCaptor = ArgumentCaptor.forClass(String.class);
            when(entityManager.createNativeQuery(sqlCaptor.capture(), eq(ArrestInfo.class)))
                    .thenReturn(query);
            when(query.setParameter(anyInt(), any())).thenReturn(query);
            when(query.getSingleResult()).thenReturn(mock(ArrestInfo.class));

            cdwRepository.getArrestInfoSummary(ARR_ID);

            assertTrue(sqlCaptor.getValue().contains("WHERE ARR.ARR_ID = ?1"),
                    "Summary query must filter by ARR_ID with a positional parameter");
        }

        @Test
        @DisplayName("List queries should use IN (?1) clause")
        void listQueryShouldUseInClause() {
            ArgumentCaptor<String> sqlCaptor = ArgumentCaptor.forClass(String.class);
            when(entityManager.createNativeQuery(sqlCaptor.capture())).thenReturn(query);
            when(query.setParameter(anyInt(), any())).thenReturn(query);
            when(query.getResultList()).thenReturn(Collections.emptyList());

            cdwRepository.getArrestInfoSummary(List.of(ARR_ID));

            assertTrue(sqlCaptor.getValue().contains("IN (?1)"),
                    "List query must use an IN clause with a positional parameter");
        }
    }
}
