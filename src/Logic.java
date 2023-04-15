
import java.util.Scanner;

public class Logic {

    Scanner scanner = new Scanner(System.in);
    Meny meny = new Meny();

    DBHandler DBHandler = new DBHandler("Workers");

    public void start() {
        boolean running = true;
        DBHandler.createTables();

        //populateDatabase();

        while(running){
            meny.mainMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addPerson();
                case 2 -> addCar();
                case 3 -> addWorkplace();
                case 4 -> {
                    DBHandler.listWorkers();
                    updateWorker();
                }
                case 5 -> {
                    DBHandler.listcars();
                    editCar();
                }
                case 6 -> {
                    DBHandler.listWorkplaces();
                    editWorkplace();
                }
                case 7 -> {
                    DBHandler.listWorkers();
                    deletePerson();
                }
                case 8 -> {
                    DBHandler.listcars();
                    deleteCar();
                }
                case 9 -> {
                    DBHandler.listWorkplaces();
                    deleteWorkplace();
                }
                case 10 -> DBHandler.listWorkers();
                case 11 -> DBHandler.listcars();
                case 12 -> DBHandler.listWorkplaces();
                case 13 -> {
                    DBHandler.listcars();
                    showAllPersonsByCar();
                }
                case 14 -> {
                    DBHandler.listWorkplaces();
                    showAllPersonsByWorkplace();
                }
                case 15 -> showAllPersonsByCarAndWorkplace();
                case 16 -> showWorker();
                case 17 -> {
                    exit();
                    running = false;
                }
                default -> meny.invalidInput();
            }
        }

    }

    private void showAllPersonsByCarAndWorkplace() {
        try{
            DBHandler.listcars();
            System.out.println("Enter id of car: ");
            int carId = scanner.nextInt();
            DBHandler.listWorkplaces();
            System.out.println("Enter id of workplace: ");
            int workplaceId = scanner.nextInt();
            DBHandler.listWorkersByCarAndWorkplace(carId, workplaceId);
        }catch (Exception e){
            System.out.println("Invalid input");
            System.out.println(e.getMessage());
        }
    }

    private void showAllPersonsByWorkplace() {
        try{
            System.out.println("Enter id of workplace: ");
            int workplaceId = scanner.nextInt();
            DBHandler.listWorkersByWorkplace(workplaceId);
        }catch (Exception e){
            System.out.println("Invalid input");
            System.out.println(e.getMessage());
        }
    }

    private void showAllPersonsByCar() {
        try{
            System.out.println("Enter id of car: ");
            int carId = scanner.nextInt();
            DBHandler.listWorkersByCar(carId);
    }catch (Exception e){
            System.out.println("Invalid input");
            System.out.println(e.getMessage());
        }
    }

    private void deleteWorkplace() {
        try{
            System.out.println("Enter id of workplace you want to delete: ");
            int workplaceId = scanner.nextInt();
            DBHandler.deleteWorkplace(workplaceId);
            System.out.println("Workplace " + workplaceId +" deleted");
        }catch (Exception e){
            System.out.println("Invalid input");
            System.out.println(e.getMessage());
        }

    }

    private void deleteCar() {
        try{
            System.out.println("Enter id of car you want to delete: ");
            int carId = scanner.nextInt();
            DBHandler.deleteCar(carId);
            System.out.println("Car " + carId +" deleted");
        }catch (Exception e){
            System.out.println("Invalid input");
            System.out.println(e.getMessage());
        }

    }

    private void deletePerson() {
        try{
            System.out.println("Enter id of person you want to delete: ");
            int personId = scanner.nextInt();
            DBHandler.deleteWorker(personId);
            System.out.println("Person " + personId +" deleted");
        }catch (Exception e){
            System.out.println("Invalid input");
            System.out.println(e.getMessage());
        }

    }

    private void editWorkplace() {
        try{
            System.out.println("Enter id of workplace you want to update: ");
            int workplaceId = scanner.nextInt();
            Workplace w = DBHandler.fetchWorkplace(workplaceId);
            scanner.nextLine();

            System.out.println("Enter new workplace-name: ");
            w.setName(scanner.next());
            scanner.nextLine();

            System.out.println("Enter new phone number: ");
            w.setPhoneNr(scanner.nextInt());

            DBHandler.updateWorkplace(w);
            System.out.println("Workplace " + workplaceId +" updated");
        }catch (Exception e){
            System.out.println("Invalid input");
            System.out.println(e.getMessage());
        }
    }

    private void editCar() {
        try{
            System.out.println("Enter id of car you want to update: ");
            int carId = scanner.nextInt();
            Car c = DBHandler.fetchCar(carId);
            scanner.nextLine();

            System.out.println("Enter new brand: ");
            c.setMakeName(scanner.next());
            scanner.nextLine();

            System.out.println("Enter new model: ");
            c.setModelName(scanner.next());

            DBHandler.updatecar(c);
            System.out.println("Car " + carId +" updated");
        } catch (Exception e){
            System.out.println("Invalid input");
            System.out.println(e.getMessage());
        }
    }



    private void showWorker() {
        try {
            System.out.println("whats the personId of the worker that you want to see?");
            int personId = scanner.nextInt();
            Person p = DBHandler.fetchWorker(personId);
            System.out.println(p);

        } catch (Exception e) {
            System.out.println("Invalid input");
            System.out.println(e.getMessage());
        }

    }

    private void updateWorker() {
        try{
            System.out.println("Enter id of person you want to update: ");
            int personId = scanner.nextInt();
            Person p = DBHandler.fetchWorker(personId);

            System.out.println("Enter new first name: ");
            p.setFirstName(scanner.next());
            System.out.println("Enter new last name: ");
            p.setLastName(scanner.next());
            System.out.println("Enter new age: ");
            p.setAge(scanner.nextInt());
            System.out.println("Enter new workplace: ");
            p.setWorkplaceId(scanner.nextInt());
            System.out.println("Enter new car: ");
            p.setCarId(scanner.nextInt());
            DBHandler.updateWorker(p);
            System.out.println("Worker " + personId +" updated");
        }catch (Exception e){
            System.out.println("Invalid input");
            System.out.println(e.getMessage());
        }

    }


    private void addWorkplace() {
        try {
            System.out.println("Enter name: ");
            String name = scanner.next();
            System.out.println("Enter phone number: ");
            int phoneNr = scanner.nextInt();
            DBHandler.createWorkplace(new Workplace(name, phoneNr));
            System.out.println("Workplace "+ name + " added");
        } catch (Exception e) {
            System.out.println("Invalid input, please try again");
            System.out.println(e.getMessage());
        }
    }

    private void exit() {
        System.out.println("Exiting program");
        System.exit(0);
    }

    private void addCar() {
        try {
            System.out.println("Enter brand: ");
            String brand = scanner.next();
            System.out.println("Enter model: ");
            String model = scanner.next();
            DBHandler.createCar(new Car(brand, model));
            System.out.println("Car added");
        } catch (Exception e) {
            System.out.println("Invalid input, please try again");
            System.out.println(e.getMessage());
        }
    }

    private void addPerson() {
        try {
            System.out.println("Enter first name: ");
            String firstName = scanner.next();
            System.out.println("Enter last name: ");
            String lastName = scanner.next();
            System.out.println("Enter age: ");
            int age = scanner.nextInt();
            System.out.println("Enter car id: ");
            int carId = scanner.nextInt();
            System.out.println("Enter workplace id: ");
            int workplaceId = scanner.nextInt();
            DBHandler.createPerson(new Person(firstName, lastName, age, workplaceId, carId));
            System.out.println("Person added");
        } catch (Exception e) {
            System.out.println("Invalid input, please try again");
            System.out.println(e.getMessage());
        }
    }

    private void populateDatabase() {
        DBHandler.createPerson(new Person("johan", "Skywalker", 19, 1, 1));
        DBHandler.createPerson(new Person("Leif", "Organa", 19, 4, 2));
        DBHandler.createPerson(new Person("Hansan", "Solo", 55, 1, 3));
        DBHandler.createPerson(new Person("Kalle", "Wookie", 19, 3, 2));
        DBHandler.createPerson(new Person("Morgana", "Svensson", 19, 2, 4));

        DBHandler.createWorkplace(new Workplace("McDonalds", 8002));
        DBHandler.createWorkplace(new Workplace("Burger King", 8003));
        DBHandler.createWorkplace(new Workplace("KFC", 8004));
        DBHandler.createWorkplace(new Workplace("Subway", 8005));

        DBHandler.createCar(new Car("Volvo", "V70"));
        DBHandler.createCar(new Car("Tesla", "Model S"));
        DBHandler.createCar(new Car("BMW", "M5"));
        DBHandler.createCar(new Car("Audi", "A6"));
        DBHandler.createCar(new Car("Mercedes", "C63"));

    }
}
