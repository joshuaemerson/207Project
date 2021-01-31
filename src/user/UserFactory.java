package user;

// Factory design pattern from https://www.tutorialspoint.com/design_pattern/factory_pattern.htm

import user.attendee.Attendee;
import user.organizer.Organizer;
import user.speaker.Speaker;
import user.vip.Vip;

/**
 * This class is an implementation of the Factory design pattern. It creates all types of new Users.
 */
public class UserFactory {

    /**
     * This method creates a new User object with the given information.
     * @param name This parameter refers to the name of the user.
     * @param address This parameter refers to the address of the user.
     * @param email This parameter refers to the email of the user.
     * @param username This parameter refers to the username of the user.
     * @param password This parameter refers to the password of the user.
     * @param userType This parameter refers to the type of user this person is.
     * @param company This parameter refers to the company of the user
     * @param bio This parameter refers to the bio of the user
     * @return Returns the new User object,
     */
    public User createNewUser(String name, String address, String email,
                              String username, String password, String userType, String company, String bio) {
        if (userType == null) {
            return null;
        }
        if (userType.equalsIgnoreCase("ORGANIZER")) {
            return new Organizer(name, address, email, username, password, company, bio);

        } else if (userType.equalsIgnoreCase("ATTENDEE")) {
            return new Attendee(name, address, email, username, password, company, bio);

        } else if (userType.equalsIgnoreCase("SPEAKER")) {
            return new Speaker(name, address, email, username, password, company, bio);
        }
        else if (userType.equalsIgnoreCase("VIP")) {
            return new Vip(name, address, email, username, password, company, bio);
        }
        return null;
    }
}
