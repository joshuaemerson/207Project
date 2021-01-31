package user.vip;

import user.attendee.Attendee;

/**
 * This class contains all of the characteristics and actions of an attendee. They
 * have a name, address, email, username, and password. Attendees can attend events
 * and sign up for events.
 */
public class Vip extends Attendee {


    /**
     * This method constructs a new vip object with an empty list of attendingEvents.
     * @param name Refers to the name of the vip.
     * @param address Refers to the address of the vip.
     * @param email Refers to the email of the vip.
     * @param userName Refers to the username of the vip.
     * @param password Refers to the password of the vip.
     * @param company Refers to the company of the vip.
     * @param bio Refers to the bio of the vip.
     */
    public Vip(String name, String address, String email, String userName, String password, String company, String bio) {
        super(name, address, email, userName, password, company, bio);
    }

    // Getter Methods

    /**
     * This method gets the type of user this person is.
     * @return Returns a string representation of "vip".
     */
    public String getUserType(){
        return "vip";
    }

}


