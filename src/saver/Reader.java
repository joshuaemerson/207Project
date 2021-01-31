package saver;

import event.EventManager;
import message.MessageManager;
import request.RequestManager;
import user.UserManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class Reader {

    Connection conn;
    DateTimeFormatter formatter;

    public Reader(Connection conn) {
        this.conn = conn;
        formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    }

    /**
     * Reads in the userManager information from the database
     * @throws SQLException If it can't connect
     * @return The newly read in userManager
     */
    public UserManager readInUsers() throws SQLException {
        UserManager userManager = new UserManager();
        PreparedStatement getUsers = conn.prepareStatement("select * from users");
        ResultSet allUsers = getUsers.executeQuery();
        while (allUsers.next()) {
            String username = allUsers.getString("username");
            String password = allUsers.getString("password");
            String address = allUsers.getString("address");
            String email = allUsers.getString("email");
            String company = allUsers.getString("company");
            String bio = allUsers.getString("bio");
            String name = allUsers.getString("name");
            String type = allUsers.getString("userType");
            userManager.addUser(name, address, email, username, password, type, company, bio);
            if (type.equalsIgnoreCase("speaker")) {
                PreparedStatement getSpeaking = conn.prepareStatement("select eventname from speakingevents where" +
                        " speakerusername = ?");
                getSpeaking.setString(1, username);
                ResultSet allSpeaking = getSpeaking.executeQuery();
                List<String> speakingAt = new ArrayList<>();
                while (allSpeaking.next()) {
                    userManager.addSpeakingEvent(username, allSpeaking.getString("eventname"));
                }
            } else {
                PreparedStatement getAttendeeEvent = conn.prepareStatement("select eventname from userevent where " +
                        "username = ?");
                getAttendeeEvent.setString(1, username);
                ResultSet eventsSet = getAttendeeEvent.executeQuery();
                while (eventsSet.next()) {
                    userManager.directSignUp(username, eventsSet.getString("eventname"));
                }
            }
            if (type.equalsIgnoreCase("organizer")) {
                PreparedStatement getCreatedEvent = conn.prepareStatement("select eventname from organizingevents" +
                        " where organizerusername = ?");
                getCreatedEvent.setString(1, username);
                ResultSet createdSet = getCreatedEvent.executeQuery();
                List<String> curr = new ArrayList<>();
                curr.add(username);
                while (createdSet.next()) {
                    userManager.createdEvent(createdSet.getString("eventname"), curr);
                }
            }
        }
        return userManager;
    }

    /**
     * Reads in the messageManager information from the database
     * @throws SQLException If it can't connect
     * @return The newly read in messageManager
     */
    public MessageManager readInMessages() throws SQLException {
        MessageManager messageManager = new MessageManager();
        PreparedStatement getUsernames = conn.prepareStatement("select username from users");
        ResultSet usernames = getUsernames.executeQuery();
        while(usernames.next()) {
            messageManager.addUserInbox(usernames.getString("username"));
        }
        PreparedStatement getAllMessages = conn.prepareStatement("select content, senderUsername, " +
                "recepientUsername, beenread, starred, deleted, archived, datetimecreated, datetimedeleted, " +
                "datetimecreatedcopy from messages");
        ResultSet messagesSet = getAllMessages.executeQuery();
        while(messagesSet.next()){
            String content = messagesSet.getString("content");
            String senderUsername = messagesSet.getString("senderUsername");
            String recipientUsername = messagesSet.getString("recepientUsername");
            boolean beenread = false;
            boolean starred = false;
            boolean archived = false;
            boolean deleted = false;
            int inbeenread = messagesSet.getInt("beenread");
            int instarred = messagesSet.getInt("starred");
            int inarchived = messagesSet.getInt("archived");
            int indeleted = messagesSet.getInt("deleted");
            if (inbeenread == 1) beenread = true;
            if (instarred == 1) starred = true;
            if (inarchived == 1) archived = true;
            if (indeleted == 1) deleted = true;
            String inCopy = messagesSet.getString("datetimecreatedcopy");
            LocalDateTime dateTimeCreatedCopy = LocalDateTime.parse(inCopy, formatter);
            String inCreated = messagesSet.getString("datetimecreated");
            LocalDateTime dateTimeCreated = LocalDateTime.parse(inCreated, formatter);
            LocalDateTime dateTimeDeleted;
            String inDeleted = messagesSet.getString("datetimedeleted");
            if(inDeleted.equalsIgnoreCase("-1")) dateTimeDeleted = null;
            else  dateTimeDeleted = LocalDateTime.parse(inDeleted, formatter);
            messageManager.addMessage(senderUsername, content, recipientUsername, beenread, dateTimeCreated,
                    dateTimeDeleted, starred, deleted, archived, dateTimeCreatedCopy);
        }
        return messageManager;
    }

    /**
     * Reads in the requestManager information from the database
     * @throws SQLException If it can't connect
     * @return The newly read in requestManager
     */
    public RequestManager readInRequests() throws SQLException{
        RequestManager requestManager = new RequestManager();
        PreparedStatement getUsernames = conn.prepareStatement("select username from users");
        ResultSet usernames = getUsernames.executeQuery();
        while(usernames.next()) {
            requestManager.addUserRequests(usernames.getString("username"));
        }
        PreparedStatement getAllRequests = conn.prepareStatement("select * from requests");
        ResultSet allRequests = getAllRequests.executeQuery();
        while(allRequests.next()){
            String requesterUsername = allRequests.getString("requestusername");
            String requestStatus = allRequests.getString("requeststatus");
            String content = allRequests.getString("content");
            requestManager.createNewRequest(content, requesterUsername, requestStatus);
        }
        return requestManager;
    }

    /**
     * Reads in the eventManager information from the database
     * @throws SQLException If it can't connect
     * @return The newly read in eventManager
     */
    public EventManager readInEvents() throws SQLException{
        EventManager eventManager = new EventManager();
        PreparedStatement getAllRooms = conn.prepareStatement("select * from room");
        ResultSet allRooms = getAllRooms.executeQuery();
        while(allRooms.next()){
            int roomNumber = allRooms.getInt("roomnumber");
            int capacity = allRooms.getInt("capacity");
            int computers = allRooms.getInt("computers");
            int inProjector = allRooms.getInt("projector");
            boolean projector = false;
            if(inProjector == 1) projector = true;
            int chairs = allRooms.getInt("chairs");
            int tables = allRooms.getInt("tables");
            eventManager.addRoom(roomNumber, capacity, computers, projector, chairs, tables);
        }
        PreparedStatement getAllEvents = conn.prepareStatement("select * from eventlist");
        ResultSet allEvents = getAllEvents.executeQuery();
        while(allEvents.next()){
            String type = allEvents.getString("eventtype");
            String name = allEvents.getString("eventname");
            PreparedStatement getCreators = conn.prepareStatement("select organizerusername from organizingevents " +
                    "where eventname = ?");
            getCreators.setString(1, name);
            ResultSet allCreators = getCreators.executeQuery();
            List<String> creators = new ArrayList<>();
            while(allCreators.next()){
                creators.add(allCreators.getString("organizerusername"));
            }
            PreparedStatement getAttendees = conn.prepareStatement("select username from userevent " +
                    "where eventname = ?");
            getAttendees.setString(1, name);
            ResultSet allAttendees = getAttendees.executeQuery();
            List<String> attendees = new ArrayList<>();
            while(allAttendees.next()){
                attendees.add(allAttendees.getString("username"));
            }
            LocalDateTime time = LocalDateTime.parse(allEvents.getString("time"), formatter);
            int duration = allEvents.getInt("duration");
            int roomNumber = allEvents.getInt("roomnumber");
            int capacity = allEvents.getInt("capacity");
            int computers = allEvents.getInt("requiredComputers");
            int inProjector = allEvents.getInt("requiredprojector");
            boolean projector = false;
            if(inProjector == 1) projector = true;
            int chairs = allEvents.getInt("requiredChairs");
            int tables = allEvents.getInt("requiredTABLES");
            int inVIP = allEvents.getInt("vipevent");
            boolean VIP = false;
            if(inVIP == 1) VIP = true;
            String tag = allEvents.getString("tag");
            if (type.equalsIgnoreCase("party")){
                eventManager.addEvent(type, name, time, duration, roomNumber, capacity, computers, projector, chairs,
                        tables, creators, VIP, null, null, tag);
            }
            else if (type.equalsIgnoreCase("talk")){
                PreparedStatement getSpeaker = conn.prepareStatement("select speakerusername from talkpanel " +
                        "where eventname = ?");
                getSpeaker.setString(1, name);
                ResultSet allSpeaker = getSpeaker.executeQuery();
                allSpeaker.next();
                String speaker = allSpeaker.getString("speakerusername");
                eventManager.addEvent(type, name, time, duration, roomNumber, capacity, computers, projector, chairs,
                        tables, creators, VIP, speaker, null, tag);
            }
            else if (type.equalsIgnoreCase("panel")){
                PreparedStatement getSpeakers = conn.prepareStatement("select speakerusername from talkpanel " +
                        "where eventname = ?");
                getSpeakers.setString(1, name);
                ResultSet allSpeakers = getSpeakers.executeQuery();
                List<String> speakers = new ArrayList<>();
                while(allSpeakers.next()){
                    speakers.add(allSpeakers.getString("speakerusername"));
                }
                eventManager.addEvent(type, name, time, duration, roomNumber, capacity, computers, projector, chairs,
                        tables, creators, VIP, null, speakers, tag);
            }
            for (String attendee : attendees) {
                eventManager.addAttendee(name, attendee);
            }
        }
        return eventManager;
    }
}
