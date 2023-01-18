package com.regpet.api.models;

import com.regpet.api.interfaces.IEntityDefaults;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "employees", schema = "public")
public class Employee implements IEntityDefaults<UUID> {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false, length = 70)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ngo_id")
    private Ngo ngo;
}
