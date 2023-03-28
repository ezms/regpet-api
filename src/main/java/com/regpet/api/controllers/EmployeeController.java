package com.regpet.api.controllers;

import com.regpet.api.core.BaseController;
import com.regpet.api.dto.exceptions.ErrorDTO;
import com.regpet.api.dto.exceptions.InvalidFieldDTO;
import com.regpet.api.dto.exceptions.MissingFieldsMessageDTO;
import com.regpet.api.dto.requests.EmployeeRequestDTO;
import com.regpet.api.exceptions.MissingFieldException;
import com.regpet.api.exceptions.NotFoundException;
import com.regpet.api.services.EmployeeService;
import com.regpet.api.services.NgoService;
import com.regpet.api.utils.RequestUtils;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/employees")
public class EmployeeController extends BaseController {

    @Inject
    EmployeeService employeeService;

    @Inject
    NgoService ngoService;

    @POST
    public Response registerEmployee(@QueryParam("ngo") UUID ngoId, EmployeeRequestDTO requestDTO) {
        try {
            RequestUtils.validateRequestBody(requestDTO, validator);
            return Response.status(Response.Status.CREATED)
                    .entity(employeeService.saveEmployee(requestDTO, ngoId)).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) (e.getErrors()))).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    public Response getAll(@QueryParam("ngo") UUID ngoId) {
        try {
            return Response.status(Response.Status.OK).entity(employeeService.retrieveEmployees(ngoId)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID id) {
        try {
            return Response.status(Response.Status.OK).entity(employeeService.getById(id)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, EmployeeRequestDTO requestDTO) {
        try {
            RequestUtils.validateRequestBody(requestDTO, validator);
            return Response.status(Response.Status.OK).entity(employeeService.update(id, requestDTO)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        } catch (MissingFieldException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new MissingFieldsMessageDTO(e.getMessage(), (List<InvalidFieldDTO>) (e.getErrors()))).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        try {
            employeeService.delete(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorDTO(e.getError())).build();
        }
    }
}
