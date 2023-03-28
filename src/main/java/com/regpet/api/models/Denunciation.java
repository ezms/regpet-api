package com.regpet.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "denunciations", schema = "public")
public class Denunciation implements IEntityDefault<UUID> {

    @Id
    @Column(name = "denunciation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "denunciation_date", nullable = false)
    private LocalDateTime denunciationDate;

    @Column(name = "details", nullable = false)
    private String details;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToMany(mappedBy = "denunciations", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Animal> animals = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;
}
