package com.darklord.school.service;


import com.darklord.school.constants.DarklordSchoolConstants;
import com.darklord.school.model.Contact;
import com.darklord.school.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        Contact saveContact = contactRepository.save(contact);
        if(null != saveContact && saveContact.getContactId() > 0)
            isSaved = true;
        return isSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        return contactRepository.findByStatus(
                DarklordSchoolConstants.OPEN,pageable);
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(String.valueOf(contactId));
        contact.ifPresent(contact1 -> {
            contact1.setStatus("CLOSE");
        });
        Contact result = contactRepository.save(contact.get());
        if(null != result && result.getUpdatedBy() != null)
            isUpdated= true;
        return  isUpdated;
    }



}
