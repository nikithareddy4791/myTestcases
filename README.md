package org.nypd.ddd.converter;

import java.util.Date;

import org.modelmapper.AbstractConverter;
import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

public class LocalDateToDateConverter extends AbstractConverter<LocalDate, Date> {

    @Override
    protected Date convert(LocalDate source) {
        if (source == null) {
            return null;
        }

        // 1. Convert LocalDate to a ZonedDateTime at the start of the day
        // 2. Extract the Instant from that ZonedDateTime
        // 3. Use DateTimeUtils to convert the BP Instant to a java.util.Date
        return DateTimeUtils.toDate(
            source.atStartOfDay(ZoneId.systemDefault()).toInstant()
        );
    }
}


-==================================
package org.nypd.ddd.converter;

import java.sql.Timestamp;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

public class TimestampToOffsetDateTimeConverter implements Converter<Timestamp, OffsetDateTime> {

    @Override
    public OffsetDateTime convert(MappingContext<Timestamp, OffsetDateTime> context) {
        Timestamp source = context.getSource();
        if (source == null) {
            return null;
        }
        // Convert the Timestamp to an Instant, which is implicitly in UTC
        Instant instant = DateTimeUtils.toInstant(source); 
        // Create an OffsetDateTime from the Instant, assuming UTC offset
        // Adjust the ZoneOffset if you need a different default offset
        return OffsetDateTime.ofInstant(instant, ZoneOffset.UTC); 
    }
}


==========================


package org.nypd.ddd.converter;

import java.util.Date;

import org.modelmapper.AbstractConverter;
import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

public class DateToLocalDateConverter extends AbstractConverter<Date, LocalDate> {

    @Override
    protected LocalDate convert(Date source) {
        if (source == null) {
            return null;
        }

        // 1. Use DateTimeUtils to convert java.util.Date to ThreeTen Instant
        // 2. Map Instant to ZonedDateTime using the system timezone
        // 3. Extract the LocalDate part
        return DateTimeUtils.toInstant(source)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}


