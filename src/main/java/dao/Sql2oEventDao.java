package dao;
import models.Event;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import javax.annotation.PostConstruct;
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

    @Override
    public void updateEvent(int id, String name, String description, String speaker, String room){
        String sql = "UPDATE events SET name = :name, description = :description, speaker = :speaker, room = :room WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .addParameter("description", description)
                    .addParameter("speaker", speaker)
                    .addParameter("room", room)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id){
        String sql = "DELETE from events WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllEvents() {
        String sql = "DELETE from events";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}