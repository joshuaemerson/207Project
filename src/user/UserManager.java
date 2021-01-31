package user;

import event.Event;
import event.EventManager;
import user.attendee.Attendee;
import user.organizer.Organizer;
import user.speaker.Speaker;
import user.vip.Vip;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The UserManager class stores a list of all of the users. userMap
 * stores every username along with its associated User.
 */
public class UserManager implements Serializable {

    private HashMap<String, User> userMap;
    UserFactory userFactory;

    /**
     * Constructs a UserManger object
     */
    public UserManager(){
        userMap = new HashMap<>();
        this.userFactory = new UserFactory();
    }

    /**
     * checkCredentials determines if the user is in userMap based on the username.
     * @param username This parameter refers to the username of the user.
     * @return This method returns true if the user is in userMap and false if it isn't.
     */
    public boolean checkCredentials(String username){
        return userMap.get(username) != null;
    }

    /**
     * This method returns the user associated with the username.
     * @param username This parameter refers to the username of the user.
     * @return This method returns the user associated with the username.
     */
    public User getUser(String username){
        return userMap.get(username);
    }

    /**
     * This method returns the map of users.
     * @return Returns the map of users.
     */
    public HashMap<String, User> getUserMap(){
        return userMap;
    }


    /**
     * This method adds a new user to userMap if it is not already a user.
     * @param name Name of this new user
     * @param address Address of this new user
     * @param email Email of this new user
     * @param username Username of this new user
     * @param password Password of this new user
     * @param type Type of this new user
     * @param company Company of this new user
     * @param bio Bio of this new user
     * @return Returns true if the user was added to userMap and false otherwise.
     */
    public boolean addUser(String name, String address, String email, String username, String password, String type,
                           String company, String bio){
        if(checkCredentials(username)){
            return false;
        }
        User newUser = userFactory.createNewUser(name, address, email, username, password, type, company, bio);
        userMap.put(newUser.getUsername(), newUser);
        return true;
    }

    /**
     * This method returns the type of user the person is based on username.
     * @param username This parameter refers to the username of the user.
     * @return Returns a string representation of the user's type or null if user is not in userMap.
     */
    public String getUserType(String username){
        if(userMap.get(username) == null){
            return "";
        }
        return userMap.get(username).getUserType();
    }

    /**
     * This method returns the type of user the person is.
     * @param user This parameter refers to the user.
     * @return Returns a string representation of the user's type.
     */
    public String getUserType(User user){
        return user.getUserType();
    }

    /**
     * This method returns the list of events the user will speak at if they are a speaker.
     * @param username This parameter refers to the username of the user.
     * @return Returns the list of events the user will be speaking at if they are a speaker. Returns null otherwise.
     */
    public List<String> getSpeakingEvents(String username){
        return ((Speaker) userMap.get(username)).getSpeakingEvents();
    }

    /**
     * Remove the event from the list of events the speaker will speak at.
     * @param username Refers to the username of the speaker.
     * @param eventName Refers to the name of the event that will be cancelled.
     */
    public void removeSpeakingEvent(String username, String eventName){
        ((Speaker) getUser(username)).getSpeakingEvents().remove(eventName);
    }

    /**
     * Adds an Event name to a speaker's list of speaking events.
     * @param username The String username of the speaker of the event
     * @param eventName The String name of the event.
     */
    public void addSpeakingEvent(String username, String eventName) {
        ((Speaker) userMap.get(username)).addSpeakingEvent(eventName);
    }
    /**
     * This method returns a list of all organizers' usernames.
     * @return Returns a list of all organizers' usernames.
     */
    public List<String> getOrganizerUsernames(){
        List<String> organizers = new ArrayList<>();
        for(String username : userMap.keySet()){
            if(userMap.get(username) instanceof Organizer){
                organizers.add(username);
            }
        }
        return organizers;
    }

    /**
     * This method goes through the list of usernames and returns a map of usernames to user types.
     * @return A map of the usernames to the type of each user.
     */
    public Map<String, String> getUserTypes(){
        Map<String, String> new_map = new HashMap<>();
        for(String username : userMap.keySet()){
            new_map.put(username, userMap.get(username).getUserType());
        }
        return new_map;
    }

    /**
     * This method returns a list of events that the user will be attending if they are an organizer or attendee.
     * @param username Refers to the username of the user.
     * @return Returns a list of strings that represent the events the user will be attending.
     */
    public List<String> getAttendingEvents(String username){
        if(userMap.get(username).getUserType().equals("attendee") || userMap.get(username).getUserType().equals("vip")){
            return ((Attendee) userMap.get(username)).getAttendingEvents();
        }else if(userMap.get(username).getUserType().equals("organizer")){
            return ((Organizer) userMap.get(username)).getAttendingEvents();
        }else{
            return null;
        }
    }

