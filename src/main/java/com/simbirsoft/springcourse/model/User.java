package com.simbirsoft.springcourse.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The unique id of user")
    private Long id;

    @NonNull
    @ApiModelProperty(notes = "Username")
    @Column(nullable = false, unique = true)
    private String username;

    @NonNull
    @ApiModelProperty(notes = "Password")
    private String password;

    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable (name = "users_authority",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = { @JoinColumn(name = "authority_id")})
    private Set<Authority> authorities = new HashSet<>();
}
