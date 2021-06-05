package sample;

public class PersonaException extends Exception{

    public static String BAD_AGE_LOWER = "Age must be greater than 0";
    public static String BAD_AGE_UPPER = "Age must be lower than 120";
    public static String BAD_AGE = "This text is not an age";

    public PersonaException(String message) {
        super(message);
    }

}
