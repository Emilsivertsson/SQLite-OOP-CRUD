public class Person {
    private int personId; // primarykey

    private String firstName;

    private String lastName;

    private int age;

    private int workplace;

    private int car;

    //construktor to update a existing person
    public Person (int personId, String firstName, String lastName, int age, int workplace, int car){
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.workplace = workplace;
        this.car = car;
    }

    //construktor to create a new person
    public Person (String firstName, String lastName, int age, int workplace, int car){
        this.personId = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.workplace = workplace;
        this.car = car;
    }

    public int getId() {
        return personId;
    }

    public void setId(int id) {
        if(this.personId == 0){
            this.personId = id;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWorkplace() {
        return workplace;
    }

    public void setWorkplace(int workplace) {
        this.workplace = workplace;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    @Override
    public String toString(){
        return "Id: " + personId + "\n"+
                "Firstname: " + firstName + "\n"+
                "Lastname: " + lastName +  "\n"+
                "Workplace: " + workplace  +"\n"+
                "Car: " + car;
    }
}

