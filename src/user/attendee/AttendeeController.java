package user.attendee;

import event.EventManager;
import message.MessageManager;
import request.RequestManager;
import user.UserController;
import user.UserManager;

import java.util.List;

/**
 * A controller that deals with Attendee users
 */
public class AttendeeController extends UserController {

    AttendeePresenter p;

    /**
     * This constructs an AttendeeController object
     * @param userManager the instance of the User Manager
     * @param eventManager the instance of the Event Manager
     * @param messageManager the instance of the Message Manager
     * @param username the username of the Attendee accessing the AttendeeController
     * @param requestManager the instance of the Request Manager.
     */
    public AttendeeController(UserManager userManager, EventManager eventManager, MessageManager messageManager,
                              String username, RequestManager requestManager){
        super(userManager, eventManager, messageManager, username, requestManager);
        this.p = new AttendeePresenter();
    }

    /**
     * Runs the Attendee controller by asking for input and performing the actions
     */
    public void run(){
        deletedMessagesCheck();
        p.displayOptions();
        p.displayTaskInput();
        int input = 0;
        input = p.nextInt();
        while (input != 4){ // 4 is ending condition
            deletedMessagesCheck();
            determineInput(input);
            input = p.nextInt();
        }
    }

    private void determineInput(int input) {
        switch (input) {
            case 0:
                p.displayMessageOptions();
                int choice = p.nextInt();
                final int endCond = 5;
                while (choice != endCond) {
                    determineInput0(choice);
                    choice = p.nextInt();
                }
                break;

            case 1:
                p.displayEventOptions();
                int choice1 = p.nextInt();
                final int endCond1 = 5;
                while (choice1 != endCond1) {
                    determineInput1(choice1);
                    choice1 = p.nextInt();
                }
                break;

            case 2:
                p.displayRequestOptions();
                int choice2 = p.nextInt();
                final int endCond2 = 2;
                while (choice2 != endCond2) {
                    determineInput2(choice2);
                    choice2 = p.nextInt();
                }
                break;

            case 3:
                p.displayUserOptions();
                int choice3 = p.nextInt();
                final int endCond3 = 4;
                while (choice3 != endCond3){
                    determineInput3(choice3);
                    choice3 = p.nextInt();
                }
                break;

            case 6:
                p.displayOptions();
                break;

            default:
                p.displayInvalidInputError();
                break;
        }
        deletedMessagesCheck();
        p.displayOptions();
        p.displayNextTaskPromptAttendee();
    }

    protected void determineInput0(int input){
        switch (input){
            case 0:
                viewMessages(messageManager.generateEffectiveMessageList(this.username, "inbox"),
                        "inbox");
                break;
            case 1:
                viewMessages(messageManager.generateEffectiveMessageList(this.username, "starred"),
                        "starred");
                break;
            case 2:
                viewMessages(messageManager.generateEffectiveMessageList(this.username, "deleted"),
                        "deleted");
                break;
            case 3:
                viewMessages(messageManager.generateEffectiveMessageList(this.username, "archived"),
                        "archived");
                break;
            case 4:
                determineInputSendMessages();
                break;
            default:
                p.displayMessageOptionsInvalidChoice();
                break;
        }
        deletedMessagesCheck();
        p.displayNextTaskPromptOrgOptDisplayed();
        p.displayMessageOptions();
    }

    protected void determineInput1(int input){
        switch (input){
            case 0:
                viewEventList();
                break;

            case 1:
                viewSignedUpForEvent(this.username);
                break;
            case 2:
                determineInputCancelEventReservation();
                break;

            case 3:
                determineInputSignUpEvent();
                break;
            case 4:
                searchForEvents();
                break;
            default:
                p.displayEventOptionsInvalidChoice();
                break;
        }
        deletedMessagesCheck();
        p.displayNextTaskPromptOrgOptDisplayed();
        p.displayEventOptions();
    }

    protected void determineInput2(int input){
        switch (input){
            case 0:
                viewRequests(username);
                break;

            case 1:
                String req = p.displayMakeRequest();
                boolean valid = requestManager.checkIsValidRequest(req);
                while(!valid){
                    p.invalidRequest();
                    req = p.displayMakeRequest();
                    valid = requestManager.checkIsValidRequest(req);
                }
                makeRequest(req, username);
                p.displaySuccessfulRequestSubmission();
                break;
            default:
                p.displayRequestsOptionsInvalidChoice();
                break;
        }
        deletedMessagesCheck();
        p.displayNextTaskPromptOrgOptDisplayed();
        p.displayRequestOptions();
    }

