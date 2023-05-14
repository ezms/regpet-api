package com.regpet.api.controllers;

import com.regpet.api.core.BaseController;
import com.regpet.api.dto.exceptions.ErrorDTO;
import com.regpet.api.dto.exceptions.InvalidFieldDTO;
import com.regpet.api.dto.exceptions.MissingFieldsMessageDTO;
import com.regpet.api.dto.requests.CreateRescueRequestDTO;
import com.regpet.api.dto.requests.UpdateRescueRequestDTO;
import com.regpet.api.exceptions.MissingFieldException;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.services.RescueService;
import com.regpet.api.utils.RequestUtils;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Path("/rescues")
public class RescueController extends BaseController {

    @Inject
    RescueService rescueService;

    @POST
    @PermitAll
    public Response makeRescue(@QueryParam("userId") UUID userId, CreateRescueRequestDTO requestDTO) {
        try {
            RequestUtils.validateRequestBody(requestDTO, validator);
            return Response.status(Response.Status.CREATED).entity(rescueService.register(userId, requestDTO)).build();
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
    public Response getRescue(@PathParam("id") UUID rescueId) {
        try {
            return Response.status(Response.Status.OK).entity(rescueService.findById(rescueId)).build();
        }  catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @RolesAllowed({"admin", "ngo", "guest", "animalProtector"})
    public Response getRescues(@QueryParam("limit") Integer limiter, @QueryParam("offset") Integer offset) {
        try {
            return Response.status(Response.Status.OK).entity(rescueService.findAll(limiter, offset)).build();
        }  catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @Path("/user/{id}")
    @RolesAllowed({"admin", "ngo", "guest", "animalProtector"})
    public Response getRescuesByUser(@PathParam("id") UUID userId,
                                     @QueryParam("limit") Integer limiter,
                                     @QueryParam("offset") Integer offset) {
        try {
            return Response.status(Response.Status.OK).entity(rescueService.findByUser(userId, limiter, offset)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @Path("/filter/user/{id}")
    @RolesAllowed({"admin", "ngo", "guest", "animalProtector"})
    public Response getRescuesByTimeInterval(@PathParam("id") UUID userId,
                                     @QueryParam("start") LocalDateTime startDate,
                                     @QueryParam("finish") LocalDateTime finishDate,
                                     @QueryParam("limit") Integer limiter,
                                     @QueryParam("offset") Integer offset) {
        try {
            return Response.status(Response.Status.OK).entity(rescueService
                    .findByTimeInterval(userId, startDate, finishDate, limiter, offset)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @PUT
    @Path("/{id}")
    // TODO:Criar validação para não ser alterada
    @RolesAllowed({"admin", "ngo", "animalProtector"})
    public Response getRescue(@PathParam("id") UUID rescueId, UpdateRescueRequestDTO requestDTO) {
        try {
            RequestUtils.validateRequestBody(requestDTO, validator);
            return Response.status(Response.Status.OK).entity(rescueService.updateRescue(rescueId, requestDTO)).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) (e.getErrors()))).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }
}
