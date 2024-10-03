package com.darklord.school.service;

import com.darklord.school.constants.DarklordSchoolConstants;
import com.darklord.school.model.Person;
import com.darklord.school.model.Roles;
import com.darklord.school.repository.PersonRepository;
import com.darklord.school.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

        @Autowired
        private PersonRepository personRepository;

        @Autowired
        private RolesRepository rolesRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

    public boolean createNewPerson(Person person) {
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(DarklordSchoolConstants.STUDENT_ROLE);
        person.setRoles(role);

        // Only hash the password after validation
        person.setPwd(passwordEncoder.encode(person.getPwd()));

        // Save the entity
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }
}
