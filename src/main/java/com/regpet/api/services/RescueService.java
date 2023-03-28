package com.regpet.api.services;

import com.regpet.api.dto.animals.AnimalResponseDTO;
import com.regpet.api.dto.requests.CreateRescueRequestDTO;
import com.regpet.api.dto.requests.UpdateRescueRequestDTO;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.models.Animal;
import com.regpet.api.models.Denunciation;
import com.regpet.api.models.Rescue;
import com.regpet.api.models.User;
import com.regpet.api.repositories.AddressRepository;
import com.regpet.api.repositories.AnimalRepository;
import com.regpet.api.repositories.RescueRepository;
import com.regpet.api.repositories.UserRepository;
import com.regpet.api.utils.TextUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequestScoped
public class RescueService {

    @Inject
    RescueRepository rescueRepository;

    @Inject
    UserRepository userRepository;

    public Rescue register(UUID userId, CreateRescueRequestDTO request) throws NotFoundException {
        User foundUser = userRepository.getById(userId);
        if (foundUser == null) {
            throw new NotFoundException("User not found");
        }
        Rescue newRescue = new Rescue();
        newRescue.setRescueDate(request.getRescueDate());
        newRescue.setStory(request.getStory() != null ? request.getStory() : "");
        newRescue.setUser(foundUser);
        newRescue.setAnimals(
                request.getAnimals() != null &&
                        !request.getAnimals().isEmpty() ?
                        addAnimalList(newRescue, request.getAnimals()) :
                        null
        );
        request.getAddress().setRescues(List.of(newRescue));
        newRescue.setAddress(List.of(request.getAddress()));
        rescueRepository.add(newRescue);
        return newRescue;
    }

    private List<Animal> addAnimalList(Rescue rescue, List<Animal> animalList) {
        for (Animal animal : animalList) {
            animal.addRescue(rescue);
        }
        return rescue.getAnimals();
    }

    public Rescue findById(UUID id) throws NotFoundException {
        Rescue foundRescue = rescueRepository.getById(id);
        if (Objects.isNull(foundRescue)) throw new NotFoundException("Rescue not found");
        return foundRescue;
    }

    public List<Rescue> findAll(Integer limiter, Integer offset) throws NotFoundException {
        List<Rescue> rescueList = rescueRepository.paginate(limiter, offset);
        if (rescueList.isEmpty()) throw new NotFoundException("No data");
        return rescueList;
    }

    public Rescue updateRescue(UUID rescueId, UpdateRescueRequestDTO requestDTO) throws NotFoundException {
        Rescue foundRescue = rescueRepository.getById(rescueId);
        if (foundRescue == null) {
            throw new NotFoundException("Rescue not found");
        }
        foundRescue.setStory(requestDTO.getStory());
        rescueRepository.update(foundRescue.getId(), foundRescue);
        return foundRescue;
    }

    public List<Rescue> findByUser(UUID userId, Integer limit, Integer offset) throws NotFoundException {
        List<Rescue> rescueList = rescueRepository.getByUser(userId, limit, offset);
        if (rescueList != null) return rescueList;
        throw new NotFoundException("No data.");
    }

    public List<Rescue> findByTimeInterval(
            UUID userId, LocalDateTime start, LocalDateTime finish
            , Integer limit, Integer offset) throws NotFoundException {
        List<Rescue> rescueList = rescueRepository.getByTimeInterval(userId, start, finish, limit, offset);
        if (rescueList != null) return rescueList;
        throw new NotFoundException("No data.");
    }
}
