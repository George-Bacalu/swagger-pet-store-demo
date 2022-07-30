package com.project.service;

import com.project.model.Pet;

import java.util.List;

public interface PetService {

   List<Pet> findAll();

   Pet findById(Long id);

   Pet save(Pet pet);

   Pet update(Pet pet);

   void deleteById(Long id);
}
