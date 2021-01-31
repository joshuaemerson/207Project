===== GETTING STARTED =====
    Welcome to the program! If you have not set up the MySQL database, follow the Appendix at the bottom of the file. We begin in ProgramMain. Run the main method and let's begin.

    You will be asked if you would like to read from a file or not:
    If you choose to read from a file, you will be reading from the presets we have provided. See below for details.
    If you choose not to, you will be starting from scratch.

    Follow the prompts on screen for either option. See the defaults provided and adding user section below for
    conditions and additional details.

    You are now asked if you would like to sign in as a new or existing user.
    The new user option is for registering a new account and existing user is for logging in as an existing user.
    If you decide to create a new user, see the new user account conditions section.
    If working with the defaults provided and signing in as an existing user, see the defaults section below
    for the usernames and passwords of existing users.

    The following section is about specific users and what they can do:


===== SETTING UP MySQL =====
    === Windows ===
    Download the MySQL Community Server installer here: https://dev.mysql.com/downloads/windows/installer/8.0.html
    Follow instructions
        During the Choose a Setup Type, select custom
        Add the following three files
            MySQL Server x64
            MySQL Java Connector (J/Connector)
            Under applications, MySQL Shell x64
    Leave everything else as default
    It will ask you to set up a password during the installation phase, make sure you remember the password
    Make sure you know where the files are being saved
    Complete the installation
    Open the MySQL Command Line Client
    Enter your password
    Enter the following
        CREATE DATABASE conference;
        exit;

    === MacOS ===
    Download the MySQL Community Server here: https://dev.mysql.com/downloads/windows/installer/8.0.html
        Select the DMG Archive Version
        Start installation
        It will ask you to create a password, make sure you remember it
        Leave everything else as default
        Complete installation
    Open System Preferences
    MySQL should appear in the bottom row
    Click it, then start the server
    Open the terminal
    Type the following
        echo 'export PATH=usr/local/mysql/bin:$PATH' >> ~/.bash_profile
        . ~/.bash_profile
    Then type the following
        mysql -u root -p
    Enter the password you created during installing
    Enter the following
        CREATE DATABASE conference;
    Download the Connector/J here https://dev.mysql.com/downloads/connector/j/ (use platform independent,
    ZIP is probably easier)

    === Both Platforms ===
    Open IntelliJ and the project file
    In the project pane, right click the project file (the top level file on the project pane)
    Open Module Settings
    Select libraries
    Select the + button
    Select Java
    Navigate to wherever your Connector/J file is and find the mysql-connector-java-8.0.22.jar file and select it
    Open the CreateConferenceTables java file in the saver package
    Change the password on line 16 to your password (the third string)
    Run the main method in this file
    Open the Connector java file
    Again, in the constructor look for the spot to enter your password again
    The program is now ready to run


===== USER MANUAL =====
    === Attendees and VIPs ===
    Once they log in, they will be shown a menu of 5 options.
        Messages (0)- they may:
            - see their inbox
            - see starred, deleted, or archived messages
            - send a message
        Events (1)- they may:
            - view event list, and their scheduled events
            - sign up for, and search for events
            - cancel event reservations
        Requests (2)- they may view their requests, or make requests
        User Options (3)- they may:
            - view their corporation and edit their corporation information
            - view their bio, and edit their bio
    Quit (4)
    At any menu, there is the option of going back to the main screen.

    === Speakers ===
    Once they log in, they will be shown a menu of 5 options.
        Events (1)- they may:
            - view their scheduled events
        Requests (2)- they may view their requests, or make requests
        User Options (3)- they may:
            - view their corporation and edit their corporation information
            - view their bio, and edit their bio
    Quit (4)

    === Organizers ===
    Once they log in, they will be shown a menu of 5 options.
        Messages (0)- they may:
            - see their inbox
            - see starred, deleted, or archived messages
            - send a message
            - message attendees at one of their events.
        Events (1)- they may:
            - view event list, and their scheduled events
            - sign up for, and search for events
            - cancel event reservations and modify their capacity
            - reschedule events
            - add rooms
            - get conference stats
            - search for events
        User Options (2)- they may:
            - view information like rooms, speakers, organizers, attendees, vips
            - view their corporation and edit their corporation information
            - view their bio, and edit their bio
        Requests (3)- they may view their requests, or make requests
    Quit (4)
    At any menu, there is the option of going back to the main screen.



===== NAVIGATING =====
    Once you follow all prompts and are logged into your account, you will be provided a menu of options. Enter the
    corresponding number to perform that task.
    After completing a task, you can enter ask to be shown the menu again, or end the program.

    The corresponding numbers for these are provided for your reference:
         Organizers: 14 to be shown the menu of options, 20 to end the program
         Attendees: 7 to be shown the menu of options, 8 to end the program
         Speakers: 5 to be shown the menu of options, 6 to end the program

