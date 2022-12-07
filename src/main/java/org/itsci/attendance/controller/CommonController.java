package org.itsci.attendance.controller;

import org.springframework.beans.BeanWrapperImpl;

import java.util.regex.Pattern;

public abstract class CommonController {

    protected boolean FieldsValueMatchValidator(Object value, String field, String fieldMatch) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
        Object fieldValue = beanWrapper.getPropertyValue(field);
        Object fieldMatchValue = beanWrapper.getPropertyValue(fieldMatch);
        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }

    protected boolean FieldNotNullValidator(Object value, String field) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
        Object fieldValue = beanWrapper.getPropertyValue(field);
        if (fieldValue != null) {
            return fieldValue.toString().trim().length() > 0;
        }
        return false;
    }

    protected boolean FieldPatternValidator(Object value, String field) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
        Object fieldValue = beanWrapper.getPropertyValue(field);
        if (fieldValue != null) {
            Pattern p = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
            return p.matcher((CharSequence) fieldValue).matches();
        }
        return false;
    }
}
