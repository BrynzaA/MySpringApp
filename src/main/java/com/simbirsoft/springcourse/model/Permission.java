package com.simbirsoft.springcourse.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PermissionType name;

    public Permission() {
    }
}
