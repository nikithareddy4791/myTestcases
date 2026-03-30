package io.swagger.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.threetenbp.ThreeTenModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CustomInstantDeserializer Tests")
class CustomInstantDeserializerTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        ThreeTenModule module = new ThreeTenModule();
        module.addDeserializer(Instant.class, CustomInstantDeserializer.INSTANT);
        module.addDeserializer(OffsetDateTime.class, CustomInstantDeserializer.OFFSET_DATE_TIME);
        module.addDeserializer(ZonedDateTime.class, CustomInstantDeserializer.ZONED_DATE_TIME);
        objectMapper.registerModule(module);
    }

    // -------------------------------------------------------------------------
    // Static constant sanity checks
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("INSTANT static constant should not be null")
    void instantConstantShouldNotBeNull() {
        assertNotNull(CustomInstantDeserializer.INSTANT);
    }

    @Test
    @DisplayName("OFFSET_DATE_TIME static constant should not be null")
    void offsetDateTimeConstantShouldNotBeNull() {
        assertNotNull(CustomInstantDeserializer.OFFSET_DATE_TIME);
    }

    @Test
    @DisplayName("ZONED_DATE_TIME static constant should not be null")
    void zonedDateTimeConstantShouldNotBeNull() {
        assertNotNull(CustomInstantDeserializer.ZONED_DATE_TIME);
    }

    // -------------------------------------------------------------------------
    // Instant deserialization
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("Instant Deserialization")
    class InstantDeserializationTests {

        @Test
        @DisplayName("Should deserialize ISO-8601 UTC string to Instant")
        void shouldDeserializeIsoUtcStringToInstant() throws Exception {
            String json = "\"2024-06-15T10:30:00Z\"";
            Instant result = objectMapper.readValue(json, Instant.class);
            assertNotNull(result);
            assertEquals(Instant.parse("2024-06-15T10:30:00Z"), result);
        }

        @Test
        @DisplayName("Should deserialize epoch millis (integer) to Instant")
        void shouldDeserializeEpochMillisToInstant() throws Exception {
            objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
            long epochMillis = 1718447400000L;
            String json = String.valueOf(epochMillis);
            Instant result = objectMapper.readValue(json, Instant.class);
            assertNotNull(result);
            assertEquals(Instant.ofEpochMilli(epochMillis), result);
        }

        @Test
        @DisplayName("Should deserialize epoch seconds (float) to Instant")
        void shouldDeserializeEpochSecondsFloatToInstant() throws Exception {
            String json = "1718447400.5";
            Instant result = objectMapper.readValue(json, Instant.class);
            assertNotNull(result);
            assertEquals(1718447400L, result.getEpochSecond());
        }

        @Test
        @DisplayName("Should return null for empty string")
        void shouldReturnNullForEmptyString() throws Exception {
            String json = "\"\"";
            Instant result = objectMapper.readValue(json, Instant.class);
            assertNull(result);
        }

        @Test
        @DisplayName("Should handle +0000 suffix by converting to Z")
        void shouldHandlePlusZeroTimezone() throws Exception {
            // +0000 is normalized to Z inside the deserializer
            String json = "\"2024-06-15T10:30:00+0000\"";
            Instant result = objectMapper.readValue(json, Instant.class);
            assertNotNull(result);
            assertEquals(Instant.parse("2024-06-15T10:30:00Z"), result);
        }

        @Test
        @DisplayName("Should deserialize epoch as nanoseconds when feature is enabled")
        void shouldDeserializeEpochAsNanosecondsWhenFeatureEnabled() throws Exception {
            objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
            long epochSeconds = 1718447400L;
            String json = String.valueOf(epochSeconds);
            Instant result = objectMapper.readValue(json, Instant.class);
            assertNotNull(result);
            assertEquals(Instant.ofEpochSecond(epochSeconds, 0), result);
        }
    }

    // -------------------------------------------------------------------------
    // OffsetDateTime deserialization
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("OffsetDateTime Deserialization")
    class OffsetDateTimeDeserializationTests {

        @Test
        @DisplayName("Should deserialize ISO offset date-time string preserving the instant (epoch second)")
        void shouldDeserializeIsoOffsetDateTimeString() throws Exception {
            // ADJUST_DATES_TO_CONTEXT_TIME_ZONE is ON by default, so the mapper shifts the
            // result to the JVM default zone (UTC in most CI environments).  We therefore
            // compare the underlying epoch-second, which is timezone-invariant, rather
            // than the offset-specific representation.
            objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
            String json = "\"2024-06-15T10:30:00+05:30\"";
            OffsetDateTime result = objectMapper.readValue(json, OffsetDateTime.class);
            assertNotNull(result);
            assertEquals(OffsetDateTime.parse("2024-06-15T10:30:00+05:30").toEpochSecond(),
                    result.toEpochSecond());
        }

        @Test
        @DisplayName("Should deserialize ISO offset date-time and preserve offset when adjustment disabled")
        void shouldPreserveOffsetWhenAdjustmentDisabled() throws Exception {
            objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
            String json = "\"2024-06-15T10:30:00+05:30\"";
            OffsetDateTime result = objectMapper.readValue(json, OffsetDateTime.class);
            assertNotNull(result);
            assertEquals(OffsetDateTime.parse("2024-06-15T10:30:00+05:30"), result);
        }

        @Test
        @DisplayName("Should deserialize epoch millis to OffsetDateTime")
        void shouldDeserializeEpochMillisToOffsetDateTime() throws Exception {
            objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
            long epochMillis = 1718447400000L;
            OffsetDateTime result = objectMapper.readValue(String.valueOf(epochMillis), OffsetDateTime.class);
            assertNotNull(result);
        }

        @Test
        @DisplayName("Should deserialize epoch seconds (float) to OffsetDateTime")
        void shouldDeserializeEpochSecondsFloatToOffsetDateTime() throws Exception {
            String json = "1718447400.0";
            OffsetDateTime result = objectMapper.readValue(json, OffsetDateTime.class);
            assertNotNull(result);
            assertEquals(1718447400L, result.toEpochSecond());
        }

        @Test
        @DisplayName("Should return null for empty string")
        void shouldReturnNullForEmptyStringOffsetDateTime() throws Exception {
            String json = "\"\"";
            OffsetDateTime result = objectMapper.readValue(json, OffsetDateTime.class);
            assertNull(result);
        }
    }

    // -------------------------------------------------------------------------
    // ZonedDateTime deserialization
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("ZonedDateTime Deserialization")
    class ZonedDateTimeDeserializationTests {

        @Test
        @DisplayName("Should deserialize ISO zoned date-time string and preserve zone when adjustment disabled")
        void shouldDeserializeIsoZonedDateTimeString() throws Exception {
            // With ADJUST_DATES_TO_CONTEXT_TIME_ZONE enabled (default), the deserializer
            // converts the zone to the JVM default (UTC in CI), so "Asia/Kolkata" becomes
            // "UTC".  Disable the feature to assert the original zone is preserved.
            objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
            String json = "\"2024-06-15T10:30:00+05:30[Asia/Kolkata]\"";
            ZonedDateTime result = objectMapper.readValue(json, ZonedDateTime.class);
            assertNotNull(result);
            assertEquals("Asia/Kolkata", result.getZone().getId());
        }

        @Test
        @DisplayName("Should deserialize ISO zoned date-time and preserve the instant (epoch second)")
        void shouldPreserveInstantForZonedDateTime() throws Exception {
            // Regardless of zone adjustment, the epoch-second must be invariant.
            String json = "\"2024-06-15T10:30:00+05:30[Asia/Kolkata]\"";
            ZonedDateTime result = objectMapper.readValue(json, ZonedDateTime.class);
            assertNotNull(result);
            long expectedEpochSecond = ZonedDateTime.parse("2024-06-15T10:30:00+05:30[Asia/Kolkata]")
                    .toEpochSecond();
            assertEquals(expectedEpochSecond, result.toEpochSecond());
        }

        @Test
        @DisplayName("Should deserialize epoch millis to ZonedDateTime")
        void shouldDeserializeEpochMillisToZonedDateTime() throws Exception {
            objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
            long epochMillis = 1718447400000L;
            ZonedDateTime result = objectMapper.readValue(String.valueOf(epochMillis), ZonedDateTime.class);
            assertNotNull(result);
        }

        @Test
        @DisplayName("Should deserialize decimal seconds to ZonedDateTime")
        void shouldDeserializeDecimalSecondsToZonedDateTime() throws Exception {
            String json = "1718447400.0";
            ZonedDateTime result = objectMapper.readValue(json, ZonedDateTime.class);
            assertNotNull(result);
        }

        @Test
        @DisplayName("Should return null for empty string")
        void shouldReturnNullForEmptyStringZonedDateTime() throws Exception {
            String json = "\"\"";
            ZonedDateTime result = objectMapper.readValue(json, ZonedDateTime.class);
            assertNull(result);
        }
    }

    // -------------------------------------------------------------------------
    // Error cases
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("Error Handling")
    class ErrorHandlingTests {

        @Test
        @DisplayName("Should throw exception for invalid date-time string for Instant")
        void shouldThrowExceptionForInvalidInstantString() {
            String json = "\"not-a-date\"";
            assertThrows(Exception.class, () -> objectMapper.readValue(json, Instant.class));
        }

        @Test
        @DisplayName("Should throw exception for invalid date-time string for OffsetDateTime")
        void shouldThrowExceptionForInvalidOffsetDateTimeString() {
            String json = "\"not-a-date\"";
            assertThrows(Exception.class, () -> objectMapper.readValue(json, OffsetDateTime.class));
        }

        @Test
        @DisplayName("Should throw exception for invalid date-time string for ZonedDateTime")
        void shouldThrowExceptionForInvalidZonedDateTimeString() {
            String json = "\"not-a-date\"";
            assertThrows(Exception.class, () -> objectMapper.readValue(json, ZonedDateTime.class));
        }
    }
}


