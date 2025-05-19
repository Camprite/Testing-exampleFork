package com.learning.courses.api.rest.controller;

import com.learning.courses.dto.ContactDTO;
import com.learning.courses.dto.CreateContactDTO;
import com.learning.courses.model.Contact;
import com.learning.courses.service.ContactService;
import com.learning.courses.service.CourseService;
import com.learning.courses.service.PersonCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "ContactController", description = "Contact API")
@RequestMapping("/api/contact")
public class ContactController {
    private final CourseService courseService;
    private final ContactService contactService;
    private final PersonCourseService personCourseService;

    @PostMapping
    @Operation(summary = "Create contact")
    public Long createCourse(@RequestBody CreateContactDTO createContactDTO) {
        return contactService.createContact(createContactDTO);
    }
    @GetMapping
    @Operation(summary = "Get all contacts")
    public List<ContactDTO> getAllContacts() {
        return contactService.getAllContacts();
    }
    @PutMapping("/{id}")
    @Operation(summary = "Add contact to person")
    public Long addContactToPerson(@RequestBody CreateContactDTO createContactDTO,@PathVariable  Long id) {
        return contactService.addContactToPerson(id,createContactDTO);
    }
    @DeleteMapping("/{id}/{contactId}")
    @Operation(summary = "Remove contact to person")
    public Long removeContactFromPerson(@PathVariable  Long id, @PathVariable Long contactId) {
        return contactService.removeContactFromPerson(id,contactId);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get person all contacts")
    public List<Contact> getPersonAllContacts(@PathVariable Long id) {
        return contactService.getPersonAllContacts(id);
    }
}
