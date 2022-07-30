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
@XmlAccessorType(XmlAccessType.PROPERTY) // Define what types in this class need to be mapped to XML
//XmlAccessType.FIELD: Maps all fields in this class to XML
//XmlAccessType.PROPERTY: Maps properties in this class (get/set methods) to XML
//XmlAccessType.PUBLIC_MEMBER: Map all public fields or properties in this class to XML at the same time (default)
//XmlAccessType.NONE: Not mapped
@XmlType(name = "pet", propOrder = "tags") // allows you to specify the content order in the generated schema type
@JsonPropertyOrder("photoUrls") // allows a specific order to be preserved while serializing a JSON object
@JsonInclude(JsonInclude.Include.NON_NULL) // used at exclude properties having null/empty or default values
public class Pet implements Serializable {

   @Serial
   private final static long serialVersionUID = 1L;

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
