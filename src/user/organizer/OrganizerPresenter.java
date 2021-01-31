package user.organizer;

import event.Event;
import request.Request;
import room.Room;
import user.attendee.AttendeePresenter;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * This class is a Presenter Class with specific functionality for Organizer Controllers.
 * It handles asking for user input and printing any error messages.
 */
public class OrganizerPresenter extends AttendeePresenter {

    /**
     * The constructor method for the Presenter class of the Organizer.
     */
    public OrganizerPresenter() {
    }

    /**
     * Prints all the tasks which an Organizer can do.
     */
    public void displayOptions2() {
        System.out.println("\n(0) Messages\n(1) Events\n(2) User Options\n(3) Requests\n(4) Quit");
    }

    /**
     * Prints all of the options an organizer can do relating to messages.
     */
    public void displayMessageOptions() {
        System.out.println("(0) See Inbox\n(1) See Starred Messages\n(2) See Deleted Messages\n(3) See Archived Messages" +
                "\n(4) Send Message\n(5) Message All Attendees\n(6) Message Event Attendees" +
                "\n(7) Message All Speakers\n(8) Go back to main screen");
    }

    /**
     * Prints all of the options an organizer can do related to events.
     */
    public void displayEventOptions() {
        System.out.println("(0) View Event List\n(1) View My Scheduled Events\n(2) Cancel Event Reservation\n(3) Sign up for Event" +
                "\n(4) Add Event\n(5) Cancel Event\n(6) Reschedule Event\n(7) View All Rooms \n(8) Add Room \n(9) Modify an Event's capacity\n" +
                "(10) Display Conference Statistics\n(11)Search for events\n(12)Go back to main screen ");
    }

    /**
     * Prints all of the options an organizer can do related to users.
     */
    public void displayUserOptions() {
        System.out.println("(0) Add New User\n(1) View Speakers\n(2) View Attendees\n(3) View Organizers" +
                "\n(4) View VIPs\n(5) View Corporation\n(6) Edit Corporation Information\n" +
                "(7) View Bio\n(8) Edit Bio\n(9) Go back to main screen");
    }

    /**
     * Prints all of the options an organizer can do relating to requests.
     */
    public void displayRequestOptions() {
        System.out.println("(0) Address Requests\n(1) View Addressed Request\n(2) View User Requests\n(3) Go back to main screen");
    }

    /**
     * Prints a message that tells the user that their input is invalid when navigating the messages menu.
     */
    public void displayMessageOptionsInvalidChoice() {
        System.out.println("Invalid input. Please enter a number between 0 and 9:");
    }

    /**
     * Prints a message that tells the user that their input is invalid when navigating the events menu.
     */
    public void displayEventOptionsInvalidChoice() {
        System.out.println("Invalid input. Please enter a number between 0 and 12:");
    }

    /**
     * Prints a message that tells the user that their input is invalid when navigating the users menu.
     */
    public void displayUserOptionsInvalidChoice() {
        System.out.println("Invalid input. Please enter a number between 0 and 8:");
    }

    /**
     * Prints a message that tells the user that their input is invalud when navigating the requests menu.
     */
    public void displayRequestsOptionsInvalidChoice() {
        System.out.println("Invalid input. Please enter a number between 0 and 3:");
    }

    /**
     * Prompts the Organizer that the process of adding an Event will now begin.
     */
    public void displayAddConferencePrompt() {
        System.out.println("To Add an Event to the Conference, enter the following:");
    }

    /**
     * Prompts the Organizer to add the Title of the Event they want to create.
     * @return Returns a string representation of the user input.
     */
    public String displayEventTitlePrompt() {
        System.out.print("Enter the Event Title (or type 'q' to exit): ");
        return scan.nextLine();
    }

    /**
     * Prompts the Organizer to enter the name of the Speaker for the Event they want to create.
     * @return The speaker username
     */
    public String displayEnterSpeakerPrompt() {
        System.out.print("Enter a Speaker's username: ");
        return scan.nextLine();
    }

    /**
     * Prompts the Organizer to enter the name of the Speaker for the Event they want to create.
     * @return The speaker username
     */
    public String askNewSpeakerPrompt() {
        System.out.print("Enter a new speaker? (Y/N): ");
        return scan.nextLine();
    }

