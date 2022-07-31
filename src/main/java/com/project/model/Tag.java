package com.project.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class Tag implements Serializable {

   @Serial
   private final static long serialVersionUID = 1L;

   @ApiModelProperty(notes = "id", dataType = "long", example = "1", required = true)
   private Long id;

   private String name;
}
