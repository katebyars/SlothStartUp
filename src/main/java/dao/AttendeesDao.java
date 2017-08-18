package dao;
import java.util.List;
import models.Attendees;

public interface AttendeesDao {

    //create an attendee
    void add (Attendees attendee);

//    //list all attendees
    List<Attendees> getAll();
//
//    //find an attendee by its id
//    Attendees findById(int id);
//
//    //update attendee details
//    void updateAttendee(int id, String name, String description, String speaker, String room);
//
//    //delete an attendee
//    void deleteById(int id);
//
//    //delete all attendees
//    void clearAllAttendees();

}
