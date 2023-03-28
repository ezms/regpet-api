package com.regpet.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "rescues", schema = "public")
public class Rescue implements IEntityDefault<UUID> {

    @Id
    @Column(name = "rescue_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "rescue_date", nullable = false)
    private LocalDateTime rescueDate;

    @Column(name = "story")
    private String story;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "rescues", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Address> address;

    @JsonIgnore
    @ManyToMany(mappedBy = "rescues", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Animal> animals = new ArrayList<>();
}
