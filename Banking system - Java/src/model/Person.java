package model;

import java.util.Objects;

public class Person implements java.io.Serializable, Observer {
    public int personId;
    public String name;
    public String phone;

    public Person(int personId, String name, String phone) {
        this.personId = personId;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public void notifyEvent() {
        System.out.println("There was an event on "+ name +"'s accounts!");
    }
}
