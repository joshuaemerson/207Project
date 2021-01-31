package saver;

import event.EventManager;
import message.MessageManager;
import request.RequestManager;
import user.UserManager;

import java.sql.*;


public class Connector {
    Connection conn;
    Writing writer;
    Reader reader;

    /**
     * Creates a connector between the database and this program
     */
    public Connector() {
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/conference",
                    "root", "1257g08#E");
            writer = new Writing(conn);
            reader = new Reader(conn);
        } catch (SQLException e) {
            System.out.println("An error occured while connecting MySQL database");
            e.printStackTrace();
        }
    }

    /**
     * Checks to see if there are files that already exist or not
     * @return Whether or not files exist
     * @throws SQLException If it can't connect
     */
    public boolean determineExisting() throws SQLException {
        PreparedStatement exists = conn.prepareStatement("SELECT * from users");
        ResultSet rs = exists.executeQuery();
        return rs.next();
    }


    /**
     * Closes the connection between the database and this program
     * @throws SQLException If it can't connect
     */
    public void end() throws SQLException {
        conn.close();
    }

    /**
     * Clears all the tables in the database
     * @throws SQLException If it can't connect
     */
    public void clearEverything() throws SQLException {
        writer.clearEverything();
    }

    /**
     * Saves the usermanager information in the database
     * @param userManager The userManager we want to save
     * @throws SQLException If it can't connect
     */
    public void saveUserManager(UserManager userManager) throws SQLException{
        writer.saveUserManager(userManager);
    }

    /**
     * Saves the eventManager information in the database
     * @param eventManager The eventManager we want to save
     * @throws SQLException If it can't connect
     */
    public void saveEventManager(EventManager eventManager) throws SQLException {
        writer.saveEventManager(eventManager);
    }


    /**
     * Saves the messageManager information in the database
     * @param messageManager The userManager we want to save
     * @throws SQLException If it can't connect
     */
    public void saveMessageManager(MessageManager messageManager) throws SQLException{
        writer.saveMessageManager(messageManager);
    }

    /**
     * Saves the requestManager information in the database
     * @param requestManager The userManager we want to save
     * @throws SQLException If it can't connect
     */
    public void saveRequestManager(RequestManager requestManager) throws SQLException{
        writer.saveRequestManager(requestManager);

    }

    /**
     * Reads in the userManager information from the database
     * @throws SQLException If it can't connect
     * @return The newly read in userManager
     */
    public UserManager readInUsers() throws SQLException {
        return reader.readInUsers();
    }

    /**
     * Reads in the messageManager information from the database
     * @throws SQLException If it can't connect
     * @return The newly read in messageManager
     */
    public MessageManager readInMessages() throws SQLException {
        return reader.readInMessages();
    }

    /**
     * Reads in the requestManager information from the database
     * @throws SQLException If it can't connect
     * @return The newly read in requestManager
     */
    public RequestManager readInRequests() throws SQLException{
        return reader.readInRequests();
    }

    /**
     * Reads in the eventManager information from the database
     * @throws SQLException If it can't connect
     * @return The newly read in eventManager
     */
    public EventManager readInEvents() throws SQLException{
       return reader.readInEvents();
    }

}
