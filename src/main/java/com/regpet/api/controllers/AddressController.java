package com.regpet.api.controllers;

import com.regpet.api.core.BaseController;
import com.regpet.api.dto.address.UserAddressesDTO;
import com.regpet.api.dto.exceptions.ErrorDTO;
import com.regpet.api.dto.exceptions.InvalidFieldDTO;
import com.regpet.api.dto.exceptions.MissingFieldsMessageDTO;
import com.regpet.api.dto.requests.AddressRequestDTO;
import com.regpet.api.exceptions.MissingFieldException;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.models.Address;
import com.regpet.api.services.AddressService;
import com.regpet.api.utils.RequestUtils;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/addresses")
@OpenAPIDefinition(info = @Info(description = "Endpoints for working with address locations",
        title = "Address", version = "0.0.1"))
public class AddressController extends BaseController {

    @Inject
    AddressService addressService;

    @POST
    public Response setAddress(@QueryParam("userId") UUID userId, AddressRequestDTO request) {
        try {
            RequestUtils.validateRequestBody(request, validator);
            if (userId != null) {
                Address address = addressService.saveWithUser(userId, request);
                return Response.status(Response.Status.CREATED).entity(address).build();
            }
            return Response.status(Response.Status.CREATED).entity(addressService.save(request)).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) (e.getErrors()))).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response retrieveAddress(@PathParam("id") UUID id) {
        try {
            return Response.status(Response.Status.OK).entity(
                    addressService.findById(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    public Response retrieveUserAddresses(@QueryParam("userId") UUID userId) {
        try {
            return Response.status(Response.Status.OK).entity(new UserAddressesDTO(
                    addressService.findByUser(userId))).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateAddressData(@PathParam("id") UUID id, Address address) {
        try {
            RequestUtils.validateRequestBody(address, validator);
            return Response.status(Response.Status.OK).entity(
                    addressService.update(id, address)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getError()).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) e.getErrors())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeAddress(@PathParam("id") UUID id, Address address) {
        try {
            addressService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getError()).build();
        }
    }

    @GET
    @Path("/filter")
    public Response getPartialAddresses(@QueryParam("zipCode") String zipCode) {
        return Response.status(Response.Status.OK).entity(addressService.findByZipCode(zipCode)).build();
    }
}
