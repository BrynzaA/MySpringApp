package com.simbirsoft.springcourse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "staff")
@ApiModel(description = "Details about staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The unique id of staff")
    private Long id;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ApiModelProperty(notes = "The unique id of employee")
    private Employee employee;

    @Column(name = "position")
    @ApiModelProperty(notes = "The staff's position")
    private String position;

    @Column(name = "salary")
    @ApiModelProperty(notes = "The staff's salary")
    private Float salary;
}
