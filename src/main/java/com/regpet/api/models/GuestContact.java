package com.regpet.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "guest_contacts", schema = "public")
public class GuestContact {

    @Id
    @Column(name = "guest_contact_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "instagram_url")
    private String instagramUrl;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @Column(name = "twitter_url")
    private String twitterUrl;

    @Column(name = "cellphone_number", nullable = false, length = 20)
    private String cellphoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "guest_id")
    private Guest guest;
}
