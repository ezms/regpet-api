package com.regpet.api.services;

import com.regpet.api.dto.requests.VolunteerRequestDTO;
import com.regpet.api.dto.volunteers.VolunteerResponseDTO;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.models.Ngo;
import com.regpet.api.models.Volunteer;
import com.regpet.api.repositories.NgoRepository;
import com.regpet.api.repositories.VolunteerRepository;
import com.regpet.api.utils.TextUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequestScoped
public class VolunteerService {

    @Inject
    VolunteerRepository volunteerRepository;

    @Inject
    NgoRepository ngoRepository;

    public Volunteer saveVolunteer(VolunteerRequestDTO request, UUID ngoId) throws NotFoundException {
        Ngo ngo = ngoRepository.getById(ngoId);
        if (Objects.isNull(ngo)) throw new NotFoundException("No NGO found.");
        Volunteer volunteer = new Volunteer();
        volunteer.setName(TextUtils.capitalizeName(request.getName()));
        volunteer.setNgo(ngoRepository.getById(ngoId));
        volunteer.setStatus(request.getStatus());
        return volunteerRepository.add(volunteer);
    }

    public List<VolunteerResponseDTO> retrieveVolunteers(UUID ngoId) throws NotFoundException {
        Ngo ngo = ngoRepository.getById(ngoId);
        if (Objects.isNull(ngo)) throw new NotFoundException("No NGO found.");
        List<VolunteerResponseDTO> results = volunteerRepository.findByNGO(ngo.getId());
        return (Objects.nonNull(results) && !results.isEmpty()) ? results : new ArrayList<>();
    }

    public Volunteer getById(UUID id) throws NotFoundException {
        Volunteer volunteer = volunteerRepository.getById(id);
        if (Objects.isNull(volunteer)) throw new NotFoundException("No data found.");
        return volunteer;
    }

    public Volunteer update(UUID id, VolunteerRequestDTO requestDTO) throws NotFoundException {
        Volunteer volunteer = volunteerRepository.getById(id);
        if (Objects.isNull(volunteer)) throw new NotFoundException("No data found.");
        volunteer.setName(TextUtils.capitalizeName(requestDTO.getName()));
        volunteer.setStatus(requestDTO.getStatus());
        return volunteerRepository.update(id, volunteer);
    }

    public void delete(UUID id) throws NotFoundException {
        Volunteer volunteer = volunteerRepository.getById(id);
        if (Objects.isNull(volunteer)) throw new NotFoundException("No data found.");
        volunteerRepository.delete(volunteer.getId());
    }
}
