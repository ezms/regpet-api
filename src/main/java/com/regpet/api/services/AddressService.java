package com.regpet.api.services;

import com.regpet.api.dto.requests.AddressRequestDTO;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.models.Address;
import com.regpet.api.models.User;
import com.regpet.api.repositories.AddressRepository;
import com.regpet.api.repositories.UserRepository;
import com.regpet.api.utils.RequestUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.*;

@RequestScoped
public class AddressService {

    @Inject
    AddressRepository addressRepository;

    @Inject
    UserRepository userRepository;

    public Address save(AddressRequestDTO dto) {
        return addressRepository.add(buildAddress(dto));
    }

    public Address save(UUID userId, AddressRequestDTO dto) throws NotFoundException {
        User foundUser = userRepository.getById(userId);
        Address address = buildAddress(dto);
        ArrayList<User> userList = new ArrayList<>();
        userList.add(foundUser);
        if (Objects.nonNull(foundUser)) {
            address.setUsers(userList);
        }

        return addressRepository.add(buildAddress(dto));
    }

    public Address buildAddress(AddressRequestDTO request) {
        Address address = new Address();
        address.setZipCode(request.getZipCode());
        address.setState(request.getState());
        address.setCity(request.getCity());
        address.setDistrict(request.getDistrict());
        address.setPublicPlace(request.getPublicPlace());
        address.setNumber(request.getNumber());
        return address;
    }
}
