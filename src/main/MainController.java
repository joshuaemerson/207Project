package main;

import event.EventManager;
import message.MessageManager;
import request.RequestManager;
import saver.Connector;
import user.UserManager;
import user.attendee.AttendeeController;
import user.organizer.OrganizerController;
import user.speaker.SpeakerController;
import user.vip.VipController;

import java.sql.SQLException;

/**
 * Refers to the controller class that handles all of the other controller classes.
 */
public class MainController {
    protected MessageManager messageManager;
    protected UserManager userManager;
    protected EventManager eventManager;
    protected RequestManager requestManager;
    protected String username;
    MainPresenter p;
    boolean startingScratch;
    protected Connector conn;

    /**
     * Constructs a MainController object with MessageManager, UserManager, EventManager, ReaderWriter objects,
     * and a String username.
     * @throws SQLException Refers to the exception that is raised when there is a problem accessing the database.
     */
    public MainController() throws SQLException {
        username = "";
        p = new MainPresenter();
        conn = new Connector();
        startingScratch = conn.determineExisting();
    }



//    /**
//     * This method declares three new files for users, messages, and events and returns 0, 1 or 2 based on which files
//     * exist.
//     * @return Returns 0 if only the users is a file, 1 if users, messages, and events are files, and 2 otherwise.
//     */
//    public int filesExist() {
//        File users = new File("users.ser");
//        File messages = new File("messages.ser");
//        File events = new File("events.ser");
//        File rooms = new File("rooms.ser");
//        File requests = new File("requests.ser");
//        // File requestStatuses = new File("requestStatuses.ser"); Don't need to check for - included in requests check
//
//        if (users.isFile() && messages.isFile() && !events.isFile() && !rooms.isFile() && !requests.isFile()) {
//            return 0;
//        }
//        else if (users.isFile() && messages.isFile() && !events.isFile() && rooms.isFile() && !requests.isFile()){
//            return 1;
//        }
//        else if (users.isFile() && messages.isFile() && events.isFile() && rooms.isFile() && !requests.isFile()) {
//            return 2;
//        }
//        if (users.isFile() && messages.isFile() && !events.isFile() && !rooms.isFile() && requests.isFile()) {
//            return 3;
//        }
//        else if (users.isFile() && messages.isFile() && !events.isFile() && rooms.isFile() && requests.isFile()){
//            return 4;
//        }
//        else if (users.isFile() && messages.isFile() && events.isFile() && rooms.isFile() && requests.isFile()) {
//            return 5;
//        }
//        else {
//            return 6; // nothing exists
//        }
//    }
//
//    /**
//     * This method is responsible for determining whether or not the program will use pre-existing files. If the user
//     * wants to, they can load all of the pre-existing files, except for events and rooms.
//     */
//    public void fileQ0() {
//        try {
//            String answer = p.displayPreExistingFilePrompt(); // This reads the answer they give
//            while(!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No")) {
//                answer = p.displayInvalidFileChoice();
//
//            } if (answer.equalsIgnoreCase("Yes")) {
//                readInFiles0(RW, userManager, messageManager);
//                p.displayDownloadCompletion();
//                startingScratch = false;
//            }
//        } catch (InputMismatchException ime) {
//            p.displayInvalidInputError();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * This method is responsible for determining whether or not the program will use pre-existing files. If the user
//     * wants to, they can load all of the pre-existing files, except for events.
//     */
//    public void fileQ1() {
//        try {
//            String answer = p.displayPreExistingFilePrompt(); // This reads the answer they give
//            while(!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No")) {
//                answer = p.displayInvalidFileChoice();
//            } if (answer.equalsIgnoreCase("Yes")) {
//                readInFiles1(RW, userManager, messageManager, eventManager);
//                p.displayDownloadCompletion();
//                startingScratch = false;
//            }
//        } catch (InputMismatchException ime) {
//            p.displayInvalidInputError();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * This method is responsible for determining whether or not the program will use pre-existing files. If the user
//     * wants to, they can load all of the pre-existing files.
//     */
//    public void fileQ2() {
//        try {
//            String answer = p.displayPreExistingFilePrompt(); // This reads the answer they give
//            while(!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No")) {
//                answer = p.displayInvalidFileChoice();
//            } if (answer.equalsIgnoreCase("Yes")) {
//                readInFiles2(RW, userManager, messageManager, eventManager);
//                p.displayDownloadCompletion();
//                startingScratch = false;
//            }
//        } catch (InputMismatchException ime) {
//            p.displayInvalidInputError();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * This method is responsible for determining whether or not the program will use pre-existing files. If the user
//     * wants to, they can load all of the pre-existing files, except for events and rooms.
//     */
//    public void fileQ3() {
//        try {
//            String answer = p.displayPreExistingFilePrompt(); // This reads the answer they give
//            while(!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No")) {
//                answer = p.displayInvalidFileChoice();
//
//            } if (answer.equalsIgnoreCase("Yes")) {
//                readInFiles3(RW, userManager, messageManager, requestManager);
//                p.displayDownloadCompletion();
//                startingScratch = false;
//            }
//        } catch (InputMismatchException ime) {
//            p.displayInvalidInputError();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * This method is responsible for determining whether or not the program will use pre-existing files. If the user
//     * wants to, they can load all of the pre-existing files, except for events.
//     */
//    public void fileQ4() {
//        try {
//            String answer = p.displayPreExistingFilePrompt(); // This reads the answer they give
//            while(!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No")) {
//                answer = p.displayInvalidFileChoice();
//            } if (answer.equalsIgnoreCase("Yes")) {
//                readInFiles4(RW, userManager, messageManager, eventManager, requestManager);
//                p.displayDownloadCompletion();
//                startingScratch = false;
//            }
//        } catch (InputMismatchException ime) {
//            p.displayInvalidInputError();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * This method is responsible for determining whether or not the program will use pre-existing files. If the user
//     * wants to, they can load all of the pre-existing files.
//     */
//    public void fileQ5() {
//        try {
//            String answer = p.displayPreExistingFilePrompt(); // This reads the answer they give
//            while(!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No")) {
//                answer = p.displayInvalidFileChoice();
//            } if (answer.equalsIgnoreCase("Yes")) {
//                readInFiles5(RW, userManager, messageManager, eventManager, requestManager);
//                p.displayDownloadCompletion();
//                startingScratch = false;
//            }
//        } catch (InputMismatchException ime) {
//            p.displayInvalidInputError();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * This method is responsible for calling the appropriate controller depending on the user. At the end, it saves
     * all the users, messages, events, and rooms to the appropriate files.
     * @throws SQLException Refers to the exception that is raised when there is a problem accessing the database.
     */
    public void run() throws SQLException {
        LoginController log = new LoginController();

        if (startingScratch){
            userManager = conn.readInUsers();
            eventManager = conn.readInEvents();
            messageManager = conn.readInMessages();
            requestManager = conn.readInRequests();
            this.username = log.login(userManager, messageManager, 1,requestManager);
        }
        else {
            userManager = new UserManager();
            eventManager = new EventManager();
            messageManager = new MessageManager();
            requestManager = new RequestManager();
            this.username = log.login(userManager, messageManager, 0, requestManager);
        }
        if (username.equals("q")){
            return;
        }
        String type = this.userManager.getUserType(this.username);
        switch (type) {
            case "organizer": {
                OrganizerController controller = new OrganizerController(userManager, eventManager, messageManager, username, requestManager);
                controller.run();
                break;
            }
            case "attendee": {
                AttendeeController controller = new AttendeeController(userManager, eventManager, messageManager, username, requestManager);
                controller.run();
                break;
            }
            case "speaker": {
                SpeakerController controller = new SpeakerController(userManager, eventManager, messageManager, username, requestManager);
                controller.run();
                break;
            }
            case "vip":{
                VipController controller = new VipController(userManager, eventManager, messageManager, username, requestManager);
                controller.run();
                break;
            }
        }

        conn.clearEverything();
        conn.saveUserManager(userManager);
        conn.saveEventManager(eventManager);
        conn.saveMessageManager(messageManager);
        conn.saveRequestManager(requestManager);

//        RW.write(userManager.getUserMap(), "users");
//        RW.write(messageManager.getAllUserMessages(), "messages");
//        RW.write(eventManager.getAllEvents(), "events");
//        RW.writeList(eventManager.getRooms());
//        RW.write(requestManager.getAllRequests(), "requests");
//        RW.write(requestManager.getAllRequestStatuses(), "requestStatuses");
        conn.end();
        p.displaySignedOut();
    }

//    private void readInFiles0(ReaderWriter RW, UserManager UM, MessageManager MM) throws IOException, ClassNotFoundException {
//        UM.setUserMapReadIn();
//        MM.setAllUserMessagesReadIn();
//    }
//
//    private void readInFiles1(ReaderWriter RW, UserManager UM, MessageManager MM, EventManager EM) throws IOException, ClassNotFoundException {
//        UM.setUserMapReadIn();
//        MM.setAllUserMessagesReadIn();
//        EM.setRoomsReadIn();
//    }
//
//    private void readInFiles2(ReaderWriter RW, UserManager UM, MessageManager MM, EventManager EM) throws IOException, ClassNotFoundException {
//        UM.setUserMapReadIn();
//        MM.setAllUserMessagesReadIn();
//        EM.setAllEventsReadIn();
//        EM.setRoomsReadIn();
//    }
//
//    private void readInFiles3(ReaderWriter RW, UserManager UM, MessageManager MM, RequestManager RM) throws IOException, ClassNotFoundException {
//        UM.setUserMapReadIn();
//        MM.setAllUserMessagesReadIn();
//        RM.setAllRequestsReadIn();
//        RM.setAllRequestStatusesReadIn();
//    }
//
//    private void readInFiles4(ReaderWriter RW, UserManager UM, MessageManager MM, EventManager EM, RequestManager RM) throws IOException, ClassNotFoundException {
//        UM.setUserMapReadIn();
//        MM.setAllUserMessagesReadIn();
//        EM.setRoomsReadIn();
//        RM.setAllRequestsReadIn();
//        RM.setAllRequestStatusesReadIn();
//    }
//
//    private void readInFiles5(ReaderWriter RW, UserManager UM, MessageManager MM, EventManager EM, RequestManager RM) throws IOException, ClassNotFoundException {
//        UM.setUserMapReadIn();
//        MM.setAllUserMessagesReadIn();
//        EM.setAllEventsReadIn();
//        EM.setRoomsReadIn();
//        RM.setAllRequestsReadIn();
//        RM.setAllRequestStatusesReadIn();
//    }
}
// Give RW to each use case
// change where RW method is called
//move it into the use case instead of controller call it
//controller calls use case which the calls gateway