package org.itsci.attendance.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends User {
    @OneToMany
    private Set<Section> sections;
}
