package View;

import java.util.ArrayList;

/*
* Represents the operations that a Choose Your Own Adventure View Requires.
* update page navigator
*
* display current page number
*
*/

public interface ViewCYOAOperations {

    /*
     * Sets the main title of the CYOA for display.
    */
    void setTitle(String title);

    /*
    * Sets the main text of the CYOA for display.
    */
    void setMaintext(String mainText);

    /*
    * gets the main text of the CYOA for display.
    */
    String getMaintext();

    /*
    * Sets the choices of the CYOA for display.
    */
    void setChoicesText(ArrayList<String> choices);

    /*
    * gets the choices of the CYOA for display.
    */
    ArrayList<String> getChoicesText();

    /*
    * Sets the page navigation of the CYOA for display.
    */
    void setPages(int numpages);

    /*
     * gets selected page navigation of the CYOA for display.
     */
    int getSelectedPage();

    /*
    * Sets the current page number of the CYOA for display.
    */
    void setPageNumber(int pgnum);

    /*
    * displays the main screen.
    */
    void displayMainScreen();

    /*
    * displays editing screen.
    */
    void displayEditingScreen();
}
