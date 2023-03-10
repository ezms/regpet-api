package com.regpet.api.models;

import com.regpet.api.interfaces.IEntityDefaults;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "addresses", schema = "public")
public class Address implements IEntityDefaults<UUID> {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "zip_code", nullable = false, length = 8)
    private String zipCode;

    @Column(name = "state", nullable = false, length = 20)
    private String state;

    @Column(name = "city", nullable = false, length = 30)
    private String city;

    @Column(name = "district", nullable = false, length = 90)
    private String district;

    @Column(name = "public_place", nullable = false, length = 150)
    private String publicPlace;

    @Column(name = "number", nullable = false, length = 10)
    private String number;

    @Column(name = "complement", length = 100)
    private String complement;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_addresses",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "addresses_rescues",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "rescue_id")
    )
    private List<Rescue> rescues;
}