===== DETAILS AND WARNINGS ABOUT SAVING =====
    Once you are done using the program for the time being, and want to save your files, you MUST end the program using
    the reference above, or else your data will not be saved.

    If you choose to completely disregard the default files we have provided and do not need them, move them to a
    different location or delete them so that they do not interfere with the new files that you will save. If you are
    adding onto the defaults we have provided, disregard the previous
    sentence.

===== DEFAULTS PROVIDED =====
    ==Organizers==
    username: gblythe, password: pworg,
        [address: 22 Phase Two Drive, name: George Blythe, email: gblythe@email.com, company: none,
        bio: I’m an organizer!]

    username: esherman, password: hello11,
        [address: 100 Demo Circle, name: Ellis Sherman, email: ellis@email.com, company: Bell,
        bio: My name is Ellis and I’m 21 years old,]

    ==Attendees==

    ==Speakers==
    username: piqcyl, password: zed,
        [address: 1000 Colonial Farm Road Langley Virginia USA, name: John Smith, email: piqcyl@ca.gov,
        company: Canadian Tire, bio: This is me]

    username: romanovm, password: abc123,
        [address: 143 University Avenue, name: Martha Romanov, company: Qoogle, bio: I am a motivational speaker]

    ==VIPs==
    username: tsmith, password: pass,
        [address: 16 East Ave, name: Taylor Smith, email: tsmith@email.com, company: Amazon,
         bio: My name is Taylor Smith and I'm 20 years old]

    ==Requests==
    username of requester: romanovm, request status: pending, request message: I require gluten free food

    username of requester: romanovm, request status: addressed,
        request message: I want chocolate dollars brought to my talk so I can throw them to the audience

    username of requester: tsmith, request status: pending, request message: I require a vegetarian meal plan

    ==Messages==

    ==Rooms==
    room number: 1000, capacity: 2000, num. computers: 0, projector?: no, num. tables: 100, num. chairs: 1000

    room number: 101, capacity: 20, num. computers: 1, projector?: yes, num. tables: 20, num. chairs: 20

    room number: 100, capacity: 100, num. computers: 100, projector?: no, num. tables: 100, num. chairs: 100

    ==Events==

        ==Parties==
         Title: Company Christmas Party, Date: 2021/2/12 14:00:00,
            [duration in hours: 1, room #: 101, capacity: 50, # of computers: 0, projector?: yes,
            # of chairs: 50, # of tables: 3, VIP only?: no, event type: company]

        ==Panels==
        Title: Motivational Panel, Date: 2020/12/20 11:59:59,
            [duration in hours: 2, room #: 100, capacity: 100, # of computers: 1, projector?: no,
             # of chairs: 50, # of tables: 10, VIP only?: yes, event type: motivational]

        ==Talks==
        Title: The Best Talk, Date: 2020/12/31 12:59:59,
            [duration in hours: 1, room #: 1000, capacity: 1000, # of computers: 0, projector?: no,
            # of chairs: 1000, # of tables: 10, VIP only?: no, event type: motivational]

        Title: How to make five billion dollars, Date: 2020/12/31 12:12:12,
            [duration in hours: 1, room #: 101, capacity: 20, # of computers: 1, projector?: yes,
            # of chairs: 20, # of tables: 20, VIP only?: no, event type: development

===== NEW USER ACCOUNT CONDITIONS =====
    If you choose to create a new account, the following conditions MUST hold:
        - username and password must be at least 3 characters,
        - name at least 2 characters, and
        - address at least 6 characters
    The email must also follow standard email conventions.

    You will also be prompted about which company you are associated with.
        - You can either enter 'none' for your company, OR
        - Enter the company name; in this case:
            - you must enter some input that is at least one character


===== DESIGN PATTERNS USED =====
1. Factory Design Pattern: UserFactory (used in UserManager) and EventFactory (EventManager)
2. Dependency Injection: Controllers of all Users (created instances of the users elsewhere and pass them in to the controller rather than hard coding them)
3. AttendeeController, SpeakerController, OrganizerController, VIPController
4. Liskov Substitution Principle: Lists, Maps, Set (vs ArrayList, HashMap, HashSet) - eventManager, messageManager, userManager, Event, Users
5. Single Responsibility Principle - Message, Event, User (Attendee, Organizer, Speaker, VIP), Request, Room


===== APPENDIX: SETTING UP THE MYSQL DATABASE =====
1. Download and install the MySQL Community Server here: https://dev.mysql.com/downloads/mysql/
2. Under your 'System Preferences' (OS Dependent) make sure the server is running.
3. Run the mysql binary in the command line (on mac this is at /usr/local/mysql/bin/ with the command `mysql -u root -p`) and enter the password created during installation. This creates and account with username 'root' and your particular password.
4. In MySQL run 'CREATE DATABASE conference;' and exit.
5. In CreateConferenceTables.java and Connector.java, put in your password. The default is csc@207uoft. If you ran the correct MySQL command, the username remains root.
6. Open up Project settings and make a new Java library with the file JDBC/mysql-connector-java-8.0.22.jar
7. Run CreateConferenceTables.main(). This syncs the MySQL database with the project.
8. Run ProgramMain.main() and everything should work.

