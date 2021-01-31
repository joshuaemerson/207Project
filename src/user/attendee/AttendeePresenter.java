package user.attendee;

import user.UserPresenter;

import java.util.List;

/**
 * This class is a Presenter Class with specific functionality for Attendee Controllers.
 * It handles asking for user input and printing any error messages.
 */
public class AttendeePresenter extends UserPresenter {


    public AttendeePresenter(){
    }

    /**
     * Prints the tasks which an Attendee is able to do.
     */
    public void displayOptions(){
        System.out.println("(0) Messages\n(1) Events\n(2) Requests\n(3) User Options\n(4) Quit");
    }

    /**
     * Prints the things an attendee can do relating to Messages
     */
    public void displayMessageOptions(){
        System.out.println("(0) See Inbox\n(1) See Starred Messages\n(2) See Deleted Messages\n(3) See Archived Messages\n" +
                "(4) Send Message\n(5) Go back to Main Screen");
    }

    /**
     * Displays all the user options that an Attendee/VIP user is able to perform.
     */
    public void displayUserOptions(){
        System.out.println("(0) View Corporation\n(1) Edit Corporation Information\n" +
                "(2) View Bio\n(3) Edit Bio\n(4) Go back to Main Screen");
    }

    /**
     * Prints the things an attendee can do relating to Events
     */
    public void displayEventOptions(){
        System.out.println("(0) View Event List\n(1) View My Scheduled Events\n(2) Cancel Event Reservation\n" +
                "(3) Sign up for an Event\n(4) Search for an event\n(5) Go back to Main Screen");
    }

    /**
     * Prints the things an attendee can do relating to Requests
     */
    public void displayRequestOptions(){
        System.out.println("(0) View My Requests\n(1) Make a Request\n(2) Go back to Main Screen");
    }

    /**
     * Prints a message that tells the user that their input is invalid when navigating the messages menu.
     */
    public void displayMessageOptionsInvalidChoice() {
        System.out.println("Invalid input. Please enter a number between 0 and 4:");
    }

    /**
     * Prints a message that tells the user that their input is invalid when navigating the events menu.
     */
    public void displayEventOptionsInvalidChoice() {
        System.out.println("Invalid input. Please enter a number between 0 and 4:");
    }

    /**
     * Prints a message that tells the user that their input is invalid when navigating the requests menu.
     */
    public void displayRequestsOptionsInvalidChoice() {
        System.out.println("Invalid input. Please enter a number between 0 and 2:");
    }

    /**
     * Prompts an Organizer or Attendee on which User they would like to message.
     * @return Returns the user's next string input.
     */
    public String displayMethodPrompt(){
        System.out.println("Who would you like to message? (Please enter the username of the recipient). Otherwise, type 'q' to exit");
        return scan.nextLine();
    }

    /**
     * Prints an error message which notifies the Organizer that a User they are trying to Message is not in their contacts list.
     */
    public void displayNotMessageableError() {
        System.out.println("Sorry, you are not allowed to message this User.");
    }

    /**
     * Prompts an Attendee or Organizer to enter the contents of the message they would like to send.
     * @param recipient: The username of the User who is being messaged.
     * @return Returns the next string input from the user.
     */
    public String displayEnterMessagePrompt(String recipient){
        System.out.println("Enter the message you would like to send to " + recipient + ". " + "If you would no longer like to send a message, type 'q' to exit. ");
        return scan.nextLine();
    }


    /**
     * Prints the event list for the conference.
     * @param stringsOfEvents: a List of strings describing all events in this conference.
     */
    public void displayEventList(List<String> stringsOfEvents){
        if (stringsOfEvents.size() == 0){
            System.out.println("There are no events created yet. ");
            return;
        }
        System.out.println("Here is a list of all the available events at this conference: ");
        int counter = 1;
        for (String curr : stringsOfEvents){
            System.out.println(counter + ". " + curr);
            counter ++;
        }
    }

    /**
     * Prints all the events that an Attendee or Organizer has signed up for.
     * @param stringsOfEvents: a List of strings describing all Events that this User has signed up for.
     */
    public void displaySignedUpEvents(List<String> stringsOfEvents){
        if (stringsOfEvents.size() == 0){
            System.out.println("You haven't signed up for any events yet. ");
            return;
        }
        System.out.println("Here is the list of events you have signed up for: ");
        int counter = 1;
        for (String curr : stringsOfEvents) {
            System.out.println(counter + ": " + curr);
        }
    }

    /**
     * Prints the message that the attendee is not attending any events.
     */
    public void displayNotAttendingAnyEvents(){
        System.out.println("You aren't attending any events so there are no event reservations to cancel.");
    }

    /**
     * Prompts an Attendee or Organizer on which Event they would like to cancel their spot in.
     * @return The event
     */
    public String displayEventCancelPrompt(){
        System.out.println("What is the name of the event you no longer want to attend? Type 'q' if you no longer want to cancel your spot in an event. ");
        return scan.nextLine();
    }

    /**
     * Prints an error message that tells an Attendee or Organizer that the Event they are trying to cancel is not one of the events they have signed up for.
     */
    public void displayEventCancellationError2(){
        System.out.println("You are currently not attending any events. For future use, you must be " +
                "signed up for an event to use this feature.");
    }

