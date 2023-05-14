package com.regpet.api.controllers;

import com.regpet.api.core.BaseController;
import com.regpet.api.dto.exceptions.ErrorDTO;
import com.regpet.api.dto.exceptions.InvalidFieldDTO;
import com.regpet.api.dto.exceptions.MissingFieldsMessageDTO;
import com.regpet.api.dto.requests.AnimalProtectorRequestDTO;
import com.regpet.api.exceptions.MissingFieldException;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.services.AnimalProtectorService;
import com.regpet.api.utils.RequestUtils;
import lombok.NonNull;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/animals-protectors")
public class AnimalProtectorController extends BaseController {

    @Inject
    AnimalProtectorService animalProtectorService;

    @POST
    @RolesAllowed({"users", "guest", "admin"})
    public Response registerAnimalProtector(@NonNull @QueryParam("userId") UUID userId,
                                            AnimalProtectorRequestDTO requestDTO) {
        try {
            RequestUtils.validateRequestBody(requestDTO, validator);
            return Response.status(Response.Status.CREATED).entity(
                    animalProtectorService.register(userId, requestDTO)).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) e.getErrors())).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @RolesAllowed({"admin", "ngo", "guest", "animalProtector"})
    public Response getAllNGO() {
        try {
            return Response.status(Response.Status.OK).entity(animalProtectorService.findAll()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin", "ngo", "guest"})
    public Response getNgoById(@PathParam("id") UUID id) {
        try {
            return Response.status(Response.Status.OK).entity(animalProtectorService.findById(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"admin", "ngo"})
    public Response a(@PathParam("id") UUID id, AnimalProtectorRequestDTO requestDTO) {
        try {
            RequestUtils.validateRequestBody(requestDTO, validator);
            return Response.status(Response.Status.NO_CONTENT)
                    .entity(animalProtectorService.update(id, requestDTO)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getError()).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) e.getErrors())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response a(@PathParam("id") UUID id) {
        try {
            animalProtectorService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(
                    new ErrorDTO(e.getError())).build();
        }
    }
}
