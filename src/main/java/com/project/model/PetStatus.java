package com.project.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum PetStatus {

   AVAILABLE("available"), PENDING("pending"), SOLD("sold");

   private final String status;

   @Override
   @JsonValue // allows to serialize an entire object using its single method.
   public String toString() {
      return String.valueOf(status);
   }

   @JsonCreator // used to fine tune the constructor or factory method used in deserialization
   public static PetStatus getStatusFromValue(String value) {
      return Arrays.stream(PetStatus.values()).filter(status -> status.toString().equals(value)).findFirst().orElse(null);
   }
}
