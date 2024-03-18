package de.codecentric.workshops.jpaworkshop.jpa.datatypes;

import de.codecentric.workshops.jpaworkshop.jpa.datatypes.zipcode.Zipcode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.StringUtils;

@Converter(autoApply = true)
public class ZipcodeConverter implements AttributeConverter<Zipcode, String> {
	@Override
	public String convertToDatabaseColumn(Zipcode attribute) {
		return attribute == null ? null : attribute.getValue();
	}

	@Override
	public Zipcode convertToEntityAttribute(String dbData) {
		if (StringUtils.hasText(dbData)) {
			return Zipcode.of(dbData);
		} else {
			return null;
		}
	}
}
