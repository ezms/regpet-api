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
@Table(name = "ngos", schema = "public")
public class Ngo implements IEntityDefault<UUID> {

    @Id
    @Column(name = "ngo_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;

    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;

    @Column(name = "trading_name", nullable = false, length = 100)
    private String tradingName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "ngo", fetch = FetchType.LAZY)
    private List<Employee> employees;

    @JsonIgnore
    @OneToMany(mappedBy = "ngo", fetch = FetchType.LAZY)
    private List<Volunteer> volunteers;
}
