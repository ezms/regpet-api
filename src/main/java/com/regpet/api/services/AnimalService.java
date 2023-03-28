package com.regpet.api.services;

import com.regpet.api.dto.animals.AnimalResponseDTO;
import com.regpet.api.dto.requests.CreateAnimalRequestDTO;
import com.regpet.api.dto.requests.UpdateAnimalRequestDTO;
import com.regpet.api.enums.AnimalStatus;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.models.Animal;
import com.regpet.api.repositories.AnimalRepository;
import com.regpet.api.utils.TextUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class AnimalService {

    @Inject
    AnimalRepository animalRepository;

    public AnimalResponseDTO save(CreateAnimalRequestDTO requestDTO) {
        Animal animal = new Animal();
        animal.setSpecies(requestDTO.getSpecies());
        animal.setBreed(requestDTO.getBreed());
        animal.setName(
                requestDTO.getName() != null || !requestDTO.getName().isBlank()
                ? TextUtils.capitalizeName(requestDTO.getName())
                : "No Name");
        animal.setFoundOn(requestDTO.getFoundOn() != null ? requestDTO.getFoundOn() : LocalDateTime.now());
        animal.setGender(requestDTO.getGender());
        animal.setDescription(requestDTO.getDescription() != null ? requestDTO.getDescription() : "");
        animal.setAnimalStatus(AnimalStatus.ABANDONED);
        animalRepository.add(animal);
        return new AnimalResponseDTO(
                animal.getId(),
                animal.getSpecies(),
                animal.getBreed(),
                animal.getName(),
                animal.getFoundOn(),
                animal.getGender(),
                animal.getDescription(),
                animal.getAnimalStatus());
    }

    public List<Animal> getAll(Integer limit, Integer offset) throws NotFoundException {
        List<Animal> animalList = animalRepository.paginate(limit, offset);
        if (animalList != null && !animalList.isEmpty()) {
            return animalList;
        }
        throw new NotFoundException("No data found, please try again later.");
    }

    public Animal getByAnimalId(UUID id) throws NotFoundException {
        Animal foundAnimal = animalRepository.getById(id);
        if (foundAnimal != null) {
            return foundAnimal;
        }
        throw new NotFoundException("No data found, please try again later.");
    }

    public List<Animal> getByName(String name) throws NotFoundException {
        List<Animal> animals = animalRepository.getAnimalsByName(name);
        if (animals.isEmpty()) {
            throw new NotFoundException("No data found.");
        }
        return animals;
    }

    public AnimalResponseDTO updateAnimal(UUID animalId, UpdateAnimalRequestDTO requestDTO) throws NotFoundException {
        Animal foundAnimal = animalRepository.getById(animalId);
        if (foundAnimal == null) {
            throw new NotFoundException("Animal not found");
        }
        foundAnimal.setName(TextUtils.capitalizeName(requestDTO.getName()));
        foundAnimal.setDescription(requestDTO.getDescription() != null ? requestDTO.getDescription() : "");
        foundAnimal.setAnimalStatus(requestDTO.getAnimalStatus());
        animalRepository.update(foundAnimal.getId(), foundAnimal);
        return new AnimalResponseDTO(
                foundAnimal.getId(),
                foundAnimal.getSpecies(),
                foundAnimal.getBreed(),
                foundAnimal.getName(),
                foundAnimal.getFoundOn(),
                foundAnimal.getGender(),
                foundAnimal.getDescription(),
                foundAnimal.getAnimalStatus());
    }
}
