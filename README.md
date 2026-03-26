package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.nnnn.ddd.AppConstants;
import org.threeten.bp.LocalDate;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ArrestInfo Model Tests")
class ArrestInfoTest {

    // =========================================================================
    // Default constructor + getters/setters
    // =========================================================================

    @Nested
    @DisplayName("Default Constructor and Setters")
    class DefaultConstructorTests {

        @Test
        @DisplayName("default constructor creates instance with null fields")
        void defaultConstructor_createsInstanceWithNullFields() {
            ArrestInfo info = new ArrestInfo();
            assertThat(info).isNotNull();
            assertThat(info.getArrId()).isNull();
            assertThat(info.getDeftFrstNm()).isNull();
            assertThat(info.getDeftLastNm()).isNull();
            assertThat(info.getArrSealedFlg()).isNull();
        }

        @Test
        @DisplayName("setters and getters work correctly")
        void settersAndGetters_workCorrectly() {
            ArrestInfo info = new ArrestInfo();
            LocalDate today = LocalDate.now();

            info.setArrId("ARR001");
            info.setArrPct("061");
            info.setArrPb("BK");
            info.setTopCharge("PL 265.03");
            info.setAoTax("123456");
            info.setAoFrstNm("John");
            info.setAoLastNm("Smith");
            info.setAoCmd("061");
            info.setDeftFrstNm("Jane");
            info.setDeftLastNm("Doe");
            info.setDeftNysid("NY123456");
            info.setArrSealedFlg("N");
            info.setDeftGender("F");
            info.setArrDt(today);
            info.setDeftBrthDt(today);
            info.setFelonyFlg(1);
            info.setDvFlg(0);
            info.setIndexCrimeFlg(0);
            info.setCmplntId(List.of("C001", "C002"));

            assertThat(info.getArrId()).isEqualTo("ARR001");
            assertThat(info.getArrPct()).isEqualTo("061");
            assertThat(info.getArrPb()).isEqualTo("BK");
            assertThat(info.getTopCharge()).isEqualTo("PL 265.03");
            assertThat(info.getAoTax()).isEqualTo("123456");
            assertThat(info.getAoFrstNm()).isEqualTo("John");
            assertThat(info.getAoLastNm()).isEqualTo("Smith");
            assertThat(info.getAoCmd()).isEqualTo("061");
            assertThat(info.getDeftFrstNm()).isEqualTo("Jane");
            assertThat(info.getDeftLastNm()).isEqualTo("Doe");
            assertThat(info.getDeftNysid()).isEqualTo("NY123456");
            assertThat(info.getArrSealedFlg()).isEqualTo("N");
            assertThat(info.getDeftGender()).isEqualTo("F");
            assertThat(info.getArrDt()).isEqualTo(today);
            assertThat(info.getDeftBrthDt()).isEqualTo(today);
            assertThat(info.getFelonyFlg()).isEqualTo(1);
            assertThat(info.getDvFlg()).isEqualTo(0);
            assertThat(info.getIndexCrimeFlg()).isEqualTo(0);
            assertThat(info.getCmplntId()).containsExactly("C001", "C002");
        }

        @Test
        @DisplayName("addCmplntIdItem adds to list")
        void addCmplntIdItem_addsToList() {
            ArrestInfo info = new ArrestInfo();
            info.addCmplntIdItem("C001");
            info.addCmplntIdItem("C002");

            assertThat(info.getCmplntId()).containsExactly("C001", "C002");
        }

        @Test
        @DisplayName("addCmplntIdItem initializes list if null")
        void addCmplntIdItem_initializesListIfNull() {
            ArrestInfo info = new ArrestInfo();
            assertThat(info.getCmplntId()).isNull();

            info.addCmplntIdItem("C001");

            assertThat(info.getCmplntId()).isNotNull().hasSize(1);
        }
    }

    // =========================================================================
    // Builder-style constructor (5 args)
    // =========================================================================

    @Nested
    @DisplayName("5-Arg Constructor (summary)")
    class FiveArgConstructorTests {

