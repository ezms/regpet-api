package com.regpet.api.services;

import com.regpet.api.dto.requests.CreateUserRequestDTO;
import com.regpet.api.dto.requests.UserUpdateRequestDTO;
import com.regpet.api.dto.user.UserCreateResponseDTO;
import com.regpet.api.dto.user.UserRequiredFieldsDTO;
import com.regpet.api.dto.user.UserResponseDTO;
import com.regpet.api.dto.user.UserUpdateResponseDTO;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.exceptions.WrongFieldException;
import com.regpet.api.models.User;
import com.regpet.api.repositories.UserRepository;
import com.regpet.api.utils.TextUtils;
import com.regpet.api.validators.PatternValidator;
import io.quarkus.elytron.security.common.BcryptUtil;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Objects;
import java.util.UUID;

@RequestScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public UserCreateResponseDTO add(CreateUserRequestDTO requestData) throws WrongFieldException {
        validateCreateUserRequiredFields(new UserRequiredFieldsDTO(
                requestData.getName(), requestData.getEmail(), requestData.getPhoneNumber()));

        User user = new User();
        user.setName(TextUtils.capitalizeName(requestData.getName()));
        user.setUsername(requestData.getUsername());
        user.setPhoneNumber(requestData.getPhoneNumber());
        user.setEmail(requestData.getEmail());
        user.setPassword(BcryptUtil.bcryptHash(requestData.getPassword()));
        user.setIsActive(false);

        userRepository.add(user);

        return new UserCreateResponseDTO(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber());
    }

    public UserResponseDTO findById(UUID id) throws NotFoundException {
        User foundUser = userRepository.getById(id);

        if (Objects.isNull(foundUser))
            throw new NotFoundException("User not found.");

        return new UserResponseDTO(
                foundUser.getId(), foundUser.getName(), foundUser.getUsername(), foundUser.getEmail(),
                foundUser.getBio(), foundUser.getProfilePhoto()
        );
    }

    public UserUpdateResponseDTO update(UUID id, UserUpdateRequestDTO request) throws NotFoundException, WrongFieldException {
        User foundUser = userRepository.getById(id);

        if (Objects.isNull(foundUser))
            throw new NotFoundException("User not found.");

        validateCreateUserRequiredFields(new UserRequiredFieldsDTO(
                request.getName(), request.getEmail(), request.getPhoneNumber()));

        foundUser.setName(TextUtils.capitalizeName(request.getName()));
        foundUser.setUsername(request.getUsername());
        foundUser.setEmail(request.getEmail());
        foundUser.setBio(request.getBio());
        foundUser.setPhoneNumber(request.getPhoneNumber());
        foundUser.setProfilePhoto(request.getProfilePhoto());

        userRepository.update(id, foundUser);

        return new UserUpdateResponseDTO(
                foundUser.getId(), foundUser.getName(), foundUser.getUsername(),
                foundUser.getBio(), foundUser.getEmail(), foundUser.getPhoneNumber(), foundUser.getProfilePhoto());
    }

    public void delete(UUID id) throws NotFoundException {
        User foundUser = userRepository.getById(id);
        if (Objects.isNull(foundUser))
            throw new NotFoundException("User not found.");
        userRepository.delete(foundUser.getId());
    }

    private void validateCreateUserRequiredFields(UserRequiredFieldsDTO requestData) throws WrongFieldException {
        final String PHONE_NUMBER_PATTERN =
                "^\\(?(?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])\\)? ?(?:[2-8]|9[1-9])[0-9]{3}\\-?[0-9]{4}$";
        final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        if (requestData.getName().length() < 3)
            throw new WrongFieldException("Name must have more than 3(three) characters.");

        PatternValidator.validate(PHONE_NUMBER_PATTERN, requestData.getPhoneNumber(), "cellphone number");
        PatternValidator.validate(EMAIL_PATTERN, requestData.getEmail(), "email address");
    }
}
