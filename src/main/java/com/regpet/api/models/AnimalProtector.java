package com.regpet.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "animal_protectors", schema = "public")
public class AnimalProtector {

    @Id
    @Column(name = "animal_protector_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "organization_name", nullable = false, length = 100)
    private String organizationName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
