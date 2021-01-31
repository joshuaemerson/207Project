package user;

import main.Presenter;

import java.util.List;

// using this https://stackoverflow.com/questions/40715424/printing-out-datetime-in-a-specific-format-in-java/40715452

/**
 * This class is a Presenter Class with common functionality between Attendee, Organizer and Speaker Controllers.
 * It handles asking for user input and printing any error messages.
 */
public class UserPresenter extends Presenter {

    /**
     * Prompts a User to choose a task.
     */
    public void displayTaskInput(){
        System.out.print("What would you like to do?\nEnter the corresponding number: ");
    }

    /**
     * Prompts an Attendee to choose another task once they have completed a task.
     */
    public void displayNextTaskPromptAttendee(){
        System.out.print("Please enter next task (reminder, you can type '6' to see what you can do): ");
    }

    /**
     * Prompts an Organizer to choose another task once they have completed a task.
     */
    public void displayNextTaskPromptOrganizer(){
        System.out.print("Please enter next task (reminder, you can type '14' to see what you can do):\n ");
    }

    public void displayNextTaskPromptOrgOptDisplayed(){
        System.out.print("Please enter next task:\n");
    }

    /**
     * Prompts a Speaker to choose another task once they have completed a task.
     */
    public void displayNextTaskPromptSpeaker(){
        System.out.print("Please enter next task (reminder, you can type '5' to see what you can do): ");
    }

    /**
     * Prints an error message when a User inputs an invalid task.
     */
    public void displayInvalidInputError(){
        System.out.println("The input should be in the proper range. Please try again.");
    }

    /**
     * Notifies a User that their message has been sent successfully.
     */
    public void displayMessageSentPrompt(){
        System.out.println("Message Sent\n");
    }

    /**
     * Notifies a User that their message has been sent successfully.
     */
    public void displayMessageSentPrompt2(){
        System.out.println("Messages Sent");
    }

    /**
     * Asks for the content of the message to be sent
     * @return The message text
     */
    public String displayEnterMessagePrompt(){
        System.out.println("Please enter the message. ");
        return scan.nextLine();
    }


    /**
     * Notifies a User that they successfully canceled their spot in an event.
     */
    public void displaySuccessfulCancellation(){
        System.out.println("Successfully Cancelled Spot in Event");
    }

    /**
     * Notifies a User that they successfully canceled their spot in an event.
     */
    public void displayUnsuccessfulCancellation(){
        System.out.println("Cancellation of spot in event was unsuccessful");
    }

    /**
     * Notifies a User that the selected message they attemped to unstar is not starred.
     */
    public void displayUnstarError(){
        System.out.println("Invalid input. The selected message is not starred.");
    }

    /**
     * Notifies a User that the selected message they attemped to star is already starred.
     */
    public void displayStarError(){
        System.out.println("Invalid input. The selected message is already starred.");
    }

    /**
     * Notifies a User that the selected message they attemped to pin is already pinned.
     */
    public void displayPinnedError(){
        System.out.println("Invalid input. The selected message is already pinned.");
    }

    /**
     * Notifies a User that the selected message they attemped to unpin is already unpinned.
     */
    public void displayUnpinnedError(){
        System.out.println("Invalid input. The selected message is already unpinned.");
    }


    /**
     * Prints an error message when a User inputs an invalid Event.
     */
    public void displayInvalidEventError(){
        System.out.println("Invalid Event. Please try again");
    }

    /**
     * Prints an error message when a User inputs an invalid date.
     */
    public void displayDateError(){
        System.out.println("Invalid Date. Please try again.");
    }


    private void printInboxMessages(String sender, String content, String time, int counter, boolean isRead,
                                    boolean isStarred, boolean isPinned){
        String buffer = ("==========================");

        System.out.println(buffer + "\n" + counter + ". Sent By: " + sender + "\n" +
                (!isRead? ("\u25CF "): "") + (isStarred? ("\u2605"): "") + (isPinned? ("(PINNED)"): "") + " Message: " +
                content.substring(0, Math.min(10, content.length())) + "..." +
                "\n" + time);
    }

    private void printDeletedMessages(String sender, String content, String time, int counter){
        String buffer = ("==========================");

        System.out.println(buffer + "\n" + counter + ". Sent By: " + sender + "\n" +
                 "\uD83D\uDDD1" + " Message: " +
                content.substring(0, Math.min(10, content.length())) + "..." +
                "\n" + time);
    }

    private void printArchivedMessage(String sender, String content, String time, int counter){
        String buffer = ("==========================");

        System.out.println(buffer + "\n" + counter + ". Sent By: " + sender + "\n" +
                "\uD83D\uDCC2" + " Message: " +
                content.substring(0, Math.min(10, content.length())) + "..." +
                "\n" + time);
    }