    /**
     * Prompts the Organizer to enter the room number for the Event they want to create.
     * @return Return an integer representation of the user's input that represents the desired room number.
     */
    public int displayEnterRoomNumberPrompt() {
        System.out.print("Enter a room number: ");
        return nextInt();
    }

    /**
     * Prints an error message notifying the Organizer that the Speaker they tried to add to their Event does not exist.
     */
    public void displaySpeakerCredentialError() {
        System.out.println("This speaker does not exist. You will be asked to create an account for them.");
    }

    /**
     * Prints the message that the time input is invalid.
     */
    public void displayInvalidHourError(){
        System.out.println("Invalid time. The event must begin between 9:00 and 16:00");
    }

    /**
     * Prints the message the date input is invalid.
     */
    public void displayInvalidDateError(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate currentDate = currentDateTime.toLocalDate();
        LocalTime currentTime = currentDateTime.toLocalTime();
        LocalTime endTime = LocalTime.of(16, 0);
        if (currentTime.isAfter(endTime)) {
            System.out.println("Invalid date entered. The soonest you may schedule an event is tomorrow at 9AM.");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
            String date = currentDate.format(formatter);
            System.out.println("Invalid date entered. An event can only be scheduled for " + date +
                    " any time before 5PM and any following date");
        }
    }

    /**
     * Prints an error message notifying the Organizer that the Speaker or Room for the Event they are trying to create will be double booked.
     */
    public void displayEventCreationError() {
        System.out.println("The event was invalid. Please try again.");
    }

    /**
     * Notifies the Organizer that the Event was created successfully.
     */
    public void displaySuccessfulEventCreation() {
        System.out.println("Event created successfully. ");
    }

    /**
     * Prompts the Organizer to enter the Message they want to send to all Attendees in the conference.
     * @return The message
     */
    public String displayAllAttendeeMessagePrompt() {
        System.out.println("Enter what you want to say to all the attendees (1 line) or type 'q' to exit.");
        return scan.nextLine();
    }

    /**
     * Prompts the Organizer to enter the Message they want to send to all Attendees in the Event they created.
     * @return The message
     */
    public String displayAllAttendeeEventMessagePrompt() {
        System.out.println("What do you want to say to all the attendees at this event? (1 line)");
        return scan.nextLine();
    }

    /**
     * Prompts the Organizer on which Event they want to send a Message to.
     * @return The Event
     */
    public String displayEventMessagePrompt() {
        System.out.print("Enter the event you want to message or type 'q' to exit: ");
        return scan.nextLine();
    }

    /**
     * Prompts the Organizer what Message they want to send to all speakers in the conference.
     * @return The message
     */
    public String displayAllSpeakerMessagePrompt() {
        System.out.println("Enter what you want to say to all the speakers (1 line) or type 'q' to exit.");
        return scan.nextLine();
    }

    /**
     * Prompts the Organizer as to what Event they want to remove.
     * @return The event
     */
    public String displayEventRemovalPrompt() {
        System.out.print("Enter the event you want to remove or type 'q' to exit: ");
        return scan.nextLine();
    }

