package com.project.controller;

import com.project.exception.InvalidDataException;
import com.project.exception.ResourceNotFoundException;
import com.project.model.Pet;
import com.project.model.PetStatus;
import com.project.service.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(value = "Pet Rest Controller", description = "Everything about your pets", tags = "/pet")
@RestController
@RequestMapping(value = "/pet", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PetController {

   private final PetService petService;

   @ApiOperation(value = "Get all pets", response = List.class)
   @ApiResponses(value = {
         @ApiResponse(code = 200, message = "Successful operation"),
         @ApiResponse(code = 404, message = "No pets found"),
         @ApiResponse(code = 500, message = "Internal server error")})
   @GetMapping
   public ResponseEntity<List<Pet>> getAllPets() {
      if (petService.findAll() == null) throw new ResourceNotFoundException("No pets found");
      return ResponseEntity.ok(petService.findAll());
   }

   @ApiOperation(value = "Find pet by ID", notes = "Returns a single pet", response = Pet.class)
   @ApiResponses(value = {
         @ApiResponse(code = 200, message = "Successful operation"),
         @ApiResponse(code = 400, message = "Invalid ID supplied"),
         @ApiResponse(code = 404, message = "Pet not found")})
   @GetMapping("/{petId}")
   public ResponseEntity<Pet> getPetById(@ApiParam(value = "ID of pet to return", example = "1", required = true) @PathVariable("petId") Long id) {
      if (id < 0) throw new InvalidDataException("Id " + id + " is invalid");
      if (petService.findById(id) == null) throw new ResourceNotFoundException("No pet with id " + id + " was found");
      return ResponseEntity.ok(petService.findById(id));
   }

   @ApiOperation(value = "Add a new pet to the store", response = Pet.class)
   @ApiResponses(value = {
         @ApiResponse(code = 201, message = "Successful operation"),
         @ApiResponse(code = 405, message = "Invalid input")})
   @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Pet> savePet(@ApiParam(value = "Pet object that needs to be added to the store", required = true) @Valid @RequestBody Pet pet, BindingResult bindingResult) {
      if (bindingResult.hasErrors()) throw new InvalidDataException("Pet " + pet + " is invalid");
      return ResponseEntity.status(HttpStatus.CREATED).body(petService.save(pet));
   }

   @ApiOperation(value = "Update an existing pet", response = Pet.class)
   @ApiResponses(value = {
         @ApiResponse(code = 200, message = "Successful operation"),
         @ApiResponse(code = 400, message = "Invalid ID supplied"),
         @ApiResponse(code = 404, message = "Pet not found"),
         @ApiResponse(code = 405, message = "Validation exception")})
   @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Pet> updatePet(@ApiParam(value = "Pet object that needs to be added to the store", required = true) @Valid @RequestBody Pet pet, BindingResult bindingResult) {
      if (bindingResult.hasErrors()) throw new InvalidDataException("Pet " + pet + " is invalid");
      return ResponseEntity.ok(petService.update(pet));
   }

   @ApiOperation(value = "Deletes a pet", response = Map.class)
   @ApiResponses(value = {
         @ApiResponse(code = 200, message = "Successful operation"),
         @ApiResponse(code = 400, message = "Invalid ID supplied"),
         @ApiResponse(code = 404, message = "No pet with given id found"),
         @ApiResponse(code = 500, message = "Internal server error")})
   @DeleteMapping("/{petId}")
   public ResponseEntity<Map<String, Boolean>> deleteOrderById(@ApiParam(value = "ID of pet to return", example = "1", required = true) @PathVariable("petId") Long id) {
      if (id < 0) throw new InvalidDataException("Id " + id + " is invalid");
      petService.deleteById(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of("deleted", Boolean.TRUE));
   }

   @ApiOperation(value = "Finds pet by status", notes = "Multiple status values can be provided with comma separated strings", response = List.class)
   @ApiResponses(value = {
         @ApiResponse(code = 200, message = "Successful operation"),
         @ApiResponse(code = 400, message = "Invalid status value")})
   @GetMapping("/findByStatus")
   public ResponseEntity<List<Pet>> getPetsByStatus(
         @ApiParam(value = "Status values that need to be considered for filter", required = true, allowableValues = "available, pending, sold", allowMultiple = true)
         @Valid @RequestParam PetStatus[] status) {
      if (status == null) throw new InvalidDataException("Invalid status value");
      return ResponseEntity.ok(petService.getPetsByStatus(status));
   }

   @ApiOperation(value = "Finds pets by tags", notes = "Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing", response = List.class)
   @ApiResponses(value = {
         @ApiResponse(code = 200, message = "Successful operation"),
         @ApiResponse(code = 400, message = "Invalid tag value")})
   @GetMapping("/findByTags")
   public ResponseEntity<List<Pet>> getPetsByTags(@ApiParam(value = "Tags to filter by", required = true, allowMultiple = true) @Valid @RequestParam List<String> tags) {
      if(tags == null) throw new InvalidDataException("Invalid tags value");
      if(tags.isEmpty()) throw new ResourceNotFoundException("No tags were provided");
      return ResponseEntity.ok(petService.getPetsByTag(tags));
   }
}