@@@@@@@@@@@@@@@@@@@@@@@@@@@@





package io.swagger.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.threetenbp.ThreeTenModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JacksonConfiguration Tests")
class JacksonConfigurationTest {

    private JacksonConfiguration jacksonConfiguration;

    @BeforeEach
    void setUp() {
        jacksonConfiguration = new JacksonConfiguration();
    }

    @Test
    @DisplayName("threeTenModule() bean should not return null")
    void threeTenModuleShouldNotBeNull() {
        ThreeTenModule module = jacksonConfiguration.threeTenModule();
        assertNotNull(module);
    }

    @Test
    @DisplayName("threeTenModule() should return a ThreeTenModule instance")
    void shouldReturnThreeTenModuleInstance() {
        ThreeTenModule module = jacksonConfiguration.threeTenModule();
        assertInstanceOf(ThreeTenModule.class, module);
    }

    @Test
    @DisplayName("Module should register Instant deserializer — Instant string is parseable")
    void moduleShouldRegisterInstantDeserializer() throws Exception {
        ThreeTenModule module = jacksonConfiguration.threeTenModule();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        String json = "\"2024-06-15T10:30:00Z\"";
        Instant result = mapper.readValue(json, Instant.class);

        assertNotNull(result);
        assertEquals(Instant.parse("2024-06-15T10:30:00Z"), result);
    }

