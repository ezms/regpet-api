package com.regpet.api.services;

import com.regpet.api.dto.requests.UserLoginDTO;
import com.regpet.api.repositories.UserRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class JWTService {

    @Inject
    UserRepository userRepository;

    public String generateJWT(String email, String password) {
        if (!userRepository.isEmailAlreadyRegistered(email)) {
            throw new NotFoundException("User not found!");
        }
        UserLoginDTO loginDTO = userRepository.getLoginData(email);
        if (BcryptUtil.matches(password, loginDTO.getPassword())) {
            Set<String> roles = new HashSet<>(
                    Arrays.asList("admin", "ngo", "guest", "animalProtector"));
            long duration = System.currentTimeMillis() + 3600;
            return Jwt.issuer("regpet-api")
                    .subject("regpet")
                    .upn(loginDTO.getEmail())
                    .groups(roles)
                    .expiresAt(duration)
                    .sign();
        }
        return null;
    }
}