        @Test
        @DisplayName("sets arrId, topCharge, deftFrstNm, deftLastNm, deftNysid from sql.Date")
        void constructor_setsBasicFields() {
            Date sqlDate = Date.valueOf("2024-01-15");
            ArrestInfo info = new ArrestInfo("ARR001", "PL 265.03", sqlDate, "Jane", "Doe", "NY123456");

            assertThat(info.getArrId()).isEqualTo("ARR001");
            assertThat(info.getTopCharge()).isEqualTo("PL 265.03");
            assertThat(info.getDeftFrstNm()).isEqualTo("Jane");
            assertThat(info.getDeftLastNm()).isEqualTo("Doe");
            assertThat(info.getDeftNysid()).isEqualTo("NY123456");
            assertThat(info.getArrDt()).isNotNull();
        }

        @Test
        @DisplayName("converts sql.Date to LocalDate correctly")
        void constructor_convertsSqlDateToLocalDate() {
            Date sqlDate = Date.valueOf("2024-03-15");
            ArrestInfo info = new ArrestInfo("ARR001", "PL 265.03", sqlDate, "Jane", "Doe", "NY123");

            assertThat(info.getArrDt().getYear()).isEqualTo(2024);
            assertThat(info.getArrDt().getMonthValue()).isEqualTo(3);
            assertThat(info.getArrDt().getDayOfMonth()).isEqualTo(15);
        }
    }

    // =========================================================================
    // Masked constructor (8 args) — sealed flag + masking logic
    // =========================================================================

    @Nested
    @DisplayName("8-Arg Constructor (masked summary)")
    class EightArgConstructorTests {

        @Test
        @DisplayName("unsealed arrest - shows real defendant info")
        void constructor_unsealedArrest_showsRealInfo() {
            Date sqlDate = Date.valueOf("2024-01-15");
            ArrestInfo info = new ArrestInfo("ARR001", "PL 265.03", sqlDate,
                    "Jane", "Doe", "NY123456", 'N', true);

            assertThat(info.getDeftFrstNm()).isEqualTo("Jane");
            assertThat(info.getDeftLastNm()).isEqualTo("Doe");
            assertThat(info.getDeftNysid()).isEqualTo("NY123456");
            assertThat(info.getTopCharge()).isEqualTo("PL 265.03");
            assertThat(info.getArrSealedFlg()).isEqualTo("N");
        }

        @Test
        @DisplayName("sealed arrest with maskSealed=true - masks defendant info")
        void constructor_sealedArrest_maskSealed_masksInfo() {
            Date sqlDate = Date.valueOf("2024-01-15");
            ArrestInfo info = new ArrestInfo("ARR001", "PL 265.03", sqlDate,
                    "Jane", "Doe", "NY123456", 'Y', true);

            assertThat(info.getDeftFrstNm()).isEqualTo(AppConstants.SEALED_STRING);
            assertThat(info.getDeftLastNm()).isEqualTo(AppConstants.SEALED_STRING);
            assertThat(info.getDeftNysid()).isEqualTo(AppConstants.SEALED_STRING);
            assertThat(info.getTopCharge()).isEqualTo(AppConstants.SEALED_STRING);
            assertThat(info.getArrSealedFlg()).isEqualTo("Y");
        }

        @Test
        @DisplayName("sealed arrest with maskSealed=false - shows real info despite sealed")
        void constructor_sealedArrest_maskSealedFalse_showsRealInfo() {
            Date sqlDate = Date.valueOf("2024-01-15");
            ArrestInfo info = new ArrestInfo("ARR001", "PL 265.03", sqlDate,
                    "Jane", "Doe", "NY123456", 'Y', false);

            assertThat(info.getDeftFrstNm()).isEqualTo("Jane");
            assertThat(info.getDeftLastNm()).isEqualTo("Doe");
            assertThat(info.getDeftNysid()).isEqualTo("NY123456");
            assertThat(info.getTopCharge()).isEqualTo("PL 265.03");
        }

        @Test
        @DisplayName("null arrSealedFlg - sets arrSealedFlg to null")
        void constructor_nullSealedFlg_setsNull() {
            Date sqlDate = Date.valueOf("2024-01-15");
            ArrestInfo info = new ArrestInfo("ARR001", "PL 265.03", sqlDate,
                    "Jane", "Doe", "NY123456", null, true);

            assertThat(info.getArrSealedFlg()).isNull();
            // Not sealed so real info shown
            assertThat(info.getDeftFrstNm()).isEqualTo("Jane");
        }
    }

