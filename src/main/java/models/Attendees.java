package models;
import java.util.ArrayList;
import java.util.List;

public class Attendees {
    private String name;
    private String homeCity;
    private int age;
    private String foodPreference;
    private int id;
    private List<Event> EventIds;


    public Attendees(String name) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFoodPreference() {
        return foodPreference;
    }

    public void setFoodPreference(String foodPreference) {
        this.foodPreference = foodPreference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Event> getEventIds() {
        return EventIds;
    }

    public void setEventIds(List<Event> eventIds) {
        EventIds = eventIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendees attendees = (Attendees) o;

        if (age != attendees.age) return false;
        if (id != attendees.id) return false;
        if (!name.equals(attendees.name)) return false;
        if (homeCity != null ? !homeCity.equals(attendees.homeCity) : attendees.homeCity != null) return false;
        if (foodPreference != null ? !foodPreference.equals(attendees.foodPreference) : attendees.foodPreference != null)
            return false;
        return EventIds != null ? EventIds.equals(attendees.EventIds) : attendees.EventIds == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (homeCity != null ? homeCity.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (foodPreference != null ? foodPreference.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (EventIds != null ? EventIds.hashCode() : 0);
        return result;
    }
}