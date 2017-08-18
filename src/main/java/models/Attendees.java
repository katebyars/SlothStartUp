package models;
import java.util.ArrayList;
import java.util.List;

public class Attendees {
    private String name;
    private String homeCity;
    private int age;
    private String foodPreference;
    private int id;
    private int eventId;

    public Attendees(String name, String homeCity, int age, String foodPreference, int eventId) {
        this.name = name;
        this.homeCity = homeCity;
        this.age = age;
        this.foodPreference = foodPreference;
        this.eventId = eventId;
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

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendees attendees = (Attendees) o;

        if (age != attendees.age) return false;
        if (id != attendees.id) return false;
        if (eventId != attendees.eventId) return false;
        if (!name.equals(attendees.name)) return false;
        if (!homeCity.equals(attendees.homeCity)) return false;
        return foodPreference.equals(attendees.foodPreference);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + homeCity.hashCode();
        result = 31 * result + age;
        result = 31 * result + foodPreference.hashCode();
        result = 31 * result + id;
        result = 31 * result + eventId;
        return result;
    }
}