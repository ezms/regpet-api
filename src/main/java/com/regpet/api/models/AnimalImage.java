package com.regpet.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "animals_images", schema = "public")
public class AnimalImage {

    @Id
    @Column(name = "animal_image_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "mime", nullable = false, length = 20)
    private String mime;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;
}
