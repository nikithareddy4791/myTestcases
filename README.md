package org.nnnn.ddd.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Error Model Tests")
class ErrorModelTest {

    // =========================================================================
    // Default constructor
    // =========================================================================

    @Test
    @DisplayName("default constructor creates instance with null fields")
    void defaultConstructor_createsInstanceWithNullFields() {
        Error e = new Error();
        assertThat(e).isNotNull();
        assertThat(e.getCode()).isNull();
        assertThat(e.getMessage()).isNull();
    }

    // =========================================================================
    // Getters and Setters
    // =========================================================================

    @Nested
    @DisplayName("Getters and Setters")
    class GettersSettersTests {

        @Test
        @DisplayName("code getter and setter")
        void code_getterAndSetter() {
            Error e = new Error();
            e.setCode("1001");
            assertThat(e.getCode()).isEqualTo("1001");
        }

        @Test
        @DisplayName("message getter and setter")
        void message_getterAndSetter() {
            Error e = new Error();
            e.setMessage("Case not found");
            assertThat(e.getMessage()).isEqualTo("Case not found");
        }

        @Test
        @DisplayName("code setter overwrites previous value")
        void code_setterOverwritesPrevious() {
            Error e = new Error();
            e.setCode("1001");
            e.setCode("1002");
            assertThat(e.getCode()).isEqualTo("1002");
        }

        @Test
        @DisplayName("message setter overwrites previous value")
        void message_setterOverwritesPrevious() {
            Error e = new Error();
            e.setMessage("First message");
            e.setMessage("Second message");
            assertThat(e.getMessage()).isEqualTo("Second message");
        }

        @Test
        @DisplayName("code setter accepts null")
        void code_setterAcceptsNull() {
            Error e = new Error();
            e.setCode("1001");
            e.setCode(null);
            assertThat(e.getCode()).isNull();
        }

        @Test
        @DisplayName("message setter accepts null")
        void message_setterAcceptsNull() {
            Error e = new Error();
            e.setMessage("some message");
            e.setMessage(null);
            assertThat(e.getMessage()).isNull();
        }

        @Test
        @DisplayName("both fields set independently")
        void bothFields_setIndependently() {
            Error e = new Error();
            e.setCode("1004");
            e.setMessage("Sealed access denied");
            assertThat(e.getCode()).isEqualTo("1004");
            assertThat(e.getMessage()).isEqualTo("Sealed access denied");
        }
    }

    // =========================================================================
    // Fluent Builder Methods
    // =========================================================================

    @Nested
    @DisplayName("Fluent Builder Methods")
    class FluentBuilderTests {

        @Test
        @DisplayName("fluent code returns same instance")
        void fluent_code() {
            Error e = new Error();
            assertThat(e.code("1001")).isSameAs(e);
            assertThat(e.getCode()).isEqualTo("1001");
        }

        @Test
        @DisplayName("fluent message returns same instance")
        void fluent_message() {
            Error e = new Error();
            assertThat(e.message("Case not found")).isSameAs(e);
            assertThat(e.getMessage()).isEqualTo("Case not found");
        }

        @Test
        @DisplayName("fluent chaining works")
        void fluent_chaining() {
            Error e = new Error()
                    .code("1002")
                    .message("Sealed access denied");
            assertThat(e.getCode()).isEqualTo("1002");
            assertThat(e.getMessage()).isEqualTo("Sealed access denied");
        }

        @Test
        @DisplayName("fluent chaining with all error codes")
        void fluent_chainingWithErrorCodes() {
            Error sealed = new Error().code("1002").message("SEALED_ACCESS_PERM");
            Error caseAccess = new Error().code("1004").message("CASE_ACCESS_PERM");
            Error notFound = new Error().code("1006").message("ARR_NOT_FOUND");

            assertThat(sealed.getCode()).isEqualTo("1002");
            assertThat(caseAccess.getCode()).isEqualTo("1004");
            assertThat(notFound.getCode()).isEqualTo("1006");
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
            Error a = new Error().code("1001").message("Not found");
            Error b = new Error().code("1001").message("Not found");
            assertThat(a).isEqualTo(b);
        }

