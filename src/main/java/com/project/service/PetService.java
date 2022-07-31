package com.project.service;

import com.project.model.Pet;
import com.project.model.PetStatus;
import com.project.model.Tag;

import java.util.List;

public interface PetService {

   List<Pet> findAll();

   Pet findById(Long id);

   Pet save(Pet pet);

   Pet update(Pet pet);

   void deleteById(Long id);

   List<Pet> getPetsByStatus(PetStatus[] petStatuses);

   List<Pet> getPetsByTag(List<String> tagNames);
}
