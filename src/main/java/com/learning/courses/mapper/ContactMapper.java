package com.learning.courses.mapper;

import com.learning.courses.dto.ContactDTO;
import com.learning.courses.dto.CourseDTO;
import com.learning.courses.dto.CreateContactDTO;
import com.learning.courses.dto.CreateCourseDTO;
import com.learning.courses.model.Contact;
import com.learning.courses.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ContactMapper {


    @Mapping(target = "id", ignore = true)
    Contact toEntity(CreateContactDTO createContactDTO);


    CreateContactDTO toDTO(Contact contact);
    ContactDTO toDTO2(Contact contact);
    List<ContactDTO> toDTO(List<Contact> contactList);

}
