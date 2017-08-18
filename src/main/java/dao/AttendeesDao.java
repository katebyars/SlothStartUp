package dao;
import java.util.List;
import models.Attendees;

public interface AttendeesDao {

    void add (Attendees attendee);

    List<Attendees> getAll();

    Attendees findById(int id);

    void updateAttendee(int id, String name, String homeCity, int age, String foodPreference, int eventId);
//
//    //delete an attendee
//    void deleteById(int id);
//
//    //delete all attendees
//    void clearAllAttendees();

}
