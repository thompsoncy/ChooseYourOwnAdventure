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

    /*
    * Saves current CYOA to given location.
    */
    void save(String location) throws FileNotFoundException;

    /*
    * loads current CYOA to given location.
    */
    void load(String location) throws FileNotFoundException;

    /*
    * exports this CYOA to given location in the form of a book in text from.
    */
    void export(String location) throws FileNotFoundException;
}
