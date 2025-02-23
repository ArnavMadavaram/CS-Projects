package survey;

/**
 * Name: Arnav Madavaram
 * Username: madaa01
 */

public class CancelledSurveyException extends Exception {
    public CancelledSurveyException() {
        super("Your survey has been cancelled.");
    }
}
