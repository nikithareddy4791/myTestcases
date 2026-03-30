package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("dddOffice Model Tests")
class DddOfficeTest {

    // =========================================================================
    // Constructors
    // =========================================================================

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_createsInstanceWithNullFields() {
        dddOffice o = new dddOffice();
        assertThat(o).isNotNull();
        assertThat(o.getId()).isNull();
        assertThat(o.getdddOfficeDesc()).isNull();
        assertThat(o.getInactiveFlg()).isNull();
    }

    @Test
    @DisplayName("3-arg constructor sets all fields")
    void threeArgConstructor_setsAllFields() {
        dddOffice o = new dddOffice(1, "Manhattan", 0);
        assertThat(o.getId()).isEqualTo(1);
        assertThat(o.getdddOfficeDesc()).isEqualTo("Manhattan");
        assertThat(o.getInactiveFlg()).isEqualTo(0);
    }

    @Test
    @DisplayName("3-arg constructor with null values")
    void threeArgConstructor_withNullValues() {
        dddOffice o = new dddOffice(null, null, null);
        assertThat(o.getId()).isNull();
        assertThat(o.getdddOfficeDesc()).isNull();
        assertThat(o.getInactiveFlg()).isNull();
    }

    @Test
    @DisplayName("3-arg constructor with inactive flag = 1")
    void threeArgConstructor_inactiveOffice() {
        dddOffice o = new dddOffice(2, "Brooklyn", 1);
        assertThat(o.getId()).isEqualTo(2);
        assertThat(o.getdddOfficeDesc()).isEqualTo("Brooklyn");
        assertThat(o.getInactiveFlg()).isEqualTo(1);
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
            dddOffice o = new dddOffice();
            o.setId(1);
            assertThat(o.getId()).isEqualTo(1);
        }

        @Test
        @DisplayName("dddOfficeDesc getter and setter")
        void dddOfficeDesc_getterAndSetter() {
            dddOffice o = new dddOffice();
            o.setdddOfficeDesc("Bronx");
            assertThat(o.getdddOfficeDesc()).isEqualTo("Bronx");
        }

        @Test
        @DisplayName("inactiveFlg getter and setter - active (0)")
        void inactiveFlg_active() {
            dddOffice o = new dddOffice();
            o.setInactiveFlg(0);
            assertThat(o.getInactiveFlg()).isEqualTo(0);
        }

        @Test
        @DisplayName("inactiveFlg getter and setter - inactive (1)")
        void inactiveFlg_inactive() {
            dddOffice o = new dddOffice();
            o.setInactiveFlg(1);
            assertThat(o.getInactiveFlg()).isEqualTo(1);
        }

        @Test
        @DisplayName("all setters work together")
        void allSetters_workTogether() {
            dddOffice o = new dddOffice();
            o.setId(5);
            o.setdddOfficeDesc("Queens");
            o.setInactiveFlg(0);
            assertThat(o.getId()).isEqualTo(5);
            assertThat(o.getdddOfficeDesc()).isEqualTo("Queens");
            assertThat(o.getInactiveFlg()).isEqualTo(0);
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
            dddOffice o = new dddOffice();
            assertThat(o.id(1)).isSameAs(o);
            assertThat(o.getId()).isEqualTo(1);
        }

        @Test
        @DisplayName("fluent dddOfficeDesc returns same instance")
        void fluent_dddOfficeDesc() {
            dddOffice o = new dddOffice();
            assertThat(o.dddOfficeDesc("Staten Island")).isSameAs(o);
            assertThat(o.getdddOfficeDesc()).isEqualTo("Staten Island");
        }

        @Test
        @DisplayName("fluent inactiveFlg returns same instance")
        void fluent_inactiveFlg() {
            dddOffice o = new dddOffice();
            assertThat(o.inactiveFlg(0)).isSameAs(o);
            assertThat(o.getInactiveFlg()).isEqualTo(0);
        }

        @Test
        @DisplayName("fluent chaining works")
        void fluent_chaining() {
            dddOffice o = new dddOffice()
                    .id(1)
                    .dddOfficeDesc("Manhattan")
                    .inactiveFlg(0);
            assertThat(o.getId()).isEqualTo(1);
            assertThat(o.getdddOfficeDesc()).isEqualTo("Manhattan");
            assertThat(o.getInactiveFlg()).isEqualTo(0);
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
        void equals_sameFields_returnsTrue() {
            dddOffice a = new dddOffice(1, "Manhattan", 0);
            dddOffice b = new dddOffice(1, "Manhattan", 0);
            assertThat(a).isEqualTo(b);
        }

        @Test
        @DisplayName("different id not equal")
        void equals_differentId_returnsFalse() {
            dddOffice a = new dddOffice(1, "Manhattan", 0);
            dddOffice b = new dddOffice(2, "Manhattan", 0);
            assertThat(a).isNotEqualTo(b);
        }

        @Test
        @DisplayName("different desc not equal")
        void equals_differentDesc_returnsFalse() {
            dddOffice a = new dddOffice(1, "Manhattan", 0);
            dddOffice b = new dddOffice(1, "Brooklyn", 0);
            assertThat(a).isNotEqualTo(b);
        }

        @Test
        @DisplayName("different inactiveFlg not equal")
        void equals_differentInactiveFlg_returnsFalse() {
            dddOffice a = new dddOffice(1, "Manhattan", 0);
            dddOffice b = new dddOffice(1, "Manhattan", 1);
            assertThat(a).isNotEqualTo(b);
        }

        @Test
        @DisplayName("same instance equal to itself")
        void equals_sameInstance() {
            dddOffice a = new dddOffice(1, "Manhattan", 0);
            assertThat(a).isEqualTo(a);
        }

        @Test
        @DisplayName("not equal to null")
        void equals_null_returnsFalse() {
            assertThat(new dddOffice(1, "Manhattan", 0)).isNotEqualTo(null);
        }

        @Test
        @DisplayName("not equal to different type")
        void equals_differentType_returnsFalse() {
            assertThat(new dddOffice(1, "Manhattan", 0)).isNotEqualTo("string");
        }

        @Test
        @DisplayName("equal instances have same hashCode")
        void hashCode_equalInstances() {
            dddOffice a = new dddOffice(1, "Manhattan", 0);
            dddOffice b = new dddOffice(1, "Manhattan", 0);
            assertThat(a.hashCode()).isEqualTo(b.hashCode());
        }

        @Test
        @DisplayName("different instances have different hashCode")
        void hashCode_differentInstances() {
            dddOffice a = new dddOffice(1, "Manhattan", 0);
            dddOffice b = new dddOffice(2, "Brooklyn", 0);
            assertThat(a.hashCode()).isNotEqualTo(b.hashCode());
        }
    }

    // =========================================================================
    // toString()
    // =========================================================================

    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test
        @DisplayName("contains class name")
        void toString_containsClassName() {
            assertThat(new dddOffice().toString()).contains("dddOffice");
        }

        @Test
        @DisplayName("contains id value when set")
        void toString_containsId() {
            dddOffice o = new dddOffice(1, "Manhattan", 0);
            assertThat(o.toString()).contains("1");
        }

        @Test
        @DisplayName("contains dddOfficeDesc when set")
        void toString_containsDesc() {
            dddOffice o = new dddOffice(1, "Manhattan", 0);
            assertThat(o.toString()).contains("Manhattan");
        }

        @Test
        @DisplayName("shows null for unset fields")
        void toString_showsNull() {
            assertThat(new dddOffice().toString()).contains("null");
        }
    }
}
