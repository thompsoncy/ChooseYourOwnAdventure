package Model;

import Model.IChoice;

import java.util.ArrayList;

/*
* represents a page of a Choose Your Own Adventure Book.
*/
public interface IPage {
    /*
    * gets the current main text of this page.
    */
    String getMainText();

    /*
    * sets the current pagenumber of this page.
    */
    void setPageNumber(int pagenumber);

    /*
    * gets the current pagenumber of this page.
    */
   int getPageNumber();

    /*
    * sets the current main text of this page.
    */
    void setMainText(String newtext);

    /*
     * adds a choice to this page.
     */
    void addChoice(IChoice newchoice);

    /*
     * adds a choice to this page at specified position.
     */
    void addChoice(IChoice newchoice, int position);

    /*
    * removes specified choice and returns the removed choice returns null if
    * the specified choice does not exist.
    */
    IChoice removeChoice(int position);


    /*
     * removes choice that leads to given Page  and replaces it with choice that leads to current page
     * does nothing if there is no such choice
     */
    void removeChoice(IPage page);

    /*
    * gets the choices of this page.
    */
    ArrayList<IChoice> getChoices();

    /*
     * gets the choices text of this page.
     */
    ArrayList<String> getChoicesText();

    /*
     * sets the choices text of this page.
     */
    void setChoicesText( ArrayList<String> newtext);
}
