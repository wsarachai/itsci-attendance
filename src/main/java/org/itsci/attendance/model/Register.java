package org.itsci.attendance.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "registers")
public class Register {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="student_id")
    private Student student;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="section_id")
    private Lecture lecture;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="laboratory_id")
    private Laboratory laboratory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
