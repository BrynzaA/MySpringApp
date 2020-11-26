package com.simbirsoft.springcourse.model;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PermissionType name;

    public Permission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Permission(PermissionType name){
        this.name = name;
    }

    public PermissionType getName() {
        return name;
    }

    public void setName(PermissionType name) {
        this.name = name;
    }
}
