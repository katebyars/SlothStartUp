package dao;
import models.Attendees;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oAttendeesDao implements AttendeesDao {

    private final Sql2o sql2o;

    public Sql2oAttendeesDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Attendees attendee) {
        String sql = "INSERT INTO attendees (name, homecity, age, foodpreference, eventid) VALUES (:name, :homecity, :age, :foodpreference, :eventid)";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql)
                    .addParameter("name", attendee.getName())
                    .addParameter("homecity", attendee.getHomeCity())
                    .addParameter("age", attendee.getAge())
                    .addParameter("foodpreference", attendee.getFoodPreference())
                    .addParameter("eventid", attendee.getEventId())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("HOMECITY", "homecity")
                    .addColumnMapping("AGE", "age")
                    .addColumnMapping("FOODPREFERENCE", "foodpreference")
                    .addColumnMapping("EVENTID", "eventid")
                    .executeUpdate()
                    .getKey();
            attendee.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Attendees> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM attendees")
                    .executeAndFetch(Attendees.class);
        }
    }
    @Override
    public Attendees findById(int id){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM attendees WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Attendees.class); //fetch an individual item
        }
    }

    @Override
    public void updateAttendee (int id, String name, String homecity, int age, String foodpreference, int eventid){
        String sql = "UPDATE attendees SET name = :name, homecity = :homecity, age = :age, foodpreference = :foodpreference, eventid = :eventid WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("homecity", homecity)
                    .addParameter("age", age)
                    .addParameter("foodpreference", foodpreference)
                    .addParameter("eventid", eventid)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id){
        String sql = "DELETE from attendees WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

//    @Override
//    public void clearAllEvents() {
//        String sql = "DELETE from events";
//        try (Connection con = sql2o.open()) {
//            con.createQuery(sql)
//                    .executeUpdate();
//        } catch (Sql2oException ex){
//            System.out.println(ex);
//        }
//    }
}