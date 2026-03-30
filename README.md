package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ReferenceCode Model Tests")
class ReferenceCodeTest {

    // =========================================================================
    // Constructors
    // =========================================================================

    @Test
    @DisplayName("4-arg constructor sets all fields")
    void fourArgConstructor_setsAllFields() {
        ReferenceCode rc = new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony");
        assertThat(rc.getId()).isEqualTo(1);
        assertThat(rc.getCodeType()).isEqualTo("CHARGE_TYPE");
        assertThat(rc.getCodeVal()).isEqualTo("F");
        assertThat(rc.getCodeDesc()).isEqualTo("Felony");
    }

    @Test
    @DisplayName("4-arg constructor with null values")
    void fourArgConstructor_nullValues() {
        ReferenceCode rc = new ReferenceCode(null, null, null, null);
        assertThat(rc.getId()).isNull();
        assertThat(rc.getCodeType()).isNull();
        assertThat(rc.getCodeVal()).isNull();
        assertThat(rc.getCodeDesc()).isNull();
    }

    @Test
    @DisplayName("4-arg constructor with misdemeanor values")
    void fourArgConstructor_misdemeanor() {
        ReferenceCode rc = new ReferenceCode(2, "CHARGE_TYPE", "M", "Misdemeanor");
        assertThat(rc.getId()).isEqualTo(2);
        assertThat(rc.getCodeVal()).isEqualTo("M");
        assertThat(rc.getCodeDesc()).isEqualTo("Misdemeanor");
    }

    // =========================================================================
    // Getters and Setters
    // =========================================================================

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test
        @DisplayName("id getter and setter")
        void id_getterAndSetter() {
            ReferenceCode rc = new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony");
            rc.setId(99);
            assertThat(rc.getId()).isEqualTo(99);
        }

        @Test
        @DisplayName("codeType getter and setter")
        void codeType_getterAndSetter() {
            ReferenceCode rc = new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony");
            rc.setCodeType("DV_TYPE");
            assertThat(rc.getCodeType()).isEqualTo("DV_TYPE");
        }

        @Test
        @DisplayName("codeVal getter and setter")
        void codeVal_getterAndSetter() {
            ReferenceCode rc = new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony");
            rc.setCodeVal("M");
            assertThat(rc.getCodeVal()).isEqualTo("M");
        }

        @Test
        @DisplayName("codeDesc getter and setter")
        void codeDesc_getterAndSetter() {
            ReferenceCode rc = new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony");
            rc.setCodeDesc("Misdemeanor");
            assertThat(rc.getCodeDesc()).isEqualTo("Misdemeanor");
        }

        @Test
        @DisplayName("all fields set independently")
        void allFields_setIndependently() {
            ReferenceCode rc = new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony");
            rc.setId(10);
            rc.setCodeType("DV_TYPE");
            rc.setCodeVal("Y");
            rc.setCodeDesc("Domestic Violence");
            assertThat(rc.getId()).isEqualTo(10);
            assertThat(rc.getCodeType()).isEqualTo("DV_TYPE");
            assertThat(rc.getCodeVal()).isEqualTo("Y");
            assertThat(rc.getCodeDesc()).isEqualTo("Domestic Violence");
        }

