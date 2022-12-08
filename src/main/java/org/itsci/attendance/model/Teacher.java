package org.itsci.attendance.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends User {
    private String prename;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "teachers")
    private Set<Section> sections;

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }
}