    @Test
    @DisplayName("Module should register OffsetDateTime deserializer — instant (epoch second) is preserved")
    void moduleShouldRegisterOffsetDateTimeDeserializer() throws Exception {
        ThreeTenModule module = jacksonConfiguration.threeTenModule();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        // ADJUST_DATES_TO_CONTEXT_TIME_ZONE is enabled by default and will shift the
        // result to the JVM default zone (UTC in CI), changing the offset.
        // Disable it so we can assert the original offset is retained.
        mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);

        String json = "\"2024-06-15T10:30:00+05:30\"";
        OffsetDateTime result = mapper.readValue(json, OffsetDateTime.class);

        assertNotNull(result);
        assertEquals(OffsetDateTime.parse("2024-06-15T10:30:00+05:30"), result);
    }

    @Test
    @DisplayName("Module should register ZonedDateTime deserializer — zone is preserved when adjustment disabled")
    void moduleShouldRegisterZonedDateTimeDeserializer() throws Exception {
        ThreeTenModule module = jacksonConfiguration.threeTenModule();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        // Same reasoning as OffsetDateTime: disable zone adjustment to assert the
        // original zone ID survives deserialization.
        mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);

        String json = "\"2024-06-15T10:30:00+05:30[Asia/Kolkata]\"";
        ZonedDateTime result = mapper.readValue(json, ZonedDateTime.class);

        assertNotNull(result);
        assertEquals("Asia/Kolkata", result.getZone().getId());
    }

    @Test
    @DisplayName("threeTenModule() should return a new instance on each invocation")
    void shouldReturnNewInstanceOnEachCall() {
        ThreeTenModule first = jacksonConfiguration.threeTenModule();
        ThreeTenModule second = jacksonConfiguration.threeTenModule();
        assertNotSame(first, second);
    }

    @Test
    @DisplayName("Registered Instant deserializer should be CustomInstantDeserializer.INSTANT")
    void instantDeserializerShouldBeCustomInstantDeserializer() throws Exception {
        ThreeTenModule module = jacksonConfiguration.threeTenModule();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        // Verify the custom +0000 → Z normalization behaviour, which is unique
        // to CustomInstantDeserializer (proving it — not the default — was registered).
        String json = "\"2024-06-15T10:30:00+0000\"";
        Instant result = mapper.readValue(json, Instant.class);
        assertEquals(Instant.parse("2024-06-15T10:30:00Z"), result);
    }
}






@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@



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
        @DisplayName("Should silently roll over non-leap-year Feb 29 to Mar 01 (ThreeTen-BP lenient parsing)")
        void shouldRollOverNonLeapYearFeb29ToMarchFirst() {
            // ThreeTen-BP's DateTimeFormatter is lenient by default: 2023-02-29 does not
            // exist, so it rolls over to 2023-03-01 instead of throwing an exception.
            LocalDateConverter converter = new LocalDateConverter("yyyy-MM-dd");
            LocalDate result = converter.convert("2023-02-29");
            assertNotNull(result);
            assertEquals(LocalDate.of(2023, 3, 1), result);
        }
    }
}

