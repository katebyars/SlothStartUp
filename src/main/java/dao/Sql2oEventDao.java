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

    @Override
    public void add (Event event) {
        String sql = "INSERT INTO events (name, description, speaker, room) VALUES (:name, :description, :speaker, :room)"; //raw sql
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql)
                    .addParameter("name", event.getName())
                    .addParameter("description", event.getDescription())
                    .addParameter("speaker", event.getSpeaker())
                    .addParameter("room", event.getRoom())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("DESCRIPTION", "description")
                    .addColumnMapping("SPEAKER","speaker")
                    .addColumnMapping("ROOM","room")
                    .executeUpdate()
                    .getKey();
            event.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }
    @Override
    public List<Event> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM events")
                    .executeAndFetch(Event.class);
        }
    }
    @Override
    public Event findById(int id){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM events WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Event.class); //fetch an individual item
        }
    }

}
    //
//    //list all events
//    List<Event> getAll();
//
    //find an event by its id
//    @Override
//    public Event findById(int id) {
//        try(Connection con = sql2o.open()) {
//            return con.createQuery("SELECT * FROM events WHERE id = :id")
//                    .addParameter("id", id)
//                    .executeAndFetchFirst(Event.class);
//        }
//    }
//
//    //update event details
//    void update(int id, String name);
//
//    //delete an event
//    void deleteById(int id);
//
//    //delete all events
//    void clearAllEvents();
//}
