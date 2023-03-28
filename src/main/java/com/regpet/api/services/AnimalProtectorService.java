package com.regpet.api.services;

import com.regpet.api.dto.requests.AnimalProtectorRequestDTO;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.models.AnimalProtector;
import com.regpet.api.models.User;
import com.regpet.api.repositories.AnimalProtectorRepository;
import com.regpet.api.repositories.UserRepository;
import com.regpet.api.utils.TextUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class AnimalProtectorService {

    @Inject
    AnimalProtectorRepository animalProtectorRepository;

    @Inject
    UserRepository userRepository;

    public AnimalProtector register(UUID userId, AnimalProtectorRequestDTO request) throws NotFoundException {
        User foundUser = userRepository.getById(userId);
        if (foundUser == null) {
            throw new NotFoundException("User not found.");
        }

        AnimalProtector animalProtector = new AnimalProtector();
        animalProtector.setOrganizationName(TextUtils.capitalizeName(request.getOrganizationName()));
        animalProtector.setUser(foundUser);
        animalProtectorRepository.add(animalProtector);
        return animalProtector;
    }

    public List<AnimalProtector> findAll() throws NotFoundException {
        List<AnimalProtector> animalProtectors = animalProtectorRepository.getAll();
        if (animalProtectors.isEmpty()) {
            throw new NotFoundException("No data found.");
        }
        return animalProtectors;
    }

    public AnimalProtector findById(UUID id) throws NotFoundException {
        AnimalProtector AnimalProtector = animalProtectorRepository.getById(id);
        if (AnimalProtector != null) {
            return AnimalProtector;
        }
        throw new NotFoundException("Animal Protector not found.");
    }

    public AnimalProtector update(UUID id, AnimalProtectorRequestDTO requestDTO) throws NotFoundException {
        AnimalProtector AnimalProtector = animalProtectorRepository.getById(id);
        if (AnimalProtector == null) {
            throw new NotFoundException("Animal Protector not found.");
        }

        AnimalProtector.setOrganizationName(TextUtils.capitalizeName(requestDTO.getOrganizationName()));
        return AnimalProtector;
    }

    public void delete(UUID id) throws NotFoundException {
        AnimalProtector AnimalProtector = animalProtectorRepository.getById(id);
        if (AnimalProtector == null) {
            throw new NotFoundException("Animal Protector not found.");
        }
        animalProtectorRepository.delete(AnimalProtector.getId());
    }
}
