package com.example.bank.annotation;

import org.mapstruct.Mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Mapping(target = "id", ignore = true)
@Mapping(target = "createdAt", ignore = true)
public @interface IgnoreCommonDataMapping {
}

