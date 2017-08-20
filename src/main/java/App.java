import com.sun.org.apache.regexp.internal.RE;
import dao.AttendeesDao;
import dao.EventDao;
import dao.Sql2oAttendeesDao;
import dao.Sql2oEventDao;
import models.Attendees;
import models.Event;
import org.eclipse.jetty.http.HttpStatus;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;
import java.util.*;

public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");

        String connectionString = "jdbc:h2:~/codeschool.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oEventDao eventDao = new Sql2oEventDao(sql2o);
        Sql2oAttendeesDao attendeesDao = new Sql2oAttendeesDao(sql2o);


        //delete all events
        get("/events/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            eventDao.clearAllEvents();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //delete all attendees
        get("/attendees/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            attendeesDao.clearAllAttendees();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete individual event
        get("/events/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfEvent = Integer.parseInt(req.params("id"));
            eventDao.findById(idOfEvent);
            eventDao.deleteById(idOfEvent);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete individual attendee
        get("/attendees/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idofAttendee = Integer.parseInt(req.params("id"));
            attendeesDao.findById(idofAttendee);
            attendeesDao.deleteById(idofAttendee);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        // show index
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "about.hbs");
        }, new HandlebarsTemplateEngine());

        //show a new event form
        get("/events/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        //process a new event form
        post("/events/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String description = req.queryParams("description");
            String speaker = req.queryParams("speaker");
            String room = req.queryParams("room");
            Event event = new Event(name, description, speaker, room);
            eventDao.add(event);
            model.put("event", event);
            res.redirect("/admin");
            return null;
        }, new HandlebarsTemplateEngine());

        //show all events and attendees
        get("/admin", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Event> allEvents = eventDao.getAll();
            model.put("allEvents", allEvents);
            List<Attendees> allAttendees = attendeesDao.getAll();
            model.put("allAttendees", allAttendees);
            return new ModelAndView(model, "admin.hbs");
        }, new HandlebarsTemplateEngine());

        //show a form to add attendees
        get("/events/:id/attendees", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfEvent = Integer.parseInt(req.params("id"));
            Event addAttendees = eventDao.findById(idOfEvent);
            model.put("addAttendees", addAttendees);
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        //process a form to add attendees
        post("/events/:id/attendees", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String homeCity = req.queryParams("homecity");
            int age = Integer.parseInt(req.queryParams("age"));
            String food = req.queryParams("food");
            int idOfEvent = Integer.parseInt(req.params("id"));
            Attendees attendee = new Attendees(name, homeCity, age, food, idOfEvent);
            attendeesDao.add(attendee);
            res.redirect("/admin");
            return null;
        }, new HandlebarsTemplateEngine());

        //show an individual event
        get("/events/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfEvent = Integer.parseInt(req.params("id"));
            Event findEvent = eventDao.findById(idOfEvent);
            model.put("event", findEvent);
            return new ModelAndView(model, "event-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //show an individual attendee
        get("/attendees/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAttendee = Integer.parseInt(req.params("id"));
            Attendees findAttendee = attendeesDao.findById(idOfAttendee);
            model.put("attendee", findAttendee);
            return new ModelAndView(model, "attendee-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //show a form to update an event
        get("/events/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfEvent = Integer.parseInt(req.params("id"));
            Event editEvent = eventDao.findById(idOfEvent);
            model.put("editEvent", editEvent);
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        //process a form to update an event
        post("/events/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            String newDescription = req.queryParams("description");
            String newSpeaker = req.queryParams("speaker");
            String newRoom = req.queryParams("room");
            int idOfEvent = Integer.parseInt(req.params("id"));
            Event editEvent = eventDao.findById(idOfEvent);
            eventDao.updateEvent(idOfEvent, newName, newDescription, newSpeaker, newRoom);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //show a form to update an attendee
        get("/attendees/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAttendee = Integer.parseInt(req.params("id"));
            Attendees editAttendee = attendeesDao.findById(idOfAttendee);
            model.put("editAttendee", editAttendee);
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        //process a form to update an attendee
        post("/attendees/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            int newAge = Integer.parseInt(req.queryParams("age"));
            String newDiet = req.queryParams("foodpreference");
            String newCity = req.queryParams("homecity");
            int idOfAttendee = Integer.parseInt(req.params("id"));
            int eventId = attendeesDao.findById(idOfAttendee).getEventId();
            Attendees editAttendee = attendeesDao.findById(idOfAttendee);
            attendeesDao.updateAttendee(idOfAttendee, newName, newCity, newAge, newDiet, eventId);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
