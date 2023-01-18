package com.regpet.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "volunteers", schema = "public")
public class Volunteer {

    @Id
    @Column(name = "volunteer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false, length = 70)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ngo_id")
    private Ngo ngo;
}
