package com.vamsi.spring.beans;

import javax.persistence.AttributeConverter;

public class BooleanYNConverter implements AttributeConverter<Boolean, String> {

	@Override
    public String convertToDatabaseColumn(Boolean value) {
        if (value) {
            return "Y";
        } else {
            return "N";
        }
    }
    @Override
    public Boolean convertToEntityAttribute(String value) {
        return "Y".equals(value);
    }

}
