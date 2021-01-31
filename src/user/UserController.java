package user;

import event.EventManager;
import message.MessageManager;
import request.Request;
import request.RequestManager;

import java.time.LocalDateTime;
import java.util.List;

public abstract class UserController {
    public UserManager userManager;
    public EventManager eventManager;
    public MessageManager messageManager;
    public String username;
    public RequestManager requestManager;
    UserPresenter p;

    /**
     * Creates a UserController
     *
     * @param userManager    user use case
     * @param eventManager   event use case
     * @param messageManager message use case
     * @param username       username of the user
     * @param request        request user case
     */
    public UserController(UserManager userManager, EventManager eventManager, MessageManager messageManager,
                          String username, RequestManager request) {
        this.userManager = userManager;
        this.eventManager = eventManager;
        this.messageManager = messageManager;
        this.username = username;
        this.requestManager = request;
        p = new UserPresenter();
    }

    /**
     * Displays all of the messages this user has received of a certain type.
     * @param messageList Refers to a list of messages (encoded as a list of strings) that live in the inbox we display.
     * @param inboxType Refers to the type of inbox that the messages live in.
     */
    protected void viewMessages(List<List<String>> messageList, String inboxType) {

        if (messageList.size() == 0) {
            if (inboxType.equalsIgnoreCase("inbox")) {
                p.displayEmptyInbox();
            } else if (inboxType.equalsIgnoreCase("starred")) {
                p.displayEmptyStarredInbox();
            } else if (inboxType.equalsIgnoreCase("deleted")) {
                p.displayEmptyDeletedInbox();
            } else if (inboxType.equalsIgnoreCase("archived")) {
                p.displayEmptyArchivedInbox();
            }
        } else {
            // display all the user's messages
            int counter = 1;

            for (List<String> effectiveMessage : messageList) {
                p.displayUserMessages(effectiveMessage, counter, inboxType);
                counter++;
            }

            // prompt the user to choose what message to read
            int requestedMessage = messageList.size() - p.displaySelectMessage() + 1;
            while (requestedMessage > messageList.size() || requestedMessage < 1) {
                p.displayMessageNonExistent();
                requestedMessage = p.displaySelectMessage();
            }

            List<String> effectiveRequestedMessage = messageList.get(messageList.size() - requestedMessage);
            p.displayFullMessage(effectiveRequestedMessage);

            // based on inbox type and choice, allow user to select what action to take
            String messageAction;
            switch (inboxType){
                case "deleted":
                    messageAction = p.displayDeletedActionPrompt();
                    break;
                case "archived":
                    messageAction = p.displayArchivedActionPrompt();
                    break;
                default:
                    messageAction = p.displayMessageActionPrompt();
                    break;
            }
            takeMessageAction(messageAction, effectiveRequestedMessage, inboxType);

        }
    }


