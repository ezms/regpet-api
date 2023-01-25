package com.regpet.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.regpet.api.interfaces.IEntityDefault;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "addresses", schema = "public")
public class Address implements IEntityDefault<UUID> {

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

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_addresses"
            , joinColumns = @JoinColumn(name = "address_id")
            , inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "addresses_rescues"
            , joinColumns = @JoinColumn(name = "address_id")
            , inverseJoinColumns = @JoinColumn(name = "rescue_id")
    )
    private List<Rescue> rescues;

    public void addUser(User user) {
        if (user != null) {
            if (!getUsers().contains(user)) {
                getUsers().add(user);
            }

            if (!user.getAddresses().contains(this)) {
                user.getAddresses().add(this);
            }
        }
    }
}
