package com.project.config;

import com.project.model.PetStatus;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, PetStatus> {

   @Override
   public PetStatus convert(String source) {
      return PetStatus.valueOf(source.toUpperCase());
   }
}
