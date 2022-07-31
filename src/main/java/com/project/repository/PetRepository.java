package com.project.repository;

import com.project.exception.ResourceNotFoundException;
import com.project.model.Category;
import com.project.model.Pet;
import com.project.model.PetStatus;
import com.project.model.Tag;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class PetRepository {

   private Map<Long, Pet> pets = new HashMap<>();

   @PostConstruct
   private void initializePets() {
      Pet pet1 = Pet.builder().id(1L).name("dog")
            .category(Category.builder().id(1L).name("dogs").build())
            .photoUrls(List.of("https://www.url1.com", "https://www.url2.com"))
            .tags(List.of(Tag.builder().id(1L).name("tag1").build(), Tag.builder().id(2L).name("tag2").build()))
            .status(PetStatus.AVAILABLE)
            .build();

      Pet pet2 = Pet.builder().id(2L).name("cat")
            .category(Category.builder().id(2L).name("cats").build())
            .photoUrls(List.of("https://www.url3.com", "https://www.url4.com", "https://www.url5.com"))
            .tags(List.of(Tag.builder().id(3L).name("tag3").build(), Tag.builder().id(4L).name("tag4").build()))
            .status(PetStatus.PENDING)
            .build();

      Pet pet3 = Pet.builder().id(3L).name("parrot")
            .category(Category.builder().id(3L).name("parrots").build())
            .photoUrls(List.of("https://www.url6.com"))
            .tags(List.of(Tag.builder().id(5L).name("tag5").build(), Tag.builder().id(6L).name("tag6").build(), Tag.builder().id(7L).name("tag7").build()))
            .status(PetStatus.SOLD)
            .build();

      pets = Map.ofEntries(Map.entry(pet1.getId(), pet1), Map.entry(pet2.getId(), pet2), Map.entry(pet3.getId(), pet3));
   }

   public List<Pet> findAll() {
      return new ArrayList<>(pets.values());
   }

   public Pet findById(Long id) {
      return findAll().stream().filter(pet -> Objects.equals(pet.getId(), id)).findFirst().orElseThrow(() -> new ResourceNotFoundException("Pet with id " + id + " not found!"));
   }

   public Pet save(Pet pet) {
      return pets.compute(pet.getId(), (key, value) -> pet);
   }

   public Pet update(Pet pet) {
      Pet petToUpdate = findById(pet.getId());
      petToUpdate.setId(pet.getId());
      petToUpdate.setName(pet.getName());
      petToUpdate.setCategory(pet.getCategory());
      petToUpdate.setPhotoUrls(pet.getPhotoUrls());
      petToUpdate.setTags(pet.getTags());
      petToUpdate.setStatus(pet.getStatus());
      return petToUpdate;
   }

   public void deleteById(Long id) {
      pets.remove(id);
   }

   public List<Pet> getPetsByStatus(PetStatus[] petStatuses) {
      List<String> statusesList = Arrays.stream(petStatuses).map(Enum::name).toList();
      return findAll().stream().filter(pet -> statusesList.stream().anyMatch(status -> pet.getStatus().name().equals(status))).collect(Collectors.toList());
   }

   public List<Pet> getPetsByTags(List<String> tagNames) {
      return findAll().stream().filter(pet -> pet.getTags().stream().map(Tag::getName).anyMatch(tagNames::contains)).collect(Collectors.toList());
   }
}
