package com.regpet.api.controllers;

import com.regpet.api.core.BaseController;
import com.regpet.api.dto.exceptions.ErrorDTO;
import com.regpet.api.dto.exceptions.InvalidFieldDTO;
import com.regpet.api.dto.exceptions.MissingFieldsMessageDTO;
import com.regpet.api.dto.requests.GuestContactRequestDTO;
import com.regpet.api.exceptions.MissingFieldException;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.exceptions.WrongFieldException;
import com.regpet.api.services.GuestContactService;
import com.regpet.api.services.GuestService;
import com.regpet.api.utils.RequestUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/guests")
public class GuestController extends BaseController {

    @Inject
    GuestService guestService;

    @Inject
    GuestContactService contactService;

    @POST
    public Response register(@QueryParam("userId") UUID userId) {
        try {
            return Response.status(Response.Status.OK).entity(guestService.add(userId)).build();
        } catch (WrongFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDTO(e.getMessage())).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID id) {
        try {
            return Response.status(Response.Status.OK).entity(guestService.findById(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @PUT
    @Path("/{id}/contacts")
    public Response saveContacts(@PathParam("id") UUID id, GuestContactRequestDTO requestDTO) {
        try {
            RequestUtils.validateRequestBody(requestDTO, validator);
            return Response.status(Response.Status.OK).entity(contactService.save(requestDTO, id)).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) (e.getErrors()))).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @Path("/{id}/contacts")
    public Response getContacts(@PathParam("id") UUID id) {
        try {
            return Response.status(Response.Status.OK).entity(contactService.findByGuest(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }
}
