package dao;
import java.util.List;
import models.Event;

public interface EventDao {

    //create an event
    void add (Event event);

    //list all events
    List<Event> getAll();

    //find an event by its id
    Event findById(int id);

    //update event details
    void updateEvent(int id, String name, String description, String speaker, String room);

    //delete an event
    void deleteById(int id);

    //delete all events
    void clearAllEvents();

}
