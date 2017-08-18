package dao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models.Event;
import dao.Sql2oEventDao;
import dao.EventDao;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import static org.junit.Assert.*;

public class Sql2oEventDaoTest {

    private Sql2oEventDao eventDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        eventDao = new Sql2oEventDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    //helper
    public  Event testEvent (){
        Event testEvent = new Event("New Event", "A description", "Tim FerriSloth", "Super Slow Ballroom");
        return testEvent;
    }

    @Test
    public void eventIsAddedToDAO_True () {
        Event testEvent = testEvent();
        eventDao.add(testEvent);
        assertEquals(1, eventDao.getAll().size());

    }
    @Test
    public void getAllEvents_True() {
        Event testEvent = testEvent();
        Event testEvent2 = testEvent();
        Event testEvent3 = testEvent();
        eventDao.add(testEvent);
        eventDao.add(testEvent2);
        eventDao.add(testEvent3);
        assertEquals(3, eventDao.getAll().size());
    }

    @Test
    public void findAnEventById_True() throws Exception {
        Event testEvent = testEvent();
        Event testEvent2 = new Event("New Event 2", "A description", "Tim", "Super");
        Event testEvent3 = testEvent();
        eventDao.add(testEvent);
        eventDao.add(testEvent2);
        eventDao.add(testEvent3);
        assertEquals("New Event 2", eventDao.findById(2).getName());
    }

    @Test
    public void updateEventUpdatesName_True() {
        Event testEvent = testEvent();
        eventDao.add(testEvent);
        eventDao.updateEvent(1, "An Event", "A description", "Tim FerriSloth", "Super Slow Ballroom");
        assertEquals("An Event", eventDao.findById(1).getName());
    }
    @Test
    public void updateEventUpdatesNameWhenMoreThanOneEvent_True() {
        Event testEvent = testEvent();
        Event testEvent2 = testEvent();
        Event testEvent3 = testEvent();
        Event testEvent4 = testEvent();
        eventDao.add(testEvent);
        eventDao.add(testEvent2);
        eventDao.add(testEvent3);
        eventDao.add(testEvent4);
        eventDao.updateEvent(2, "An Event", "A description", "Tim FerriSloth", "Super Slow Ballroom");
        assertEquals("An Event", eventDao.findById(2).getName());
    }
    @Test
    public void deleteAnEventById_True() {
        Event testEvent = testEvent();
        Event testEvent2 = new Event("New Event 2", "A description", "Tim", "Super");
        Event testEvent3 = testEvent();
        eventDao.add(testEvent);
        eventDao.add(testEvent2);
        eventDao.add(testEvent3);
        eventDao.deleteById(2);
        assertEquals(2, eventDao.getAll().size());
    }
    @Test
    public void deletesCorrectEventWhenDeleteEventById_True() {
        Event testEvent = testEvent();
        Event testEvent2 = new Event("New Event 2", "A description", "Tim", "Super");
        Event testEvent3 = testEvent();
        eventDao.add(testEvent);
        eventDao.add(testEvent2);
        eventDao.add(testEvent3);
        eventDao.deleteById(2);
      assertEquals("New Event", eventDao.findById(3).getName());
    }

    @Test
    public void deleteAllEvents_True() {
        Event testEvent = testEvent();
        Event testEvent2 = new Event("New Event 2", "A description", "Tim", "Super");
        Event testEvent3 = testEvent();
        eventDao.add(testEvent);
        eventDao.add(testEvent2);
        eventDao.add(testEvent3);
        eventDao.clearAllEvents();
        assertEquals(0, eventDao.getAll().size());
    }
}