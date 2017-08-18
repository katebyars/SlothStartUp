package dao;
import models.Attendees;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import models.Event;
import dao.Sql2oEventDao;
import dao.EventDao;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import static org.junit.Assert.*;

public class Sql2oAttendeesDaoTest {

    private Sql2oAttendeesDao attendeesDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        attendeesDao = new Sql2oAttendeesDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    //helper
    public Attendees testAttendee (){
        Attendees testAttendee = new Attendees("Flash", "SlowVille", 123, "Bugaterian", 3);
        return testAttendee;
    }

    @Test
    public void attendeesAddToTheDao_True () throws Exception {
        Attendees testAttendee = testAttendee();
        attendeesDao.add(testAttendee);
        assertEquals(1, attendeesDao.getAll().size());
    }
    @Test
    public void findAnAttendeeById_True() throws Exception {
        Attendees attendee = testAttendee();
        attendeesDao.add(attendee);
        Attendees newAttendee = testAttendee();
        attendeesDao.add(newAttendee);
        Attendees otherAttendee = testAttendee();
        attendeesDao.add(otherAttendee);
        assertEquals(newAttendee, attendeesDao.findById(2));
    }

}