package com.learning.courses.repository;


import com.learning.courses.model.Contact;
import com.learning.courses.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Contact getContactsById(Long contactId);

    Long removeContactById(Long contactId);
}
