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
public class ApiResponse implements Serializable {

   @Serial
   private final static long serialVersionUID = 1L;

   @ApiParam(name = "fileCode", value = "fileCode")
   @ApiModelProperty(notes = "code", example = "1")
   private Integer code;

   @ApiParam(name = "type", value = "type")
   @ApiModelProperty(notes = "type")
   private String type;

   @ApiParam(name = "message", value = "message")
   @ApiModelProperty(notes = "message")
   private String message;
}
