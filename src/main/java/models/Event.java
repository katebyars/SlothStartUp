package models;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private String name;
    private String description;
    private String speaker;
    private String room;
    private int id;
    private List<Attendees> attendees;


    public Event(String name, String description, String speaker, String room) {
        this.name = name;
        this.description = description;
        this.speaker = speaker;
        this.room = room;
        this.id = id;
        this.attendees = attendees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Attendees> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendees> attendees) {
        this.attendees = attendees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (!name.equals(event.name)) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        if (speaker != null ? !speaker.equals(event.speaker) : event.speaker != null) return false;
        if (room != null ? !room.equals(event.room) : event.room != null) return false;
        return attendees != null ? attendees.equals(event.attendees) : event.attendees == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (speaker != null ? speaker.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (attendees != null ? attendees.hashCode() : 0);
        return result;
    }
}
