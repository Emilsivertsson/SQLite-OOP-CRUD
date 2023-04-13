import java.util.Scanner;

public class Logic {

    Scanner scanner = new Scanner(System.in);
    Meny meny = new Meny();

    PersonHandler personHandler = new PersonHandler("Workers");

    public void start() {
        personHandler.createTableWorkers();
        personHandler.createTableWorkplace();
        personHandler.createTableCar();

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
                //updatePerson();
                break;
            case 5:
                //editCar();
                break;
            case 6:
                //editWorkplace();
                break;
            case 7:
                //deletePerson();
                break;
            case 8:
                //deleteCar();
                break;
            case 9:
                //deleteWorkplace();
                break;
            case 10:
                //showAllPersons();
                break;
            case 11:
                //showAllCars();
                break;
            case 12:
               //showAllWorkplaces();
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
            default:
                meny.invalidInput();
                break;
        }
    }

    private void updatePerson() {

    }

    private void addWorkplace() {
        try {
            System.out.println("Enter name: ");
            String name = scanner.next();
            System.out.println("Enter phone number: ");
            int phoneNr = scanner.nextInt();
            personHandler.createWorkplace(new Workplace(name, phoneNr));
            System.out.println("Workplace added");
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
