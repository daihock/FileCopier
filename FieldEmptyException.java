/**
 * Custom exception. Thrown manually. Used to skip unnecessary code execution
 */

public class FieldEmptyException extends Throwable {

    /**
     * Using superclass method with Message parameter
     *
     * @param string Message
     */
    public FieldEmptyException(String string) {
        super(string);
    }
}
