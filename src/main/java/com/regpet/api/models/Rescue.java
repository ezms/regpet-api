package com.regpet.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "rescues", schema = "public")
public class Rescue {

    @Id
    @Column(name = "rescue_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "rescue_date", nullable = false)
    private LocalDateTime rescueDate;

    @Column(name = "story")
    private String story;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "rescues", fetch = FetchType.LAZY)
    private List<Animal> animals;
}
