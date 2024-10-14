package com.darklord.school.rest;

import com.darklord.school.constants.DarklordSchoolConstants;
import com.darklord.school.model.Contact;
import com.darklord.school.model.Response;
import com.darklord.school.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/api/contact")
@CrossOrigin(origins = "*")
public class ContactRestController {
    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/getMessagesByStatus")
    public List<Contact> getMessagesByStatus(@RequestParam(name="status") String status){
        return contactRepository.findByStatus(status);
    }
    @PostMapping("/getMessagesByStatus")
    public List<Contact> getMessagesByStatus(@RequestBody Contact contact){
        if(null != contact && null != contact.getStatus()){
            return contactRepository.findByStatus(contact.getStatus());
        }
        else{
            return List.of();
        }
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<com.darklord.school.model.Response> saveMsg(@RequestHeader("invocationFrom") String invocationForm, @Valid @RequestBody Contact contact){
        log.info(String.format("Header invocationForm =%s",invocationForm));
        contactRepository.save(contact);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message saved successfully");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMsgSaved","true")
                .body(response);
    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity){
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key, value) -> {
            log.info(String.format(
                    "Header '%s' = '%s'",key,value.stream().collect(Collectors.joining("|"))
            ));
        });
        Contact contact = requestEntity.getBody();
        assert contact != null;
        contactRepository.deleteById(String.valueOf(contact.getContactId()));
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message successfully deleted");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contactReq){
        Response response = new Response();
        Optional<Contact> contact = contactRepository.findById(String.valueOf(contactReq.getContactId()));
        if(contact.isPresent()){
            contact.get().setStatus(DarklordSchoolConstants.CLOSE);
            contactRepository.save(contact.get());
        }
        else{
            response.setStatusCode("400");
            response.setStatusMsg("Invalid Contact ID recived");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }
        response.setStatusCode("200");
        response.setStatusMsg("Message successfully closed");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}


