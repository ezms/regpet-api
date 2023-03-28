package com.regpet.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.regpet.api.interfaces.IEntityDefault;
import io.quarkus.security.jpa.Password;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
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

    @Email(message = "Invalid email address")
    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Password
    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "profile_photo")
    private byte[] profilePhoto;

    @Column(name = "is_active")
    private Boolean isActive;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<AnimalProtector> animalProtector;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Denunciation> denunciations;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Rescue> rescues;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Guest> guests;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Ngo> ngoList;

    @JsonIgnore
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Address> addresses;
}
