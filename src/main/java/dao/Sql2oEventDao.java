package dao;
import models.Event;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class Sql2oEventDao implements EventDao {

    private final Sql2o sql2o;

    public Sql2oEventDao (Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //create an event
    void add (Event event);
    //
//    //list all events
//    List<Event> getAll();
//
    //find an event by its id
    @Override
    public Event findById(int id) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM events WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Event.class);
        }
    }
//
//    //update event details
//    void update(int id, String name);
//
//    //delete an event
//    void deleteById(int id);
//
//    //delete all events
//    void clearAllEvents();
}