        @Test
        @DisplayName("setter accepts null")
        void setter_acceptsNull() {
            ReferenceCode rc = new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony");
            rc.setCodeType(null);
            rc.setCodeVal(null);
            rc.setCodeDesc(null);
            assertThat(rc.getCodeType()).isNull();
            assertThat(rc.getCodeVal()).isNull();
            assertThat(rc.getCodeDesc()).isNull();
        }
    }

    // =========================================================================
    // Fluent Builder Methods
    // =========================================================================

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test
        @DisplayName("fluent id returns same instance")
        void fluent_id() {
            ReferenceCode rc = new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony");
            assertThat(rc.id(99)).isSameAs(rc);
            assertThat(rc.getId()).isEqualTo(99);
        }

        @Test
        @DisplayName("fluent codeType returns same instance")
        void fluent_codeType() {
            ReferenceCode rc = new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony");
            assertThat(rc.codeType("DV_TYPE")).isSameAs(rc);
            assertThat(rc.getCodeType()).isEqualTo("DV_TYPE");
        }

        @Test
        @DisplayName("fluent codeVal returns same instance")
        void fluent_codeVal() {
            ReferenceCode rc = new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony");
            assertThat(rc.codeVal("M")).isSameAs(rc);
            assertThat(rc.getCodeVal()).isEqualTo("M");
        }

        @Test
        @DisplayName("fluent codeDesc returns same instance")
        void fluent_codeDesc() {
            ReferenceCode rc = new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony");
            assertThat(rc.codeDesc("Misdemeanor")).isSameAs(rc);
            assertThat(rc.getCodeDesc()).isEqualTo("Misdemeanor");
        }

        @Test
        @DisplayName("fluent chaining works")
        void fluent_chaining() {
            ReferenceCode rc = new ReferenceCode(1, "X", "X", "X")
                    .id(5)
                    .codeType("DV_TYPE")
                    .codeVal("Y")
                    .codeDesc("Domestic Violence");
            assertThat(rc.getId()).isEqualTo(5);
            assertThat(rc.getCodeType()).isEqualTo("DV_TYPE");
            assertThat(rc.getCodeVal()).isEqualTo("Y");
            assertThat(rc.getCodeDesc()).isEqualTo("Domestic Violence");
        }
    }

    // =========================================================================
    // equals() and hashCode()
    // =========================================================================

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsHashCodeTests {

        @Test
        @DisplayName("same fields are equal")
        void equals_sameFields() {
            assertThat(new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony"))
                    .isEqualTo(new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony"));
        }

        @Test
        @DisplayName("different id not equal")
        void equals_differentId() {
            assertThat(new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony"))
                    .isNotEqualTo(new ReferenceCode(2, "CHARGE_TYPE", "F", "Felony"));
        }

        @Test
        @DisplayName("different codeType not equal")
        void equals_differentCodeType() {
            assertThat(new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony"))
                    .isNotEqualTo(new ReferenceCode(1, "DV_TYPE", "F", "Felony"));
        }

        @Test
        @DisplayName("different codeVal not equal")
        void equals_differentCodeVal() {
            assertThat(new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony"))
                    .isNotEqualTo(new ReferenceCode(1, "CHARGE_TYPE", "M", "Felony"));
        }

        @Test
        @DisplayName("different codeDesc not equal")
        void equals_differentCodeDesc() {
            assertThat(new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony"))
                    .isNotEqualTo(new ReferenceCode(1, "CHARGE_TYPE", "F", "Misdemeanor"));
        }

        @Test
        @DisplayName("all null fields are equal")
        void equals_allNullFields() {
            assertThat(new ReferenceCode(null, null, null, null))
                    .isEqualTo(new ReferenceCode(null, null, null, null));
        }

        @Test
        @DisplayName("same instance equal to itself")
        void equals_sameInstance() {
            ReferenceCode a = new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony");
            assertThat(a).isEqualTo(a);
        }

        @Test
        @DisplayName("not equal to null")
        void equals_null() {
            assertThat(new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony"))
                    .isNotEqualTo(null);
        }

        @Test
        @DisplayName("not equal to different type")
        void equals_differentType() {
            assertThat(new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony"))
                    .isNotEqualTo("string");
        }

        @Test
        @DisplayName("equal instances have same hashCode")
        void hashCode_equalInstances() {
            assertThat(new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony").hashCode())
                    .isEqualTo(new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony").hashCode());
        }

        @Test
        @DisplayName("different instances have different hashCode")
        void hashCode_differentInstances() {
            assertThat(new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony").hashCode())
                    .isNotEqualTo(new ReferenceCode(2, "DV_TYPE", "M", "Misdemeanor").hashCode());
        }
    }

    // =========================================================================
    // toString() — also covers toIndentedString()
    // =========================================================================

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test
        @DisplayName("contains class name")
        void toString_containsClassName() {
            assertThat(new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony").toString())
                    .contains("ReferenceCode");
        }

        @Test
        @DisplayName("contains all field values")
        void toString_containsAllValues() {
            ReferenceCode rc = new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony");
            String result = rc.toString();
            assertThat(result).contains("1");
            assertThat(result).contains("CHARGE_TYPE");
            assertThat(result).contains("F");
            assertThat(result).contains("Felony");
        }

        @Test
        @DisplayName("contains all field names")
        void toString_containsAllFieldNames() {
            String result = new ReferenceCode(1, "CHARGE_TYPE", "F", "Felony").toString();
            assertThat(result).contains("id");
            assertThat(result).contains("codeType");
            assertThat(result).contains("codeVal");
            assertThat(result).contains("codeDesc");
        }

        @Test
        @DisplayName("null fields show null - covers toIndentedString(null)")
        void toString_nullFields() {
            assertThat(new ReferenceCode(null, null, null, null).toString())
                    .contains("null");
        }

        @Test
        @DisplayName("multiline value indented - covers toIndentedString newline replacement")
        void toString_multilineValue() {
            ReferenceCode rc = new ReferenceCode(1, "CHARGE_TYPE", "line1\nline2", "Felony");
            assertThat(rc.toString()).contains("line1").contains("line2");
        }
    }
}