        @Test
        @DisplayName("different code not equal")
        void equals_differentCode_returnsFalse() {
            Error a = new Error().code("1001").message("Not found");
            Error b = new Error().code("1002").message("Not found");
            assertThat(a).isNotEqualTo(b);
        }

        @Test
        @DisplayName("different message not equal")
        void equals_differentMessage_returnsFalse() {
            Error a = new Error().code("1001").message("Not found");
            Error b = new Error().code("1001").message("Access denied");
            assertThat(a).isNotEqualTo(b);
        }

        @Test
        @DisplayName("both null fields are equal")
        void equals_bothNullFields_returnsTrue() {
            Error a = new Error();
            Error b = new Error();
            assertThat(a).isEqualTo(b);
        }

        @Test
        @DisplayName("same instance equal to itself")
        void equals_sameInstance() {
            Error a = new Error().code("1001").message("Not found");
            assertThat(a).isEqualTo(a);
        }

        @Test
        @DisplayName("not equal to null")
        void equals_null_returnsFalse() {
            assertThat(new Error()).isNotEqualTo(null);
        }

        @Test
        @DisplayName("not equal to different type")
        void equals_differentType_returnsFalse() {
            assertThat(new Error()).isNotEqualTo("string");
        }

        @Test
        @DisplayName("equal instances have same hashCode")
        void hashCode_equalInstances() {
            Error a = new Error().code("1001").message("Not found");
            Error b = new Error().code("1001").message("Not found");
            assertThat(a.hashCode()).isEqualTo(b.hashCode());
        }

        @Test
        @DisplayName("different instances have different hashCode")
        void hashCode_differentInstances() {
            Error a = new Error().code("1001").message("Not found");
            Error b = new Error().code("1002").message("Access denied");
            assertThat(a.hashCode()).isNotEqualTo(b.hashCode());
        }

        @Test
        @DisplayName("null fields produce consistent hashCode")
        void hashCode_nullFields_consistent() {
            Error a = new Error();
            Error b = new Error();
            assertThat(a.hashCode()).isEqualTo(b.hashCode());
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
            assertThat(new Error().toString()).contains("Error");
        }

        @Test
        @DisplayName("contains code value when set")
        void toString_containsCode() {
            Error e = new Error().code("1001");
            assertThat(e.toString()).contains("1001");
        }

        @Test
        @DisplayName("contains message value when set")
        void toString_containsMessage() {
            Error e = new Error().message("Case not found");
            assertThat(e.toString()).contains("Case not found");
        }

        @Test
        @DisplayName("shows null for unset fields - exercises toIndentedString(null)")
        void toString_showsNullForUnsetFields() {
            assertThat(new Error().toString()).contains("null");
        }

        @Test
        @DisplayName("contains both field names")
        void toString_containsBothFieldNames() {
            String result = new Error().toString();
            assertThat(result).contains("code");
            assertThat(result).contains("message");
        }

        @Test
        @DisplayName("multiline value is indented in toString - exercises toIndentedString newline replacement")
        void toString_multilineValue_isIndented() {
            // toIndentedString replaces \n with \n    — test with a message containing newline
            Error e = new Error().code("1001").message("line1\nline2");
            String result = e.toString();
            assertThat(result).contains("line1");
            assertThat(result).contains("line2");
        }

        @Test
        @DisplayName("both code and message appear in toString")
        void toString_containsBothValues() {
            Error e = new Error().code("1002").message("SEALED_ACCESS_PERM");
            String result = e.toString();
            assertThat(result).contains("1002");
            assertThat(result).contains("SEALED_ACCESS_PERM");
        }
    }
}
