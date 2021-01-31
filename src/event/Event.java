package event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents an Event object. An Event can have a name, speaker, time, duration(in hours), room,
 * capacity, and set of attendees attending. The event also contains how many computers, chairs, tables, and
 * whether or not a projector is required for the event to run.
 */
public abstract class Event implements Comparable<Event>, Serializable {

    private final String name;
    private LocalDateTime time;
    private final int duration;
    private final int roomNumber;
    private int capacity;
    private final int requiredComputers;
    private final boolean requiredProjector;
    private final int requiredChairs;
    private final int requiredTables;
    private final Set<String> attendeeSet;
    private final List<String> creators;
    private final boolean vipEvent;
    private final String tag;

    /**
     * This constructs an event
     * @param name The name of the event
     * @param time The event time
     * @param duration The event duration(in hours).
     * @param roomNumber The event room number
     * @param capacity This refers to the capacity of the event.
     * @param requiredComputers Refers to the amount of computers required for the event.
     * @param requiredProjector Refers to whether or not a projector is required for the event.
     * @param requiredChairs Refers to number of chairs required for the event.
     * @param requiredTables Refers to the number of tables required for the event.
     * @param creators Refers to the list of usernames of users that created the event.
     * @param vipEvent Refers to whether or not this event is limited to VIP's only
     * @param tag Refers to the category this event is in.
     */
    public Event(String name, LocalDateTime time, Integer duration, int roomNumber, int capacity,
                 int requiredComputers, boolean requiredProjector, int requiredChairs, int requiredTables,
                 List<String> creators, boolean vipEvent, String tag) {
        this.name = name;
        this.time = time;
        this.duration = duration;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.requiredComputers = requiredComputers;
        this.requiredProjector = requiredProjector;
        this.requiredChairs = requiredChairs;
        this.requiredTables = requiredTables;
        attendeeSet = new HashSet<String>();
        this.creators = creators;
        this.vipEvent = vipEvent;
        this.tag = tag;
    }

    // Getter Methods

    /**
     * This method is a getter for name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * This method is a getter for event time
     * @return LocalDateTime time
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * This method is a getter for tag
     * @return String tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method is a getter for duration
     * @return duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * This method is a getter for roomNumber
     * @return roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * This method is a getter for the set of attendees
     * @return attendeeSet
     */
    public Set<String> getAttendeeSet() {
        return attendeeSet;
    }

    /**
     * This method gets the capacity of the event.
     * @return Returns the capacity of the event.
     */
    public int getCapacity(){
        return this.capacity;
    }

    /**
     * This method gets the required number of computers in the event.
     * @return Returns the required number of computers in the event.
     */
    public int getRequiredComputers(){
        return this.requiredComputers;
    }

    /**
     * This method gets whether or not a projector is required for the event.
     * @return Returns true if a projector is required and false otherwise.
     */
    public boolean getRequiredProjector(){
        return this.requiredProjector;
    }

    /**
     * This method returns how big the attendee set is.
     * @return an int giving the size of the attendee set.
     */
    public int getSize() {
        return attendeeSet.size();
    }

    /**
     * This method returns the type of Event this event is.
     * @return Will return a string representation of the Event type.
     */
    public abstract String getEventType();

    /**
     * This method is a getter for the speaker's username (Only applicable for Talk.java)
     * @return Returns speaker's username (Only applicable for Talk.java)
     */
    public abstract String getSpeakerName();

    /**
     * This method is a getter for the list of speakers (Only applicable for Panel)
     * @return Returns the list of speakers (Only applicable for Panel)
     */
    public abstract List<String> getSpeakersList();

    /**
     * This method gets whether or not the event is VIP exclusive.
     * @return Refers to whether or not this event is exclusive to VIP's only.
     */
    public boolean getVipEvent(){ return this.vipEvent; }

    /**
     * This method gets the list of creator usernames that were responsible for creating the event.
     * @return Returns the list of creators.
     */
    public List<String> getCreators(){
        return this.creators;
    }

    /**
     * This method gets the required number of tables required for the event.
     * @return Returns the required number of tables required for the event.
     */
    public int getRequiredTables(){
        return this.requiredTables;
    }

    /**
     * This method gets the number of chairs required for the event.
     * @return Returns the required number of chairs in the event.
     */
    public int getRequiredChairs(){
        return this.requiredChairs;
    }

    // Setter Methods

    /**
     * This method is a setter for the time
     * @param time The Event Time
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * This method sets the capacity of the event.
     * @param capacity Refers to the new capacity of the event.
     */
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    // Other Methods

    /**
     * CompareTo
     * @param e The Event being compared to
     * @return Returns the compareTo result based on the time of the event
     */
    public int compareTo(Event e) {
        return this.getTime().compareTo(e.getTime());
    }


    /**
     * This method adds an attendee
     * @param attendee The attendee username to be added
     */
    public void addAttendee(String attendee) {
        this.attendeeSet.add(attendee);
    }
}