public class Workplace {
    private int workplaceId;

    public String name;

    public int phoneNr;

    //construktor to edit existing workplace
    public Workplace (int workplaceId, String name, int phoneNr){
        this.workplaceId = workplaceId;
        this.name = name;
        this.phoneNr = phoneNr;
    }

    //construktor to make a new workplace
    public Workplace (String name, int phoneNr){
        this.workplaceId = 0;
        this.name = name;
        this.phoneNr = phoneNr;
    }

    public int getId() {
        return workplaceId;
    }

    public void setId(int id) {
        if (this.workplaceId == 0){
            this.workplaceId = id;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(int phoneNr) {
        this.phoneNr = phoneNr;
    }

    @Override
    public String toString() {
        return "Workplace{" +
                "id=" + workplaceId +
                ", name='" + name + '\'' +
                ", phoneNr=" + phoneNr +
                '}';
    }
}
