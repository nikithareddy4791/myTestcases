package org.nnnn.ddd.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.modelmapper.spi.MappingContext;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Converter Tests")
class ConverterTest {

    // =========================================================================
    // LocalDateToDateConverter
    // =========================================================================

    @Nested
    @DisplayName("LocalDateToDateConverter")
    class LocalDateToDateConverterTests {

        private final LocalDateToDateConverter converter = new LocalDateToDateConverter();

        @Test
        @DisplayName("null source returns null")
        void convert_nullSource_returnsNull() {
            assertThat(converter.convert(null)).isNull();
        }

        @Test
        @DisplayName("converts a LocalDate to a Date at midnight in the system timezone")
        void convert_validLocalDate_returnsDateAtMidnight() {
            LocalDate source = LocalDate.of(2024, Month.JUNE, 15);

            Date result = converter.convert(source);

            assertThat(result).isNotNull();

            // Round-trip: convert back to LocalDate via system timezone and verify
            LocalDate roundTripped = result.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
            assertThat(roundTripped.getYear()).isEqualTo(2024);
            assertThat(roundTripped.getMonthValue()).isEqualTo(6);
            assertThat(roundTripped.getDayOfMonth()).isEqualTo(15);
        }

        @Test
        @DisplayName("converts the epoch date (1970-01-01) correctly")
        void convert_epochDate_returnsCorrectDate() {
            LocalDate epoch = LocalDate.of(1970, Month.JANUARY, 1);

            Date result = converter.convert(epoch);

            assertThat(result).isNotNull();
            LocalDate roundTripped = result.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
            assertThat(roundTripped).isEqualTo(
                    java.time.LocalDate.of(1970, 1, 1)
            );
        }

        @Test
        @DisplayName("converts a future date correctly")
        void convert_futureDate_returnsCorrectDate() {
            LocalDate future = LocalDate.of(2099, Month.DECEMBER, 31);

            Date result = converter.convert(future);

            assertThat(result).isNotNull();
            LocalDate roundTripped = result.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
            assertThat(roundTripped.getYear()).isEqualTo(2099);
            assertThat(roundTripped.getMonthValue()).isEqualTo(12);
            assertThat(roundTripped.getDayOfMonth()).isEqualTo(31);
        }

        @Test
        @DisplayName("time component of result is midnight (start of day)")
        void convert_validLocalDate_timeIsStartOfDay() {
            LocalDate source = LocalDate.of(2024, Month.MARCH, 20);

            Date result = converter.convert(source);

            // Convert to LocalDateTime in system zone and assert time is 00:00:00
            java.time.LocalDateTime ldt = result.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
            assertThat(ldt.getHour()).isZero();
            assertThat(ldt.getMinute()).isZero();
            assertThat(ldt.getSecond()).isZero();
        }
    }

    // =========================================================================
    // TimestampToOffsetDateTimeConverter
    // =========================================================================

    @Nested
    @DisplayName("TimestampToOffsetDateTimeConverter")
    class TimestampToOffsetDateTimeConverterTests {

        private final TimestampToOffsetDateTimeConverter converter =
                new TimestampToOffsetDateTimeConverter();

        @SuppressWarnings("unchecked")
        private MappingContext<Timestamp, OffsetDateTime> mockContext(Timestamp ts) {
            MappingContext<Timestamp, OffsetDateTime> ctx = mock(MappingContext.class);
            when(ctx.getSource()).thenReturn(ts);
            return ctx;
        }

        @Test
        @DisplayName("null source returns null")
        void convert_nullSource_returnsNull() {
            MappingContext<Timestamp, OffsetDateTime> ctx = mockContext(null);

            assertThat(converter.convert(ctx)).isNull();
        }

        @Test
        @DisplayName("converts a known Timestamp to the correct UTC OffsetDateTime")
        void convert_validTimestamp_returnsCorrectOffsetDateTime() {
            // 2024-06-15 10:30:00 UTC
            Timestamp ts = Timestamp.valueOf(LocalDateTime.of(2024, 6, 15, 10, 30, 0));
            // Timestamp.valueOf uses system-local time — adjust to get the UTC millis
            long utcMillis = ts.getTime();
            MappingContext<Timestamp, OffsetDateTime> ctx = mockContext(ts);

            OffsetDateTime result = converter.convert(ctx);

            assertThat(result).isNotNull();
            assertThat(result.getOffset()).isEqualTo(ZoneOffset.UTC);
            // Epoch millis must match
            assertThat(result.toInstant().toEpochMilli()).isEqualTo(utcMillis);
        }

