package com.regpet.api.services;

import com.regpet.api.dto.requests.CreateDenunciationRequestDTO;
import com.regpet.api.dto.requests.UpdateDenunciationRequestDTO;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.models.Animal;
import com.regpet.api.models.Denunciation;
import com.regpet.api.models.User;
import com.regpet.api.repositories.DenunciationRepository;
import com.regpet.api.repositories.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequestScoped
public class DenunciationService {

    @Inject
    DenunciationRepository denunciationRepository;

    @Inject
    UserRepository userRepository;

    public Denunciation register(UUID userId, CreateDenunciationRequestDTO requestDTO) throws NotFoundException {
        User foundUser = userRepository.getById(userId);
        if (foundUser == null) {
            throw new NotFoundException("User not found");
        }

        Denunciation denunciation = new Denunciation();
        denunciation.setDenunciationDate(requestDTO.getDenunciationDate() != null
                ? requestDTO.getDenunciationDate() : LocalDateTime.now());
        denunciation.setDetails(requestDTO.getDetails() != null ? requestDTO.getDetails() : null);
        denunciation.setUser(foundUser);
        denunciation.setAnimals(requestDTO.getAnimals() != null &&
                !requestDTO.getAnimals().isEmpty() ?
                addAnimalList(denunciation, requestDTO.getAnimals()) :
                null
        );
        requestDTO.getAddress().setDenunciation(List.of(denunciation));
        denunciation.setAddress(requestDTO.getAddress() != null ? requestDTO.getAddress() : null);
        return denunciationRepository.add(denunciation);
    }

    private List<Animal> addAnimalList(Denunciation denunciation, List<Animal> animalList) {
        for (Animal animal : animalList) {
            animal.addDenunciation(denunciation);
        }
        return denunciation.getAnimals();
    }

    public Denunciation findById(UUID id) throws NotFoundException {
        Denunciation foundDenunciation = denunciationRepository.getById(id);
        if (Objects.isNull(foundDenunciation)) throw new NotFoundException("Denunciation not found");
        return foundDenunciation;
    }

    public List<Denunciation> findAll(Integer limiter, Integer offset) throws NotFoundException {
        List<Denunciation> denunciationList = denunciationRepository.paginate(limiter, offset);
        if (denunciationList.isEmpty()) throw new NotFoundException("No data");
        return denunciationList;
    }

    public Denunciation updateDenunciation(UUID denunciationId, UpdateDenunciationRequestDTO requestDTO) throws NotFoundException {
        Denunciation foundDenunciation = denunciationRepository.getById(denunciationId);
        if (foundDenunciation == null) {
            throw new NotFoundException("Report not found");
        }
        foundDenunciation.setDetails(requestDTO.getDetails());
        denunciationRepository.update(foundDenunciation.getId(), foundDenunciation);
        return foundDenunciation;
    }

    public List<Denunciation> findByUser(UUID userId, Integer limit, Integer offset) throws NotFoundException {
        List<Denunciation> denunciationList = denunciationRepository.getByUser(userId, limit, offset);
        if (denunciationList != null) return denunciationList;
        throw new NotFoundException("No data.");
    }

    public List<Denunciation> findByTimeInterval(
            UUID userId, LocalDateTime start, LocalDateTime finish
            , Integer limit, Integer offset) throws NotFoundException {
        List<Denunciation> denunciationList = denunciationRepository.getByTimeInterval(userId, start, finish, limit, offset);
        if (denunciationList != null) return denunciationList;
        throw new NotFoundException("No data.");
    }
}
