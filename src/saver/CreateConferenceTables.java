package saver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateConferenceTables {

    /**
     * Sets up the  tables for the database
     * @param args The passed-in command line arguments
     */
    public static void main(String [] args){
        try( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/conference",
                "root", "1257g08#E")){

            //list of events
            PreparedStatement dropEL = conn.prepareStatement("CREATE TABLE eventlist(eventname varchar(200) PRIMARY" +
                    " KEY, time varchar(50), duration int, roomnumber int, capacity int, requiredComputers int, " +
                    "requiredprojector int, requiredChairs int, requiredTABLES int, vipevent int, tag varchar(100), " +
                    "eventtype varchar(100))");
            int clear = dropEL.executeUpdate();

            //list of messages
            PreparedStatement dropM = conn.prepareStatement("CREATE TABLE messages (id int  PRIMARY KEY AUTO_INCREMENT" +
                    ", content varchar(500), senderUsername varchar(100), recepientUsername varchar(100), beenread int," +
                    " starred int, deleted int, archived int, datetimecreated varchar(30), datetimedeleted varchar(30), " +
                    "datetimecreatedcopy varchar(30))");
            clear += dropM.executeUpdate();

            //maps organizers to events they have created
            PreparedStatement dropOE = conn.prepareStatement("CREATE TABLE organizingevents (organizerusername " +
                    "varchar(100), eventname varchar(200), PRIMARY KEY (organizerusername, eventname))");
            clear += dropOE.executeUpdate();

            //list of requests
            PreparedStatement dropRQ = conn.prepareStatement("CREATE TABLE requests (id int AUTO_INCREMENT, " +
                    "requestusername varchar(100), requeststatus varchar(100), content varchar(200), PRIMARY KEY (id))");
            clear += dropRQ.executeUpdate();

            //the list of rooms
            PreparedStatement dropR = conn.prepareStatement("CREATE TABLE room(roomnumber int, capacity int, " +
                    "computers int, projector int, tables int, chairs int, PRIMARY KEY (roomnumber))");
            clear += dropR.executeUpdate();

            //maps speakers to events they are giving
            PreparedStatement dropSE = conn.prepareStatement("CREATE TABLE speakingevents(speakerusername varchar(100)" +
                    ", eventname varchar(200), PRIMARY KEY (speakerusername, eventname))");
            clear += dropSE.executeUpdate();

            //maps speakers to talks/panels they are giving
            PreparedStatement dropTP = conn.prepareStatement("CREATE TABLE talkpanel(eventname varchar(100), " +
                    "speakerusername varchar(100), PRIMARY KEY (eventname, speakerusername))");
            clear += dropTP.executeUpdate();

            //maps users to events they are attending
            PreparedStatement dropUE = conn.prepareStatement("CREATE TABLE userevent(username varchar(100), " +
                    "eventname varchar(200), PRIMARY KEY (username, eventname))");
            clear += dropUE.executeUpdate();

            // list of users
            PreparedStatement dropU = conn.prepareStatement("CREATE TABLE users(username varchar(100), " +
                    "userType varchar(20), password varchar(100), address varchar(100), email varchar(150), " +
                    "company varchar(100), bio varchar(300), name varchar(100), PRIMARY KEY (`username`))");
            clear += dropU.executeUpdate();

            PreparedStatement insertUsers1 = conn.prepareStatement("INSERT INTO users VALUES('mkent', 'attendee', " +
                    "'is33', '16 Phase One Blvd', 'melody@email.com', 'Microsoft', 'I like listening to talks', 'Melody Kent')");
            int result = insertUsers1.executeUpdate();

            PreparedStatement insertUsers2 = conn.prepareStatement("INSERT INTO users VALUES('tsmith', 'vip', 'pass', " +
                    "'16 East Ave', 'tsmith@email.com', 'Amazon', 'My name is Taylor Smith and I am 20 years old', 'Taylor Smith')");

            result += insertUsers2.executeUpdate();

            PreparedStatement insertSpeaker1 = conn.prepareStatement("INSERT INTO users VALUES('piqcyl', 'speaker', " +
                    "'zed', '1000 Colonial Farm Road Langley Virginia USA', 'piqcyl@ca.gov', 'Canadian Tire', 'This is me', 'John Smith')");
            result += insertSpeaker1.executeUpdate();

            PreparedStatement pairSpeaker1 = conn.prepareStatement("INSERT INTO speakingevents VALUES('piqcyl', 'The Best Talk')");
            result += pairSpeaker1.executeUpdate();

            PreparedStatement tp1 = conn.prepareStatement("INSERT INTO talkpanel VALUES('The Best Talk', 'piqcyl')");
            result += tp1.executeUpdate();

            PreparedStatement insertUsers3 = conn.prepareStatement("INSERT INTO users VALUES('romanovm', 'speaker', " +
                    "'abc123', '143 University Avenue', 'romanov@gmail.com', 'Qoogle', 'I am a motivational speaker', 'Martha Romanov')");
            result += insertUsers3.executeUpdate();

            PreparedStatement pairSpeaker2 = conn.prepareStatement("INSERT INTO speakingevents VALUES('romanovm', " +
                    "'How to make five billion dollars')");
            result += pairSpeaker2.executeUpdate();

            PreparedStatement tp2 = conn.prepareStatement("INSERT INTO talkpanel VALUES('How to make five billion dollars'," +
                    "'romanovm')");
            result += tp2.executeUpdate();

            PreparedStatement insertOrganizer1 = conn.prepareStatement("INSERT INTO users VALUES('gblythe', " +
                    "'organizer', 'pworg', '22 Phase Two Drive', 'gblythe@email.com', 'none', 'I’m an organizer!', 'George Blythe')");
            result += insertOrganizer1.executeUpdate();

            PreparedStatement insertUsers4 = conn.prepareStatement("INSERT INTO users VALUES('esherman', 'organizer', " +
                    "'hello11', '100 Demo Circle', 'ellis@email.com', 'Bell', 'My name is Ellis and I’m 21 years old', 'Ellis Sherman')");
            result += insertUsers4.executeUpdate();

            PreparedStatement insertTalk1 = conn.prepareStatement("INSERT INTO eventlist VALUES('The Best Talk', " +
                    "'2020/12/31 12:59:59', 1, 1000, 1000, 0, 0, 1000, 10, 0, 'motivational', 'talk')");
            result += insertTalk1.executeUpdate();

            PreparedStatement insertTalk2 = conn.prepareStatement("INSERT INTO eventlist VALUES('How to make " +
                    "five billion dollars', '2020/12/31 12:12:12', 2, 101, 20, 1, 1, 20, 20, 0, 'development', 'talk')");
            result += insertTalk2.executeUpdate();

            PreparedStatement insertTalk = conn.prepareStatement("INSERT INTO eventlist VALUES('Motivational Panel', " +
                    "'2020/12/20 11:59:59', 2, 100, 100, 1, 0, 50, 10, 1, 'motivational', 'panel')");
            result += insertTalk.executeUpdate();

            PreparedStatement pairSpeaker3 = conn.prepareStatement("INSERT INTO speakingevents VALUES('romanovm', 'Motivational Panel')");
            result += pairSpeaker3.executeUpdate();

            PreparedStatement pairSpeaker4 = conn.prepareStatement("INSERT INTO speakingevents VALUES('piqcyl', 'Motivational Panel')");
            result += pairSpeaker4.executeUpdate();

            PreparedStatement tp3 = conn.prepareStatement("INSERT INTO talkpanel VALUES('Motivational Panel', 'romanovm')");
            result += tp3.executeUpdate();

            PreparedStatement tp4 = conn.prepareStatement("INSERT INTO talkpanel VALUES('Motivational Panel', 'piqcyl')");
            result += tp4.executeUpdate();

            PreparedStatement insertRequest = conn.prepareStatement("INSERT INTO requests VALUES(1, 'romanovm', 'pending', " +
                    "'I require gluten free food')");
            result += insertRequest.executeUpdate();

            PreparedStatement insertRequest1 = conn.prepareStatement("INSERT INTO requests VALUES(2, 'romanovm', 'addressed', " +
                    "'I want chocolate dollars brought to my talk so I can throw them to the audience')");
            result += insertRequest1.executeUpdate();

            PreparedStatement insertRequest2 = conn.prepareStatement("INSERT INTO requests VALUES(3, 'tsmith', " +
                    "'pending', 'I require a vegetarian meal plan')");
            result += insertRequest2.executeUpdate();

            PreparedStatement insertParty = conn.prepareStatement("INSERT INTO eventlist VALUES('Company Christmas Party', " +
                    "'2021/12/12 14:00:00', 1, 101, 50, 0,1, 50, 3, 0, 'company', 'party')");
            result += insertParty.executeUpdate();

            PreparedStatement insertRoom = conn.prepareStatement("INSERT INTO room VALUES(101, 100, " +
                    "100, 1, 200, 200)");
            result += insertRoom.executeUpdate();

            PreparedStatement insertRoom1 = conn.prepareStatement("INSERT INTO room VALUES(100, 1000, " +
                    "1000, 0, 1000, 1000)");
            result += insertRoom1.executeUpdate();

            PreparedStatement insertRoom2 = conn.prepareStatement("INSERT INTO room VALUES(1000, 2000, 0, 0, 100, 1000)");
            result += insertRoom2.executeUpdate();

            PreparedStatement addOrganizer = conn.prepareStatement("INSERT INTO organizingevents VALUES('esherman', " +
                    "'Motivational Panel')");
            result += addOrganizer.executeUpdate();

            PreparedStatement addOrganizer1 = conn.prepareStatement("INSERT INTO organizingevents VALUES('esherman', " +
                    "'How to make five billion dollars')");
            result += addOrganizer1.executeUpdate();

            PreparedStatement addOrganizer2 = conn.prepareStatement("INSERT INTO organizingevents VALUES('gblythe', " +
                    "'Company Christmas Party')");
            result += addOrganizer2.executeUpdate();

            PreparedStatement addOrganizer3 = conn.prepareStatement("INSERT INTO organizingevents VALUES('gblythe', " +
                    "'The Best Talk')");
            result += addOrganizer3.executeUpdate();

            PreparedStatement addAttendees = conn.prepareStatement("INSERT INTO userevent VALUES ('tsmith', " +
                    "'Company Christmas Party')");
            result += addAttendees.executeUpdate();
            PreparedStatement addAttendees1 = conn.prepareStatement("INSERT INTO userevent VALUES ('mkent', " +
                    "'Company Christmas Party')");
            result += addAttendees1.executeUpdate();

            PreparedStatement addAttendees2 = conn.prepareStatement("INSERT INTO userevent VALUES ('mkent', " +
                    "'How to make five billion dollars')");
            result += addAttendees2.executeUpdate();




        }


        catch(SQLException e){
            System.out.println("An error occurred while connecting MySQL database");
            e.printStackTrace();
        }
    }
}
