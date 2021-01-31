package user.vip;

import event.EventManager;
import message.MessageManager;
import request.RequestManager;
import user.UserManager;
import user.attendee.AttendeeController;
import user.attendee.AttendeePresenter;

import java.util.List;

public class VipController extends AttendeeController {

    AttendeePresenter p;

    /**
     * This constructs a VipController object
     * @param userManager the instance of the User Manager
     * @param eventManager the instance of the Event Manager
     * @param messageManager the instance of the Message Manager
     * @param username the username of the user accessing the VipController
     * @param requestManager the instance of the Request Manager.
     */
    public VipController(UserManager userManager, EventManager eventManager, MessageManager messageManager, String username, RequestManager requestManager) {
        super(userManager, eventManager, messageManager, username, requestManager);
        this.p = new AttendeePresenter();
    }

    /**
     * Determines the user input for which event they want to sign up for.
     */
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
        while (!eventManager.checkEventIsRegistered(eventSignedUp)){
            eventSignedUp = p.displayInvalidEventSignUp();
            if (eventSignedUp.equalsIgnoreCase("q")){
                break;
            }
        }
        if (!eventSignedUp.equalsIgnoreCase("q")) {
            signUp(eventSignedUp);
        }

    }

}
