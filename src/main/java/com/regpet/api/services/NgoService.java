package com.regpet.api.services;

import com.regpet.api.dto.requests.CreateNGORequestDTO;
import com.regpet.api.dto.requests.NgoUpdateRequestDTO;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.exceptions.WrongFieldException;
import com.regpet.api.models.Ngo;
import com.regpet.api.models.User;
import com.regpet.api.repositories.NgoRepository;
import com.regpet.api.repositories.UserRepository;
import com.regpet.api.utils.TextUtils;
import com.regpet.api.validators.CNPJValidator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class NgoService {

    @Inject
    NgoRepository ngoRepository;

    @Inject
    UserRepository userRepository;

    public Ngo register(UUID userId, CreateNGORequestDTO request) throws NotFoundException, WrongFieldException {
        User foundUser = userRepository.getById(userId);
        if (foundUser == null) {
            throw new NotFoundException("User not found.");
        }

        if (!CNPJValidator.validate(request.getCnpj())) {
            throw new WrongFieldException("Bad cnpj format.");
        }

        Ngo ngo = new Ngo();
        ngo.setCnpj(request.getCnpj());
        ngo.setCompanyName(TextUtils.capitalizeName(request.getCompanyName()));
        ngo.setTradingName(TextUtils.capitalizeName(request.getTradingName()));
        ngo.setUser(foundUser);
        ngoRepository.add(ngo);
        return ngo;
    }

    public List<Ngo> findAll() throws NotFoundException {
        List<Ngo> ngoList = ngoRepository.getAll();
        if (ngoList.isEmpty()) {
            throw new NotFoundException("No data found.");
        }
        return ngoList;
    }

    public Ngo findById(UUID id) throws NotFoundException {
        Ngo foundNgo = ngoRepository.getById(id);
        if (foundNgo != null) {
            return foundNgo;
        }
        throw new NotFoundException("Ngo not found.");
    }

    public Ngo update(UUID id, NgoUpdateRequestDTO requestDTO) throws NotFoundException {
        Ngo foundNgo = ngoRepository.getById(id);
        if (foundNgo == null) {
            throw new NotFoundException("Ngo not found.");
        }

        foundNgo.setCompanyName(TextUtils.capitalizeName(requestDTO.getCompanyName()));
        foundNgo.setTradingName(TextUtils.capitalizeName(requestDTO.getTradingName()));
        return foundNgo;
    }

    public void delete(UUID id) throws NotFoundException {
        Ngo foundNgo = ngoRepository.getById(id);
        if (foundNgo == null) {
            throw new NotFoundException("Ngo not found.");
        }
        ngoRepository.delete(foundNgo.getId());
    }
}
