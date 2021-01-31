package room;

import java.io.Serializable;

/**
 * This class represents the rooms that events could take place in. The number of people that can be
 * in the room is represented by capacity. The actual number of the room is represented by roomNumber.
 * Rooms can have some number of computers, a projector, tables, and chairs.
 */
public class Room implements Serializable {

    private final int capacity;
    private final int roomNumber;
    private final int computers;
    private final boolean projector;
    private final int tables;
    private final int chairs;

    /**
     * This method constructs a room with capacity 2 and room number roomNumber.
     * @param roomNumber Refers to the room number of the room.
     * @param capacity Refers to the capacity of the room.
     * @param computers Refers to the number of computers in the room.
     * @param projector Refers to whether or not the room contains a projector.
     * @param chairs Refers to the number of chairs in the room.
     * @param tables Refers to the number of tables in the room.
     */
    public Room(int roomNumber, Integer capacity, int computers, boolean projector, int chairs, int tables){
        this.roomNumber = roomNumber;
        if(capacity == null){
            this.capacity = 2;
        }else{
            this.capacity = capacity;
        }
        this.computers = computers;
        this.projector = projector;
        this.tables = tables;
        this.chairs = chairs;
    }

    // Getter methods

    /**
     * This method gets the capacity of the room.
     * @return Returns the capacity of the room.
     */
    public int getCapacity(){
        return capacity;
    }

    /**
     * This method gets the room number.
     * @return Returns the room number of the room.
     */
    public int getRoomNumber(){
        return roomNumber;
    }

    /**
     * This method gets the number of computers in the room.
     * @return Returns the number of computers in the room.
     */
    public int getComputers(){
        return this.computers;
    }

    /**
     * This method gets the number of tables in the room.
     * @return Returns the number of tables in the room.
     */
    public int getTables(){
        return this.tables;
    }

    /**
     * This method returns whether or not there is a projector in the room.
     * @return Returns true if there is a projector in the room and false otherwise.
     */
    public boolean getProjector(){
        return this.projector;
    }

    /**
     * This method gets the number of chairs in the room.
     * @return Returns the number of chairs in the room.
     */
    public int getChairs(){
        return this.chairs;
    }

    // Setter Methods

    // Other Methods

    /**
     * This method formats a room object into a string.
     * @return Returns a string representation of the room's attributes.
     */
    public String toString(){
        int projector = 0;

        if(getProjector()){
            projector = 1;
        }

        return "Room " + this.roomNumber + " - Capacity: " + this.capacity + ", Equipment: " +
                this.computers + " Computers, " + projector + " Projector(s), " + this.chairs +
                " Chairs, " + this.tables + " Tables ";
    }
}

