package com.regpet.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.regpet.api.enums.Gender;
import com.regpet.api.enums.AnimalStatus;
import com.regpet.api.interfaces.IEntityDefault;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "animals", schema = "public")
public class Animal implements IEntityDefault<UUID> {

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
    private AnimalStatus animalStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY)
    private List<AnimalImage> image;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "animals_rescues",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "rescue_id")
    )
    private List<Rescue> rescues = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "animals_denunciations",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "denunciation_id")
    )
    private List<Denunciation> denunciations = new ArrayList<>();

    public void addRescue(Rescue rescue) {
        if (rescue != null) {
            if (!getRescues().contains(rescue)) {
                getRescues().add(rescue);
            }

            if (!rescue.getAnimals().contains(this)) {
                rescue.getAnimals().add(this);
            }
        }
    }

    public void addDenunciation(Denunciation denunciation) {
        if (denunciation != null) {
            if (!getDenunciations().contains(denunciation)) {
                getDenunciations().add(denunciation);
            }

            if (!denunciation.getAnimals().contains(this)) {
                denunciation.getAnimals().add(this);
            }
        }
    }
}
