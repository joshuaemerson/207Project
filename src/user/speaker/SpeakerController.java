package user.speaker;

import event.Event;
import event.EventManager;
import message.MessageManager;
import request.RequestManager;
import user.UserManager;
import user.attendee.AttendeeController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * A controller that deals with Speaker users
 */
public class SpeakerController extends AttendeeController {
    SpeakerPresenter p;

    /**
     * Creates a Speaker Controller
     * @param userManager user use case
     * @param eventManager event use case
     * @param messageManager message use case
     * @param username username of the user
     * @param request the request
     */
    public SpeakerController(UserManager userManager, EventManager eventManager, MessageManager messageManager,
                             String username, RequestManager request){
        super(userManager, eventManager, messageManager, username, request);
        p = new SpeakerPresenter();
    }
    /**
     * Runs the Speaker controller by asking for input and performing the actions
     */
    public void run(){
        deletedMessagesCheck();
        p.displayOptions3();
        p.displayTaskInput();
        int input = p.nextInt();
        final int END_CONDITION = 4;
        while (input != END_CONDITION){
            deletedMessagesCheck();
            determineInput(input);
            input = p.nextInt();
        }
    }



    /**
     * Used to determine what task the user would like to do based off their keyboard input.
     * @param input: The integer value that corresponds to the task the user would like to do.
     */
    private void determineInput(int input){
        switch (input){
            case 0:
                p.displayMessageOptions();
                int choice = p.nextInt();
                final int endCond = 6;
                while (choice != endCond) {
                    determineInputMessage(choice);
                    choice = p.nextInt();
                }
                break;

            case 1:
                p.displayEventOptions();
                int choice1 = p.nextInt();
                final int endCond1 = 1;
                while (choice1 != endCond1) {
                    determineInputEvent(choice1);
                    choice1 = p.nextInt();
                }
                break;

            case 2:
                p.displayRequestOptions();
                int choice3 = p.nextInt();
                final int endCond3 = 2;
                while (choice3 != endCond3) {
                    determineInputRequest(choice3);
                    choice3 = p.nextInt();
                }
                break;

            case 3:
                p.displayUserOptions();
                int choice4 = p.nextInt();
                final int endCond4 = 4;
                while (choice4 != endCond4){
                    determineInput3(choice4);
                    choice4 = p.nextInt();
                }
                break;

            case 5:
                p.displayOptions3();
                break;

            default:
                p.displayInvalidInputError();
                break;
        }
        deletedMessagesCheck();
        p.displayNextTaskPromptSpeaker();
        p.displayOptions3();
    }

    /**
     * Used to determine what message task the user would like to do based off their keyboard input.
     * @param input: The integer value that corresponds to the task the user would like to do.
     */
    private void determineInputMessage(int input){
        switch (input) {
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
                List<String> speakingEventsNames = userManager.getSpeakingEvents(username);
                List<String> speakingEventsToString = eventManager.getToStringsOfSpeakingEvents(speakingEventsNames);
                p.displayAllEventsGiven(speakingEventsToString);
                if (speakingEventsNames.size() == 0){
                    break;
                }

                p.displayEnterNumberOfEventsToMessage();
                int num = p.nextInt();

                while(num < 1 || num > speakingEventsNames.size()){
                    num = p.displayInvalidNumberOfEventsToMessage();
                    if (num == -1){
                        break;
                    }
                }
                if (num == -1){
                    break;
                }
                String next = "";
                List<String> eventNames = new ArrayList<>();
                for (int i = 0; i < num; i++) {
                    if (i == 0) {
                        next = p.displayEnterEventNamePrompt();
                    }
                    else {
                        next = p.displayEnterEventNamePrompt2();
                    }
                    if (next.equals("q")){
                        break;
                    }
                    if (speakingEventsNames.contains(next) && !eventNames.contains(next)) {
                        eventNames.add(next);
                    }
                    else if(speakingEventsNames.contains(next)){
                        p.displayEventAlreadyAddedError();
                        i--;
                    }
                    else if(!speakingEventsNames.contains(next)){
                        p.displayEventNotGivenError();
                        i--;
                    }
                }
                if(next.equals("q")) {
                    break;
                }
                String message = p.displayEnterMessagePrompt();

                sendBlastMessage(eventNames, message);
                break;

            case 5:
                String eventName = p.displayEventSelectorPrompt();
                viewScheduledEvents(username);
                if(eventManager.getAllEvents().containsKey(eventName)){
                    Set<String> eventAttendees = eventManager.getEventAttendees(eventName);
                    String toMessage = p.displayEventAttendeesList(eventAttendees);
                    ArrayList<String> usernameList = new ArrayList<>(eventAttendees);
                    if(usernameList.contains(toMessage)){
                        String messageContent = p.displayEnterMessagePrompt();
                        replyMessage(messageContent, toMessage);
                        break;
                    } else {
                        p.displayInvalidInputError();
                    }
                } else{
                    p.displayInvalidInputError();
                }
                break;

            default:
                p.displayMessageOptionsInvalidChoice();
                break;
        }
        deletedMessagesCheck();
        p.displayNextTaskPrompt();
        p.displayMessageOptions();
    }

    private void determineInputEvent(int input){
        if (input == 0) {
            viewScheduledEvents(this.username);
        } else {
            p.displayEventOptionsInvalidChoice();
        }
        deletedMessagesCheck();
        p.displayNextTaskPrompt();
        p.displayEventOptions();
    }

    private void determineInputRequest(int input){
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
        p.displayNextTaskPrompt();
        p.displayRequestOptions();
    }

    private void viewScheduledEvents(String username){
        List<String> allEvents = userManager.getSpeakingEvents(username);
        List<String> notHappened = eventManager.eventNotHappened(allEvents);
        List<Event> chronological  = eventManager.chronologicalEvents(notHappened);
        p.displayAllFutureEventsGiving(chronological);
    }

    private void sendBlastMessage(List<String> eventNames, String message){
        messageManager.speakerBlastMessage(eventNames, message, eventManager, this.username);
        p.displayMessageSentPrompt2();
    }
}
