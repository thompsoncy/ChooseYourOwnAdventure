package Model;

/*
* represents a choice of a Choose Your Own Adventure Book.
*/
public interface IChoice {

    /*
    * gets the Description of this choice returns an empty string if there is not one
    */
    String getDescription();

    /*
    * sets the description of this choice.
    */
    void setDescription(String description);

    /*
    * gets the Destination of this choice returns a null if there is not one
    */
    IPage getDestination();

    /*
    * sets the Destination of this choice.
    */
    void setDestination(IPage newpage);

}
