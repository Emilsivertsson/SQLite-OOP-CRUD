import java.sql.*;

public class PersonHandler {
    Connection conn = null;

    //String dbname = "workers";

    public PersonHandler(String dbname) {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbname + ".db");
        } catch (SQLException e) {
            System.out.println("Något gick fel vid anslutningen");
        }
    }

    public void createTables() {
        String workers = "CREATE TABLE IF NOT EXISTS workers " +
                "(personId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "\n firstname VARCHAR (50)," +
                "\n lastname VARCHAR (50)," +
                "\n age INTEGER," +
                "\n workplaceId INTEGER," +
                "\n carId INTEGER," +
                "FOREIGN KEY(workplaceId) REFERENCES workplace(workplaceId)," +
                "FOREIGN KEY (carId) REFERENCES car(carId))";

        String workplace = "CREATE TABLE IF NOT EXISTS workplace " +
                "(workplaceId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "\n name VARCHAR (50)," +
                "\n phoneNr INTEGER )";

        String car = "CREATE TABLE IF NOT EXISTS car " +
                "(carId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "\n makeName VARCHAR (50)," +
                "\n modelName INTEGER)";

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(workers);
            stmt.execute(workplace);
            stmt.execute(car);
            System.out.println("the tables are created");

        } catch (SQLException e) {
            System.out.println("Det gick fel vid skapandet av tabellerna");
            System.out.println(e.getMessage());
        }
    }


    public void createPerson(Person person) {

        try {
            PreparedStatement prpstmt = conn.prepareStatement("INSERT INTO workers(firstname, lastname, age, workplaceid, carid) VALUES (?,?,?,?,?)");
            prpstmt.setString(1, person.getFirstName());
            prpstmt.setString(2, person.getLastName());
            prpstmt.setInt(3, person.getAge());
            prpstmt.setInt(4, person.getWorkplaceId());
            prpstmt.setInt(5, person.getCarId());
            prpstmt.executeUpdate();
            System.out.println("Personen är skapad");

        } catch (SQLException e) {
            System.out.println("Det gick fel vid skapandet av personer.");
            System.out.println(e.getMessage());
        }

    }

    public void createCar(Car car) {

        try {
            PreparedStatement prpstmt = conn.prepareStatement("INSERT INTO car(makeName, modelName) VALUES (?,?)");
            prpstmt.setString(1, car.getMakeName());
            prpstmt.setString(2, car.getModelName());
            prpstmt.executeUpdate();
            System.out.println("Bilen är skapad");

        } catch (SQLException e) {
            System.out.println("Det gick fel vid skapandet av bilen.");
            System.out.println(e.getMessage());
        }
    }

    public void createWorkplace(Workplace workplace) {

        try {
            PreparedStatement prpstmt = conn.prepareStatement("INSERT INTO workplace(name, phoneNr) VALUES (?,?)");
            prpstmt.setString(1, workplace.getName());
            prpstmt.setInt(2, workplace.getPhoneNr());
            prpstmt.executeUpdate();
            System.out.println("Workplace är skapad");

        } catch (SQLException e) {
            System.out.println("Det gick fel vid skapandet av workplace.");
            System.out.println(e.getMessage());
        }
    }


    public void updateWorker(Person person) {
        String sql = "UPDATE workers SET firstName = ?, lastName = ?, age = ?, workplaceId = ?, carid = ? WHERE personId = ?";

        try {
            PreparedStatement prstmt = conn.prepareStatement(sql);
            prstmt.setString(1, person.getFirstName());
            prstmt.setString(2, person.getLastName());
            prstmt.setInt(3, person.getAge());
            prstmt.setInt(4, person.getWorkplaceId());
            prstmt.setInt(5, person.getCarId());
            prstmt.setInt(6, person.getId());
            prstmt.executeUpdate();
            System.out.println("Personen är uppdaterad");

        } catch (SQLException e) {
            System.out.println("personen kunde inte uppdateras");
            System.out.println(e.getMessage());
        }
    }

    public void updateWorkplace(Workplace workplace) {
        String sql = "UPDATE workplace SET name = ?, phoneNr = ? WHERE workplaceId = ? ";

        try {
            PreparedStatement prstmt = conn.prepareStatement(sql);
            prstmt.setString(1, workplace.getName());
            prstmt.setInt(2, workplace.getPhoneNr());
            prstmt.setInt(3, workplace.getId());
            prstmt.executeUpdate();
            System.out.println("Arbetsplatsen har uppdaterats");

        } catch (SQLException e) {
            System.out.println("Arbetsplatsen kunde inte uppdateras");
            System.out.println(e.getMessage());
        }
    }

    public void updatecar(Car car) {
        String sql = "UPDATE car SET makeName = ?, modelName = ? WHERE carId = ?";

        try {
            PreparedStatement prstmt = conn.prepareStatement(sql);
            prstmt.setString(1, car.getMakeName());
            prstmt.setString(2, car.getModelName());
            prstmt.setInt(3, car.getId());
            prstmt.executeUpdate();
            System.out.println("Bilen har uppdaterats");
        } catch (SQLException e) {
            System.out.println("Bilen kunde inte uppdateras");
            System.out.println(e.getMessage());
        }
    }

    public void listWorkers() {
        String sql = "SELECT workers.personId AS id, workers.firstname || ' ' || workers.lastname AS name, workers.age, car.makeName, workplace.name AS workplace from workers\n" +
                "INNER JOIN car ON workers.carId = car.carId\n" +
                "INNER JOIN workplace ON workers.workplaceId = workplace.workplaceId";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("--Id -- Name--Car--Workplace--");
            while (rs.next()) {
                System.out.println(rs.getInt("Id") + " " +
                        rs.getString("Name") + " " +
                        rs.getInt("Age") + " " +
                        rs.getString("Makename") + " " +
                        rs.getString("Workplace"));
            }
        } catch (SQLException e) {
            System.out.println("something went wrong when fetching data");
            System.out.println(e.getMessage());
        }
    }

    public void listcars() {
        String sql = "SELECT carid, makeName, modelName FROM car";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("--carId -- makeName--modelName--");
            while (rs.next()) {
                System.out.println(rs.getInt("carId") + " " +
                        rs.getString("makeName") + " " +
                        rs.getString("modelName"));
            }
        } catch (SQLException e) {
            System.out.println("something went wrong when fetching data");
            System.out.println(e.getMessage());
        }
    }

    public Person fetchWorker(int personid) {
        Person person = null;
        String sql = "SELECT personId, firstname, lastname, age, workplaceId, carid FROM workers\n WHERE personId = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, personid);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                person = new Person(
                        rs.getInt("personId"),
                        rs.getString("Firstname"),
                        rs.getString("Lastname"),
                        rs.getInt("Age"),
                        rs.getInt("WorkplaceId"),
                        rs.getInt("CarId"));
            } else {
                System.out.println("There is no person with that id");
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong when fetching data");
            System.out.println(e.getMessage());
        }


        return person;
    }

    public Car fetchCar(int carId) {
        Car car = null;
        String sql = "SELECT carid, makeName, modelName FROM car WHERE carId = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, carId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                car = new Car(
                        rs.getInt("carId"),
                        rs.getString("makeName"),
                        rs.getString("modelName"));
            } else {
                System.out.println("There is no car with that id");
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong when fetching data");
            System.out.println(e.getMessage());
        }

        return car;
    }

    public void listWorkplaces() {
        String sql = "SELECT workplaceId, name, phoneNr FROM workplace";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("--WorkplaceId -- Name--PhoneNr--");
            while (rs.next()) {
                System.out.println(rs.getInt("WorkplaceId") + " " +
                        rs.getString("Name") + " " +
                        rs.getInt("PhoneNr"));
            }
        } catch (SQLException e) {
            System.out.println("something went wrong when fetching data");
            System.out.println(e.getMessage());
        }
    }

    public Workplace fetchWorkplace(int workplaceId) {
        Workplace workplace = null;
        String sql = "SELECT workplaceId, name, phoneNr FROM workplace WHERE workplaceId = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, workplaceId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                workplace = new Workplace(
                        rs.getInt("workplaceId"),
                        rs.getString("name"),
                        rs.getInt("phoneNr"));
            } else {
                System.out.println("There is no workplace with that id");
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong when fetching data");
            System.out.println(e.getMessage());
        }

        return workplace;
    }

    public void deleteWorker(int personId) {
        String sql = "DELETE FROM workers WHERE personId = ?";

        try {
            PreparedStatement prstmt = conn.prepareStatement(sql);
            prstmt.setInt(1, personId);
            prstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Something went wrong when deleting worker");
        }
    }

    public void deleteCar(int carId) {
        String sql = "DELETE FROM car WHERE carId = ?";

        try {
            PreparedStatement prstmt = conn.prepareStatement(sql);
            prstmt.setInt(1, carId);
            prstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Something went wrong when deleting car");
        }
    }

    public void deleteWorkplace(int workplaceId) {
        String sql = "DELETE FROM workplace WHERE workplaceId = ?";

        try {
            PreparedStatement prstmt = conn.prepareStatement(sql);
            prstmt.setInt(1, workplaceId);
            prstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Something went wrong when deleting workplace");
        }
    }
}





