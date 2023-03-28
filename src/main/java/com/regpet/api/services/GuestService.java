package com.regpet.api.services;

import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.exceptions.WrongFieldException;
import com.regpet.api.models.Guest;
import com.regpet.api.models.User;
import com.regpet.api.repositories.GuestRepository;
import com.regpet.api.repositories.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Objects;
import java.util.UUID;

@RequestScoped
public class GuestService {

    @Inject
    GuestRepository guestRepository;

    @Inject
    UserRepository userRepository;

    public Guest add(UUID userId) throws WrongFieldException, NotFoundException {
        User foundUser = userRepository.getById(userId);
        if (Objects.isNull(foundUser)) {
            throw new NotFoundException("User not found.");
        }

        Guest guest = new Guest();
        guest.setUser(foundUser);
        guestRepository.add(guest);
        return guest;
    }

    public Guest findById(UUID id) throws NotFoundException {
        Guest foundGuest = guestRepository.getById(id);
        if (Objects.isNull(foundGuest))
            throw new NotFoundException("Guest not found.");
        return foundGuest;
    }

    public void delete(UUID id) throws NotFoundException {
        Guest foundGuest = guestRepository.getById(id);
        if (Objects.isNull(foundGuest))
            throw new NotFoundException("Guest not found.");
        guestRepository.delete(foundGuest.getId());
    }
}