    // =========================================================================
    // Full constructor (19 args) — all fields including flags
    // =========================================================================

    @Nested
    @DisplayName("Full Constructor (19 args)")
    class FullConstructorTests {

        private ArrestInfo buildFullArrestInfo(String kyCd, Character dvFlg, Character lawCatCd,
                                               String cmplntId, Character sealedFlg) {
            return new ArrestInfo(
                    "ARR001",
                    Date.valueOf("2024-01-15"),
                    "061",
                    sealedFlg,
                    "PL 265.03",
                    "BK",
                    "123456",
                    "John",
                    "Smith",
                    "061",
                    "Jane",
                    "Doe",
                    "NY123",
                    "F",
                    Date.valueOf("1990-05-20"),
                    cmplntId,
                    kyCd,
                    dvFlg,
                    lawCatCd
            );
        }

        @Test
        @DisplayName("sets all basic fields correctly")
        void constructor_setsAllBasicFields() {
            ArrestInfo info = buildFullArrestInfo(null, null, null, null, 'N');

            assertThat(info.getArrId()).isEqualTo("ARR001");
            assertThat(info.getArrPct()).isEqualTo("061");
            assertThat(info.getArrPb()).isEqualTo("BK");
            assertThat(info.getAoTax()).isEqualTo("123456");
            assertThat(info.getAoFrstNm()).isEqualTo("John");
            assertThat(info.getAoLastNm()).isEqualTo("Smith");
            assertThat(info.getAoCmd()).isEqualTo("061");
            assertThat(info.getDeftFrstNm()).isEqualTo("Jane");
            assertThat(info.getDeftLastNm()).isEqualTo("Doe");
            assertThat(info.getDeftNysid()).isEqualTo("NY123");
            assertThat(info.getDeftGender()).isEqualTo("F");
            assertThat(info.getArrSealedFlg()).isEqualTo("N");
        }

        @Test
        @DisplayName("converts deftBrthDt from sql.Date to LocalDate")
        void constructor_convertsDeftBrthDt() {
            ArrestInfo info = buildFullArrestInfo(null, null, null, null, 'N');

            assertThat(info.getDeftBrthDt()).isNotNull();
            assertThat(info.getDeftBrthDt().getYear()).isEqualTo(1990);
            assertThat(info.getDeftBrthDt().getMonthValue()).isEqualTo(5);
            assertThat(info.getDeftBrthDt().getDayOfMonth()).isEqualTo(20);
        }

        @Test
        @DisplayName("null deftBrthDt stays null")
        void constructor_nullDeftBrthDt_staysNull() {
            ArrestInfo info = new ArrestInfo(
                    "ARR001", Date.valueOf("2024-01-15"), "061", 'N',
                    "PL 265.03", "BK", "123456", "John", "Smith", "061",
                    "Jane", "Doe", "NY123", "F",
                    null, // null deftBrthDt
                    null, null, null, null
            );

            assertThat(info.getDeftBrthDt()).isNull();
        }

        // ===== indexCrimeFlg logic =====

        @Test
        @DisplayName("indexCrimeFlg=0 when kyCd is null")
        void constructor_indexCrimeFlg_nullKyCd_setsZero() {
            ArrestInfo info = buildFullArrestInfo(null, null, null, null, 'N');
            assertThat(info.getIndexCrimeFlg()).isEqualTo(0);
        }

