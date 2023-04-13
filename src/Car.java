public class Car {

    private int carId;

    public String makeName;

    public String modelName;

    //Construktor to update existing car
    public Car(int id, String makeName, String modelName){
        this.carId = id;
        this.makeName = makeName;
        this.modelName = modelName;
    }

    //construktor to create new car
    public Car(String makeName, String modelName){
        this.carId = 0;
        this.makeName = makeName;
        this.modelName = modelName;
    }

    public int getId() {
        return carId;
    }

    public void setId(int id) {
        if(this.carId == 0){
            this.carId = id;
        }
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + carId +
                ", makeName='" + makeName + '\'' +
                ", modelName='" + modelName + '\'' +
                '}';
    }
}
