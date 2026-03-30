package org.nnnn.ddd.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nnnn.ddd.model.ArrestInfo;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    private static final String ARR_ID = "ARR001";
    private static final Date ARR_DATE = Date.valueOf(LocalDate.of(2024, 1, 15));

    // =========================================================================
    // getArrestInfo(String arrId) — single result
    // =========================================================================

    @Nested
    @DisplayName("getArrestInfo(String)")
    class GetArrestInfoSingleTests {

        @Test
        @DisplayName("calls createNativeQuery with ArrestInfo class")
        void getArrestInfo_callsCreateNativeQuery() {
            ArrestInfo expected = new ArrestInfo();
            when(entityManager.createNativeQuery(anyString(), eq(ArrestInfo.class))).thenReturn(query);
            when(query.setParameter(eq(1), eq(ARR_ID))).thenReturn(query);
            when(query.getSingleResult()).thenReturn(expected);

            ArrestInfo result = cdwRepository.getArrestInfo(ARR_ID);

            verify(entityManager).createNativeQuery(anyString(), eq(ArrestInfo.class));
            assertThat(result).isEqualTo(expected);
        }

        @Test
        @DisplayName("sets parameter 1 to arrId")
        void getArrestInfo_setsArrIdParameter() {
            ArrestInfo expected = new ArrestInfo();
            when(entityManager.createNativeQuery(anyString(), eq(ArrestInfo.class))).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getSingleResult()).thenReturn(expected);

            cdwRepository.getArrestInfo(ARR_ID);

            verify(query).setParameter(1, ARR_ID);
        }

        @Test
        @DisplayName("calls getSingleResult and returns result")
        void getArrestInfo_callsGetSingleResult() {
            ArrestInfo expected = new ArrestInfo();
            when(entityManager.createNativeQuery(anyString(), eq(ArrestInfo.class))).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getSingleResult()).thenReturn(expected);

            ArrestInfo result = cdwRepository.getArrestInfo(ARR_ID);

            verify(query).getSingleResult();
            assertThat(result).isSameAs(expected);
        }
    }

    // =========================================================================
    // getArrestInfoSummary(String arrId) — single result overload
    // =========================================================================

    @Nested
    @DisplayName("getArrestInfoSummary(String)")
    class GetArrestInfoSummaryStringTests {

        @Test
        @DisplayName("calls createNativeQuery with ArrestInfo class")
        void getArrestInfoSummary_string_callsCreateNativeQuery() {
            ArrestInfo expected = new ArrestInfo();
            when(entityManager.createNativeQuery(anyString(), eq(ArrestInfo.class))).thenReturn(query);
            when(query.setParameter(eq(1), eq(ARR_ID))).thenReturn(query);
            when(query.getSingleResult()).thenReturn(expected);

            ArrestInfo result = cdwRepository.getArrestInfoSummary(ARR_ID);

            verify(entityManager).createNativeQuery(anyString(), eq(ArrestInfo.class));
            assertThat(result).isEqualTo(expected);
        }

        @Test
        @DisplayName("sets parameter 1 to arrId")
        void getArrestInfoSummary_string_setsParameter() {
            ArrestInfo expected = new ArrestInfo();
            when(entityManager.createNativeQuery(anyString(), eq(ArrestInfo.class))).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getSingleResult()).thenReturn(expected);

            cdwRepository.getArrestInfoSummary(ARR_ID);

            verify(query).setParameter(1, ARR_ID);
        }

        @Test
        @DisplayName("returns single result")
        void getArrestInfoSummary_string_returnsSingleResult() {
            ArrestInfo expected = new ArrestInfo();
            when(entityManager.createNativeQuery(anyString(), eq(ArrestInfo.class))).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getSingleResult()).thenReturn(expected);

            assertThat(cdwRepository.getArrestInfoSummary(ARR_ID)).isSameAs(expected);
        }
    }

    // =========================================================================
    // getArrestInfoSummary(List<String>) — list overload
    // NOTE: This has real logic — maps Object[] to ArrestInfo with masked=false
    // =========================================================================

    @Nested
    @DisplayName("getArrestInfoSummary(List<String>)")
    class GetArrestInfoSummaryListTests {

        @Test
        @DisplayName("returns empty list when no results")
        void getArrestInfoSummary_list_emptyResults_returnsEmptyList() {
            when(entityManager.createNativeQuery(anyString())).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getResultList()).thenReturn(Collections.emptyList());

            List<ArrestInfo> result = cdwRepository.getArrestInfoSummary(List.of(ARR_ID));

            assertThat(result).isEmpty();
        }

        @Test
        @DisplayName("maps Object[] result to ArrestInfo with masked=false")
        void getArrestInfoSummary_list_mapsResultToArrestInfo() {
            Object[] row = buildResultRow(ARR_ID, "Assault", ARR_DATE, "John", "Doe", "NYSID123", 'N');

            when(entityManager.createNativeQuery(anyString())).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getResultList()).thenReturn(List.of(row));

            List<ArrestInfo> result = cdwRepository.getArrestInfoSummary(List.of(ARR_ID));

            assertThat(result).hasSize(1);
            ArrestInfo info = result.get(0);
            assertThat(info.getArrId()).isEqualTo(ARR_ID);
            assertThat(info.getTopCharge()).isEqualTo("Assault");
        }

        @Test
        @DisplayName("masked=false so defendant name is NOT masked")
        void getArrestInfoSummary_list_notMasked_nameVisible() {
            Object[] row = buildResultRow(ARR_ID, "Assault", ARR_DATE, "John", "Doe", "NYSID123", 'N');

            when(entityManager.createNativeQuery(anyString())).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getResultList()).thenReturn(List.of(row));

            List<ArrestInfo> result = cdwRepository.getArrestInfoSummary(List.of(ARR_ID));

            ArrestInfo info = result.get(0);
            // Not masked — defendant first/last name should be present
            assertThat(info.getDeftFrstNm()).isEqualTo("John");
            assertThat(info.getDeftLastNm()).isEqualTo("Doe");
        }

        @Test
        @DisplayName("maps multiple rows to multiple ArrestInfo objects")
        void getArrestInfoSummary_list_multipleRows_mapsAll() {
            Object[] row1 = buildResultRow("ARR001", "Assault", ARR_DATE, "John", "Doe", "NYSID1", 'N');
            Object[] row2 = buildResultRow("ARR002", "Robbery", ARR_DATE, "Jane", "Smith", "NYSID2", 'N');

            when(entityManager.createNativeQuery(anyString())).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(row1, row2));

            List<ArrestInfo> result = cdwRepository.getArrestInfoSummary(List.of("ARR001", "ARR002"));

            assertThat(result).hasSize(2);
            assertThat(result.get(0).getArrId()).isEqualTo("ARR001");
            assertThat(result.get(1).getArrId()).isEqualTo("ARR002");
        }

        @Test
        @DisplayName("sets parameter 1 to arrIds list")
        void getArrestInfoSummary_list_setsParameter() {
            List<String> arrIds = List.of("ARR001", "ARR002");
            when(entityManager.createNativeQuery(anyString())).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getResultList()).thenReturn(Collections.emptyList());

            cdwRepository.getArrestInfoSummary(arrIds);

            verify(query).setParameter(1, arrIds);
        }

        @Test
        @DisplayName("maps sealed flag from result row")
        void getArrestInfoSummary_list_mapsSealedFlag() {
            Object[] row = buildResultRow(ARR_ID, "Assault", ARR_DATE, "John", "Doe", "NYSID123", 'Y');

            when(entityManager.createNativeQuery(anyString())).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getResultList()).thenReturn(List.of(row));

            List<ArrestInfo> result = cdwRepository.getArrestInfoSummary(List.of(ARR_ID));

            assertThat(result).hasSize(1);
            // Sealed flag Y means sealed arrest
            assertThat(result.get(0).getArrSealedFlg()).isEqualTo("Y");
        }
    }

    // =========================================================================
    // getMaskedArrestInfoSummary(List<String>)
    // NOTE: Same query as getArrestInfoSummary(List) but masked=true
    // =========================================================================

    @Nested
    @DisplayName("getMaskedArrestInfoSummary(List<String>)")
    class GetMaskedArrestInfoSummaryTests {

        @Test
        @DisplayName("returns empty list when no results")
        void getMaskedArrestInfoSummary_emptyResults_returnsEmptyList() {
            when(entityManager.createNativeQuery(anyString())).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getResultList()).thenReturn(Collections.emptyList());

            List<ArrestInfo> result = cdwRepository.getMaskedArrestInfoSummary(List.of(ARR_ID));

            assertThat(result).isEmpty();
        }

        @Test
        @DisplayName("maps Object[] result to ArrestInfo with masked=true")
        void getMaskedArrestInfoSummary_mapsResultWithMaskedTrue() {
            Object[] row = buildResultRow(ARR_ID, "Assault", ARR_DATE, "John", "Doe", "NYSID123", 'Y');

            when(entityManager.createNativeQuery(anyString())).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getResultList()).thenReturn(List.of(row));

            List<ArrestInfo> result = cdwRepository.getMaskedArrestInfoSummary(List.of(ARR_ID));

            assertThat(result).hasSize(1);
            ArrestInfo info = result.get(0);
            assertThat(info.getArrId()).isEqualTo(ARR_ID);
        }

        @Test
        @DisplayName("masked=true so defendant name IS masked")
        void getMaskedArrestInfoSummary_masked_nameIsMasked() {
            Object[] row = buildResultRow(ARR_ID, "Assault", ARR_DATE, "John", "Doe", "NYSID123", 'Y');

            when(entityManager.createNativeQuery(anyString())).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getResultList()).thenReturn(List.of(row));

            List<ArrestInfo> result = cdwRepository.getMaskedArrestInfoSummary(List.of(ARR_ID));

            ArrestInfo info = result.get(0);
            // Masked — defendant name should be hidden (null or masked value)
            assertThat(info.getDeftFrstNm()).isNotEqualTo("John");
            assertThat(info.getDeftLastNm()).isNotEqualTo("Doe");
        }

        @Test
        @DisplayName("maps multiple rows correctly")
        void getMaskedArrestInfoSummary_multipleRows_mapsAll() {
            Object[] row1 = buildResultRow("ARR001", "Assault", ARR_DATE, "John", "Doe", "NYSID1", 'Y');
            Object[] row2 = buildResultRow("ARR002", "Robbery", ARR_DATE, "Jane", "Smith", "NYSID2", 'Y');

            when(entityManager.createNativeQuery(anyString())).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getResultList()).thenReturn(Arrays.asList(row1, row2));

            List<ArrestInfo> result = cdwRepository.getMaskedArrestInfoSummary(List.of("ARR001", "ARR002"));

            assertThat(result).hasSize(2);
        }

        @Test
        @DisplayName("sets parameter 1 to arrIds list")
        void getMaskedArrestInfoSummary_setsParameter() {
            List<String> arrIds = List.of("ARR001");
            when(entityManager.createNativeQuery(anyString())).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getResultList()).thenReturn(Collections.emptyList());

            cdwRepository.getMaskedArrestInfoSummary(arrIds);

            verify(query).setParameter(1, arrIds);
        }

        @Test
        @DisplayName("calls getResultList not getSingleResult")
        void getMaskedArrestInfoSummary_callsGetResultList() {
            when(entityManager.createNativeQuery(anyString())).thenReturn(query);
            when(query.setParameter(eq(1), any())).thenReturn(query);
            when(query.getResultList()).thenReturn(Collections.emptyList());

            cdwRepository.getMaskedArrestInfoSummary(List.of(ARR_ID));

            verify(query).getResultList();
            verify(query, never()).getSingleResult();
        }
    }

    // =========================================================================
    // Helper
    // =========================================================================

    /**
     * Builds a mock Object[] row matching the SELECT column order:
     * result[0] = ARR_ID, result[1] = TOP_CHARGE, result[2] = ARR_DT,
     * result[3] = DEFT_FRST_NM, result[4] = DEFT_LAST_NM,
     * result[5] = DEFT_NYSID, result[6] = ARR_SEALED_FLG
     */
    private Object[] buildResultRow(String arrId, String topCharge, Date arrDt,
                                     String frstNm, String lastNm,
                                     String nysid, Character sealedFlg) {
        return new Object[]{arrId, topCharge, arrDt, frstNm, lastNm, nysid, sealedFlg};
    }
}
