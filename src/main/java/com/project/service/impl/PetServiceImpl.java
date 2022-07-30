package com.project.service.impl;

import com.project.model.Pet;
import com.project.model.PetStatus;
import com.project.repository.PetRepository;
import com.project.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

   private final PetRepository petRepository;

   @Override
   public List<Pet> findAll() {
      return petRepository.findAll();
   }

   @Override
   public Pet findById(Long id) {
      return petRepository.findById(id);
   }

   @Override
   public Pet save(Pet pet) {
      return petRepository.save(pet);
   }

   @Override
   public Pet update(Pet pet) {
      return petRepository.update(pet);
   }

   @Override
   public void deleteById(Long id) {
      petRepository.deleteById(id);
   }

   @Override
   public List<Pet> getPetsByStatus(PetStatus[] petStatuses) {
      return petRepository.getPetsByStatus(petStatuses);
   }
}
