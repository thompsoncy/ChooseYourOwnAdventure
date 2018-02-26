package Controller;

/*
 * represents a simple CYOA controller that has basic operations.
*/

import Model.CYOAModelOperations;
import Model.SimpleCYOAModel;
import Model.SimplePage;
import View.SimpleView;
import View.ViewCYOAOperations;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
* Represents a simple controller for  a Choose Your Own Adventure MVC.
*/
public class SimpleController implements  CYOAControllerOperations {
    private FileReaderNExporter fileReaderNExporter;
    private CYOAModelOperations model;
    private ViewCYOAOperations view;
    private int pagenumber;

    public SimpleController() {
        model = new SimpleCYOAModel("");
        view = new SimpleView(this );
        pagenumber = 0;
    }

    @Override
    public void startNew() {
        this.model = new SimpleCYOAModel("");
    }

    protected void setPage(int pg) {
        pagenumber = pg;
        view.setMaintext(model.getPage(pagenumber).getMainText());
        view.setChoicesText(model.getChoicesText(pagenumber));
        view.setPageNumber(pagenumber);
        view.setPages(this.model.getPages().size());
    }

    @Override
    public void action(String action) {
        Scanner sc = new Scanner(action);
        switch(sc.next()) {
            case "NewStory":
                view.setMaintext("write the first page of your story here then add choices");
                model.addPage();
                ArrayList<String> pages = new ArrayList<>();
                pages.add("The First Page Nothing Writen Here Yet");
                view.setPages(1);
                view.setPageNumber(0);
                view.setTitle("");
                view.displayEditingScreen();
                break;
            case "LoadStory":
                while (true) {
                    String lfilename = JOptionPane.showInputDialog(null, "Please Type In The Name of The" +
                                    "File Where The Book Will Be Loaded From", "Loading Book",
                            JOptionPane.QUESTION_MESSAGE);
                    if (lfilename != null) {
                        try {
                            model.export(lfilename);
                            break;
                        } catch (FileNotFoundException e) {
                        }
                    }
                }
                break;
            case "Go":
                model.setChoicesText(pagenumber, view.getChoicesText());
                model.getPage(pagenumber).setMainText(view.getMaintext());
                this.setPage(view.getSelectedPage());
                break;
            case "Save":
                String sfilename = JOptionPane.showInputDialog(null, "Please Type In The Name of The" +
                                "File Where The Book Will Be Saved", "Saving Book",
                        JOptionPane.QUESTION_MESSAGE);
                if (sfilename != null) {
                    try {
                        model.save(sfilename);
                    } catch (FileNotFoundException e) {
                    }
                }
                break;
            case "Link":
                String message = "type in the choice number and the " +
                "pg number that you want linked, \n such as to link choice 1 to page 0 you would put in \" 1 0\"";
                while(true) {
                    String userinput = JOptionPane.showInputDialog(null, message
                            , "Link", JOptionPane.QUESTION_MESSAGE);
                    if (userinput == null) {
                        break;
                    }
                    Scanner gettingints = new Scanner(userinput);
                    if (gettingints.hasNextInt()) {
                        int choicenumber = gettingints.nextInt();
                        if (gettingints.hasNextInt()) {
                            int newpagenumber = gettingints.nextInt();
                            if(newpagenumber < model.getPages().size() && choicenumber < model.getPage(this.pagenumber).getChoices().size()) {
                                model.linkChoiceToPage(this.pagenumber, choicenumber, newpagenumber);
                                break;
                            }
                        }
                    }
                    message = "Please Put only the choice and page number and make sure they are correct";
                }
                break;
            case "MakeBook":
                String filename = JOptionPane.showInputDialog(null, "Please Type In The Name of The" +
                                "File Where The Book Will Be Stored Please", "Making Book",
                        JOptionPane.QUESTION_MESSAGE);
                if (filename != null) {
                    try {
                        model.export(filename);
                    } catch (FileNotFoundException e) {
                    }
                }
                break;
            case "DeletePage":
                if(this.pagenumber != 0) {
                    this.model.removePage(this.pagenumber);
                }
                this.setPage(0);
                break;
            case "AddChoice":
                model.addNewPageAsChoice(this.pagenumber);
                ArrayList<String> choices = view.getChoicesText();
                choices.add("");
                view.setChoicesText(choices);
                //model.setChoicesText(pagenumber, view.getChoicesText());
                break;
            case "Page":
                int choicesnumber = sc.nextInt();
                int destination = model.getPage(pagenumber).getChoices()
                        .get(choicesnumber).getDestination().getPageNumber();
                model.setChoicesText(pagenumber, view.getChoicesText());
                model.getPage(pagenumber).setMainText(view.getMaintext());
                this.setPage(destination);
                break;
            case "Choice":
                int choicenumber = sc.nextInt();
                model.deleteChoice(pagenumber, choicenumber);
                ArrayList<String> choice = view.getChoicesText();
                choice.remove(choicenumber);
                view.setChoicesText(choice);
                break;
        }
    }
}
