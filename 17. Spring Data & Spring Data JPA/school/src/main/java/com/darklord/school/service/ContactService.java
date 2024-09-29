package com.darklord.school.service;


import com.darklord.school.model.Contact;
import com.darklord.school.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/*
@Slf4j, is a Lombok-provided annotation that will automatically generate an SLF4J
Logger static property in the class at compilation time.
* */
@Slf4j
@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = true;
        contact.setStatus("OPEN");
        contact.setCreatedBy("ANONYMOUS");
        contact.setCreatedAt(LocalDateTime.now());
        Contact saveContact = contactRepository.save(contact);
        if(null != saveContact && saveContact.getContactId() > 0)
            isSaved = true;
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findByStatus("OPEN");
        return  contactMsgs;
    }

    public boolean updateMsgStatus(int contactId, String updatedBy){
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(String.valueOf(contactId));
        contact.ifPresent(contact1 -> {
            contact1.setStatus("CLOSE");
            contact1.setUpdatedBy(updatedBy);
            contact1.setUpdatedAt(LocalDateTime.now());
        });
        Contact result = contactRepository.save(contact.get());
        if(null != result && result.getUpdatedBy() != null)
            isUpdated= true;
        return  isUpdated;
    }

}
