package org.itsci.attendance.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends User {
    private String code;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="student")
    private Set<Register> registers;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(Set<Register> registers) {
        this.registers = registers;
    }
}
