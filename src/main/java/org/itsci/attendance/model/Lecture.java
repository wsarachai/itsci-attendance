package org.itsci.attendance.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lectures")
public class Lecture extends Section {
    @ManyToOne
    private Room room;
    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn (name = "lecture_id")
    private List<Attendance> attendances;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }
}
