import java.sql.*;

public class PersonHandler {
    Connection conn = null;

    String dbname = "workers";

    public PersonHandler (String dbname){
        this.dbname = dbname;
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbname +".db");
        } catch (SQLException e){
            System.out.println("Något gick fel vid anslutningen");
        }
    }

    public void createTableWorkers(){
        String sql = "CREATE TABLE IF NOT EXISTS workers " +
                "(personId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "\n firstname VARCHAR (50)," +
                "\n lastname VARCHAR (50)," +
                "\n age INTEGER," +
                "\n workplaceId INTEGER," +
                "\n carId INTEGER," +
                "FOREIGN KEY(workplaceId) REFERENCES workplace(workplaceId)," +
                "FOREIGN KEY (carId) REFERENCES car(carId))";

        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("tabellen person är skapad");
        } catch (SQLException e){
            System.out.println("Det gick fel vid skapandet av Persontabellen");
            System.out.println(e.getMessage());
        }
    }

    public void createTableWorkplace(){
        String sql = "CREATE TABLE IF NOT EXISTS workplace " +
                "(workplaceId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "\n name VARCHAR (50)," +
                "\n phoneNr INTEGER )";

        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("tabellen workplace är skapad");
        } catch (SQLException e){
            System.out.println("Det gick fel vid skapandet av workplacetabellen");
            System.out.println(e.getMessage());
        }

    }

    public void createTableCar(){
        String sql = "CREATE TABLE IF NOT EXISTS car " +
                "(carId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "\n makeName VARCHAR (50)," +
                "\n modelName INTEGER)";

        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("tabellen car är skapad");
        } catch (SQLException e){
            System.out.println("Det gick fel vid skapandet av car-tabellen");
            System.out.println(e.getMessage());
        }

    }

    public void createPerson(Person person){

        try{
            PreparedStatement prpstmt = conn.prepareStatement("INSERT INTO workers(firstname, lastname, age, workplace, car) VALUES (?,?,?,?,?)");
            prpstmt.setString(1,person.getFirstName());
            prpstmt.setString(2, person.getLastName());
            prpstmt.setInt(3, person.getAge());
            prpstmt.setInt(4, person.getWorkplaceId());
            prpstmt.setInt(5, person.getCarId());
            prpstmt.executeUpdate();
            System.out.println("Personen är skapad");

        }catch (SQLException e){
            System.out.println("Det gick fel vid skapandet av personer.");
            System.out.println(e.getMessage());
        }

    }

    public void createCar(Car car){

        try{
            PreparedStatement prpstmt = conn.prepareStatement("INSERT INTO car(makeName, modelName) VALUES (?,?)");
            prpstmt.setString(1, car.getMakeName());
            prpstmt.setString(2, car.getModelName());
            prpstmt.executeUpdate();
            System.out.println("Bilen är skapad");

        }catch (SQLException e){
            System.out.println("Det gick fel vid skapandet av bilen.");
            System.out.println(e.getMessage());
        }
    }

    public void createWorkplace(Workplace workplace){

        try{
            PreparedStatement prpstmt = conn.prepareStatement("INSERT INTO workplace(name, phoneNr) VALUES (?,?)");
            prpstmt.setString(1, workplace.getName());
            prpstmt.setInt(2, workplace.getPhoneNr());
            prpstmt.executeUpdate();
            System.out.println("Workplace är skapad");

        }catch (SQLException e){
            System.out.println("Det gick fel vid skapandet av workplace.");
            System.out.println(e.getMessage());
        }
    }


    public void UpdateWorker (Person person){
        String sql = "UPDATE workers SET firstName = ?, lastName = ?, age = ?, workplace = ?, car = ?";

        try{
            PreparedStatement prstmt = conn.prepareStatement(sql);
            prstmt.setString(1, person.getFirstName());
            prstmt.setString(2, person.getLastName());
            prstmt.setInt(3, person.getAge());
            prstmt.setInt(4, person.getWorkplaceId());
            prstmt.setInt(5, person.getCarId());
            prstmt.executeUpdate();
            System.out.println("Personen är uppdaterad");

        }catch (SQLException e){
            System.out.println("personen kunde inte uppdateras");
            System.out.println(e.getMessage());
        }
    }

    public void updateWorkplace(Workplace workplace){
        String sql = "UPDATE workplace SET name = ?, phoneNr = ?";

        try{
            PreparedStatement prstmt = conn.prepareStatement(sql);
            prstmt.setString(1, workplace.getName());
            prstmt.setInt(2, workplace.getPhoneNr());
            prstmt.executeUpdate();
            System.out.println("Arbetsplatsen har uppdaterats");

        }catch (SQLException e){
            System.out.println("Arbetsplatsen kunde inte uppdateras");
            System.out.println(e.getMessage());
        }
    }

    public void updatecar (Car car){
        String sql = "UPDATE car SET makeName = ?, modelName = ?";

        try{
            PreparedStatement prstmt = conn.prepareStatement(sql);
            prstmt.setString(1, car.getMakeName());
            prstmt.setString(2, car.getModelName());
            prstmt.executeUpdate();
            System.out.println("Bilen har uppdaterats");
        }catch (SQLException e){
            System.out.println("Bilen kunde inte uppdateras");
            System.out.println(e.getMessage());
        }
    }




}
