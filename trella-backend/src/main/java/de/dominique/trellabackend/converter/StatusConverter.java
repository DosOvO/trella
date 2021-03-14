package de.dominique.trellabackend.converter;

import de.dominique.trellabackend.model.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }

        return status.toString();
    }

    @Override
    public Status convertToEntityAttribute(String s) {
        return Arrays.stream(Status.values())
                .filter(status -> status.toString().equals(s))
                .findFirst()
                .orElseThrow();
    }
}
