package com.darklord.school.controller;

import com.darklord.school.model.Classes;
import com.darklord.school.model.Courses;
import com.darklord.school.model.Person;
import com.darklord.school.repository.ClassesRepository;
import com.darklord.school.repository.CourseRepository;
import com.darklord.school.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    CourseRepository courseRepository;
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


    @GetMapping("displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession httpSession, @RequestParam(value = "error", required = false) String error){
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("student.html");
        Optional<Classes> classes = classesRepository.findById(classId);
        log.info(""+classes.get());
        httpSession.setAttribute("class",classes.get());
        modelAndView.addObject("class", classes.get());
        modelAndView.addObject("person", new Person());
        if(error !=null){
            errorMessage = "Invalid Email entered!";
            modelAndView.addObject("errorMessage",errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView();
        Classes classes = (Classes) httpSession.getAttribute("class");
        System.out.println(person);
        Person personEntity = personRepository.readByEmail(person.getEmail());
        log.info("person : "+ personEntity);
        if(personEntity == null || !(personEntity.getPersonId() > 0) ){
            modelAndView.setViewName("redirect:/admin/displayStudent?classId="+classes.getClassId()+"&error=true");
            return modelAndView;
        }
        personEntity.setClasses(classes);
        personRepository.save(personEntity);
        classes.getPersons().add(personEntity);
        classesRepository.save(classes);
        modelAndView.setViewName("redirect:/admin/displayStudent?classId="+classes.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
        Classes darkClass = (Classes) session.getAttribute("class");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setClasses(null);
        darkClass.getPersons().remove(person.get());
        Classes classes = classesRepository.save(darkClass);
        session.setAttribute("class",classes);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId="+darkClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model){
        //Dynamic sorting -
        List<Courses> courses = courseRepository.findAll(Sort.by("name").ascending());

        //Static Sorting -
                // List<Courses>courses = courseRepository.findByOrderByNameDesc();
        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        modelAndView.addObject("courses", courses);
        model.addAttribute("course", new Courses());
        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model, @ModelAttribute("courses") Courses courses){
        ModelAndView modelAndView = new ModelAndView();
        courseRepository.save(courses);
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(Model model, @RequestParam int id, HttpSession session, @RequestParam(value = "error", required = false) String error){
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("course_students.html");
        Optional<Courses> courses = courseRepository.findById(id);
        modelAndView.addObject("courses", courses.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("courses", courses.get());
        if(error != null){
            errorMessage = "Invalid Email Address";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(Model model, @ModelAttribute("person") Person person, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Courses courses = (Courses) session.getAttribute("courses");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if(personEntity == null || !(personEntity.getPersonId() > 0) ){
            modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId()+"&error=true");
            return modelAndView;
        }
        personEntity.getCourses().add(courses);
        courses.getPersons().add(personEntity);
        personRepository.save(personEntity);
        session.setAttribute("courses", courses);
        modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId());
        return modelAndView;
    }

//    @GetMapping("/deleteStudentFromCourse")
//    public ModelAndView deleteStudentFromCourse(Model model, @RequestParam int personId, HttpSession session){
//        Courses courses = (Courses) session.getAttribute("courses");
//        Optional<Person> person = personRepository.findById(personId);
//        person.get().getCourses().remove(courses);
//        courses.getPersons().remove(person);
//        personRepository.save(person.get());
//        session.setAttribute("courses",courses);
//        ModelAndView modelAndView = new
//                ModelAndView("redirect:/admin/viewStudents?id="+courses.getCourseId());
//        return modelAndView;
//    }

    @GetMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse(Model model, @RequestParam int personId, HttpSession session){
        Courses courses = (Courses) session.getAttribute("courses");
        Optional<Person> personOptional = personRepository.findById(personId);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            // Remove the course from the person's courses
            person.getCourses().remove(courses);
            // Remove the person from the course's persons
            courses.getPersons().remove(person);
            // Save both person and courses to ensure changes are persisted
            personRepository.save(person);
            session.setAttribute("courses", courses);
        }

        return new ModelAndView("redirect:/admin/viewStudents?id=" + courses.getCourseId());
    }
}