    /**
     * This method signs the user up for an event.
     * @param username Refers to the username of the user.
     * @param event Refers to the event.
     * @param eventManager Refers to the class instance that contains all of the events.
     * @return Returns true if the event was added to the list of events the user will attend and false otherwise.
     */
    public boolean signUpForEvent(String username, Event event, EventManager eventManager){
        User user = userMap.get(username);

        if(eventManager.getAllEvents().get(event.getName()) == null || eventManager.checkEventFull(event.getName()) ||
                event.getTime().isBefore(LocalDateTime.now())){
            return false;
        }

        if(user.getUserType().equals("attendee")){
            for(String eventName : ((Attendee) user).getAttendingEvents()){
                if(!helpSignUp(eventName, event, eventManager)){
                    return false;
                }
            }

            ((Attendee) user).signUpForEvent(event.getName());
            eventManager.addAttendee(event.getName(), username);
        }

         else if(user.getUserType().equals("vip")) {
            for (String eventName : ((Attendee) user).getAttendingEvents()) {
               if (!helpSignUp(eventName, event, eventManager)) {
                   return false;
               }
            }

            ((Vip) user).signUpForEvent(event.getName());
            eventManager.addAttendee(event.getName(), username);
        }

        else if(user.getUserType().equals("organizer")){
            for(String eventName : ((Organizer) user).getAttendingEvents()){
                if(!helpSignUp(eventName, event, eventManager)){
                    return false;
                }
            }

            ((Organizer) user).signUpForEvent(event.getName());
            eventManager.addAttendee(event.getName(), username);

        }else{
            return false;
        }
        return true;
    }

    /**
     * This method cancels the user's spot in the event.
     * @param username Refers to the username of the user.
     * @param eventName Refers to the the name of the event.
     * @return Returns true if the event was removed from the list of events the user will attend or false otherwise.
     */
    public boolean cancelEventSpot(String username, String eventName){
        User user = userMap.get(username);

        if(user.getUserType().equals("attendee")){
            if(((Attendee) user).getAttendingEvents().contains(eventName)){
                ((Attendee) user).getAttendingEvents().remove(eventName);
            }else{
                return false;
            }
        }else if(user.getUserType().equals("organizer")){
            if(((Organizer) user).getAttendingEvents().contains(eventName)){
                ((Organizer) user).getAttendingEvents().remove(eventName);
            }else{
                return false;
            }
        }else{
            return false;
        }
        return true;
    }

    private boolean helpSignUp(String eventName, Event event, EventManager eventManager){
        Event attendEvent = eventManager.getEvent(eventName);
        double timeBetween = Math.abs(event.getTime().getHour()*60 + event.getTime().getMinute() -
                attendEvent.getTime().getHour()*60 - attendEvent.getTime().getMinute());
        return event.getTime().getDayOfYear() != attendEvent.getTime().getDayOfYear() ||
                event.getTime().getYear() != attendEvent.getTime().getYear() ||
                ((!event.getTime().isBefore(attendEvent.getTime()) ||
                        !(timeBetween < event.getDuration() * 60)) &&
                        (!event.getTime().isAfter(attendEvent.getTime()) || !(timeBetween < attendEvent.getDuration() * 60)) &&
                        (!event.getTime().isEqual(attendEvent.getTime())));
    }

    /**
     * Adds an event to the list of events that organizer has created.
     * @param event Refers to the event that is to be added to the list of events that organizer has organized.
     * @param organizers Refers to the organizers organizing the event.
     */
    public void createdEvent(String event, List<String> organizers){
        for (String name : organizers){
            Organizer curr = (Organizer)getUser(name);
            curr.createdEvent(event);
        }
    }

    /**
     * Returns a list of events that organizer has created.
     * @param organizer Refers to the username of the organizer.
     * @return The list of event names that specify the events the organizer is organizing.
     */
    public List<String> allCreatedEvents(String organizer){
        return ((Organizer) userMap.get(organizer)).getOrganizingEvents();
    }

    /**
     * This method removes an event from the list of events the organizer has created.
     * @param username Refers to the username of the organizer.
     * @param event Refers to the event that will be cancelled.
     */
    public void removeCreatedEvent(String username, Event event){
        ((Organizer) userMap.get(username)).removeEvent(event);
    }

    /**
     * This methods edits the corporation that a user is working for.
     * @param corporation Refers to the new corporation the user is working for.
     * @param username Refers to the username of the user whose company is being edited.
     */
    public void setCorporation(String corporation, String username){
        userMap.get(username).setCompany(corporation);
    }

