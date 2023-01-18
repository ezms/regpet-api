package com.regpet.api.controllers;

import com.regpet.api.dto.auth.TokenDTO;
import com.regpet.api.dto.exceptions.MissingFieldsMessageDTO;
import com.regpet.api.dto.exceptions.ErrorDTO;
import com.regpet.api.dto.requests.CreateUserRequestDTO;
import com.regpet.api.dto.requests.UserLoginDTO;
import com.regpet.api.dto.user.UserCreateResponseDTO;
import com.regpet.api.exceptions.MissingFieldException;
import com.regpet.api.exceptions.WrongFieldException;
import com.regpet.api.services.JWTService;
import com.regpet.api.services.UserService;
import com.regpet.api.utils.RequestUtils;
import io.quarkus.elytron.security.common.BcryptUtil;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@OpenAPIDefinition(info = @Info(description = "Endpoints for working with user authentication",
        title = "Authentications", version = "0.0.1"))
public class AuthController {

    @Inject
    JWTService jwtService;

    @Inject
    UserService userService;

    @Inject
    Validator validator;

    @POST
    @PermitAll
    @Path("/register")
    public Response controller(CreateUserRequestDTO userRequest) {
        try {
            RequestUtils.validateRequestBody(userRequest, validator);
            UserCreateResponseDTO response = userService.add(userRequest);
            return Response.status(Response.Status.CREATED.getStatusCode()).entity(response).build();
        } catch (MissingFieldException e) {
            return Response.status(e.getStatusCode()).entity(new MissingFieldsMessageDTO(e.getMessage(),
                    new ArrayList<>(e.getErrors()))).build();
        } catch (WrongFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @POST
    @Path("/login")
    public Response authenticate(UserLoginDTO request) {
        String jwt = jwtService.generateJWT(request.getEmail(), request.getPassword());
        return Response.status(Response.Status.OK).entity(new TokenDTO(jwt)).build();
    }
}
