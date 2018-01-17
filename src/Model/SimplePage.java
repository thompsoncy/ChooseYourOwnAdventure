package Model;

import Model.IChoice;
import Model.IPage;

import java.util.ArrayList;

/*
* represents a simple page of a choose your own adventure that has basic functionality
*/
public class SimplePage implements IPage {
    private String maintext;
    private ArrayList<IChoice> choices;
    private int pagenumber;

    // basic constructor
    public SimplePage(int pagenumber) {
        this.maintext = "";
        this.choices = new ArrayList<>();
        this.pagenumber = pagenumber;
    }

    // constructor that initiates the text
    public SimplePage(String maintext) {
        this.maintext = maintext;
        this.choices = new ArrayList<>();
    }


    @Override
    public String getMainText() {
        return this.maintext;
    }

    @Override
    public void setPageNumber(int pagenumber) {
        this.pagenumber = pagenumber;
    }

    @Override
    public int getPageNumber() {
        return pagenumber;
    }

    @Override
    public void setMainText(String newtext) {
        this.maintext = newtext;
    }

    @Override
    public void addChoice(IChoice newchoice) {
        this.choices.add(newchoice);
    }

    @Override
    public void addChoice(IChoice newchoice, int position) {
        this.choices.add(position, newchoice);
    }

    @Override
    public IChoice removeChoice(int position) {
        return this.choices.remove(position);
    }

    @Override
    public void removeChoice(IPage page) {
        for (IChoice choice: this.choices) {
            if(choice.getDestination().equals(page)) {
                choices.remove(choice);
            }
        }
    }

    @Override
    public ArrayList<IChoice> getChoices() {
        return this.choices;
    }

    @Override
    public ArrayList<String> getChoicesText() {
        ArrayList choicetexts = new ArrayList();
        for(IChoice choice : this.choices) {
            choicetexts.add(choice.getDescription());
        }
        return choicetexts;
    }

    @Override
    public void setChoicesText(ArrayList<String> newtext) {
        for(int i = 0; i < choices.size(); i++) {
            choices.get(i).setDescription(newtext.get(i));
        }
    }
}
