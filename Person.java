package medicationtracking;

/**
 * Person is the base class for Patient and Doctor.
 * It holds common identity attributes shared by all persons in the system.
 */
public abstract class Person {

    private int id;
    private String name;
    private int age;
    private String phoneNumber;

    /**
     * Creates a Person with the given details.
     *
     * @param id          unique identifier
     * @param name        full name
     * @param age         age in years
     * @param phoneNumber contact phone number
     */
    public Person(int id, String name, int age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // Getters

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPhoneNumber() { return phoneNumber; }

    // Setters

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Age: " + age + " | Phone: " + phoneNumber;
    }
}
