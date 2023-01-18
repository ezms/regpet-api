package com.regpet.api.models;

import com.regpet.api.enums.Gender;
import com.regpet.api.enums.Status;
import com.regpet.api.interfaces.IEntityDefaults;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "animals", schema = "public")
public class Animal implements IEntityDefaults<UUID> {

    @Id
    @Column(name = "animal_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "species", nullable = false, length = 100)
    private String species;

    @Column(name = "breed", nullable = false, length = 100)
    private String breed;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "found_on", nullable = false)
    private LocalDateTime foundOn;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "animal")
    private List<AnimalImage> image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "animals_rescues",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "rescue_id")
    )
    private List<Rescue> rescues;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "animals_denunciations",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "denunciation_id")
    )
    private List<Denunciation> denunciations;
}
