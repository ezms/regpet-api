package com.regpet.api.services;

import com.regpet.api.dto.files.FormDataDTO;
import com.regpet.api.models.AnimalImage;
import com.regpet.api.repositories.AnimalImageRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class AnimalImageService {

    @ConfigProperty(name = "quarkus.http.body.uploads-directory")
    String directory;

    @Inject
    AnimalImageRepository animalImageRepository;

    public AnimalImage upload(FormDataDTO formData) throws IOException {
        List<String> mimeTypes = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gif");
        if (!mimeTypes.contains(formData.getFile().contentType())) {
            throw new IOException("File not supported.");
        }

        if (formData.getFile().size() > 1024 * 1024 * 4) {
            throw new IOException("File is too big.");
        }

        String fileName = UUID.randomUUID() + "-" + formData.getFile().name();
        AnimalImage image = new AnimalImage();
        image.setLastModifiedDate(LocalDateTime.now());
        image.setName(formData.getFile().name());
        image.setSize(formData.getFile().size());
        image.setMime(formData.getFile().contentType());
        image.setKey(fileName);
        animalImageRepository.add(image);

        Files.copy(formData.getFile().filePath(), Paths.get(directory + fileName));
        return image;
    }
}
