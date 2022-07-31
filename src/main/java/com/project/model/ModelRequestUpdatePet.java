package com.project.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@ApiModel
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelRequestUpdatePet implements Serializable {

   @Serial
   private final static long serialVersionUID = 1L;

   @ApiParam(value = "Updated name of the pet")
   @ApiModelProperty(notes = "name")
   private String name;

   @ApiParam(value = "Updated status of the pet")
   @ApiModelProperty(notes = "status")
   private PetStatus status;
}
