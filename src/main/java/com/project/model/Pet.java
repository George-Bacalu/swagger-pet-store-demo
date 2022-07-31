package com.project.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "pet", propOrder = "tags")
@JsonPropertyOrder("photoUrls")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pet implements Serializable {

   @Serial
   private final static long serialVersionUID = 1L;

   @ApiModelProperty(notes = "id", dataType = "long", example = "1", required = true)
   private Long id;

   private Category category;

   @NotNull
   @ApiModelProperty(name = "name", dataType = "string", example = "doggie", required = true)
   private String name;

   @NotNull
   @ApiModelProperty(name = "photoUrls", dataType = "array", required = true)
   private List<String> photoUrls;

   private List<Tag> tags;

   @ApiModelProperty(value = "pet status in the store")
   private PetStatus status;
}
