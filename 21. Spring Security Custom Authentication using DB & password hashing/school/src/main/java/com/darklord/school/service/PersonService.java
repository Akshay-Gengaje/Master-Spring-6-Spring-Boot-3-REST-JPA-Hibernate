package com.darklord.school.service;

import com.darklord.school.constants.DarklordSchoolConstants;
import com.darklord.school.model.Person;
import com.darklord.school.model.Roles;
import com.darklord.school.repository.PersonRepository;
import com.darklord.school.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

        @Autowired
        private PersonRepository personRepository;

        @Autowired
        private RolesRepository rolesRepository;

        public boolean createNewPerson(Person person){
            boolean isSaved = false;
            Roles role = rolesRepository.getByRoleName(DarklordSchoolConstants.STUDENT_ROLE);
            person.setRoles(role);
            person = personRepository.save(person);
            if (null != person && person.getPersonId() > 0)
            {
                isSaved = true;
            }
            return isSaved;
        }
}
