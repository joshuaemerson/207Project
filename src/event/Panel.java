package event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

/**
 * Panel is an extension of Event which is a type of event with a list of speakers.
 */
public class Panel extends Event{

    private final List<String> speakers;

    /**
     * This constructs an event
     *
     * @param name              The name of the event
     * @param time              The event time
     * @param duration          The event duration(in hours).
     * @param roomNumber        The event room number
     * @param capacity          This refers to the capacity of the event.
     * @param requiredComputers Refers to the amount of computers required for the event.
     * @param requiredProjector Refers to whether or not a projector is required for the event.
     * @param requiredChairs    Refers to number of chairs required for the event.
     * @param requiredTables    Refers to the number of tables required for the event.
     * @param creators          Refers to the list of usernames of users that created the event.
     * @param vipEvent          Refers to whether or not this event is limited to VIP's only
     * @param speakers          Refers to the list of usernames of speakers that are speaking at the event.
     * @param tag               Refers to the tag of the event.
     */
    public Panel(String name, LocalDateTime time, Integer duration, int roomNumber, int capacity, int requiredComputers,
                 boolean requiredProjector, int requiredChairs, int requiredTables, List<String> creators, boolean vipEvent, List<String> speakers, String tag) {
        super(name, time, duration, roomNumber, capacity, requiredComputers, requiredProjector, requiredChairs, requiredTables, creators, vipEvent, tag);
        this.speakers = speakers;
    }

    /**
     * This method is a getter for the speakers list
     * @return Returns the list of speakers.
     */
    public List<String> getSpeakersList() {
        return speakers;
    }

    /**
     * This method returns a string representation of the type of Event this event is.
     * @return Returns "panel".
     */
    public String getEventType(){
        return "panel";
    }

    @Override
    public String getSpeakerName() {
        return null;
    }

    // This method returns the Speakers of this panel in String format
    private String speakersToString(){
        String string = speakers.get(0);
        if (speakers.size() > 1){
            for (int i = 1; i < speakers.size(); i++){
                string = string + ", " + speakers.get(i);
            }
        }
        return string;
    }

    /**
     * This method formats an event object into a string.
     * @return Returns a string representation of the attributes of an event.
     */
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String date = getTime().format(formatter);
        int projector = 0;

        if(getRequiredProjector()){
            projector = 1;
        }

        return "Title: " + getName() + "| Type: " + getEventType() + "| Time: " + date + "| Speakers: " + speakersToString() +
                "| Size: " + getSize() + "| Duration: " + getDuration() + " hour/s| Room: " + getRoomNumber() + ", Equipment Required: " +
                getRequiredComputers() + " Computers, " + projector + " Projector(s), " + getRequiredChairs() +
                " Chairs, " + getRequiredTables() + " Tables ";
    }
}