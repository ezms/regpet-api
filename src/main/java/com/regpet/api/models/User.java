package com.regpet.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.regpet.api.interfaces.IEntityDefault;
import io.quarkus.security.jpa.Password;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "public")
public class User implements IEntityDefault<UUID> {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "bio")
    private String bio;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Password
    @Column(name = "password")
    private String password;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "profile_photo")
    private byte[] profilePhoto;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<AnimalProtector> animalProtector;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Denunciation> denunciations;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Rescue> rescues;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Guest> guests;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Ngo> ngoList;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Address> addresses;
}
