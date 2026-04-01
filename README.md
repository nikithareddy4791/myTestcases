package org.nnnn.ddd.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.spi.MappingContext;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("LocalDateToDateConverter Tests")
class LocalDateToDateConverterTest {

    private final LocalDateToDateConverter converter = new LocalDateToDateConverter();

    @SuppressWarnings("unchecked")
    private MappingContext<LocalDate, Date> mockContext(LocalDate source) {
        MappingContext<LocalDate, Date> ctx = mock(MappingContext.class);
        when(ctx.getSource()).thenReturn(source);
        return ctx;
    }

    @Test
    @DisplayName("null source returns null")
    void convert_nullSource_returnsNull() {
        assertThat(converter.convert(mockContext(null))).isNull();
    }

    @Test
    @DisplayName("converts a valid LocalDate to a Date at midnight in the system timezone")
    void convert_validLocalDate_returnsDateAtMidnight() {
        LocalDate source = LocalDate.of(2024, Month.JUNE, 15);

        Date result = converter.convert(mockContext(source));

        assertThat(result).isNotNull();
        java.time.LocalDate roundTripped = result.toInstant()
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

        Date result = converter.convert(mockContext(epoch));

        assertThat(result).isNotNull();
        java.time.LocalDate roundTripped = result.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();
        assertThat(roundTripped.getYear()).isEqualTo(1970);
        assertThat(roundTripped.getMonthValue()).isEqualTo(1);
        assertThat(roundTripped.getDayOfMonth()).isEqualTo(1);
    }

    @Test
    @DisplayName("converts a future date correctly")
    void convert_futureDate_returnsCorrectDate() {
        LocalDate future = LocalDate.of(2099, Month.DECEMBER, 31);

        Date result = converter.convert(mockContext(future));

        assertThat(result).isNotNull();
        java.time.LocalDate roundTripped = result.toInstant()
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

        Date result = converter.convert(mockContext(source));

        java.time.LocalDateTime ldt = result.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime();
        assertThat(ldt.getHour()).isZero();
        assertThat(ldt.getMinute()).isZero();
        assertThat(ldt.getSecond()).isZero();
    }
}

==================
package org.nnnn.ddd.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.spi.MappingContext;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("DateToLocalDateConverter Tests")
class DateToLocalDateConverterTest {

    private final DateToLocalDateConverter converter = new DateToLocalDateConverter();

    @SuppressWarnings("unchecked")
    private MappingContext<Date, LocalDate> mockContext(Date source) {
        MappingContext<Date, LocalDate> ctx = mock(MappingContext.class);
        when(ctx.getSource()).thenReturn(source);
        return ctx;
    }

    private Date dateOf(int year, int month, int day) {
        return Date.from(
                java.time.LocalDate.of(year, month, day)
                        .atStartOfDay(java.time.ZoneId.systemDefault())
                        .toInstant()
        );
    }

    @Test
    @DisplayName("null source returns null")
    void convert_nullSource_returnsNull() {
        assertThat(converter.convert(mockContext(null))).isNull();
    }

    @Test
    @DisplayName("converts a valid Date to the correct LocalDate in the system timezone")
    void convert_validDate_returnsCorrectLocalDate() {
        Date input = dateOf(2024, 6, 15);

        LocalDate result = converter.convert(mockContext(input));

        assertThat(result).isNotNull();
        assertThat(result.getYear()).isEqualTo(2024);
        assertThat(result.getMonthValue()).isEqualTo(6);
        assertThat(result.getDayOfMonth()).isEqualTo(15);
    }

    @Test
    @DisplayName("converts the epoch Date (1970-01-01) correctly")
    void convert_epochDate_returnsEpochLocalDate() {
        Date input = dateOf(1970, 1, 1);

        LocalDate result = converter.convert(mockContext(input));

        assertThat(result.getYear()).isEqualTo(1970);
        assertThat(result.getMonthValue()).isEqualTo(1);
        assertThat(result.getDayOfMonth()).isEqualTo(1);
    }

    @Test
    @DisplayName("converts a future Date correctly")
    void convert_futureDate_returnsCorrectLocalDate() {
        Date input = dateOf(2099, 12, 31);

        LocalDate result = converter.convert(mockContext(input));

        assertThat(result.getYear()).isEqualTo(2099);
        assertThat(result.getMonthValue()).isEqualTo(12);
        assertThat(result.getDayOfMonth()).isEqualTo(31);
    }

    @Test
    @DisplayName("round-trip: LocalDate -> Date -> LocalDate produces the same date")
    void convert_roundTrip_producesSameLocalDate() {
        LocalDateToDateConverter toDate = new LocalDateToDateConverter();
        LocalDate original = LocalDate.of(2024, Month.SEPTEMBER, 5);

        // Build a MappingContext for each leg of the round-trip
        @SuppressWarnings("unchecked")
        MappingContext<LocalDate, Date> toDateCtx = mock(MappingContext.class);
        when(toDateCtx.getSource()).thenReturn(original);
        Date intermediate = toDate.convert(toDateCtx);

        LocalDate roundTripped = converter.convert(mockContext(intermediate));

        assertThat(roundTripped.getYear()).isEqualTo(original.getYear());
        assertThat(roundTripped.getMonthValue()).isEqualTo(original.getMonthValue());
        assertThat(roundTripped.getDayOfMonth()).isEqualTo(original.getDayOfMonth());
    }
}
