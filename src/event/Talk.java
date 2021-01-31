package event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

/**
 * Talk is an extension of Event that has one speaker.
 */
public class Talk extends Event{

    private final String speakerName;

    /**
     * This constructs a talk
     *
     * @param name              The name of the talk
     * @param time              The talk time
     * @param duration          The talk duration(in hours).
     * @param roomNumber        The talk room number
     * @param capacity          This refers to the capacity of the talk.
     * @param requiredComputers Refers to the amount of computers required for the talk.
     * @param requiredProjector Refers to whether or not a projector is required for the talk.
     * @param requiredChairs    Refers to number of chairs required for the talk.
     * @param requiredTables    Refers to the number of tables required for the talk.
     * @param creators          Refers to the list of usernames of users that created the talk.
     * @param vipEvent          Refers to whether or not this talk is limited to VIP's only
     * @param speakerName       Refers to the username of the speaker speaking at the talk.
     * @param tag               Refers to the tag of the event.
     */
    public Talk(String name, LocalDateTime time, Integer duration, int roomNumber, int capacity, int requiredComputers, boolean requiredProjector, int requiredChairs, int requiredTables, List<String> creators, boolean vipEvent, String speakerName, String tag) {
        super(name, time, duration, roomNumber, capacity, requiredComputers, requiredProjector, requiredChairs, requiredTables, creators, vipEvent, tag);
        this.speakerName = speakerName;
    }

    /**
     * This method is a getter for the speaker's username
     * @return Returns speaker's username
     */
    public String getSpeakerName() {
        return speakerName;
    }

    @Override
    public List<String> getSpeakersList() {
        return null;
    }

    /**
     * This method returns a string representation of the type of Event this event is.
     * @return Returns "talk".
     */
    public String getEventType(){
        return "talk";
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

        return "Title: " + getName() + "| Type: " + getEventType() + "| Time: " + date + "| Speaker: " + getSpeakerName() +
                "| Size: " + getSize() + "| Duration: " + getDuration() + " hours| Room: " + getRoomNumber() + ", Equipment Required: " +
                getRequiredComputers() + " Computers, " + projector + " Projector(s), " + getRequiredChairs() +
                " Chairs, " + getRequiredTables() + " Tables ";
    }
}
