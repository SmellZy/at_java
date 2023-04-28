package itstep.task_5;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class data implements Serializable, Comparable {
    private String time;
    private List<String> attendees;

    @JsonIgnore
    private String uuid = UUID.randomUUID().toString();

    public data() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "data{" +
                "time='" + time + '\'' +
                ", attendees=" + attendees +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        data o1 = (data)this;
        data o2 = (data)o;

        return o1.getTime().compareTo(o2.getTime());
    }
}
