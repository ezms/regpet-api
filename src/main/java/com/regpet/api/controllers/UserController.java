package com.regpet.api.controllers;

import com.regpet.api.core.BaseController;
import com.regpet.api.dto.exceptions.ErrorDTO;
import com.regpet.api.dto.exceptions.InvalidFieldDTO;
import com.regpet.api.dto.exceptions.MissingFieldsMessageDTO;
import com.regpet.api.dto.requests.UserUpdateRequestDTO;
import com.regpet.api.exceptions.MissingFieldException;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.exceptions.WrongFieldException;
import com.regpet.api.services.UserService;
import com.regpet.api.utils.RequestUtils;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/users")
public class UserController extends BaseController {

    @Inject
    UserService userService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin", "ngo", "guest", "animalProtector"})
    public Response getById(@PathParam("id") UUID id) {
        try {
            return Response.status(Response.Status.OK).entity(userService.findById(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"admin", "ngo", "guest", "animalProtector"})
    public Response update(@PathParam("id") UUID id, UserUpdateRequestDTO request) {
        try {
            RequestUtils.validateRequestBody(request, validator);
            return Response.status(Response.Status.OK).entity(userService.update(id, request)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new MissingFieldsMessageDTO(
                    e.getMessage(), (List<InvalidFieldDTO>) e.getErrors())).build();
        } catch (WrongFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response delete(@PathParam("id") UUID id) {
        try {
            userService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }
}
