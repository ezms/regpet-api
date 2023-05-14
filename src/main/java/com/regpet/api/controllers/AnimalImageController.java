package com.regpet.api.controllers;

import com.regpet.api.core.BaseController;
import com.regpet.api.dto.exceptions.ErrorDTO;
import com.regpet.api.dto.files.FormDataDTO;
import com.regpet.api.services.AnimalImageService;
import org.jboss.resteasy.reactive.MultipartForm;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/animal-images")
public class AnimalImageController extends BaseController {

    @Inject
    AnimalImageService animalImageService;

    @POST
    @RolesAllowed({"user", "guest", "ngo", "animalProtector", "admin"})
    public Response uploadAnimalImage(@MultipartForm FormDataDTO data) {
        try {
            return Response.status(Response.Status.OK).entity(
                    animalImageService.upload(data)).build();
        } catch (IOException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new ErrorDTO(e.getMessage())).build();
        }
    }
}
