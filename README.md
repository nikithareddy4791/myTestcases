package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("dddCaseStats Model Tests")
class DddCaseStatsTest {

    // =========================================================================
    // Default constructor
    // =========================================================================

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_createsInstanceWithNullFields() {
        dddCaseStats stats = new dddCaseStats();
        assertThat(stats).isNotNull();
        assertThat(stats.getOverdueCount()).isNull();
        assertThat(stats.getComingDueCount()).isNull();
    }

    // =========================================================================
    // Getters and Setters
    // =========================================================================

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test
        @DisplayName("overdueCount getter and setter")
        void overdueCount_getterAndSetter() {
            dddCaseStats stats = new dddCaseStats();
            stats.setOverdueCount(5);
            assertThat(stats.getOverdueCount()).isEqualTo(5);
        }

        @Test
        @DisplayName("overdueCount getter and setter - zero")
        void overdueCount_zero() {
            dddCaseStats stats = new dddCaseStats();
            stats.setOverdueCount(0);
            assertThat(stats.getOverdueCount()).isEqualTo(0);
        }

        @Test
        @DisplayName("overdueCount getter and setter - null")
        void overdueCount_null() {
            dddCaseStats stats = new dddCaseStats();
            stats.setOverdueCount(null);
            assertThat(stats.getOverdueCount()).isNull();
        }

        @Test
        @DisplayName("comingDueCount getter and setter")
        void comingDueCount_getterAndSetter() {
            dddCaseStats stats = new dddCaseStats();
            stats.setComingDueCount(3);
            assertThat(stats.getComingDueCount()).isEqualTo(3);
        }

        @Test
        @DisplayName("comingDueCount getter and setter - zero")
        void comingDueCount_zero() {
            dddCaseStats stats = new dddCaseStats();
            stats.setComingDueCount(0);
            assertThat(stats.getComingDueCount()).isEqualTo(0);
        }

        @Test
        @DisplayName("comingDueCount getter and setter - null")
        void comingDueCount_null() {
            dddCaseStats stats = new dddCaseStats();
            stats.setComingDueCount(null);
            assertThat(stats.getComingDueCount()).isNull();
        }

