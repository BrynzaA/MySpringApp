package com.simbirsoft.springcourse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "employee")
@ApiModel(description = "Details about employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The unique id of an employee")
    private Long id;

    @Column(name = "name")
    @ApiModelProperty(notes = "The employee's name")
    private String name;

    @Column(name = "surname")
    @ApiModelProperty(notes = "The employee's surname")
    private String surname;

    @Column(name = "date_of_birth")
    @ApiModelProperty(notes = "The employee's date of birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_employment")
    @ApiModelProperty(notes = "The employee's date of employment")
    private LocalDate dateOfEmployment;
}
