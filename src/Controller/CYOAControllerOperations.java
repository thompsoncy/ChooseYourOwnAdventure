package Controller;

import java.io.FileNotFoundException;

/*
* Represents the operations that a Choose Your Own Adventure Controller Requires.
*/
public interface CYOAControllerOperations extends IActionListener {

    /*
    * Starts a new CYOA.
    */
    void startNew();
}
