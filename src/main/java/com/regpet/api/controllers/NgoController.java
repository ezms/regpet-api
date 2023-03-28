package com.regpet.api.controllers;

import com.regpet.api.core.BaseController;
import com.regpet.api.dto.exceptions.ErrorDTO;
import com.regpet.api.dto.exceptions.InvalidFieldDTO;
import com.regpet.api.dto.exceptions.MissingFieldsMessageDTO;
import com.regpet.api.dto.requests.CreateNGORequestDTO;
import com.regpet.api.dto.requests.NgoUpdateRequestDTO;
import com.regpet.api.exceptions.MissingFieldException;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.exceptions.WrongFieldException;
import com.regpet.api.services.NgoService;
import com.regpet.api.utils.RequestUtils;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/ngo")
public class NgoController extends BaseController {

    @Inject
    NgoService ngoService;

    @POST
    public Response registerNGO(@NotNull @QueryParam("userId") UUID userId, CreateNGORequestDTO requestDTO) {
        try {
            RequestUtils.validateRequestBody(requestDTO, validator);
            return Response.status(Response.Status.CREATED).entity(ngoService.register(userId, requestDTO)).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) e.getErrors())).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        } catch (WrongFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        }
    }

    @GET
    public Response getAllNGO() {
        try {
            return Response.status(Response.Status.OK).entity(ngoService.findAll()).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getNgoById(@PathParam("id") UUID id) {
        try {
            return Response.status(Response.Status.OK).entity(ngoService.findById(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response a(@PathParam("id") UUID id, NgoUpdateRequestDTO requestDTO) {
        try {
            RequestUtils.validateRequestBody(requestDTO, validator);
            return Response.status(Response.Status.OK).entity(ngoService.update(id, requestDTO)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getError()).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) e.getErrors())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response a(@PathParam("id") UUID id) {
        try {
            ngoService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(
                    new ErrorDTO(e.getError())).build();
        }
    }
}
