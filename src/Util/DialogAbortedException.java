package Util;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 06.01.13
 * Time: 8:02
 * To change this template use File | Settings | File Templates.
 */
public class DialogAbortedException extends Exception {
    public DialogAbortedException() {
        super();
    }

    public DialogAbortedException(String message) {
        super(message);
    }
}