        @Test
        @DisplayName("result offset is always UTC regardless of input value")
        void convert_anyTimestamp_offsetIsUtc() {
            Timestamp ts = new Timestamp(0L); // epoch
            MappingContext<Timestamp, OffsetDateTime> ctx = mockContext(ts);

            OffsetDateTime result = converter.convert(ctx);

            assertThat(result.getOffset()).isEqualTo(ZoneOffset.UTC);
        }

        @Test
        @DisplayName("converts epoch Timestamp (0 ms) to 1970-01-01T00:00:00Z")
        void convert_epochTimestamp_returnsEpochOffsetDateTime() {
            Timestamp ts = new Timestamp(0L);
            MappingContext<Timestamp, OffsetDateTime> ctx = mockContext(ts);

            OffsetDateTime result = converter.convert(ctx);

            assertThat(result.getYear()).isEqualTo(1970);
            assertThat(result.getMonthValue()).isEqualTo(1);
            assertThat(result.getDayOfMonth()).isEqualTo(1);
            assertThat(result.getHour()).isZero();
            assertThat(result.getMinute()).isZero();
            assertThat(result.getSecond()).isZero();
            assertThat(result.getOffset()).isEqualTo(ZoneOffset.UTC);
        }

        @Test
        @DisplayName("preserves millisecond precision")
        void convert_timestampWithMillis_preservesMilliseconds() {
            long millis = 1_718_445_600_123L; // arbitrary ms value
            Timestamp ts = new Timestamp(millis);
            MappingContext<Timestamp, OffsetDateTime> ctx = mockContext(ts);

            OffsetDateTime result = converter.convert(ctx);

            assertThat(result.toInstant().toEpochMilli()).isEqualTo(millis);
        }
    }

    // =========================================================================
    // DateToLocalDateConverter
    // =========================================================================

    @Nested
    @DisplayName("DateToLocalDateConverter")
    class DateToLocalDateConverterTests {

        private final DateToLocalDateConverter converter = new DateToLocalDateConverter();

        @Test
        @DisplayName("null source returns null")
        void convert_nullSource_returnsNull() {
            assertThat(converter.convert(null)).isNull();
        }

        @Test
        @DisplayName("converts a Date to the correct LocalDate in the system timezone")
        void convert_validDate_returnsCorrectLocalDate() {
            // Build a Date representing 2024-06-15 at midnight in system timezone
            java.time.LocalDate javaLocalDate = java.time.LocalDate.of(2024, 6, 15);
            Date input = Date.from(
                    javaLocalDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()
            );

            LocalDate result = converter.convert(input);

            assertThat(result).isNotNull();
            assertThat(result.getYear()).isEqualTo(2024);
            assertThat(result.getMonthValue()).isEqualTo(6);
            assertThat(result.getDayOfMonth()).isEqualTo(15);
        }

        @Test
        @DisplayName("converts the epoch Date (1970-01-01) correctly")
        void convert_epochDate_returnsEpochLocalDate() {
            // epoch at midnight system time
            Date input = Date.from(
                    java.time.LocalDate.of(1970, 1, 1)
                            .atStartOfDay(java.time.ZoneId.systemDefault())
                            .toInstant()
            );

            LocalDate result = converter.convert(input);

            assertThat(result.getYear()).isEqualTo(1970);
            assertThat(result.getMonthValue()).isEqualTo(1);
            assertThat(result.getDayOfMonth()).isEqualTo(1);
        }

        @Test
        @DisplayName("converts a future Date correctly")
        void convert_futureDate_returnsCorrectLocalDate() {
            Date input = Date.from(
                    java.time.LocalDate.of(2099, 12, 31)
                            .atStartOfDay(java.time.ZoneId.systemDefault())
                            .toInstant()
            );

            LocalDate result = converter.convert(input);

            assertThat(result.getYear()).isEqualTo(2099);
            assertThat(result.getMonthValue()).isEqualTo(12);
            assertThat(result.getDayOfMonth()).isEqualTo(31);
        }

        @Test
        @DisplayName("round-trip: LocalDate -> Date -> LocalDate produces the same date")
        void convert_roundTrip_producesSameLocalDate() {
            LocalDateToDateConverter toDate = new LocalDateToDateConverter();
            LocalDate original = LocalDate.of(2024, Month.SEPTEMBER, 5);

            Date intermediate = toDate.convert(original);
            LocalDate roundTripped = converter.convert(intermediate);

            assertThat(roundTripped.getYear()).isEqualTo(original.getYear());
            assertThat(roundTripped.getMonthValue()).isEqualTo(original.getMonthValue());
            assertThat(roundTripped.getDayOfMonth()).isEqualTo(original.getDayOfMonth());
        }
    }
}