    /**
     * This methods edits the bio that a user has set.
     * @param bio The new bio the user wants to set.
     * @param username The username of the user whose bio is being altered.
     */
    public void setBio(String bio, String username){
        userMap.get(username).setBio(bio);
    }

    /**
     * The method should only be used for reading in. Used to directly add events to the list of attending events for
     * users
     * @param username The username of the user
     * @param eventname The name of the event the user is attending
     */
    public void directSignUp(String username, String eventname) {
        if (userMap.get(username).getUserType().equalsIgnoreCase("attendee")){
            ((Attendee)userMap.get(username)).signUpForEvent(eventname);
        }
        else if (userMap.get(username).getUserType().equalsIgnoreCase("organizer")){
            ((Organizer)userMap.get(username)).signUpForEvent(eventname);
        }
        if (userMap.get(username).getUserType().equalsIgnoreCase("VIP")){
            ((Vip)userMap.get(username)).signUpForEvent(eventname);
        }
    }

    /**
     * Returns the password of this user
     * @param username The username of the user
     * @return The password of the user
     */
    public String getPassword(String username){
        return getUser(username).getPassword();
    }

    /**
     * Returns the address of this user
     * @param username The username of the user
     * @return The address of the user
     */
    public String getAddress(String username){
        return getUser(username).getAddress();
    }

    /**
     * Returns the email of this user
     * @param username The username of the user
     * @return The email of the user
     */
    public String getEmail(String username){
        return getUser(username).getEmail();
    }

    /**
     * Returns the company of this user
     * @param username The username of the user
     * @return The company of the user
     */
    public String getCompany(String username){
        return getUser(username).getCompany();
    }

    /**
     * Returns the bio of this user
     * @param username The username of the user
     * @return The bio of the user
     */
    public String getBio(String username){
        return getUser(username).getBio();
    }

    /**
     * Returns the name of this user
     * @param username The username of the user
     * @return The name of the user
     */
    public String getName(String username){
        return getUser(username).getName();
    }

    /**
     * @param type Refers to the type of the user
     * @return Returns the string representation of users that are type.
     */
    public List<String> getToStringsOfUsers(String type){

        if (getUserMap() == null) return new ArrayList<>();
        else{
            List <User> attendees = getUserMap().values().stream().filter(user -> user.getUserType().equals(type)).collect(Collectors.toList());
            List<String> stringsOfAttendees = new ArrayList<>();
            for (User user: attendees){
                stringsOfAttendees.add(user.toString());
            }
            return stringsOfAttendees;
        }

    }

    /**
     * @return returns the size of the User Map
     */
    public int getSizeOfUserMap(){
        return userMap.size();
    }

    /**
     * Attempts to sign up an attendee for an Event
     * @param username Refers to the username of the user.
     * @param eventManager Refers to the use case class for events.
     * @param eventName Refers to the naem of the event.
     * @return returns true if the attendee was signed up successfully and false if the
     * attendee was not able to sign up
     */
    public boolean signUpHelper(String username, EventManager eventManager, String eventName) {
        Event event = eventManager.getEvent(eventName);
        return signUpForEvent(username, event, eventManager);
    }

    /**
     * Checks if the corresponding User is not attending any events
     * @param username Refers to the username of the user.
     * @return returns true if the User corresponding to username is attending no events
     * and false if the User is attending at least 1 event
     */
    public boolean isAttendingEventsEmpty(String username) {
        Attendee temp = (Attendee) getUser(username);
        return temp.getAttendingEvents().isEmpty();
    }

    /**
     * @param type Refers to the type of User
     * @return Returns the number of users that are of the type: type in the total users list.
     */
    public int numOfUsersOfType(String type){
        if (getUserMap().size() == 0){
            return 0;
        }

        return users(type).size();
    }

    /**
     * Returns the list of users of a given type
     * @param type The userType
     * @return A list of Users
     */
    private List<User> users(String type) {

            return userMap.values().stream()
                    .filter(user -> user.getUserType().equals(type))
                    .collect(Collectors.toList());

    }

    /**
     * Returns the list of the top i speakers by number of engagements
     * @param i The number to look for
     * @return The list of usernames
     */
    public List<String> getTopSpeakers(int i) {
        List<String> speakers = users("speaker").stream()
                .map(s -> (Speaker) s)
                .sorted(Comparator.comparingInt(Speaker::getNumberOfEvents))
                .map(User::toString)
                .collect(Collectors.toList());

        Collections.reverse(speakers);

        if(speakers.size() > i) speakers = speakers.subList(0, i);

        return speakers;
    }

    /**
     * Returns the number of events per speaker
     * @return The list
     */
    public List<Integer> speakerEvents() {
        return users("speaker").stream()
                .map(s -> (Speaker) s)
                .map(Speaker::getNumberOfEvents)
                .collect(Collectors.toList());
    }
}