    /**
     * Displays all the future events in the conference.
     * @param stringsOfEvents The list of future events.
     */
    public void displayAllFutureEvents(List<String> stringsOfEvents){
        if (stringsOfEvents.size() == 0){
            displaySignUpError2();
            return;
        }
        System.out.println("Here is the list of all future events: ");
        int counter = 1;
        for (String curr : stringsOfEvents) {
            System.out.println(counter + ": " + curr);
        }

    }

    /**
     * Prompts an Attendee or Organizer for the name of the Event they would like to sign up for.
     * @return Returns the next string input from the user.
     */
    public String displayEventSignUpPrompt(){
        System.out.println("What is the name of the event you would like to sign up for? Type 'q' if you would no longer like to sign up for an event.");
        return scan.nextLine();
    }

    /**
     * Notifies the Attendee or Organizer that they have successfully signed up for an Event.
     */
    public void displayEventSignUp(){
        System.out.println("Successfully signed up for the event");
    }


    /**
     * Prints a message tasking for another name input from the user.
     * @return Returns the next string input from the user.
     */
    public String displayInvalidEventSignUp(){
        System.out.print("Please re-enter the name " +
                "(it is case sensitive) or enter 'q' to quit: ");
        return scan.nextLine();
    }

    /**
     * Prints an error message that user cannot sign up for this event because it is only for VIP's
     */
    public void displayEventOnlyforVIPs(){
        System.out.print("That event is only for VIP's.\n");
    }


    /**
     * Prints an error message that user cannot sign up for this event because it does not exist.
     */
    public void displayEventNotRegistered(){
        System.out.print("That event does not exist.\n");
    }

    /**
     * This method displays an error message to the user that the task they selected was out of range.
     */
    public void displayInvalidUserChoice(){
        System.out.println("Invalid input. Please enter a number between 0 and 3:");
    }

    /**
     * Prints an error message that the Event sign up was unsuccessful since there are no events in this conference.
     */
    public void displaySignUpError2(){
        System.out.println("There are currently no future events in this conference. Please wait until event(s)" +
                "have been added to use this feature.");
    }

    /**
     * Prints an error message that the Event sign up was unsuccessful as the Event is at capacity.
     */
    public void displayEventFull(){
        System.out.println("This event is full!");
    }

    /**
     * Displays a prompt to the user to enter their request.
     * @return The user request.
     */
    public String displayMakeRequest(){
        System.out.println("Please enter your request (< 200 characters)");
        return scan.nextLine();
    }

    /**
     * Displays an error message notifying the user that their request was over the character limit.
     */
    public void invalidRequest(){
        System.out.println("Requests must be less than 200 characters. Please re-enter content");
    }

    /**
     * Displays a message that the users request was successful.
     */
    public void displaySuccessfulRequestSubmission(){
        System.out.println("Your request was successfully submitted.");
    }

    /**
     * Displays a prompt to the user to ask them if they would like to search by name or tag.
     * @return The way the user would like to search for an event.
     */
    public String displayPromptSearchForEvents() {
        System.out.println("Would you like to search events by name or by tag (categories)?:");
        return scan.nextLine();
    }

    /**
     * Displays an error message which notifies that the user entered an invalid event name or tag.
     */
    public void displayInvalidPromptSearchForEvents() {
        System.out.println("Invalid choice. Please type in name or tag:");
    }

    /**
     * Displays a prompt to the user to enter the name of the event they are searching for.
     * @return The name of the event the user is searching for.
     */
    public String displayPromptSearchForEventsByName() {
        System.out.println("Please enter in the name of the event you are looking for:");
        return scan.nextLine();
    }

    /**
     * Displays a prompt to the user to enter the tag of an event they are searching for.
     * @return The tag of the event the user is searching for.
     */
    public String displayPromptSearchForEventsByTag() {
        System.out.println("Please enter in the tag you are looking for the events of:");
        return scan.nextLine();
    }

    /**
     * Prints the event specified by the event name.
     * @param string Refers to the string representation of the event.
     * @param name Refers to the name of the event.
     */
    public void displayEventByName(String string, String name){
        if (name.equals("NoEvent")){
            System.out.println("There is no registered event with this name. Please try again.");
        }else{
            System.out.println("This is the registered event with the name: " + name);
            System.out.println(string);
        }
    }

    /**
     * Displays the events with the appropriate tag that the user was searching for.
     * @param strings The list of events with the appropriate tag.
     * @param tag The tag the user selected.
     */
    public void displayEventsByTag(List<String> strings, String tag){

        if (strings.size() == 0){
            System.out.println("There are no registered events with the tag: " + tag);
        }
        else{
            System.out.println("Here are the registered events with the tag: " + tag);
            for (String string: strings){
                System.out.println(string);
            }
        }
    }

    /**
     * Displays a prompt to the user to select whether or not they want to search for another event.
     * @return The users response to whether they would like to search for another event.
     */
    public String displayPromptSearchForAnotherEvent(){
        System.out.println("Would you like to search for another event? (Y/N): ");
        return scan.nextLine();
    }

    /**
     * Displays an error message which notifies the user that they entered an invalid input as to whether or not they wanted to search for another event.
     * @return The answer to whether the user wants to search for another event.
     */
    public String displayErrorSearchForAnotherEvent(){
        System.out.println("Invalid input. Please enter Y or N: ");
        return scan.nextLine();
    }
}
