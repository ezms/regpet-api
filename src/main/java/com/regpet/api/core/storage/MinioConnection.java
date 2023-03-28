package com.regpet.api.core.storage;

import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.messages.Bucket;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class MinioConnection {

    public static void connection() {
        MinioClient minio = getData();
        try {
            List<Bucket> buckets = minio.listBuckets();
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | XmlParserException | InvalidResponseException |
                 InternalException e) {
            throw new RuntimeException(e);
        }
    }

    private static MinioClient getData() {
        return MinioClient.builder()
                .endpoint("")
                .credentials("", "")
                .build();
    }
}
