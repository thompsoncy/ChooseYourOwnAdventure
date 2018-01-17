package Controller;

/*
 * represents a simple CYOA controller that has basic operations.
*/

import Model.CYOAModelOperations;
import Model.SimpleCYOAModel;
import Model.SimplePage;
import View.SimpleView;
import View.ViewCYOAOperations;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

    @Override
    public void save(String location) throws FileNotFoundException {
        this.fileReaderNExporter.saveFile(location, model);
    }

    @Override
    public void load(String location) throws FileNotFoundException {
        this.fileReaderNExporter.readFile(location);
    }

    @Override
    public void export(String location) throws FileNotFoundException {
        this.fileReaderNExporter.exportFile(location, model);
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
                break;
            case "Go":
                model.setChoicesText(pagenumber, view.getChoicesText());
                model.getPage(pagenumber).setMainText(view.getMaintext());
                this.setPage(view.getSelectedPage());
                break;
            case "Save":
                break;
            case "MakeBook":
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