    protected void determineInput3(int input) {
        switch (input) {
            case 0:
                viewCorporation();
                break;

            case 1:
                String corporation = p.displayEnterCompanyPrompt();
                if(corporation.equalsIgnoreCase("q")){
                    break;
                }
                while(corporation.equalsIgnoreCase("")){
                    corporation = p.displayInvalidCompanyError();
                }
                editCorporation(corporation);
                break;

            case 2:
                viewBio();
                break;

            case 3:
                String bio = p.displayEnterBioPrompt();
                if(bio.equalsIgnoreCase("q")){
                    break;
                }
                while(bio.equalsIgnoreCase("")){
                    bio = p.displayInvalidBioError();
                }
                editBio(bio);
                break;

            case 6:
                p.displayUserOptions();
                break;

            default:
                p.displayInvalidUserChoice();
                break;
        }
        deletedMessagesCheck();
        p.displayNextTaskPromptOrgOptDisplayed();
        p.displayUserOptions();
    }

    protected void determineInputSignUpEvent() {
        List<String> future = eventManager.getToStringsOfFutureEvents();
        //List<String> stringsOfEvents = getToStringsOfEvents();
        p.displayAllFutureEvents(future);
        if (future.size() == 0){
            return;
        }
        String eventSignedUp = p.displayEventSignUpPrompt();
        // System.out.println("What is the name of the event you would like to sign up for? Type 'q' if you would no longer like to sign up for an event.");
        if (eventSignedUp.equals("q")){
            return;
        }
        while (!eventManager.checkEventIsRegistered(eventSignedUp) ||
                eventManager.checkIfEventIsVIp(eventSignedUp)){    // Checks if the event is VIP exclusive and then prompts attendee that they cannot sign up for that event
            if (!eventManager.checkEventIsRegistered(eventSignedUp)) {
                p.displayEventNotRegistered();
            } else if (eventManager.checkIfEventIsVIp(eventSignedUp)) {
                p.displayEventOnlyforVIPs();
            }
            eventSignedUp = p.displayInvalidEventSignUp();
            if (eventSignedUp.equalsIgnoreCase("q")){
                break;
            }
        }
        if (!eventSignedUp.equalsIgnoreCase("q")) {
            signUp(eventSignedUp);
        }

    }

    public void determineInputCancelEventReservation() {
//        Attendee temp = (Attendee) userManager.getUser(this.username);
//        if(temp.getAttendingEvents().isEmpty()){
        if (userManager.isAttendingEventsEmpty(username)) {
            p.displayNotAttendingAnyEvents();
            return;
        }
        viewSignedUpForEvent(this.username);
        String cancel = p.displayEventCancelPrompt();
        // System.out.println("What is the name of the event you no longer want to attend? Type 'q' if you no longer want to cancel your spot in an event.");
        if (cancel.equals("q")){
            return;
        }
        if(!userManager.getAttendingEvents(this.username).contains(cancel)) {
            p.displayEventCancelPrompt();
            return;
        }
        else if(userManager.getAttendingEvents(this.username).size() == 0){
            p.displayEventCancellationError2();
            return;
        }
        cancelSpotInEvent(cancel);

    }

    private void determineInputSendMessages() {
        if(userManager.getUserMap().size() == 1) {
            p.displayConferenceError();
            return;
        }
        String recipient = p.displayMethodPrompt();
        // System.out.println("Who would you like to message? (Please enter the username of the recipient). Otherwise, type 'q' to exit");
        if (recipient.equals("q")){
            return;
        }
        else if(messageManager.checkIsMessageable(recipient, this.username, userManager)) {
            String messageContents = p.displayEnterMessagePrompt(recipient);
            // System.out.println("What message would you like to send to: " + recipient + ". " + "If you would no longer like to send a message, type 'q' to exit");
            if (messageContents.equals("q")){
                return;
            }
            sendMessage(recipient, messageContents);
            p.displayMessageSentPrompt();
        }
        else{
            p.displayNotMessageableError();
        }

    }




    /**
     * Sends a message to a specified user
     * @param recipient: The username of the recipient
     * @param messageContents: The content of the message the Attendee is sending
     */
    protected void sendMessage(String recipient, String messageContents) {
        messageManager.addMessage(username, messageContents, recipient);
    }


//    /**
//     * Prints the event list for the entire conference
//     */
//    protected void viewEventList() {
//        List<Event> chronological = eventManager.chronologicalEvents(eventManager.getAllEventNamesOnly());
//        p.displayEventList(chronological);
//    }

