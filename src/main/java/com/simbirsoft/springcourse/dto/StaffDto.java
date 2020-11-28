package com.simbirsoft.springcourse.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StaffDto {

    @ApiModelProperty(notes = "The unique id of employee")
    private Long employeeId;
    @ApiModelProperty(notes = "The staff's position")
    private String position;
    @ApiModelProperty(notes = "The staff's salary")
    private Float salary;
}
