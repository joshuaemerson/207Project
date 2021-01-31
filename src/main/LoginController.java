package main;

import message.MessageManager;
import request.RequestManager;
import user.User;
import user.UserManager;

/**
 * A controller that deals with logging into the program.
 */
public class LoginController {

    private UserManager userManager;
    private MessageManager messageManager;
    private RequestManager requestManager;
    MainPresenter p;

    /**
     * This constructs a login occurrence
     * @param userManager The instance of the User Manager
     * @param messageManager The instance of the Message Manager
     * @param value Whether or not to start from scratch
     * @param requestManager The instance of the Request Manager.
     * @return String username (of the user who was able to log in)
     */
    public String login(UserManager userManager, MessageManager messageManager, int value, RequestManager requestManager){
        this.userManager = userManager;
        this.messageManager = messageManager;
        this.requestManager = requestManager;
        p = new MainPresenter();
        String username = "";
        // if there are no detected files, display the new User message and prompt them to create an account, and
        // returns the username of the account that was created
        if (value == 0){
            p.displayNewFirstUserMessage();
            username = createAccount();
            return username;
        }

        p.displayNewOrReturningPrompt();
        int input = p.nextInt();

        String password;
        while(input != 1 && input != 2 ){
            input = p.nextInt();
        }
        switch (input){
            // case 1 is creating a new Account for a new User
            case 1:
                username = createAccount();
                break;
            // case 2 is logging in for individuals already have accounts
            case 2:
                username = p.displayEnterUsernamePrompt();

                while (!this.userManager.checkCredentials(username)){
                    username = p.displayUsernameExistenceError();
                    if (username.equals("q")){
                        break;
                    }
                }
                if (username.equals("q")){
                    break;
                }
                password = p.displayEnterPasswordPrompt();

                while(!this.checkLoginInfo(username, password) && !password.equals("q")) {
                    password = p.displayRedoPasswordPrompt();
                    if(password.equals("q")){
                        username = "q";
                    }
                }
                break;
        }
        return username;
    }

    // Getter Methods

    // This method returns the User associated with the given username
    private User getUserInfo(String username){
        return this.userManager.getUser(username);
    }

    // Other Methods

    // This method checks if the login information is correct, i.e. if the username is a valid username in the
    // database, and then checking if the password matches that username and returning the result
    private boolean checkLoginInfo(String username, String password){
        boolean username_valid = this.userManager.checkCredentials(username);
        boolean password_valid = false;
        if(username_valid){
            password_valid = (this.getUserInfo(username).getPassword().equals(password));
        }
        return password_valid;
    }

    // This method creates a new Account
    private String createAccount(){
        p.displayNewUserGreeting();
        String username = p.displayEnterUsernamePrompt();

        while(this.userManager.checkCredentials(username)){
            if (this.userManager.checkCredentials(username)) {
                username = p.displayRepeatUsernameError();
            }

        }

        String password = p.displayEnterPasswordPrompt();
        String name = p.displayEnterNamePrompt();
        String address = p.displayEnterAddressPrompt();
        String email = p.displayEnterEmailPrompt();

        //Modify prompt to allow for VIP
        String type = p.displayEnterStatusPrompt();
        String company = p.displayEnterCompanyPrompt();
        String bio = p.displayEnterBioPrompt();

        userManager.addUser(name, address, email, username, password, type, company, bio);
        messageManager.addUserInbox(username);
        requestManager.addUserRequests(username);
        return username;
    }
}
