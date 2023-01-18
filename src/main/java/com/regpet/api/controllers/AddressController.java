package com.regpet.api.controllers;

import com.regpet.api.dto.exceptions.ErrorDTO;
import com.regpet.api.dto.exceptions.InvalidFieldDTO;
import com.regpet.api.dto.exceptions.MissingFieldsMessageDTO;
import com.regpet.api.dto.requests.AddressRequestDTO;
import com.regpet.api.exceptions.MissingFieldException;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.services.AddressService;
import com.regpet.api.utils.RequestUtils;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressController {

    @Inject
    AddressService addressService;

    @Inject
    Validator validator;

    @POST
    public Response setAddress(@QueryParam("userId") UUID userId, AddressRequestDTO request) {
        try {
            RequestUtils.validateRequestBody(request, validator);
            if (userId != null) {
                return Response.status(Response.Status.CREATED).entity(
                        addressService.save(userId, request)).build();
            }
            return Response.status(Response.Status.CREATED).entity(addressService.save(request)).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) (e.getErrors()))).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }
}
