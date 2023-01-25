package com.regpet.api.services;

import com.regpet.api.dto.requests.AddressRequestDTO;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.models.Address;
import com.regpet.api.models.User;
import com.regpet.api.repositories.AddressRepository;
import com.regpet.api.repositories.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequestScoped
public class AddressService {

    @Inject
    AddressRepository addressRepository;

    @Inject
    UserRepository userRepository;

    public Address save(AddressRequestDTO dto) {
        return addressRepository.add(buildAddress(dto));
    }

    public Address saveWithUser(UUID userId, AddressRequestDTO dto) throws NotFoundException {
        User foundUser = userRepository.getById(userId);
        Address address = buildAddress(dto);

        if (Objects.nonNull(foundUser)) {
            address.addUser(foundUser);
        }

        addressRepository.add(address);
        return address;
    }

    public Address findById(UUID id) throws NotFoundException {
        Address foundAddress = addressRepository.getById(id);
        if (foundAddress != null) {
            return foundAddress;
        }

        throw new NotFoundException("Address not found");
    }

    public List<Address> findByUser(UUID userId) throws NotFoundException {
        List<Address> addresses = addressRepository.getByUserId(userId);

        if (addresses.isEmpty()) {
            throw new NotFoundException("No data found");
        }

        return addresses;
    }

    public Address update(UUID id, Address address) throws NotFoundException {
        Address foundAddress = addressRepository.getById(id);

        if (foundAddress == null) {
            throw new NotFoundException("Address not found.");
        }

        foundAddress.setZipCode(address.getZipCode());
        foundAddress.setState(address.getState());
        foundAddress.setCity(address.getCity());
        foundAddress.setDistrict(address.getDistrict());
        foundAddress.setPublicPlace(address.getPublicPlace());
        foundAddress.setNumber(address.getNumber());
        foundAddress.setComplement(address.getComplement());

        return addressRepository.update(id, foundAddress);
    }

    public void delete(UUID id) throws NotFoundException {
        Address foundAddress = addressRepository.getById(id);
        if (foundAddress == null) {
            throw new NotFoundException("Address not found.");
        }
        addressRepository.delete(foundAddress.getId());
    }

    private Address buildAddress(AddressRequestDTO request) {
        Address address = new Address();
        address.setZipCode(request.getZipCode());
        address.setState(request.getState());
        address.setCity(request.getCity());
        address.setDistrict(request.getDistrict());
        address.setPublicPlace(request.getPublicPlace());
        address.setNumber(request.getNumber());
        address.setComplement(request.getComplement() != null ? request.getComplement() : "");
        return address;
    }
}
