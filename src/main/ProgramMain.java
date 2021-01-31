package main;

import java.sql.SQLException;

/**
 * Refers to the controller class that can run the entire program. It calls the MainController, when run, which calls
 * all of the other classes.
 */
public class ProgramMain {

    /**
     * This method is responsible for calling the appropriate methods in MainController.
     * @param args Refers to the string argument from the command line.
     * @throws SQLException Refers to the exception that is raised when there is a problem accessing the database.
     */
    public static void main(String [] args) throws SQLException {
        MainController run = new MainController();
        run.run();
    }
}
