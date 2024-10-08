package com.darklord.school.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(schema = "class")
public class Classes extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classId;

    @NotBlank(message = "Name must not be blank")
    @Size(message = "Name must be at least 3 character long")
    private String name;
    @OneToMany(mappedBy = "classes", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Person.class)
    private Set<Person> persons;

}
