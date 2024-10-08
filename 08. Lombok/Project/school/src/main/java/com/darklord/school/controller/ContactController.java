package com.darklord.school.controller;

import com.darklord.school.model.Contact;
import com.darklord.school.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
    private final ContactService contactService;
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContatct(){
        return "contact";
    }
    
/*    
    @PostMapping (value = "/saveMsg")
    public ModelAndView saveMessage(
            @RequestParam String name, @RequestParam String mobileNum,
            @RequestParam String email, @RequestParam String subject, @RequestParam String message
    ){
        log.info("Name : " + name);
        log.info("Mobile Number : " + mobileNum);
        log.info("Email Address : "+ email);
        log.info("Subject : "+ subject);
        log.info("Message : "+ message);
        return new ModelAndView("redirect:/contact");
    }
*/
    @PostMapping (value = "/saveMsg")
    public ModelAndView saveMessage(Contact contact){
        contactService.saveMessageDetails(contact);
        return new ModelAndView("redirect:/contact");
    }
}
