package com.project.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class ApiResponse implements Serializable {

   @Serial
   private final static long serialVersionUID = 1L;

   @ApiModelProperty(notes = "code", dataType = "int", example = "1", required = true)
   private Integer code;

   private String type;

   private String message;
}
