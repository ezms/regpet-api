package com.regpet.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "admins", schema = "public")
public class Admin {

    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
