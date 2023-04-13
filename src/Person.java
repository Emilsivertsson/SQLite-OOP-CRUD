public class Person {
    private int personId; // primarykey

    private String firstName;

    private String lastName;

    private int age;

    private int workplaceId;

    private int carId;

    //construktor to update a existing person
    public Person (int personId, String firstName, String lastName, int age, int workplaceId, int carId){
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.workplaceId = workplaceId;
        this.carId = carId;
    }

    //construktor to create a new person
    public Person (String firstName, String lastName, int age, int workplace, int carId){
        this.personId = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.workplaceId = workplace;
        this.carId = carId;
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

    public int getWorkplaceId() {
        return workplaceId;
    }

    public void setWorkplaceId(int workplaceId) {
        this.workplaceId = workplaceId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Override
    public String toString(){
        return "Id: " + personId + "\n"+
                "Firstname: " + firstName + "\n"+
                "Lastname: " + lastName +  "\n"+
                "Workplace: " + workplaceId +"\n"+
                "Car: " + carId;
    }
}

