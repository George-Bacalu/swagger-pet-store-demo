package com.project.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PetStatus {

   AVAILABLE("available"),
   PENDING("pending"),
   SOLD("sold");

   private final String status;

   @Override
   @JsonValue // allows to serialize an entire object using its single method.
   public String toString() {
      return status;
   }
}