        @Test
        @DisplayName("indexCrimeFlg=1 when kyCd is 101")
        void constructor_indexCrimeFlg_kyCd101_setsOne() {
            assertThat(buildFullArrestInfo("101", null, null, null, 'N').getIndexCrimeFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("indexCrimeFlg=1 when kyCd is 102")
        void constructor_indexCrimeFlg_kyCd102_setsOne() {
            assertThat(buildFullArrestInfo("102", null, null, null, 'N').getIndexCrimeFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("indexCrimeFlg=1 when kyCd is 103")
        void constructor_indexCrimeFlg_kyCd103_setsOne() {
            assertThat(buildFullArrestInfo("103", null, null, null, 'N').getIndexCrimeFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("indexCrimeFlg=1 when kyCd is 104")
        void constructor_indexCrimeFlg_kyCd104_setsOne() {
            assertThat(buildFullArrestInfo("104", null, null, null, 'N').getIndexCrimeFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("indexCrimeFlg=1 when kyCd is 105")
        void constructor_indexCrimeFlg_kyCd105_setsOne() {
            assertThat(buildFullArrestInfo("105", null, null, null, 'N').getIndexCrimeFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("indexCrimeFlg=1 when kyCd is 106")
        void constructor_indexCrimeFlg_kyCd106_setsOne() {
            assertThat(buildFullArrestInfo("106", null, null, null, 'N').getIndexCrimeFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("indexCrimeFlg=1 when kyCd is 107")
        void constructor_indexCrimeFlg_kyCd107_setsOne() {
            assertThat(buildFullArrestInfo("107", null, null, null, 'N').getIndexCrimeFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("indexCrimeFlg=1 when kyCd is 109")
        void constructor_indexCrimeFlg_kyCd109_setsOne() {
            assertThat(buildFullArrestInfo("109", null, null, null, 'N').getIndexCrimeFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("indexCrimeFlg=0 when kyCd is non-index value")
        void constructor_indexCrimeFlg_nonIndexKyCd_setsZero() {
            assertThat(buildFullArrestInfo("999", null, null, null, 'N').getIndexCrimeFlg()).isEqualTo(0);
        }

        // ===== dvFlg logic =====

        @Test
        @DisplayName("dvFlg=1 when dvFlg char is Y")
        void constructor_dvFlg_charY_setsOne() {
            assertThat(buildFullArrestInfo(null, 'Y', null, null, 'N').getDvFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("dvFlg=0 when dvFlg char is N")
        void constructor_dvFlg_charN_setsZero() {
            assertThat(buildFullArrestInfo(null, 'N', null, null, 'N').getDvFlg()).isEqualTo(0);
        }

        @Test
        @DisplayName("dvFlg=0 when dvFlg is null")
        void constructor_dvFlg_null_setsZero() {
            assertThat(buildFullArrestInfo(null, null, null, null, 'N').getDvFlg()).isEqualTo(0);
        }

        // ===== felonyFlg logic =====

        @Test
        @DisplayName("felonyFlg=1 when lawCatCd is F")
        void constructor_felonyFlg_charF_setsOne() {
            assertThat(buildFullArrestInfo(null, null, 'F', null, 'N').getFelonyFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("felonyFlg=0 when lawCatCd is M")
        void constructor_felonyFlg_charM_setsZero() {
            assertThat(buildFullArrestInfo(null, null, 'M', null, 'N').getFelonyFlg()).isEqualTo(0);
        }

        @Test
        @DisplayName("felonyFlg=0 when lawCatCd is null")
        void constructor_felonyFlg_null_setsZero() {
            assertThat(buildFullArrestInfo(null, null, null, null, 'N').getFelonyFlg()).isEqualTo(0);
        }

        // ===== cmplntId splitting =====

        @Test
        @DisplayName("cmplntId is split by comma when provided")
        void constructor_cmplntId_splitByComma() {
            ArrestInfo info = buildFullArrestInfo(null, null, null, "C001,C002,C003", 'N');

            assertThat(info.getCmplntId()).containsExactly("C001", "C002", "C003");
        }

        @Test
        @DisplayName("cmplntId is null when not provided")
        void constructor_cmplntId_nullWhenNotProvided() {
            ArrestInfo info = buildFullArrestInfo(null, null, null, null, 'N');

            assertThat(info.getCmplntId()).isNull();
        }

        @Test
        @DisplayName("cmplntId with single value produces single-element list")
        void constructor_cmplntId_singleValue_producesSingleElement() {
            ArrestInfo info = buildFullArrestInfo(null, null, null, "C001", 'N');

            assertThat(info.getCmplntId()).containsExactly("C001");
        }

        @Test
        @DisplayName("null arrSealedFlg sets arrSealedFlg to null")
        void constructor_nullSealedFlg_setsNull() {
            ArrestInfo info = buildFullArrestInfo(null, null, null, null, null);
            assertThat(info.getArrSealedFlg()).isNull();
        }
    }

    // =========================================================================
    // Builder-style fluent methods
    // =========================================================================

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test
        @DisplayName("fluent arrId returns same instance")
        void fluent_arrId_returnsSameInstance() {
            ArrestInfo info = new ArrestInfo();
            ArrestInfo result = info.arrId("ARR001");
            assertThat(result).isSameAs(info);
            assertThat(info.getArrId()).isEqualTo("ARR001");
        }

        @Test
        @DisplayName("fluent deftFrstNm returns same instance")
        void fluent_deftFrstNm_returnsSameInstance() {
            ArrestInfo info = new ArrestInfo();
            assertThat(info.deftFrstNm("Jane")).isSameAs(info);
            assertThat(info.getDeftFrstNm()).isEqualTo("Jane");
        }

        @Test
        @DisplayName("fluent arrSealedFlg returns same instance")
        void fluent_arrSealedFlg_returnsSameInstance() {
            ArrestInfo info = new ArrestInfo();
            assertThat(info.arrSealedFlg("Y")).isSameAs(info);
            assertThat(info.getArrSealedFlg()).isEqualTo("Y");
        }

        @Test
        @DisplayName("fluent felonyFlg returns same instance")
        void fluent_felonyFlg_returnsSameInstance() {
            ArrestInfo info = new ArrestInfo();
            assertThat(info.felonyFlg(1)).isSameAs(info);
            assertThat(info.getFelonyFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("fluent dvFlg returns same instance")
        void fluent_dvFlg_returnsSameInstance() {
            ArrestInfo info = new ArrestInfo();
            assertThat(info.dvFlg(0)).isSameAs(info);
            assertThat(info.getDvFlg()).isEqualTo(0);
        }

        @Test
        @DisplayName("fluent indexCrimeFlg returns same instance")
        void fluent_indexCrimeFlg_returnsSameInstance() {
            ArrestInfo info = new ArrestInfo();
            assertThat(info.indexCrimeFlg(1)).isSameAs(info);
            assertThat(info.getIndexCrimeFlg()).isEqualTo(1);
        }
    }

    // =========================================================================
    // equals() and hashCode()
    // =========================================================================

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsAndHashCodeTests {

        @Test
        @DisplayName("two instances with same arrId are equal")
        void equals_sameArrId_returnsTrue() {
            ArrestInfo a = new ArrestInfo();
            a.setArrId("ARR001");
            ArrestInfo b = new ArrestInfo();
            b.setArrId("ARR001");

            assertThat(a).isEqualTo(b);
        }

        @Test
        @DisplayName("two instances with different arrId are not equal")
        void equals_differentArrId_returnsFalse() {
            ArrestInfo a = new ArrestInfo();
            a.setArrId("ARR001");
            ArrestInfo b = new ArrestInfo();
            b.setArrId("ARR002");

            assertThat(a).isNotEqualTo(b);
        }

        @Test
        @DisplayName("same instance is equal to itself")
        void equals_sameInstance_returnsTrue() {
            ArrestInfo a = new ArrestInfo();
            a.setArrId("ARR001");

            assertThat(a).isEqualTo(a);
        }

        @Test
        @DisplayName("instance is not equal to null")
        void equals_null_returnsFalse() {
            ArrestInfo a = new ArrestInfo();
            assertThat(a).isNotEqualTo(null);
        }

        @Test
        @DisplayName("instance is not equal to different type")
        void equals_differentType_returnsFalse() {
            ArrestInfo a = new ArrestInfo();
            assertThat(a).isNotEqualTo("string");
        }

        @Test
        @DisplayName("equal instances have same hashCode")
        void hashCode_equalInstances_samHashCode() {
            ArrestInfo a = new ArrestInfo();
            a.setArrId("ARR001");
            ArrestInfo b = new ArrestInfo();
            b.setArrId("ARR001");

            assertThat(a.hashCode()).isEqualTo(b.hashCode());
        }
    }

    // =========================================================================
    // toString()
    // =========================================================================

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test
        @DisplayName("toString contains class name")
        void toString_containsClassName() {
            ArrestInfo info = new ArrestInfo();
            assertThat(info.toString()).contains("ArrestInfo");
        }

        @Test
        @DisplayName("toString contains arrId value")
        void toString_containsArrId() {
            ArrestInfo info = new ArrestInfo();
            info.setArrId("ARR001");
            assertThat(info.toString()).contains("ARR001");
        }

        @Test
        @DisplayName("toString shows null for unset fields")
        void toString_showsNullForUnsetFields() {
            ArrestInfo info = new ArrestInfo();
            assertThat(info.toString()).contains("null");
        }
    }
}
