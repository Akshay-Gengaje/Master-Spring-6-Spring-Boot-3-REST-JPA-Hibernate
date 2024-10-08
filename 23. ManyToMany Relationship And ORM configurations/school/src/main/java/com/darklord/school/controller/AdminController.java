package com.darklord.school.controller;

import com.darklord.school.model.Classes;
import com.darklord.school.model.Person;
import com.darklord.school.repository.ClassesRepository;
import com.darklord.school.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    ClassesRepository classesRepository;
    @Autowired
    PersonRepository personRepository;
    @RequestMapping(value = "/displayClasses", method = GET)
    public ModelAndView displayClasses(Model model){
        List<Classes> classes = classesRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("classes", classes);
        modelAndView.addObject("class", new Classes());
        return modelAndView;
    }
    @RequestMapping(value = "/addNewClass", method = POST)
    public ModelAndView addNewClass(Model model, @ModelAttribute("classes") Classes classes){
        classesRepository.save(classes);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return  modelAndView;
    }

    @RequestMapping(value = "/deleteClass", method = GET)
    public ModelAndView deleteClass(Model model, @RequestParam int id){
        Optional<Classes> classes = classesRepository.findById(id);
        for(Person person : classes.get().getPersons()){
            person.setClasses(null);
            personRepository.save(person);
        }
        classesRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

}