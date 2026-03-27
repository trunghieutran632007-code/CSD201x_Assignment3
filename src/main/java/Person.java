public class Person {
    String ID;
    String name;
    String birthplace;
    String dob;

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


    @Override

    public String toString() {

        return String.format("%-10s %-10s %-20s %-10s", ID, name, dob, birthplace);

    }

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
}
