package com.regpet.api.controllers;

import com.regpet.api.core.BaseController;
import com.regpet.api.dto.exceptions.ErrorDTO;
import com.regpet.api.dto.exceptions.InvalidFieldDTO;
import com.regpet.api.dto.exceptions.MissingFieldsMessageDTO;
import com.regpet.api.dto.requests.CreateDenunciationRequestDTO;
import com.regpet.api.dto.requests.UpdateDenunciationRequestDTO;
import com.regpet.api.exceptions.MissingFieldException;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.services.DenunciationService;
import com.regpet.api.utils.RequestUtils;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Path("/denunciations")
public class DenunciationController extends BaseController {

    @Inject
    DenunciationService denunciationService;

    @POST
    @PermitAll
    public Response makeDenunciation(@QueryParam("userId") UUID userId, CreateDenunciationRequestDTO requestDTO) {
        try {
            RequestUtils.validateRequestBody(requestDTO, validator);
            return Response.status(Response.Status.CREATED).entity(denunciationService.register(
                    userId, requestDTO)).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) (e.getErrors()))).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin", "ngo", "guest", "animalProtector"})
    public Response getDenunciation(@PathParam("id") UUID denunciationsId) {
        try {
            return Response.status(Response.Status.OK).entity(denunciationService.findById(denunciationsId)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @RolesAllowed({"admin", "ngo", "guest", "animalProtector"})
    public Response getDenunciations(@QueryParam("limit") Integer limiter, @QueryParam("offset") Integer offset) {
        try {
            return Response.status(Response.Status.OK).entity(denunciationService.findAll(limiter, offset)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @Path("/user/{id}")
    @RolesAllowed({"admin", "ngo", "guest", "animalProtector"})
    public Response getDenunciationByUser(@PathParam("id") UUID userId,
                                     @QueryParam("limit") Integer limiter,
                                     @QueryParam("offset") Integer offset) {
        try {
            return Response.status(Response.Status.OK).entity(denunciationService.findByUser(userId, limiter, offset)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @Path("/filter/user/{id}")
    @RolesAllowed({"admin", "ngo", "guest", "animalProtector"})
    public Response getDenunciationByUser(@PathParam("id") UUID userId,
                                     @QueryParam("start") LocalDateTime startDate,
                                     @QueryParam("finish") LocalDateTime finishDate,
                                     @QueryParam("limit") Integer limiter,
                                     @QueryParam("offset") Integer offset) {
        try {
            return Response.status(Response.Status.OK).entity(denunciationService
                    .findByTimeInterval(userId, startDate, finishDate, limiter, offset)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @PATCH
    // TODO:Criar validação para não ser alterada
    @Path("/{id}")
    @RolesAllowed({"admin", "ngo", "animalProtector"})
    public Response getDenunciation(@PathParam("id") UUID denunciationsId, UpdateDenunciationRequestDTO requestDTO) {
        try {
            RequestUtils.validateRequestBody(requestDTO, validator);
            return Response.status(Response.Status.OK).entity(
                    denunciationService.updateDenunciation(denunciationsId, requestDTO)).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) (e.getErrors()))).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }
}
