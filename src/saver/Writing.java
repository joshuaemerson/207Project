package saver;

import event.EventManager;
import message.MessageManager;
import request.Request;
import request.RequestManager;
import user.UserManager;
import user.attendee.Attendee;
import user.organizer.Organizer;
import user.speaker.Speaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;


public class Writing {

    Connection conn;
    DateTimeFormatter formatter;

    /**
     * Creates a writing object
     * @param conn the connection to the database
     */
    public Writing(Connection conn) {
        this.conn = conn;
        formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    }

    /**
     * Removes all information from the tables
     * @throws SQLException If it can't connect
     */
    public void clearEverything() throws SQLException {
        int clear = 0;
        PreparedStatement dropEL = conn.prepareStatement("DELETE FROM eventlist");
        clear += dropEL.executeUpdate();
        PreparedStatement dropM = conn.prepareStatement("DELETE FROM messages");
        clear += dropM.executeUpdate();
        PreparedStatement dropOE = conn.prepareStatement("DELETE FROM organizingevents");
        clear += dropOE.executeUpdate();
        PreparedStatement dropRQ = conn.prepareStatement("DELETE FROM requests");
        clear += dropRQ.executeUpdate();
        PreparedStatement dropR = conn.prepareStatement("DELETE FROM room");
        clear += dropR.executeUpdate();
        PreparedStatement dropSE = conn.prepareStatement("DELETE FROM speakingevents");
        clear += dropSE.executeUpdate();
        PreparedStatement dropTP = conn.prepareStatement("DELETE FROM talkpanel");
        clear += dropTP.executeUpdate();
        PreparedStatement dropUE = conn.prepareStatement("DELETE FROM userevent");
        clear += dropUE.executeUpdate();
        PreparedStatement dropU = conn.prepareStatement("DELETE FROM users");
        clear += dropU.executeUpdate();
    }

    /**
     * Saves the usermanager information in the database
     * @param userManager The userManager we want to save
     * @throws SQLException If it can't connect
     */
    public void saveUserManager(UserManager userManager) throws SQLException{
        Set<String> setUsernames = userManager.getUserMap().keySet();
        for (String username : setUsernames) {
            PreparedStatement insertGeneral = conn.prepareStatement("insert into users (username, userType, password," +
                    " address, email, company, bio, name) values (?, ?, ?, ?, ?, ?, ?, ?)");
            insertGeneral.setString(1, username);
            String type = userManager.getUserType(userManager.getUser(username));
            insertGeneral.setString(2, type);
            insertGeneral.setString(3, userManager.getPassword(username));
            insertGeneral.setString(4, userManager.getAddress(username));
            insertGeneral.setString(5, userManager.getEmail(username));
            insertGeneral.setString(6, userManager.getCompany(username));
            insertGeneral.setString(7, userManager.getBio(username));
            insertGeneral.setString(8, userManager.getName(username));
            insertGeneral.executeUpdate();
            if(type.equalsIgnoreCase("speaker")){
                List<String> speakingAt = ((Speaker)userManager.getUser(username)).getSpeakingEvents();
                for (String event : speakingAt) {
                    PreparedStatement insertSpeakingAt = conn.prepareStatement("insert into speakingevents (" +
                            "speakerusername, eventname) values (?, ?)");
                    insertSpeakingAt.setString(1, username);
                    insertSpeakingAt.setString(2, event);
                    insertSpeakingAt.executeUpdate();
                }

            }
            else if(type.equalsIgnoreCase("organizer")){
                List<String> attending = ((Organizer)userManager.getUser(username)).getAttendingEvents();
                for (String event : attending) {
                    PreparedStatement insertSpeakingAt = conn.prepareStatement("insert into userevent (" +
                            "username, eventname) values (?, ?)");
                    insertSpeakingAt.setString(1, username);
                    insertSpeakingAt.setString(2, event);
                    insertSpeakingAt.executeUpdate();
                }
                List<String> organizing = ((Organizer)userManager.getUser(username)).getOrganizingEvents();
                for (String event : organizing){
                    PreparedStatement insertSpeakingAt = conn.prepareStatement("insert into organizingevents(" +
                            "organizerusername, eventname) values (?, ?)");
                    insertSpeakingAt.setString(1, username);
                    insertSpeakingAt.setString(2, event);
                    insertSpeakingAt.executeUpdate();
                }
            }
            else if(type.equalsIgnoreCase("attendee") || type.equalsIgnoreCase("vip")){
                List<String> attending = ((Attendee)userManager.getUser(username)).getAttendingEvents();
                for (String event : attending) {
                    PreparedStatement insertSpeakingAt = conn.prepareStatement("insert into userevent (" +
                            "username, eventname) values (?, ?)");
                    insertSpeakingAt.setString(1, username);
                    insertSpeakingAt.setString(2, event);
                    insertSpeakingAt.executeUpdate();
                }
            }

        }

    }

