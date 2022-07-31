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
public class ModelRequestUploadImage implements Serializable {

   @Serial
   private final static long serialVersionUID = 1L;

   @ApiParam(value = "Additional data to pass to server")
   @ApiModelProperty(notes = "additionalMetadata")
   private String additionalMetadata;
}
