package com.simbirsoft.springcourse.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto {

    @ApiModelProperty(notes = "The employee's name")
    private String name;
    @ApiModelProperty(notes = "The employee's surname")
    private String surname;
    @ApiModelProperty(notes = "The employee's date of birth")
    private LocalDate dateOfBirth;
    @ApiModelProperty(notes = "The employee's date of employment")
    private LocalDate dateOfEmployment;
}
