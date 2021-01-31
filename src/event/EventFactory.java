package event;

import java.time.LocalDateTime;
import java.util.List;

public class EventFactory {

    /**
     * This method reads in details about an event, and creates it
     * @param eventType the type of the event
     * @param name the name of the event
     * @param time what time the event begins
     * @param duration how long the event is
     * @param roomNumber the room number where the event is taking place
     * @param capacity the capacity of the event
     * @param requiredComputers number of computers required for the event
     * @param requiredProjector if a projector is required for the event
     * @param requiredChairs number of required chairs for the event
     * @param requiredTables number of required tables for the event
     * @param creators the creators associated with the event
     * @param vipEvent if the event is a vip event or not
     * @param speaker the speaker associated with the event
     * @param speakers the speakers associated with the event
     * @param tag the tag associated with the event
     * @return an Event with the specified details
     */
    public Event getEvent(String eventType, String name, LocalDateTime time, Integer duration, int roomNumber,
                          int capacity, int requiredComputers, boolean requiredProjector, int requiredChairs,
                          int requiredTables, List<String> creators, boolean vipEvent, String speaker,
                          List<String> speakers, String tag) {

        // returns a party event if specified
        if(eventType.equalsIgnoreCase("Party")){
            return new Party( name,  time,  duration,  roomNumber,  capacity, requiredComputers,  requiredProjector,
                    requiredChairs, requiredTables, creators,  vipEvent, tag);
        }

        // returns a talk event if specified
        else if(eventType.equalsIgnoreCase("Talk")){
            return new Talk(name,  time,  duration,  roomNumber,  capacity, requiredComputers,  requiredProjector,
                    requiredChairs, requiredTables, creators, vipEvent, speaker, tag);
        }

        // returns a panel event if specified
        else if(eventType.equalsIgnoreCase("Panel")){
            return new Panel(name,  time,  duration,  roomNumber,  capacity, requiredComputers,  requiredProjector,
                    requiredChairs, requiredTables, creators, vipEvent, speakers, tag);
        }
        else{
            return null;
        }
    }
}