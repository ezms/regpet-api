package com.regpet.api.services;

import com.regpet.api.dto.requests.GuestContactRequestDTO;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.models.Guest;
import com.regpet.api.models.GuestContact;
import com.regpet.api.repositories.GuestContactRepository;
import com.regpet.api.repositories.GuestRepository;
import com.regpet.api.validators.PhoneValidator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Objects;
import java.util.UUID;

@RequestScoped
public class GuestContactService {

    @Inject
    GuestContactRepository contactRepository;

    @Inject
    GuestRepository guestRepository;

    public GuestContact save(GuestContactRequestDTO requestDTO, UUID guestId) throws NotFoundException {
        Guest guest = guestRepository.getById(guestId);
        if (Objects.isNull(guest)) throw new NotFoundException("Guest not found.");
        GuestContact contact = new GuestContact();
        contact.setCellphoneNumber(
                PhoneValidator.validate(requestDTO.getCellphoneNumber())
                ? requestDTO.getCellphoneNumber()
                : null);
        contact.setFacebookUrl(requestDTO.getFacebookUrl() != null ? requestDTO.getFacebookUrl() : null);
        contact.setTwitterUrl(requestDTO.getTwitterUrl() != null ? requestDTO.getTwitterUrl() : null);
        contact.setInstagramUrl(requestDTO.getInstagramUrl() != null ? requestDTO.getInstagramUrl() : null);
        contact.setGuest(guest);
        return contactRepository.add(contact);
    }

    public GuestContact findByGuest(UUID guestId) throws NotFoundException {
        Guest guest = guestRepository.getById(guestId);
        if (Objects.isNull(guest)) throw new NotFoundException("Guest not found.");
        return contactRepository.findByGuest(guest.getId());
    }
}