    /**
     * Saves the eventManager information in the database
     * @param eventManager The eventManager we want to save
     * @throws SQLException If it can't connect
     */
    public void saveEventManager(EventManager eventManager) throws SQLException {
        List<List<Integer>> rooms = eventManager.getEffectiveRoomList();
        for (List<Integer> room : rooms){
            PreparedStatement insertRoom = conn.prepareStatement("insert into room (" +
                    "roomnumber, capacity, computers, projector, tables, chairs) values (?, ?, ?, ?, ?, ?)");
            insertRoom.setInt(1, room.get(0));
            insertRoom.setInt(2, room.get(1));
            insertRoom.setInt(3, room.get(2));
            insertRoom.setInt(4, room.get(3));
            insertRoom.setInt(5, room.get(4));
            insertRoom.setInt(6, room.get(5));
            insertRoom.executeUpdate();
        }
        List<String> eventNames = eventManager.getAllEventNamesOnly();
        for(String name : eventNames){
            PreparedStatement insertEvent = conn.prepareStatement("insert into eventlist (" +
                    "eventname, time, duration, roomnumber, capacity, requiredComputers, requiredprojector, " +
                    "requiredChairs, requiredTABLES, vipevent, tag, eventtype) values (?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?)");
            insertEvent.setString(1, name);
            LocalDateTime outTime = eventManager.getTime(name);
            String date = outTime.format(formatter);
            insertEvent.setString(2, date);
            insertEvent.setInt(3, eventManager.getDuration(name));
            insertEvent.setInt(4, eventManager.getRoomNumber(name));
            insertEvent.setInt(5, eventManager.getRoomCapacity(eventManager.getRoomNumber(name)));
            insertEvent.setInt(6, eventManager.getComputers(name));
            int outProjector = 0;
            if(eventManager.getProjector(name)) outProjector = 1;
            insertEvent.setInt(7, outProjector);
            insertEvent.setInt(8, eventManager.getChairs(name));
            insertEvent.setInt(9, eventManager.getTables(name));
            int vipEvent = 0;
            if(eventManager.getVipEvent(name)) vipEvent = 1;
            insertEvent.setInt(10, vipEvent);
            insertEvent.setString(11, eventManager.getTag(name));
            insertEvent.setString(12, eventManager.getType(name));
            insertEvent.executeUpdate();
            if (eventManager.getType(name).equalsIgnoreCase("talk")){
                PreparedStatement insertSpeaker = conn.prepareStatement("insert into talkpanel (eventname, " +
                        "speakerusername) values (?, ?)");
                insertSpeaker.setString(1, name);
                insertSpeaker.setString(2, eventManager.getSpeakerName(name));
                insertSpeaker.executeUpdate();
            }
            else if(eventManager.getType(name).equalsIgnoreCase("panel")){
                List<String> speakers = eventManager.getSpeakerList(name);
                for (String speaker : speakers){
                    PreparedStatement insertSpeaker = conn.prepareStatement("insert into talkpanel (eventname, " +
                            "speakerusername) values (?, ?)");
                    insertSpeaker.setString(1, name);
                    insertSpeaker.setString(2, speaker);
                    insertSpeaker.executeUpdate();
                }
            }
        }
    }

    /**
     * Saves the messageManager information in the database
     * @param messageManager The userManager we want to save
     * @throws SQLException If it can't connect
     */
    public void saveMessageManager(MessageManager messageManager) throws SQLException{
        Set<String> usernames = messageManager.getAllUserMessages().keySet();
        for (String username : usernames){
            List<List<String>> messages = messageManager.generateEffectiveMessageList(username, "all");
            for (List<String> message : messages){
                PreparedStatement insertMessage = conn.prepareStatement("insert into messages (content, " +
                        "senderUsername, recepientUsername, beenread, starred, deleted, archived, datetimecreated," +
                        " datetimedeleted, datetimecreatedcopy) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                insertMessage.setString(1, message.get(1));
                insertMessage.setString(3, username);
                insertMessage.setString(2, message.get(0));
                int beenread = 0;
                if(Boolean.parseBoolean(message.get(3))) beenread = 1;
                insertMessage.setInt(4, beenread);
                int starred = 0;
                if(Boolean.parseBoolean(message.get(4))) starred = 1;
                insertMessage.setInt(5, starred);
                int deleted = 0;
                if(Boolean.parseBoolean(message.get(5))) deleted = 1;
                insertMessage.setInt(6, deleted);
                int archived = 0;
                if(Boolean.parseBoolean(message.get(6))) archived = 1;
                insertMessage.setInt(7, archived);
                String created = message.get(2);
                insertMessage.setString(8, created);
                if (deleted == 1){
                    String deletedAt = message.get(10);
                    insertMessage.setString(9, deletedAt);
                }
                else{
                    insertMessage.setString(9, "-1");
                }
                insertMessage.setString(10, message.get(11));
                insertMessage.executeUpdate();
            }
        }

    }

    /**
     * Saves the requestManager information in the database
     * @param requestManager The userManager we want to save
     * @throws SQLException If it can't connect
     */
    public void saveRequestManager(RequestManager requestManager) throws SQLException{
        Set<String> usernames = requestManager.getAllRequests().keySet();
        for(String username : usernames){
            List<Request> requests = requestManager.getUserRequests(username);
            for (Request request : requests){
                PreparedStatement insertRequests = conn.prepareStatement("insert into requests (requestusername, " +
                        "requeststatus, content) values (?, ?, ?)");
                insertRequests.setString(1, username);
                insertRequests.setString(2, requestManager.getRequestStatus(request));
                insertRequests.setString(3, requestManager.getRequestContent(request));
                insertRequests.executeUpdate();
            }


        }
    }





}
