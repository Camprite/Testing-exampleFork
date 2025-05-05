package com.learning.courses.service;

import com.learning.courses.dto.ContactDTO;
import com.learning.courses.dto.CourseDTO;
import com.learning.courses.dto.CreateContactDTO;
import com.learning.courses.dto.CreateCourseDTO;
import com.learning.courses.exception.*;
import com.learning.courses.mapper.ContactMapper;
import com.learning.courses.mapper.CourseMapper;
import com.learning.courses.model.Contact;
import com.learning.courses.model.Course;
import com.learning.courses.model.Person;
import com.learning.courses.model.enums.CourseStatus;
import com.learning.courses.model.enums.Role;
import com.learning.courses.repository.ContactRepository;
import com.learning.courses.repository.CourseRepository;
import com.learning.courses.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final ContactMapper contactMapper;
    private final PersonService personService;
    private final ContactRepository contactRepository;
    private final PersonRepository personRepository;

    @Transactional
    public Long createContact(CreateContactDTO createContactDTO) {
        final Contact contact = contactMapper.toEntity(createContactDTO);

        return contactRepository.save(contact).getId();
    }

    @Transactional(readOnly = true)
    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll().stream()
                .map(contactMapper::toDTO2).toList();
    }
    @Transactional(readOnly = true)
    public List<Contact> getPersonAllContacts(Long id) {
        Person person = personRepository.getPersonById(id);
        return person.contacts;
    }
    @Transactional
    public Long addContactToPerson(Long id, CreateContactDTO createContactDTO) {

        Long contactId = createContact(createContactDTO);
        Contact contact = contactRepository.getContactsById(contactId);

        var person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Person.class.getSimpleName()));

        var contacts = person.getContacts();
        contacts.add(contact);
        person.setContacts(contacts);



        return  personRepository.save(person).getId();
    }
    @Transactional
    public Long removeContactFromPerson(Long id, Long contactId) {

        var person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Person.class.getSimpleName()));
        var contacts = person.contacts;
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getId().equals(contactId)) {
                iterator.remove();
            }
        }

        person.setContacts(contacts);
        personRepository.save(person);



        return  contactRepository.removeContactById(contactId);
    }


    @Transactional(readOnly = true)
    public Contact getContact(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Contact.class.getSimpleName()));
    }






}
