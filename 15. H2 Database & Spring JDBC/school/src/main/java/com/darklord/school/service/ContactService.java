package com.darklord.school.service;


import com.darklord.school.model.Contact;
import com.darklord.school.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        int result = contactRepository.saveContactMsg(contact);
        if(result > 0)
            isSaved = true;
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findMsgsWithOpenStatus("OPEN");
        return  contactMsgs;
    }

    public boolean updateMsgStatus(int contactId, String updatedBy){
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactId, updatedBy);
        if(result > 0)
            isUpdated= true;
        return  isUpdated;
    }

}
