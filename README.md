package io.swagger.configuration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LocalDateConverter Tests")
class LocalDateConverterTest {

    // -------------------------------------------------------------------------
    // Happy-path conversions
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("Valid Conversions")
    class ValidConversionTests {

        @Test
        @DisplayName("Should convert standard ISO date string yyyy-MM-dd")
        void shouldConvertIsoDateString() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            LocalDate result = converter.convert("2024-06-15");
            assertNotNull(result);
            assertEquals(LocalDate.of(2024, 6, 15), result);
        }

        @Test
        @DisplayName("Should convert slashed date format dd/MM/yyyy")
        void shouldConvertSlashedDateFormat() {
            LocalDateConverter converter = new LocalDateConverter("dd/MM/yyyy");
            LocalDate result = converter.convert("15/06/2024");
            assertNotNull(result);
            assertEquals(LocalDate.of(2024, 6, 15), result);
        }

        @Test
        @DisplayName("Should convert MM-dd-yyyy US format")
        void shouldConvertUsSeparatedFormat() {
            LocalDateConverter converter = new LocalDateConverter("MM-dd-yyyy");
            LocalDate result = converter.convert("06-15-2024");
            assertNotNull(result);
            assertEquals(LocalDate.of(2024, 6, 15), result);
        }

        @Test
        @DisplayName("Should convert leap day February 29")
        void shouldConvertLeapDay() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            LocalDate result = converter.convert("2024-02-29");
            assertNotNull(result);
            assertEquals(29, result.getDayOfMonth());
            assertEquals(2, result.getMonthValue());
        }

        @Test
        @DisplayName("Should convert year boundary date December 31")
        void shouldConvertYearBoundaryDate() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            LocalDate result = converter.convert("2024-12-31");
            assertNotNull(result);
            assertEquals(LocalDate.of(2024, 12, 31), result);
        }

        @Test
        @DisplayName("Should convert first day of year January 01")
        void shouldConvertFirstDayOfYear() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            LocalDate result = converter.convert("2024-01-01");
            assertNotNull(result);
            assertEquals(LocalDate.of(2024, 1, 1), result);
        }

        @Test
        @DisplayName("Should convert date with short year pattern yy-MM-dd")
        void shouldConvertShortYearPattern() {
            LocalDateConverter converter = new LocalDateConverter("yy-MM-dd");
            LocalDate result = converter.convert("24-06-15");
            assertNotNull(result);
            assertEquals(6, result.getMonthValue());
            assertEquals(15, result.getDayOfMonth());
        }
    }

    // -------------------------------------------------------------------------
    // Null / empty input
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("Null and Empty Input Handling")
    class NullAndEmptyInputTests {

        @Test
        @DisplayName("Should return null when source is null")
        void shouldReturnNullForNullInput() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            assertNull(converter.convert(null));
        }

        @Test
        @DisplayName("Should return null when source is empty string")
        void shouldReturnNullForEmptyString() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            assertNull(converter.convert(""));
        }
    }

    // -------------------------------------------------------------------------
    // Invalid input
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("Invalid Input Handling")
    class InvalidInputTests {

        @Test
        @DisplayName("Should throw exception for completely invalid string")
        void shouldThrowForInvalidString() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            assertThrows(Exception.class, () -> converter.convert("not-a-date"));
        }

        @Test
        @DisplayName("Should throw exception for wrong format pattern mismatch")
        void shouldThrowForFormatMismatch() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            assertThrows(Exception.class, () -> converter.convert("15/06/2024"));
        }

        @Test
        @DisplayName("Should throw exception for invalid day value")
        void shouldThrowForInvalidDay() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            assertThrows(Exception.class, () -> converter.convert("2024-06-32"));
        }

        @Test
        @DisplayName("Should throw exception for invalid month value")
        void shouldThrowForInvalidMonth() {
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            assertThrows(Exception.class, () -> converter.convert("2024-13-01"));
        }

        @Test
        @DisplayName("Should silently clamp non-leap-year Feb 29 to Feb 28 (ThreeTen-BP lenient parsing)")
        void shouldClampNonLeapYearFeb29ToFeb28() {
            // ThreeTen-BP's DateTimeFormatter is lenient by default: 2023-02-29 does not
            // exist, so it clamps backward to the last valid day of February (2023-02-28)
            // rather than throwing an exception or rolling forward to March.
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            LocalDate result = converter.convert("2023-02-29");
            assertNotNull(result);
            assertEquals(LocalDate.of(2023, 2, 28), result);
        }
    }
}
