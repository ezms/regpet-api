package com.regpet.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ngos", schema = "public")
public class Ngo {

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "ngo", fetch = FetchType.LAZY)
    private List<Employee> employees;

    @OneToMany(mappedBy = "ngo", fetch = FetchType.LAZY)
    private List<Volunteer> volunteers;
}
