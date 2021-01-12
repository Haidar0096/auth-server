package com.nlimits.authserver.adapter.output.persistence.user;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Since Jpa does not support LocalDateTime out of the box,
 * this class converts the TimeStamp (supported type) into LocalDateTime objects
 * before the model is saved in the database
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        //TODO take another look at zone id
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime.atZone(ZoneId.of("UTC+3")).toLocalDateTime());
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }
}

