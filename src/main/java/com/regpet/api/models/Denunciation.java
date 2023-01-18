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
@Table(name = "denunciations", schema = "public")
public class Denunciation {

    @Id
    @Column(name = "denunciation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "denunciation_date", nullable = false)
    private LocalDateTime denunciationDate;

    @Column(name = "details", nullable = false)
    private String details;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "denunciations", fetch = FetchType.EAGER)
    private List<Animal> animals;
}
