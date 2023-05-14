package com.regpet.api.controllers;

import com.regpet.api.core.BaseController;
import com.regpet.api.dto.exceptions.ErrorDTO;
import com.regpet.api.dto.exceptions.InvalidFieldDTO;
import com.regpet.api.dto.exceptions.MissingFieldsMessageDTO;
import com.regpet.api.dto.requests.CreateAnimalRequestDTO;
import com.regpet.api.dto.requests.UpdateAnimalRequestDTO;
import com.regpet.api.exceptions.MissingFieldException;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.services.AnimalService;
import com.regpet.api.utils.RequestUtils;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/animals")
public class AnimalController extends BaseController {

    @Inject
    AnimalService animalService;

    @POST
    @RolesAllowed({"admin", "user", "guest"})
    public Response saveAnimal(CreateAnimalRequestDTO request) {
        try {
            RequestUtils.validateRequestBody(request, validator);
            return Response.status(Response.Status.CREATED).entity(animalService.save(request)).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) e.getErrors())).build();
        }
    }

    @GET
    @RolesAllowed({"admin", "user", "ngo", "animalProtector", "guest"})
    public Response retrieveAllAnimals(@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset) {
        try {
            return Response.status(Response.Status.OK).entity(animalService.getAll(
                    limit != null ? limit : 25, offset != null ? offset : 0)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin", "user", "ngo", "animalProtector", "guest"})
    public Response retrieveAnAnimal(@PathParam("id") UUID id) {
        try {
            return Response.status(Response.Status.OK).entity(
                    animalService.getByAnimalId(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @Path("/filter")
    @RolesAllowed({"admin", "user", "ngo", "animalProtector", "guest"})
    public Response retrieveAllAnimalsByName(@QueryParam("name") String name) {
        try {
            return Response.status(Response.Status.OK).entity(
                    animalService.getByName(name)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"admin", "user", "ngo", "animalProtector", "guest"})
    public Response updateAnimalData(@PathParam("id") UUID id, UpdateAnimalRequestDTO request) {
        try {
            RequestUtils.validateRequestBody(request, validator);
            return Response.status(Response.Status.CREATED).entity(
                    animalService.updateAnimal(id, request)).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) e.getErrors())).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }
}
