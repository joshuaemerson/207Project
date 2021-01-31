package saver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Testing {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/conference",
                "root", ".....")) {
            boolean isValid = conn.isValid(0);
            System.out.println("Do we have a valid db connection? = " + isValid);
//                Object [][] students = {{"first", 3.58, LocalDateTime.now()}, {"second", 3.00, LocalDateTime.now()},
//                        {"third", 4.00, LocalDateTime.now()}, {"fourth", 1.6, LocalDateTime.now()}};
//                Writing nameWriter = new Writing(conn);
//                for (Object[] student: students){
//                    nameWriter.insertStudent((String)student[0], (double)student[1], (LocalDateTime) student[2]);
//                }
//                String[] names = {"first", "second", "third", "fourth", "fifth"};
//                Testing.Writing nameWriter = new Testing.Writing(conn);
//                nameWriter.insertNames(names);
//                PreparedStatement statement = conn.prepareStatement("insert into users (name) values (?)");
//                int insertedRows = 0;
//                for (int i = 0; i < names.length; i++){
//                    statement.setString(1, names[i]);
//                    insertedRows += statement.executeUpdate();
//                }
//                System.out.println("I just inserted " + insertedRows + " users");

//                PreparedStatement selectStatement = conn.prepareStatement("select * from users where name =  ?");
//
//                selectStatement.setString(1, "John");
//                ResultSet rs = selectStatement.executeQuery();
//                while (rs.next()) {
//                    String firstName = rs.getString("name");
//                    System.out.println("firstName = " + firstName);
//                }
        }catch (SQLException e){
            System.out.println("An error occured while connecting MySQL database");
            e.printStackTrace();
        }
    }
}


