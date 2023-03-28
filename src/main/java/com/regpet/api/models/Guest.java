package com.regpet.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.regpet.api.interfaces.IEntityDefault;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "guests", schema = "public")
public class Guest implements IEntityDefault<UUID> {

    @Id
    @Column(name = "guest_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY)
    private List<GuestContact> guestContacts;
}
