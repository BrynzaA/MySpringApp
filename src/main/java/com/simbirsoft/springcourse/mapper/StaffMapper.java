package com.simbirsoft.springcourse.mapper;

import com.simbirsoft.springcourse.dto.StaffDto;
import com.simbirsoft.springcourse.model.Staff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    Staff toStaff(StaffDto staffDto);
}