    /**
     * This method prints the event list for the entire conference
     */
    protected void viewEventList() {
        List<String> strings = eventManager.getToStringsOfEvents();
        p.displayEventList(strings);
    }

//    /**
//     * This method returns a list of events that will occur.
//     * @return Returns a list of events that have not occurred yet.
//     */
//    protected List<Event> viewFutureEventList() {
//        List<Event> chronological = eventManager.chronologicalEvents(eventManager.getAllEventNamesOnly());
//        List<Event> future = new ArrayList<>();
//        LocalDateTime now = LocalDateTime.now();
//        for (Event curr: chronological){
//            if (eventManager.getTime(curr).compareTo(now) > 0){
//                future.add(curr);
//            }
//        }
//        return future;
//    }

//    /**
//     * Prints the scheduled events of an attendee
//     * @param username: The username of this Attendee
//     */
//    protected void viewSignedUpForEvent(String username) {
//        List<String> signedUpFor = userManager.getAttendingEvents(username);
//        List<Event> chronological = eventManager.chronologicalEvents(signedUpFor);
//        p.displaySignedUpEvents(chronological);
//    }

    /**
     * Prints the scheduled events of an attendee
     * @param username: The username of this Attendee
     */
    protected void viewSignedUpForEvent(String username) {
        List<String> signedUpFor = userManager.getAttendingEvents(username);
        List<String> stringsOfEvents = eventManager.getToStringsOfSignedUpEvents(signedUpFor);
        p.displaySignedUpEvents(stringsOfEvents);
    }

//    /**
//     * Removes an attendee from an event they were signed up for
//     * @param eventName: The name of the Event we want to cancel our spot in
//     */
//    protected void cancelSpotInEvent(String eventName) {
//        Event event = eventManager.getEvent(eventName);
//        userManager.cancelEventSpot(this.username, event, eventManager);
//        p.displaySuccessfulCancellation();
//    }

    /**
     * Removes an attendee from an event they were signed up for
     * @param eventName: The name of the Event we want to cancel our spot in
     */
    protected void cancelSpotInEvent(String eventName) {
        boolean inList = eventManager.checkEventIsRegistered(eventName);
        if (!inList){
            p.displayUnsuccessfulCancellation();
        }
        boolean successful = userManager.cancelEventSpot(this.username, eventName);
        if (successful){
            p.displaySuccessfulCancellation();
        }
        else{
            p.displayUnsuccessfulCancellation();
        }
    }

    /**
     * Signs an attendee up for a new event
     * @param eventName: The name of the Event we want to sign up for
     */
    protected void signUp(String eventName) {
        //
//        Event event = eventManager.getEvent(eventName);
//        if (userManager.signUpForEvent(this.username, event, eventManager)){
        if (userManager.signUpHelper(username, eventManager, eventName)) {
            p.displayEventSignUp();
        }
        else {
            p.displayEventFull();
        }
    }

    protected void searchForEvents(){

        String resp = "y";
        do{
            String response = p.displayPromptSearchForEvents().toLowerCase();
            while (!response.equals("name") && !response.equals("tag")){
                p.displayInvalidPromptSearchForEvents();
                response = p.displayPromptSearchForEvents().toLowerCase();
            }
            if (response.equals("name")){
                String name = p.displayPromptSearchForEventsByName();
                String string = eventManager.getToStringOfEventByName(name);
                p.displayEventByName(string, name);
            }
            else if (response.equals("tag")){
                String tag = p.displayPromptSearchForEventsByTag();
                List<String> strings = eventManager.getToStringsOfEventsByTag(tag);
                p.displayEventsByTag(strings, tag);
            }
            resp = p.displayPromptSearchForAnotherEvent().toLowerCase();
            while (!resp.equals("y") && !resp.equals("n")){
                p.displayErrorSearchForAnotherEvent();
                resp = p.displayPromptSearchForAnotherEvent().toLowerCase();
            }
        }while(!resp.equals("n"));
    }

    protected void editCorporation(String corporation){
        userManager.setCorporation(corporation, this.username);
    }

    protected void viewBio(){
        p.displayViewBio(userManager.getUser(this.username).getBio());
    }

    protected void editBio(String bio){
        userManager.setBio(bio, this.username);
    }

    protected void viewCorporation(){
        p.displayViewCorporation(userManager.getUser(this.username).getCompany());
    }
}