        @Test
        @DisplayName("both fields set independently")
        void bothFields_setIndependently() {
            dddCaseStats stats = new dddCaseStats();
            stats.setOverdueCount(10);
            stats.setComingDueCount(5);
            assertThat(stats.getOverdueCount()).isEqualTo(10);
            assertThat(stats.getComingDueCount()).isEqualTo(5);
        }
    }

    // =========================================================================
    // Fluent Builder Methods
    // =========================================================================

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test
        @DisplayName("fluent overdueCount returns same instance")
        void fluent_overdueCount() {
            dddCaseStats stats = new dddCaseStats();
            assertThat(stats.overdueCount(5)).isSameAs(stats);
            assertThat(stats.getOverdueCount()).isEqualTo(5);
        }

        @Test
        @DisplayName("fluent comingDueCount returns same instance")
        void fluent_comingDueCount() {
            dddCaseStats stats = new dddCaseStats();
            assertThat(stats.comingDueCount(3)).isSameAs(stats);
            assertThat(stats.getComingDueCount()).isEqualTo(3);
        }

        @Test
        @DisplayName("fluent chaining works")
        void fluent_chaining() {
            dddCaseStats stats = new dddCaseStats()
                    .overdueCount(10)
                    .comingDueCount(5);
            assertThat(stats.getOverdueCount()).isEqualTo(10);
            assertThat(stats.getComingDueCount()).isEqualTo(5);
        }

        @Test
        @DisplayName("fluent chaining with zero values")
        void fluent_chainingZeroValues() {
            dddCaseStats stats = new dddCaseStats()
                    .overdueCount(0)
                    .comingDueCount(0);
            assertThat(stats.getOverdueCount()).isEqualTo(0);
            assertThat(stats.getComingDueCount()).isEqualTo(0);
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
            dddCaseStats a = new dddCaseStats().overdueCount(5).comingDueCount(3);
            dddCaseStats b = new dddCaseStats().overdueCount(5).comingDueCount(3);
            assertThat(a).isEqualTo(b);
        }

        @Test
        @DisplayName("different overdueCount not equal")
        void equals_differentOverdueCount_returnsFalse() {
            dddCaseStats a = new dddCaseStats().overdueCount(5).comingDueCount(3);
            dddCaseStats b = new dddCaseStats().overdueCount(10).comingDueCount(3);
            assertThat(a).isNotEqualTo(b);
        }

        @Test
        @DisplayName("different comingDueCount not equal")
        void equals_differentComingDueCount_returnsFalse() {
            dddCaseStats a = new dddCaseStats().overdueCount(5).comingDueCount(3);
            dddCaseStats b = new dddCaseStats().overdueCount(5).comingDueCount(10);
            assertThat(a).isNotEqualTo(b);
        }

        @Test
        @DisplayName("both null fields are equal")
        void equals_bothNullFields_returnsTrue() {
            dddCaseStats a = new dddCaseStats();
            dddCaseStats b = new dddCaseStats();
            assertThat(a).isEqualTo(b);
        }

        @Test
        @DisplayName("same instance equal to itself")
        void equals_sameInstance() {
            dddCaseStats a = new dddCaseStats().overdueCount(5);
            assertThat(a).isEqualTo(a);
        }

        @Test
        @DisplayName("not equal to null")
        void equals_null_returnsFalse() {
            assertThat(new dddCaseStats()).isNotEqualTo(null);
        }

        @Test
        @DisplayName("not equal to different type")
        void equals_differentType_returnsFalse() {
            assertThat(new dddCaseStats()).isNotEqualTo("string");
        }

        @Test
        @DisplayName("equal instances have same hashCode")
        void hashCode_equalInstances() {
            dddCaseStats a = new dddCaseStats().overdueCount(5).comingDueCount(3);
            dddCaseStats b = new dddCaseStats().overdueCount(5).comingDueCount(3);
            assertThat(a.hashCode()).isEqualTo(b.hashCode());
        }

        @Test
        @DisplayName("different instances have different hashCode")
        void hashCode_differentInstances() {
            dddCaseStats a = new dddCaseStats().overdueCount(5).comingDueCount(3);
            dddCaseStats b = new dddCaseStats().overdueCount(10).comingDueCount(1);
            assertThat(a.hashCode()).isNotEqualTo(b.hashCode());
        }

        @Test
        @DisplayName("zero counts equal to zero counts")
        void equals_zeroCounts_returnsTrue() {
            dddCaseStats a = new dddCaseStats().overdueCount(0).comingDueCount(0);
            dddCaseStats b = new dddCaseStats().overdueCount(0).comingDueCount(0);
            assertThat(a).isEqualTo(b);
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
            assertThat(new dddCaseStats().toString()).contains("dddCaseStats");
        }

        @Test
        @DisplayName("contains overdueCount value when set")
        void toString_containsOverdueCount() {
            dddCaseStats stats = new dddCaseStats().overdueCount(5);
            assertThat(stats.toString()).contains("5");
        }

        @Test
        @DisplayName("contains comingDueCount value when set")
        void toString_containsComingDueCount() {
            dddCaseStats stats = new dddCaseStats().comingDueCount(3);
            assertThat(stats.toString()).contains("3");
        }

        @Test
        @DisplayName("shows null for unset fields")
        void toString_showsNull() {
            assertThat(new dddCaseStats().toString()).contains("null");
        }

        @Test
        @DisplayName("contains both field names")
        void toString_containsBothFieldNames() {
            String result = new dddCaseStats().toString();
            assertThat(result).contains("overdueCount");
            assertThat(result).contains("comingDueCount");
        }
    }
}
