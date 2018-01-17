package Model;

import java.util.ArrayList;
import java.util.Stack;

/*
* Represents the operations that a Choose Your Own Adventure Model Requires.
*/
public interface CYOAModelOperations {
    /*
    * @return the title of this choose your own adventure.
    */
    String getTitle();

    /*
    * sets the title of this choose your own adventure to given title.
    */
    void setTitle(String newtitle);

    /*
     * gets the Pages of the CYOA
     */
    ArrayList<IPage> getPages();

    /*
    * removes the given page from the CYOA
    */
    void removePage(int pg);

    /*
     * gets the page at specfied number
     */
    IPage getPage(int pagenumber);

    /*
    * return the page numbers that are not linked by any other pages besides page 0.
    */
    ArrayList<Integer> getUnlinkedPageNumbers();

    /*
     * randomizes the page numbers of choices.
    */
    void randomizePages();

    /*
     * adds a new page at the next available page location
     */
    void addPage();

    /*
     * adds a previously made page as a choice with given text at given index.
     * throws illegal argument exception if index does not exist.
     */
    void addPageAsChoice(int indexofpagebeinglinked, int indexoflinkingpage, String description) throws IllegalArgumentException;

    /*
    * adds a new page as a choice at at given index.
    * throws illegal argument exception if index does not exist.
    */
    void addNewPageAsChoice(int index) throws IllegalArgumentException;

    /*
    * removes choices at given index
    * throws illegal argument exception if index does not exist.
    */
    void deleteChoice(int pageindex, int choiceindex) throws IllegalArgumentException;

    /*
     * gets the page number of destination of choice of the specified location
     * throws illegal argument exception if index does not exist.
     */
    int getDestinationChoice(int pageindex, int choiceindex) throws IllegalArgumentException;

    /*
     * gets 40 characters of the main text of each page and returns it as a list of strings
     */
    ArrayList<String> getPagesStartingText();

    /*
     * gets the text of the description of each of the choices on a page in the form of a arraylist of strings.
     */
    ArrayList<String> getChoicesText(int pg);

    /*
    * sets the text of the description of each of the choices on a page.
    */
    void setChoicesText(int pg, ArrayList<String> newtext);

}