    /**
     * Prompts the Organizer on which Event they want to reschedule.
     * @param responses Thee responses
     * @param responsibleEvents The responsible events
     */
    public void displayEventReschedulePrompt(String[] responses, List<String> responsibleEvents) {
        Scanner scan = new Scanner(System.in);
        if (responsibleEvents.size() == 0) {
            System.out.println("You are not responsible for any events. ");
            responses[0] = "q";
            return;
        }
        System.out.println("Here are all the events you are responsible for ");
        for (int i = 0; i < responsibleEvents.size(); i++) {
            System.out.print(i + ". " + responsibleEvents.get(i) + "   ");
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println("Enter the event you want to reschedule, or type 'q' to exit: ");
        responses[0] = scan.nextLine();
        while (!responsibleEvents.contains(responses[0]) && !responses[0].equalsIgnoreCase("q")) {
            System.out.println("This is not an event you can reschedule, please try again or type 'q' to exit: ");
            responses[0] = scan.nextLine();
        }
    }

    /**
     * Prompts the Organizer to enter the year of the Event they are creating/rescheduling.
     */
    public void displayEnterYearPrompt() {
        System.out.print("Enter a year: ");
    }

    /**
     * Prompts the Organizer to enter the month of the Event they are creating/rescheduling.
     */
    public void displayEnterMonthPrompt() {
        System.out.print("Enter a month (1-12): ");
    }

    /**
     * Prompts the Organizer to enter the day of the Event they are creating/rescheduling.
     */
    public void displayEnterDayPrompt() {
        System.out.print("Enter a day: ");
    }

    /**
     * Prompts the Organizer to enter the hour of the Event they are creating/rescheduling.
     */
    public void displayEnterHourPrompt() {
        System.out.print("Enter an hour (9-16): ");
    }

    /**
     * Prompts the Organizer to enter the minute of the Event they are creating/rescheduling.
     */
    public void displayEnterMinutePrompt() {
        System.out.print("Enter a minute (0-59): ");
    }

    /**
     * Prompts the Organizer to enter the username of the Speaker account they want to add to the conference
     * @return The username
     */
    public String displayEnterUsernamePrompt() {
        System.out.print("Enter the user's username or type 'q' to quit: ");
        return scan.nextLine();
    }

    /**
     * Prints an error message that notifies the Organizer that the Speaker account username they tried to add was already taken.
     */
    public String displayRepeatUsernameError() {
        System.out.print("That username is already taken, please enter another one: ");
        return scan.nextLine();
    }

    /**
     * Prints an error message which notifies the Organizer that the Speaker account they are trying to create needs a username of at least 3 characters.
     * @return The username
     */
    public String displayUsernameLengthError() {
        System.out.print("Error, username must be at least 3 characters. please enter another one: ");
        return scan.nextLine();
    }

    /**
     * Prompts the Organizer to enter the password for the Speaker account they are creating.
     * @return The Password
     */
    public String displayEnterPasswordPrompt() {
        System.out.print("Enter Password: ");
        return scan.nextLine();
    }

    /**
     * Prints an error message notifying the Organizer that the password for the Speaker account must be at least three characters.
     * @return The password
     */
    public String displayPasswordLengthError() {
        System.out.print("Error, password must be at least 3 characters.\nPlease enter again: ");
        return scan.nextLine();
    }

    /**
     * Prompts the Organizer to enter the name of the Speaker for the Speaker account they are creating.
     * @return The speaker
     */
    public String displayEnterUserNamePrompt() {
        System.out.print("Enter the user's name: ");
        return scan.nextLine();
    }

    /**
     * Prints an error message that notifies the Organizer that a Speaker must have a name of at least 2 characters.
     * @return The name
     */
    public String displayUserNameError() {
        System.out.print("Error, name must be at least 2 characters.\nPlease enter again: ");
        return scan.nextLine();
    }

    /**
     * Prompts the Organizer to enter the address of the Speaker for the Speaker account they are creating.
     * @return The address
     */
    public String displayEnterUserAddressPrompt() {
        System.out.print("Enter the user's address: ");
        return scan.nextLine();
    }

    /**
     * Prints an error message notifying an Organizer that the address of a Speaker must be at least six characters.
     * @return The address
     */
    public String displayAddressLengthError() {
        System.out.print("Error, address must be at least 6 characters.\nPlease enter again: ");
        return scan.nextLine();
    }

    /**
     * Prompts the Organizer to enter the email of the Speaker for the Speaker account they are creating.
     * @return The email
     */
    public String displayEnterUserEmailPrompt() {
        System.out.print("Enter the user's email: ");
        return scan.nextLine();
    }

    /**
     * Prints an error message notifying the Organizer that the email address of the Speaker does not match specific requirements.
     * @return The email
     */
    public String displayInvalidEmail() {
        System.out.print("The email is not up to RFC 5322 standards. Try another: ");
        return scan.nextLine();
    }

    /**
     * Prompts the Organizer to enter the number of the Room they would like to create.
     * @return Returns the integer input of the user input.
     */
    public int displayRoomCreationPrompt() {
        System.out.println("Enter the number of the Room you would " +
                "like to add, or type '0' to quit: ");

        return nextInt();
    }

    /**
     * Prints an error message which notifies the Organizer that the Room they are trying to create already exists.
     */
    public void displayRoomAlreadyExists() {
        System.out.println("This room already exists!");
    }

    /**
     * Prints all the rooms in this conference.
     * @param stringsOfRooms: a List of strings describing all rooms in this conference.
     */
    public void displayRoomList(List<String> stringsOfRooms) {
        if (stringsOfRooms.size() == 0) {
            System.out.println("No rooms have been created yet. ");
            return;
        }
        System.out.println("These are all the created rooms");
        for (String roomString : stringsOfRooms) {
            System.out.println(roomString);
        }

    }

    /**
     * Displays a series of messages that prompts the user to add more organizers to the list of people responsible
     * for creating the event.
     * @param creators   Refers to the list of creators responsible for creating the event.
     * @param organizers Refers to the list of all organizers' usernames.
     */
    public void displayAndGetCreators(List<String> creators, List<String> organizers) {
        Scanner scan = new Scanner(System.in);
        List<String> allUsernames = new ArrayList<>();
        if (organizers.size() > 1) {
            System.out.println("Here is the list of all the organizers at this conference: ");
            for (int i = 0; i < organizers.size(); i++) {
                System.out.print(i + ". " + organizers.get(i) + "   ");
                allUsernames.add(organizers.get(i));
                if (i < organizers.size() - 1 && (i + 1) % 5 == 0) {
                    System.out.println();
                }
            }
        } else {
            return;
        }
        System.out.println("Would you like to add any of them as additional organizers for this event " +
                "(this gives them the ability to reschedule or cancel this event)? Type their usernames here " +
                "or enter \"done\" when the list is complete ");
        String text = scan.nextLine();
        while (!text.equalsIgnoreCase("done")) {
            if (allUsernames.contains(text) && !creators.contains(text)) {
                creators.add(text);
                System.out.println("Organizer added");
            } else if (allUsernames.contains(text)) {
                System.out.println("This organizer is allowed to edit this event already, please re-enter a " +
                        "valid username");
            } else if (!allUsernames.contains(text)) {
                System.out.println("This user is not allowed to edit this event, please re-enter a " +
                        "valid username");
            }
            System.out.print("Next username (or 'done' to finish): ");
            text = scan.nextLine();
        }
    }

    /**
     * Displays the message that prompts the user to enter whether or not the event is VIP exclusive.
     * @return VIP exclusivity
     */
    public String displayVipPrompt() {
        System.out.println("Is this event exclusive to VIP's? Type 'Yes' or 'No'. Type 'q' to quit.");
        return scan.nextLine();
    }

    /**
     * Displays the message that informs the user that the answer to whether or not the event is VIP exclusive is invalid.
     * @return VIP exclusivity
     */
    public String displayInvalidVip() {
        System.out.println("You can only answer yes or no. Enter again or 'q' to quit.");
        return scan.nextLine();
    }

    /**
     * Displays a list of Users
     * @param stringsUserList The list of strings describing all users of type to be displayed
     * @param type     The type of User
     */
    public void displayUserList(List<String> stringsUserList, String type) {
        Collections.sort(stringsUserList);
        System.out.println("Here is the " + type + " List");
        for (String u : stringsUserList) {
            System.out.println(u);
        }
    }

    /**
     * Displays a list of events that this organizer created
     * @param futureEvents The list of created events
     */
    public void displayYourCreatedEvents(List<Event> futureEvents) {
        if (futureEvents.size() == 0) {
            System.out.println("There are no upcoming events created by you. ");
            return;
        }
        System.out.println("These are all the events coming up that you created: ");
        int counter = 1;
        for (Event named : futureEvents) {
            System.out.println(counter + ". " + named);
        }
    }

    /**
     * Displays the message that the user can't cancel the event specified.
     * @return The event
     */
    public String displayCannotCancel() {
        System.out.print("You can't cancel that event, re-enter or type 'q' to quit: ");
        return scan.nextLine();
    }

    /**
     * Displays the message that prompts the user for the specified capacity of the event.
     * @return Returns the integer input the user input.
     */
    public int displayEventCapacityPrompt() {
        System.out.println("Enter the capacity of the event you would like to add or -1 if you want to quit.");
        return nextInt();
    }

    /**
     * Displays the message that prompts the user for the specified capacity of the room.
     * @return Returns the integer input of the user.
     */
    public int displayRoomCapacityPrompt() {
        System.out.println("Enter the capacity of the room you would like to add or -1 if you want to quit.");
        return nextInt();
    }

    /**
     * Displays the message that prompts the user for the wanted duration.
     * @return Returns the integer input of the user that represents the length of the event.
     */
    public int displayDurationPrompt() {
        System.out.println("How long would you like the event to last(in hours)? You can enter 0 to quit.");
        return nextInt();
    }

    /**
     * Displays the message that prompts the user to enter the number of computers in the room.
     * @return Returns the integer input of theuser that represents the number of computers.
     */
    public int displayComputersPrompt() {
        System.out.println("How many computers? Enter -1 to quit.");
        return nextInt();
    }

    /**
     * Displays the message that prompts the user to enter whether or not there is a computer in the room.
     * @return The string that represents the answer the user gave to whether or not the room/event has a projector.
     */
    public String displayProjectorPrompt() {

        System.out.println("Does this event need a projector? Type 'Yes' or 'No'. Type 'q' to quit.");
        String disp = scan.nextLine();

        while(!disp.equalsIgnoreCase("yes") && !disp.equalsIgnoreCase("no") && !disp.equalsIgnoreCase("q")) {
            disp = displayInvalidProjector();
        }
        return disp;
    }

    /**
     * Displays the message that informs the user that the answer to whether or not the room has a projector is invalid.
     * @return New Projector
     */
    public String displayInvalidProjector() {
        System.out.println("You can only answer yes or no. Enter again or 'q' to quit.");
        return scan.nextLine();
    }

    /**
     * Displays the message that prompts the user to enter the number of chairs in the room.
     * @return Returns the integer input of the user that represents the number of chairs.
     */
    public int displayChairsPrompt() {
        System.out.println("How many chairs? Enter -1 to quit.");
        return nextInt();
    }

    /**
     * Displays the message that prompts the user to enter the number of tables in the room.
     * @return Returns the integer input of the user that represents the amount of tables.
     */
    public int displayTablesPrompt() {
        System.out.println("How many tables? Enter -1 to quit.");
        return nextInt();
    }


    /**
     * Displays the message that displays all of the recommended rooms.
     * @param capacity  Refers to the capacity of the event.
     * @param computers Refers to the amount of computers required for the event.
     * @param projector Refers to whether or not the event requires a projector.
     * @param chairs    Refers to the number of chairs required for the event.
     * @param tables    Refers to the number of tables required for the event.
     * @param rooms     Refers to the list of rooms.
     */
    public void displayRecommendedRooms(int capacity, int computers, boolean projector, int chairs, int tables, List<Room> rooms) {
        ArrayList<Room> recommendRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getCapacity() >= capacity && room.getComputers() >= computers && room.getChairs() >= chairs &&
                    room.getTables() >= tables && (!projector || projector && room.getProjector())) {
                recommendRooms.add(room);
            }
        }
        System.out.println("Recommended Rooms:");
        for (Room room : recommendRooms) {
            System.out.println(room);
        }
        System.out.println("Please enter the room you want to use. If you choose a non-existent room, the room will be " +
                "created with the necessary equipment.");
    }

    /**
     * Displays the message that tells the user that they cannot change the capacity of any events.
     */
    public void displayNoOrganizedEvents() {
        System.out.println("You have not created any events. You cannot change the capacity of anything.");
    }

    /**
     * Prints the message that the user input is not a speaker.
     * @return The message
     */
    public String displayNotSpeakerError(){
        System.out.print("This user cannot give a talk! Please try again or enter 'q' to quit: ");
        return scan.nextLine();
    }

    /**
     * Displays a message that informs the user that the room number they entered doesn't exist.
     * @return The room number
     */
    public String displayRoomNumberQuestion1(){

        System.out.println("There is no room with this room number. \nDo you want to create a new room " +
                "or do you want to be suggested a room from the existing ones? Please enter " +
                "\n(1) 'create' to create a room \n(2) 'q' to quit\nIf you choose to create a room, the room number " +
                "you enter will be used for the event by default.");
        String ans = scan.nextLine();

        while(!ans.equalsIgnoreCase("create") && !ans.equalsIgnoreCase("q")) {

            ans = displayRoomDecisionQError1();
        }

        return ans;
    }

    /**
     * Displays the message that their room decision is invalid.
     * @return The room
     */
    public String displayRoomDecisionQError1(){
        System.out.println("Error: Invalid response. Please enter: \n(1) 'create' to create a room " +
                "\n(2) 'q' to quit");
        return scan.nextLine();

    }

    /**
     * Displays the message that informs the user that the room number they entered doesn't exist and asks if they
     * want to get a list of suggestions.
     * @return The room number
     */
    public String displayRoomNumberQuestion2(){
        System.out.println("There is no room with this room number. \nDo you want to create a new room" +
                "or do you want to be suggested a room from the existing ones? Please enter " +
                "\n(1) 'create' to create a room \n(2) 'suggestions' to get a list of suggestions \n(3) 'q' to quit" +
                "\nIf you choose to create a room, the room number you enter will be used for the event by default.");

        String ans = scan.nextLine();

        while(!ans.equalsIgnoreCase("create") && !ans.equalsIgnoreCase("suggestions") // need to fix it so it doesnt give suggestions as option when empty
                && !ans.equalsIgnoreCase("q")) {
            ans = displayRoomDecisionQError2();
        }

        return ans;
    }

    /**
     * Displays the message that their response to displayRoomNumberErrorQuestion2() is invalid.
     * @return The room
     */
    public String displayRoomDecisionQError2(){
        System.out.println("Error: Invalid response. Please enter: \n(1) 'create' to create a room " +
                "\n(2) 'suggestions' to get a list of suggestions \n(3) 'q' to quit");
        return scan.nextLine();

    }

    /**
     * Displays the message that prompts the user to enter the maximum capacity of the event.
     * @param maxCapacity Refers to the capacity of the room which must be greater than or equal to the capacity of the event.
     * @return Returns the user input for the capacity.
     */
    public int displayEnterEventCapacityPrompt(int maxCapacity){
        System.out.println("Enter the number of people that can attend the event: (it cannot be greater than the room's " +
                "capacity which is " + maxCapacity + ". Enter 0 to quit.");
        return nextInt();
    }

    /**
     * Displays the message that prompts the user to enter the new capacity of the event.
     * @param maxCapacity Refers to the capacity of the room which cannot be less than that of the event.
     * @param minCapacity The minimum possible size
     * @return  The number
     */
    public int displayEnterNewEventCapacityPrompt(int maxCapacity, int minCapacity){
        System.out.println("The room this event is taking place in has a maximum capacity of " + maxCapacity +
                ". The new capacity must be greater than " + minCapacity + "; the number" +
                " of users already attending the event. The capacity can also not be 0. Please Enter the new number " +
                "of people that can attend the event:");

        int capac = nextInt();

        while(capac > maxCapacity || capac < minCapacity || capac == 0) {
            displayModifyRoomCapacityError(maxCapacity, minCapacity);
            capac = nextInt();
        }
        return capac;
    }

    /**
     * Displays the message that informs the user that the capacity entered is too high.
     * @return Returns the input from the user.
     */
    public int displayRoomCapacityError(){
        System.out.println("Error: That is an invalid capacity for the room to have. Please Enter the number of " +
                "people that can attend the event:");
        return nextInt();
    }

    /**
     * Displays the message that informs the user that their new capacity is too low.
     * @param maxCapacity Refers to he maximum capacity of the room.
     * @param numberUsersAlreadyAttending Refers to the amount of people already attending the event.
     */
    public void displayModifyRoomCapacityError(int maxCapacity, int numberUsersAlreadyAttending){
        System.out.println("Error: The room this event is taking place in has a maximum capacity of " + maxCapacity +
                ". There new capacity must be greater than or equal to " + numberUsersAlreadyAttending + "; the number" +
                " of users already attending the event. Please Enter the new number of people that can attend the event:");
    }

    /**
     * Displays the message that prompts the user to enter the event they want to change the capacity of.
     * @return The event
     */
    public String displayEventModifyPrompt(){
        System.out.print("Enter the event whose capacity you want to modify or type 'q' to exit: ");
        return scan.nextLine();
    }

    /**
     * Displays the message that informs the user that the event they entered isn't an event.
     * @return The event
     */
    public String displayCannotModifyEvent(){
        System.out.print("Error: Please enter the name of an event that you created or press 'q' to quit:");
        return scan.nextLine();
    }

    /**
     * Prints all of event stats.
     * @param stats The map of events to their statistics.
     */
    void displayNumberStats(Map<String, Double> stats) {

        List<String> keys = new ArrayList<>(stats.keySet());
        Collections.sort(keys);

        for(String s : keys) {
            System.out.println(s + ": " + fmt(stats.get(s)));
        }
    }

    private String fmt(double d) {

            return d == (long) d ? String.format("%d",(long)d) : String.format("%s", d);
    }

    /**
     * Prints the event statistics.
     * @param lists A map of event names to the list of stats.
     */
    void displayListStats(Map<String, List<String>> lists) {

        List<String> keys = new ArrayList<>(lists.keySet());
        Collections.sort(keys);

        for(String s : keys) {
            System.out.println(s);
            for(String e : lists.get(s)) {
                System.out.println(e);
            }
        }
    }

    /**
     * Displays a Histogram of data
     * @param list A frequency list
     * @param title The title of the chart
     */
    public void displayHistogram(List<Integer> list, String title) {
        if(list.isEmpty()) return;

        System.out.println(title);
        int max = Collections.max(list);
        int min = Collections.min(list);

        int[] hist = new int[max - min + 1];

        for(int i : list) hist[i - min]++;

        for (int i = min; i <= max; i++) {
            if (hist[i - min] > 0) {
                System.out.print(i + ": ");
                for (int j = 0; j < hist[i - min]; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }
    }

    /**
     * Asks the organizer what type of user they would like to create.
     * @return The type of the new user.
     */
    public String displayNewUserCreation() {
        System.out.println("What kind of new user would you like to create? (Organizer, Attendee, Speaker, VIP): ");
        return scan.nextLine();
    }

    /**
     * Prints the message that the user type is invalid.
     * @return The input from the user of the next line.
     */
    public String displayInvalidUserTypeError(){
        System.out.println("Sorry, that is not a valid user type. Please try again:");
        return scan.nextLine();
    }

    /**
     * Prints the message that the new user has been successfully created and their username and password.
     * @param username Refers to the username of the user.
     * @param password Refers to the password of the user.
     */
    public void displayNewUserCreated(String username, String password) {
        System.out.println("New user successfully created with the following details:\n" +
                "Username: " + username + "| Password: " + password);
    }

    /**
     * Prints the message that the event time has been successfully changed.
     */
    public void displayEventTimeChanged(){
        System.out.println("The event time has been changed.");
    }

    /**
     * Prints the message that the event type is not valid.
     * @return Returns the input from the user.
     */
    public String displayInvalidEventType(){
        System.out.println("This is not a valid event type. Please try again or 'q' to quit.");
        return scan.nextLine();
    }

    /**
     * Prints the message that the event type is not valid.
     * @return Returns the input from the user.
     */
    public String displayInvalidTagCategoryType(){
        System.out.println("This is not a valid tag category type. Please try again or 'q' to quit.");
        return scan.nextLine();
    }

    /**
     * Prints the message that asks the user what type of event they would like to create.
     * @return Returns the input from the user.
     */
    public String displayPromptEventType(){
        System.out.println("What kind of event would you like to create? A talk, panel, or party?");
        return scan.nextLine();
    }

    /**
     * Prints the message that asks the user what category(tag) this event is in.
     * @return Returns the input from the user.
     */
    public String displayPromptEventTagPanel(){
        System.out.println("What category would this panel be in? (development, networking, motivational)");
        return scan.nextLine();
    }

    /**
     * Prints the message that asks the user what category(tag) this event is in.
     * @return Returns the input from the user.
     */
    public String displayPromptEventTagTalk(){
        System.out.println("What category would this talk be in? (development, networking, motivational)");
        return scan.nextLine();
    }

    /**
     * Prints the message that asks the user what category(tag) this event is in.
     * @return Returns the input from the user.
     */
    public String displayPromptEventTagParty(){
        System.out.println("What category would this party be in? (graduation, company)");
        return scan.nextLine();
    }

    /**
     * Prints the message that there are not enough speakers for the panel.
     */
    public void notEnoughPeople(){
        System.out.println("Sorry. You wanted a panel and a panel must have at least two speakers. Please enter another speaker.");
    }

    /**
     * Prints the message that the room is occupied.
     * @return Returns the user's integer input.
     */
    public int displayOccupiedRoom(){
        System.out.println("This room is occupied at this time. Please select again.");
        return nextInt();
    }

    /**
     * Prints a message that tells the user that the event name is invalid.
     * @return Returns the string input from the user.
     */
    public String displayInvalidEventName(){
        System.out.println("An event with this name already exists. Please enter another name.");
        return scan.nextLine();
    }

    /**
     * Prints the message that the speaker has already been added to the list of speakers at the panel.
     */
    public void displaySpeakerAlreadyAdded(){
        System.out.println("This speaker has already been added.");
    }

    /**
     * Gets input from the user about the time.
     * @return Returns the formatted time entered.
     * @throws DateTimeException Refers to the exception that is raised when the date is not valid.
     */
    protected LocalDateTime getTime() throws DateTimeException {
        displayEnterYearPrompt();
        int y = nextInt();
        displayEnterMonthPrompt();
        int m = nextInt();
        displayEnterDayPrompt();
        int d = nextInt();
        displayEnterHourPrompt();
        int h = nextInt();
        displayEnterMinutePrompt();
        int mi = nextInt();
        return LocalDateTime.of(y, m, d, h, mi);
    }

    /**
     * Gets the user to input time if their time is invalid.
     * @return Returns the formatted time enter.
     */
    protected LocalDateTime askTime() {
        LocalDateTime time = LocalDateTime.now();
        do {
            try {
                time = getTime();
                if(time.isBefore(LocalDateTime.now())){
                    displayInvalidEventError();
                }else{
                    break;
                }
            } catch (DateTimeException d) {
                displayDateError();
            }
        } while(true);

        return time;
    }

    /**
     * Prints the message that prompts the user to mark the request.
     * @param request Refers to the request of the user.
     * @return Returns the user input.
     */
    public String displayRequestDecisionPrompt(Request request){
        System.out.println(request.getContent());
        System.out.println("Type 'addressed' to mark this request as addressed, or 'rejected' to mark it as " +
                "rejected.");
        return scan.nextLine();
    }

    /**
     * Prints the message that the request status is not valid.
     */
    public void requestDecisionInvalid(){
        System.out.println("This is not a valid status. Please mark as 'addressed' or 'rejected'");
    }

    /**
     * Prints the message that the request has been successfully addressed.
     */
    public void successfullyAddressedRequest(){
        System.out.println("Request successfully addressed.");
    }

    /**
     * Prints the message that the request has been successfully rejected.
     */
    public void successfullyRejectedRequest(){
        System.out.println("Request successfully rejected");
    }

    /**
     * Prints the message that asks the user which request the organizer wants to view.
     * @return Returns the integer input from the organizer.
     */
    public int viewRequestPrompt(){
        System.out.println("Please enter which request you would like to view");
        return Integer.parseInt(scan.nextLine());
    }

    /**
     * Prints all of the requests that are pending.
     * @param requests Refers to the list of all of the requests.
     */
    public void displayPendingRequests(List<Request> requests){
        if(requests.size() == 0){
            System.out.println("There are no pending requests at this time.");
        }
        else {
            System.out.println("Pending Requests: ");
            int counter = 1;
            for (Request request : requests) {
                System.out.println(counter + " : " + request.getContent());
                counter++;
            }
        }
    }

    /**
     * Prints all of the requests that are addressed.
     */
    public void displayAddressedRequests(){
        System.out.println("Addressed Requests: ");
    }

    /**
     * Prints the message that prompts the user which requests they would like to access.
     * @return Returns the string input from the user.
     */
    public String viewUserRequestPrompt(){
        System.out.println("Please enter which user's requests you would like to access");
        return scan.nextLine();
    }

    /**
     * Prints all of the requests a user has made.
     */
    public void displayUserRequests(){
        System.out.println("This user has made the following requests: ");
    }

    /**
     * Prints the message that the user has not made any requests.
     */
    public void noUserRequests(){
        System.out.println("This user has not made any requests.");
    }

    /**
     * Prints the message that there are no addressed requests.
     */
    public void noAddressedRequests(){
        System.out.println("There are no addressed requests at this time.");
    }

    /**
     * This method displays the status and content of a user's request
     * @param status the status of the request
     * @param content the content of the request
     */
    public void displayRequestBody(String status, String content){
        System.out.print(status + " : ");
        System.out.println(content);
    }

    /**
     * This method displays the request details, including username and content
     * @param username the username of the individual who made the request
     * @param content the content of the request
     */
    public void displayRequestDetails(String username, String content){
        System.out.print(username + " : ");
        System.out.println(content);
    }
}