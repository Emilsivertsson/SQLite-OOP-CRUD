import java.util.Scanner;

public class Logic {

    Scanner scanner = new Scanner(System.in);
    Meny meny = new Meny();

    PersonHandler personHandler = new PersonHandler("Workers");

    public void start() {
        personHandler.createTables();

        //populateDatabase();

        while(true){
            meny.mainMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    addCar();
                    break;
                case 3:
                    addWorkplace();
                    break;
                case 4:
                    personHandler.listWorkers();
                    updateWorker();
                    break;
                case 5:
                    personHandler.listcars();
                    editCar();
                    break;
                case 6:
                    personHandler.listWorkplaces();
                    editWorkplace();
                    break;
                case 7:
                    personHandler.listWorkers();
                    deletePerson();
                    break;
                case 8:
                    personHandler.listcars();
                    deleteCar();
                    break;
                case 9:
                    personHandler.listWorkplaces();
                    deleteWorkplace();
                    break;
                case 10:
                    personHandler.listWorkers();
                    break;
                case 11:
                    personHandler.listcars();
                    break;
                case 12:
                    personHandler.listWorkplaces();
                    break;
                case 13:
                    //showAllPersonsByCar();
                    break;
                case 14:
                    //showAllPersonsByWorkplace();
                    break;
                case 15:
                    //showAllPersonsByCarAndWorkplace();
                    break;
                case 16:
                    exit();
                    break;
                case 17:
                    showWorker();
                    break;
                default:
                    meny.invalidInput();
                    break;
            }
        }

    }

    private void deleteWorkplace() {
        System.out.println("Enter id of workplace you want to delete: ");
        int workplaceId = scanner.nextInt();
        personHandler.deleteWorkplace(workplaceId);
        System.out.println("Workplace " + workplaceId +" deleted");
    }

    private void deleteCar() {
System.out.println("Enter id of car you want to delete: ");
        int carId = scanner.nextInt();
        personHandler.deleteCar(carId);
        System.out.println("Car " + carId +" deleted");
    }

    private void deletePerson() {
        System.out.println("Enter id of person you want to delete: ");
        int personId = scanner.nextInt();
        personHandler.deleteWorker(personId);
        System.out.println("Person " + personId +" deleted");
    }

    private void editWorkplace() {
        System.out.println("Enter id of workplace you want to update: ");
        int workplaceId = scanner.nextInt();
        Workplace w = personHandler.fetchWorkplace(workplaceId);
        scanner.nextLine();

        System.out.println("Enter new name: ");
        w.setName(scanner.nextLine());
        scanner.nextLine();

        System.out.println("Enter new phone number: ");
        w.setPhoneNr(scanner.nextInt());

        personHandler.updateWorkplace(w);
        System.out.println("Workplace " + workplaceId +" updated");
    }

    private void editCar() {
        System.out.println("Enter id of car you want to update: ");
        int carId = scanner.nextInt();
        Car c = personHandler.fetchCar(carId);
        scanner.nextLine();

        System.out.println("Enter new brand: ");
        c.setMakeName(scanner.nextLine());
        scanner.nextLine();

        System.out.println("Enter new model: ");
        c.setModelName(scanner.nextLine());

        personHandler.updatecar(c);
        System.out.println("Car " + carId +" updated");
    }

    private void populateDatabase() {
        personHandler.createPerson(new Person("johan", "Skywalker", 19, 1, 1));
        personHandler.createPerson(new Person("Leif", "Organa", 19, 4, 2));
        personHandler.createPerson(new Person("Hansan", "Solo", 55, 1, 3));
        personHandler.createPerson(new Person("Kalle", "Wookie", 19, 3, 2));
        personHandler.createPerson(new Person("Morgana", "Svensson", 19, 2, 4));

        personHandler.createWorkplace(new Workplace("McDonalds", 8002));
        personHandler.createWorkplace(new Workplace("Burger King", 8003));
        personHandler.createWorkplace(new Workplace("KFC", 8004));
        personHandler.createWorkplace(new Workplace("Subway", 8005));

        personHandler.createCar(new Car("Volvo", "V70"));
        personHandler.createCar(new Car("Tesla", "Model S"));
        personHandler.createCar(new Car("BMW", "M5"));
        personHandler.createCar(new Car("Audi", "A6"));
        personHandler.createCar(new Car("Mercedes", "C63"));

    }

    private void showWorker() {

        System.out.println("whats the personId of the worker that you want to see?");
        int personId = scanner.nextInt();
        Person p = personHandler.fetchWorker(personId);
        System.out.println(p);

    }

    private void updateWorker() {

        System.out.println("Enter id of person you want to update: ");
        int personId = scanner.nextInt();
        Person p = personHandler.fetchWorker(personId);

        System.out.println("Enter new first name: ");
        p.setFirstName(scanner.nextLine());
        System.out.println("Enter new last name: ");
        p.setLastName(scanner.nextLine());
        System.out.println("Enter new age: ");
        p.setAge(scanner.nextInt());
        System.out.println("Enter new workplace: ");
        p.setWorkplaceId(scanner.nextInt());
        System.out.println("Enter new car: ");
        String car = scanner.next();
        personHandler.updateWorker(p);
        System.out.println("Worker " + personId +" updated");
    }


    private void addWorkplace() {
        try {
            System.out.println("Enter name: ");
            String name = scanner.next();
            System.out.println("Enter phone number: ");
            int phoneNr = scanner.nextInt();
            personHandler.createWorkplace(new Workplace(name, phoneNr));
            System.out.println("Workplace "+ name + " added");
        } catch (Exception e) {
            System.out.println("Invalid input, please try again");
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
            personHandler.createCar(new Car(brand, model));
            System.out.println("Car added");
        } catch (Exception e) {
            System.out.println("Invalid input, please try again");
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
            personHandler.createPerson(new Person(firstName, lastName, age, workplaceId, carId));
            System.out.println("Person added");
        } catch (Exception e) {
            System.out.println("Invalid input, please try again");
        }
    }
}
