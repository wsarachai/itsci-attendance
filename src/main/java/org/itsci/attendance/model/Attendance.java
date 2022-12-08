package org.itsci.attendance.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "attendances")
public class Attendance {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    private int weekNo;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn (name = "attendance_id")
    private Set<StudentAttendance> studentAttendances;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    public Set<StudentAttendance> getStudentAttendances() {
        return studentAttendances;
    }

    public void setStudentAttendances(Set<StudentAttendance> studentAttendances) {
        this.studentAttendances = studentAttendances;
    }
}
