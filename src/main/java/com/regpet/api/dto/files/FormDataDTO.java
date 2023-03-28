package com.regpet.api.dto.files;

import lombok.Getter;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@Getter
public class FormDataDTO {

    @RestForm("file")
    private FileUpload file;
}