    /**
     * Prints a message to the screen.
     * @param effectiveMessage Refers to the message (encoded as a list of strings) to be printed
     * @param counter Refers to the number that will be displayed along with the message, used for selection
     * @param inboxType Refers to the type of inbox messages live in (used for printing different symbols)
     */
    public void displayUserMessages(List<String> effectiveMessage, int counter, String inboxType){
        // indices of information in effectiveMessage:
        // sender - 0, content - 1, datetime - 2,
        // read - 3, star - 4, delete - 5, archived - 6
        // index of message in user map lol - 7
        // recipient username - 8, isPinned - 9
        // deletion date info - 10, time created copy - 11
        // if unread

        String sender = effectiveMessage.get(0);
        String content = effectiveMessage.get(1);
        String time = effectiveMessage.get(2);
        boolean isRead = Boolean.parseBoolean(effectiveMessage.get(3));
        boolean isStarred = Boolean.parseBoolean(effectiveMessage.get(4));
        boolean isPinned = Boolean.parseBoolean(effectiveMessage.get(9));

        switch (inboxType) {
            case "inbox":
            case "starred":
                printInboxMessages(sender, content, time, counter, isRead, isStarred, isPinned);

                break;
            case "deleted":
                printDeletedMessages(sender, content, time, counter);

                break;
            case "archived":
                printArchivedMessage(sender, content, time, counter);
                break;
        }
    }

    /**
     * Displays the full message
     * @param effectiveMessage Refers to a list of strings that represents the components of the message
     */
    public void displayFullMessage(List<String> effectiveMessage){
        System.out.println("Sent By: " + effectiveMessage.get(0) + "\n" +
               "Message: " +
                effectiveMessage.get(1) +
                "\nSent on:" + effectiveMessage.get(2));
    }

    /**
     * Asks the user if they really want to delete a message from their deleted messages inbox.
     * @return Their choice, either yes or no.
     */
    public String displayDeleteConfirmation(){
        System.out.println("Are you sure you want to delete this message permanently? It cannot be undone. (yes/no): ");
        return scan.nextLine();
    }

    /**
     * A prompt that asks the user which message they want to display from their inbox
     * @return The number of the message they would like to read
     */
    public int displaySelectMessage(){
        System.out.println("Which message would you like to read? (Enter the number of the corresponding message): ");
        return nextInt();
    }

    /**
     * Prompts the user to re-pick a message if they made a nonsensical selection
     */
    public void displayMessageNonExistent(){
        System.out.println("That is not a valid message. Please try again.");
    }

    /**
     * Tells the user they gave an invalid input.
     */
    public void displayInvalidInput(){
        System.out.println("That is not a valid action. Please try again.");
    }


    /**
     * Displays options that a user can take while looking at their inbox, or their starred messages.
     * @return The option that the user chose.
     */
    public String displayMessageActionPrompt(){
        System.out.println("What would you like to do with this message?(reply, mark as unread, mark as starred, " +
                "unstar, delete, archive, pin, unpin, close)");
        return scan.nextLine();
    }

    /**
     * Displays options that a user can take while looking at their deleted messages.
     * @return The option that the user chose.
     */
    public String displayDeletedActionPrompt(){
        System.out.println("What would you like to do with this message?(reply, delete, restore, " +
                "close)");
        return scan.nextLine();
    }

    /**
     * Displays options that a user can take while looking at their archived messages.
     * @return The option that the user chose.
     */
    public String displayArchivedActionPrompt(){
        System.out.println("What would you like to do with this message?(reply, unarchive, " +
                "close)");
        return scan.nextLine();
    }


    /**
     * Prints an error message when an Organizer or Attendee tries to message another User when they are the only one in the conference.
     */
    public void displayConferenceError(){
        System.out.println("There are currently no other users who are registered within this " +
                "conference. Please try again at a later time.");
    }

    /**
     * This method prints all the requests a user has made.
     * @param requests The requests a user has made
     */
    public void displayRequestsHeader(List<List<String>> requests){
        if(requests.size() == 0){
            System.out.println("You have not made any requests.");
        }
        else{
            System.out.println("Requests you have made: ");
        }
    }

    /**
     * This method displays the status and content of a user request.
     * @param requestStatus The status of a users request.
     * @param requestContent The contents of a users request.
     */
    public void displayRequestsBody(String requestStatus, String requestContent){
        System.out.print(requestStatus + " : ");
        System.out.println(requestContent);
    }
    /**
     * This method prints the corporation a user is currently associated with.
     * @param corporation The corporation the user is associated with.
     */
    public void displayViewCorporation(String corporation){
        System.out.println(corporation);
    }

    /**
     * This method prints the bio of a user.
     * @param bio The bio of a user.
     */
    public void displayViewBio(String bio){
        System.out.println(bio);
    }

    /**
     * This method prints that the user has no message in their inbox.
     */
    public void displayEmptyInbox(){System.out.println("No Messages :(");}

    /**
     * This method prints that the user has no starred messages.
     */
    public void displayEmptyStarredInbox(){System.out.println("No Starred Messages :(");}

    /**
     * This method prints that the user has no deleted messages.
     */
    public void displayEmptyDeletedInbox(){System.out.println("No Deleted Messages :(");}

    /**
     * This method prints that the user has no archived messages.
     */
    public void displayEmptyArchivedInbox(){System.out.println("No Archived Messages :(");}

}
