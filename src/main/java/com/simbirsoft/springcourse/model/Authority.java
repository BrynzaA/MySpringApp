package com.simbirsoft.springcourse.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthorityType name;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable (name = "authority_permission",
            joinColumns = {@JoinColumn(name = "authority_id")},
            inverseJoinColumns = { @JoinColumn(name = "permission_id")})
    private Set<Permission> permissions = new HashSet<>();

    public Authority() {
    }
}
