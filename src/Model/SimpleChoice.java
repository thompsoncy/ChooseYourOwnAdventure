package Model;

import Model.IChoice;
import Model.IPage;

/*
* represents a simple choice of a choose your own adventure that has basic functionality
*/
public class SimpleChoice implements IChoice {
    private String description;
    private IPage destination;

    public SimpleChoice(String description, IPage destination) {
        this.description = description;
        this.destination = destination;
    }

    /*
     * basic constructor guarantees the description will not be null
    */
    public SimpleChoice() {
        this.description = "";
    }


    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public IPage getDestination() {
        return this.destination;
    }

    @Override
    public void setDestination(IPage newpage) {
        this.destination = destination;
    }
}
