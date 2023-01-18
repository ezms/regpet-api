package com.regpet.api.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Posts {
    private UUID id;
    private String content;
    private LocalDateTime postedAt;
}
