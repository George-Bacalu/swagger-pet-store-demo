package com.project.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class ApiResponse implements Serializable {

   @Serial
   private final static long serialVersionUID = 1L;

   private Integer code;

   private String type;

   private String message;
}
