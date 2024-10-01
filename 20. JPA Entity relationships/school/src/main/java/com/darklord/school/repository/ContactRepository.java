package com.darklord.school.repository;

import com.darklord.school.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {

    List<Contact> findByStatus(String status);

}