    /**
     * Prompts the user to take an action (and performs this action) on a selected message.
     * @param messageAction Refers to the action that the user wants to take on the message.
     * @param selectedMessage Refers to the message (encoded as a list of strings) to be acted upon.
     * @param inboxType Refers to the type of inbox that the message lives in.
     */
    protected void takeMessageAction(String messageAction, List<String> selectedMessage, String inboxType) {

        String username = selectedMessage.get(8);
        int index = Integer.parseInt(selectedMessage.get(7));
        boolean isStarred = (selectedMessage.get(4).equals("true"));
        boolean isPinned = (selectedMessage.get(9).equals("true"));
        messageManager.setMessageReadStatus(messageManager.getMessageAtIndex(username, index), "read");


        // actions common to all:
        if (messageAction.equalsIgnoreCase("REPLY")) {
            String content = p.displayEnterMessagePrompt();
            replyMessage(content, selectedMessage.get(0));
        } else if (messageAction.equalsIgnoreCase("CLOSE")) {
        }

        switch (inboxType) {
            case "inbox":
            case "starred":
                while (!messageAction.equalsIgnoreCase("REPLY") &&
                        !messageAction.equalsIgnoreCase("MARK AS UNREAD") &&
                        !messageAction.equalsIgnoreCase("CLOSE") &&
                        !messageAction.equalsIgnoreCase("MARK AS STARRED") &&
                        !messageAction.equalsIgnoreCase("UNSTAR") &&
                        !messageAction.equalsIgnoreCase("DELETE") &&
                        !messageAction.equalsIgnoreCase("ARCHIVE") &&
                        !messageAction.equalsIgnoreCase("PIN") &&
                        !messageAction.equalsIgnoreCase("UNPIN")) {
                    p.displayInvalidInput();
                    messageAction = p.displayMessageActionPrompt();
                }

                if (messageAction.equalsIgnoreCase("MARK AS UNREAD")) {
                    messageManager.setMessageReadStatus(messageManager.getMessageAtIndex(username, index), "unread");
                } else if (messageAction.equalsIgnoreCase("MARK AS STARRED")) {
                    if (isStarred) {
                        p.displayStarError();
                    } else {
                        messageManager.setMessageStarredStatus(messageManager.getMessageAtIndex(username, index), "starred");
                    }
                } else if (messageAction.equalsIgnoreCase("UNSTAR")) {
                    if (isStarred) {
                        messageManager.setMessageStarredStatus(messageManager.getMessageAtIndex(username, index), "unstar");
                    } else {
                        p.displayUnstarError();
                    }
                } else if (messageAction.equalsIgnoreCase("DELETE")) {
                    messageManager.setDeletionStatus(messageManager.getMessageAtIndex(username, index), "delete");
                } else if (messageAction.equalsIgnoreCase("ARCHIVE")) {
                    messageManager.setMessageStarredStatus(messageManager.getMessageAtIndex(username, index), "unstar");
                    messageManager.setDateTimeCreatedStatus(messageManager.getMessageAtIndex(username, index), "unpin",
                            messageManager.getMessageAtIndex(username, index).getDateTimeCreatedCopy());
                    messageManager.setArchiveStatus(messageManager.getMessageAtIndex(username, index), "archive");
                } else if (messageAction.equalsIgnoreCase("PIN")) {
                    if (isPinned) {
                        p.displayPinnedError();
                    } else {
                        LocalDateTime newLDT = LocalDateTime.now();
                        LocalDateTime pinLDT = newLDT.plusYears(2);
                        messageManager.setDateTimeCreatedStatus(messageManager.getMessageAtIndex(username, index), "pin", pinLDT);
                    }
                } else if (messageAction.equalsIgnoreCase("UNPIN")) {
                    if (isPinned) {
                        messageManager.setDateTimeCreatedStatus(messageManager.getMessageAtIndex(username, index), "unpin",
                                messageManager.getMessageAtIndex(username, index).getDateTimeCreatedCopy());
                    } else {
                        p.displayUnpinnedError();
                    }
                }


                break;
            case "deleted":
                while (!messageAction.equalsIgnoreCase("DELETE") &&
                        !messageAction.equalsIgnoreCase("RESTORE") &&
                        !messageAction.equalsIgnoreCase("CLOSE")) {
                    p.displayInvalidInput();
                    messageAction = p.displayDeletedActionPrompt();
                }
                if (messageAction.equalsIgnoreCase("DELETE")) {
                    if (p.displayDeleteConfirmation().equalsIgnoreCase("YES")) {
//                    deletedMessages.remove(selectedMessage);
                        messageManager.getAllUserMessages().get(username).remove(messageManager.getMessageAtIndex(username, index));
                    }
                } else if (messageAction.equalsIgnoreCase("RESTORE")) {
//                deletedMessages.remove(selectedMessage);
                    messageManager.setDeletionStatus(messageManager.getMessageAtIndex(username, index), "restore");
                }
                break;
            case "archived":
                while (!messageAction.equalsIgnoreCase("UNARCHIVE") &&
                        !messageAction.equalsIgnoreCase("CLOSE") &&
                        !messageAction.equalsIgnoreCase("REPLY")) {
                    p.displayInvalidInput();
                    messageAction = p.displayDeletedActionPrompt();
                }
                if (messageAction.equalsIgnoreCase("UNARCHIVE")) {
//                archivedMessages.remove(selectedMessage);
                    messageManager.setArchiveStatus(messageManager.getMessageAtIndex(username, index), "restore");
                }
                break;
        }
    }





    protected void deletedMessagesCheck() {
        messageManager.deletedMessagesCheckMM(username);
    }

    /**
     * Sends a reply to the oldest message in an attendees inbox
     * @param recipient: The username of the User we are replying too
     * @param content: The content of the message the Attendee is sending
     */
    protected void replyMessage(String content, String recipient){
        messageManager.addMessage(username, content, recipient);
        p.displayMessageSentPrompt();
    }

    /**
     * Displays the requests made by a user with given username
     * @param username The String username of the user
     */
    protected void viewRequests(String username){
        List<List<String>> userRequestInfo = requestManager.getUsersRequestInfo(username);
        p.displayRequestsHeader(userRequestInfo);
        for(List<String> requestInfo : userRequestInfo){
            p.displayRequestsBody(requestInfo.get(0), requestInfo.get(1));
        }
    }

    /**
     * Creates a new request
     * @param content the String content of the request
     * @param username the String username of the individual making the request
     */
    protected void makeRequest(String content, String username){
        Request request = requestManager.createNewRequest(content, username);
        requestManager.addRequest(username, request);
    }
}


