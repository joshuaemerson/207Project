package main;

/**
 * This class is the Presenter Class for the Main and Login Controllers.
 * It handles asking for user input and printing any error messages.
 */
public class MainPresenter extends Presenter{


    // Methods for the Login Controller --------------------------------------------------------------------------------

    /**
     * Prompts user to specify if they are a new or returning user.
     */
    public void displayNewOrReturningPrompt(){
        System.out.println("Are you a (1)new user or (2)returning user: ");
    }

    /**
     * Prints a message welcoming the first user to the program.
     */
    public void displayNewFirstUserMessage(){
        System.out.println("Welcome to the conference! ");
    }


    // Methods for the Main Controller --------------------------------------------------------------------------------

    /**
     * Prints an  message when a User signs out
     */
    public void displaySignedOut(){System.out.println("You have signed out successfully. ");}
    // -----------------------------------------------------------------------------------------------------------------
}
