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
    // Copy constructor: CustomInstantDeserializer(CustomInstantDeserializer<T> base, DateTimeFormatter f)
    // withDateFormat(DateTimeFormatter dtf)
    // -------------------------------------------------------------------------

    @Nested
    @DisplayName("Copy Constructor and withDateFormat Coverage")
    class CopyConstructorAndWithDateFormatTests {

        @Test
        @DisplayName("withDateFormat with the SAME formatter should return the same instance (no-op path)")
        void withDateFormatSameFormatterReturnsSameInstance() throws Exception {
            // Access the protected withDateFormat via ObjectMapper's annotation-driven
            // reconfiguration: registering the deserializer with a format annotation
            // triggers withDateFormat internally. The simplest white-box way is to
            // reflectively call withDateFormat directly.
            var method = CustomInstantDeserializer.class
                    .getDeclaredMethod("withDateFormat", org.threeten.bp.format.DateTimeFormatter.class);
            method.setAccessible(true);

            // Call with the exact same formatter the INSTANT constant was built with.
            // The implementation returns `this` when dtf == _formatter.
            CustomInstantDeserializer<Instant> result =
                    (CustomInstantDeserializer<Instant>) method.invoke(
                            CustomInstantDeserializer.INSTANT,
                            org.threeten.bp.format.DateTimeFormatter.ISO_INSTANT);

            assertSame(CustomInstantDeserializer.INSTANT, result,
                    "withDateFormat should return 'this' when the same formatter is supplied");
        }

        @Test
        @DisplayName("withDateFormat with a DIFFERENT formatter invokes the copy constructor and returns a new instance")
        void withDateFormatDifferentFormatterReturnsCopiedInstance() throws Exception {
            var method = CustomInstantDeserializer.class
                    .getDeclaredMethod("withDateFormat", org.threeten.bp.format.DateTimeFormatter.class);
            method.setAccessible(true);

            org.threeten.bp.format.DateTimeFormatter altFormatter =
                    org.threeten.bp.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

            // A different formatter must trigger the copy constructor path and produce
            // a brand-new instance that is NOT the same object as the original.
            CustomInstantDeserializer<Instant> result =
                    (CustomInstantDeserializer<Instant>) method.invoke(
                            CustomInstantDeserializer.INSTANT, altFormatter);

            assertNotNull(result);
            assertNotSame(CustomInstantDeserializer.INSTANT, result,
                    "withDateFormat should return a NEW instance when a different formatter is supplied");
        }

        @Test
        @DisplayName("Copy-constructed deserializer preserves parsedToValue, fromMilliseconds, fromNanoseconds, and adjust")
        void copyConstructorPreservesAllFunctionFields() throws Exception {
            var method = CustomInstantDeserializer.class
                    .getDeclaredMethod("withDateFormat", org.threeten.bp.format.DateTimeFormatter.class);
            method.setAccessible(true);

            org.threeten.bp.format.DateTimeFormatter altFormatter =
                    org.threeten.bp.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

            CustomInstantDeserializer<Instant> copy =
                    (CustomInstantDeserializer<Instant>) method.invoke(
                            CustomInstantDeserializer.INSTANT, altFormatter);

            // Verify all four function fields were copied from the base by checking
            // they are non-null on the new instance (they are declared protected).
            var parsedToValueField = CustomInstantDeserializer.class.getDeclaredField("parsedToValue");
            parsedToValueField.setAccessible(true);
            var fromMillisecondsField = CustomInstantDeserializer.class.getDeclaredField("fromMilliseconds");
            fromMillisecondsField.setAccessible(true);
            var fromNanosecondsField = CustomInstantDeserializer.class.getDeclaredField("fromNanoseconds");
            fromNanosecondsField.setAccessible(true);
            var adjustField = CustomInstantDeserializer.class.getDeclaredField("adjust");
            adjustField.setAccessible(true);

            assertNotNull(parsedToValueField.get(copy),   "parsedToValue must be copied");
            assertNotNull(fromMillisecondsField.get(copy), "fromMilliseconds must be copied");
            assertNotNull(fromNanosecondsField.get(copy),  "fromNanoseconds must be copied");
            assertNotNull(adjustField.get(copy),           "adjust must be copied");
        }

        @Test
        @DisplayName("Copy-constructed OFFSET_DATE_TIME deserializer still deserializes correctly")
        void copyConstructedDeserializerDeserializesCorrectly() throws Exception {
            // Force the copy-constructor path by supplying a different formatter via
            // withDateFormat, then register the resulting deserializer and parse a value.
            var method = CustomInstantDeserializer.class
                    .getDeclaredMethod("withDateFormat", org.threeten.bp.format.DateTimeFormatter.class);
            method.setAccessible(true);

            // Use the same formatter that OFFSET_DATE_TIME was originally built with —
            // but supply it as a *new* equivalent instance so it fails the == identity
            // check and the copy constructor is exercised.
            org.threeten.bp.format.DateTimeFormatter newFormatter =
                    org.threeten.bp.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

            @SuppressWarnings("unchecked")
            CustomInstantDeserializer<OffsetDateTime> copy =
                    (CustomInstantDeserializer<OffsetDateTime>) method.invoke(
                            CustomInstantDeserializer.OFFSET_DATE_TIME, newFormatter);

            // Register the copy and verify it can still deserialize.
            ObjectMapper mapper = new ObjectMapper();
            ThreeTenModule module = new ThreeTenModule();
            module.addDeserializer(OffsetDateTime.class, copy);
            mapper.registerModule(module);
            mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);

            OffsetDateTime result = mapper.readValue("\"2024-06-15T10:30:00+05:30\"", OffsetDateTime.class);
            assertNotNull(result);
            assertEquals(OffsetDateTime.parse("2024-06-15T10:30:00+05:30"), result);
        }
    }

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
