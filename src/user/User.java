package user;

import java.io.Serializable;

/**
 * This class represents the properties and actions of all users. They all
 * have a name, address, email, username, and password.
 */
public abstract class User implements Comparable<User>, Serializable {

    private final String name;
    private final String address;
    private final String email;
    private final String username;
    private final String password;
    private String company;
    private String bio;

    /**
     * Constructs a user with a name, address, email, username, and passowrd.
     * @param name Refers to the name of the user.
     * @param address Refers to the address of the user.
     * @param email Refers to the email of the user.
     * @param username Refers to the username of the user.
     * @param password Refers to the password of the user.
     * @param company Refers to the company of the user.
     * @param bio Refers to the bio of the user.
     */

    public User(String name, String address, String email, String username, String password, String company, String bio){
        this.name = name;
        this.address = address;
        this.email = email;
        this.username = username;
        this.password = password;
        this.company = company;
        this.bio = bio;
    }

    // Getter Methods

    /**
     * This method gets the name of the user.
     * @return Returns the name of the user.
     */
    public String getName(){
        return this.name;
    }

    /**
     * This method gets the address of the user.
     * @return Returns the address of the user.
     */
    public String getAddress(){
        return this.address;
    }

    /**
     * This method gets the email of the user.
     * @return Returns the email of the user.
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * This method gets the username of the user.
     * @return Returns the username of the user.
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * This method gets the password of the user.
     * @return Returns the password of the user.
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * This methods gets the company a user is associated with.
     * @return The company of the user
     */
    public String getCompany(){
        return this.company;
    }

    /**
     * This methods get the bio of a user.
     * @return The bio of the user.
     */
    public String getBio(){
        return this.bio;
    }

    /**
     * This method returns the type of user this person is.
     * @return Will return a string representation of the user type.
     */
    public abstract String getUserType();

    // Setter Methods

    /**
     * This method sets the company of the user.
     * @param company The company of the user.
     */
    public void setCompany(String company){
        this.company = company;
    }

    /**
     * This method sets the bio of the user.
     * @param bio The bio of the user.
     */
    public void setBio(String bio){
        this.bio = bio;
    }

    // Other Methods

    /**
     * This method returns the amount of different characters between two usernames.
     * @param u Refers to a user.
     * @return Refers to the amount of unshared characters the two usernames have.
     */
    public int compareTo(User u) {
        return this.getUsername().compareTo(u.getUsername());
    }

    /**
     * This method formats a user object into a string.
     * @return Returns a string representation of the user's attributes.
     */
    public String toString() {
        return "Username: " + getUsername() + "| Name: " + getName() + "| Email: " + getEmail();
    }
}
