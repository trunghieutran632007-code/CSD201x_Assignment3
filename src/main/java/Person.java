/**
 * Represents a person entity with details such as ID, name, birthplace, and date of birth.
 */
public class Person {
    String ID;
    String name;
    String birthplace;
    String dob;

    /**
     * Initializes a new Person instance with default values.
     */
    public Person(){

    }

    /**
     * Constructor with parameters
     *
     * @param ID
     * @param name
     * @param birthplace
     * @param dob
     */
    public Person(String ID, String name, String birthplace, String dob) {
        this.ID = ID;
        this.name = name;
        this.birthplace = birthplace;
        this.dob = dob;
    }

    /**
     * Returns a formatted string representation of the Person.
     *
     * @return A formatted string containing the ID, name, date of birth, and birthplace.
     */
    @Override
    public String toString() {
        return String.format("%-10s %-10s %-20s %-10s", ID, name, dob, birthplace);
    }

    /**
     * Compares this Person to the specified object for equality based on their IDs.
     *
     * @param obj The object to compare this Person against.
     * @return true if the given object is a Person with the same ID, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Person) {
            Person person = (Person) obj;
            return ID.equals(person.ID);
        }
        return false;
    }

    /**
     * Returns a hash code value for this Person based on their ID.
     * Consistent with the {@link #equals(Object)} method — two Person
     * objects that are equal will return the same hash code.
     *
     * @return The hash code of this Person's ID.
     */
    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}