package Model;

import Model.CYOAModelOperations;
import Model.IChoice;
import Model.IPage;
import Model.SimpleChoice;
import org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCodeHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*
* represents a simple model of a choose your own adventure that has basic functionality.
* The format for loading and saving is the following
* to signal the start and end of book
* &&&START Title Authors &&&PAGESSTART &&&END
* the format of any Page within the book
* &&&PAGESTART thispagenumber maintext &&&CHOICE text &&&PAGELINK linkedpagenumber &&&PAGEEND
*
*/
public class SimpleCYOAModel implements CYOAModelOperations {
    private String title;
    private ArrayList<String> authors;
    private ArrayList<IPage> pages;


    /*
     * a basic constructor that initializes data
    */
    public SimpleCYOAModel(String title) {
        this.title = title;
        this.pages = new ArrayList<>();
    }


    @Override
    public void save(String location) throws FileNotFoundException {
        StringBuilder booksave = new StringBuilder();
        booksave.append("&&&START ");
        booksave.append(this.title);
        booksave.append(" ");
        for(String author : authors) {
            booksave.append(author);
            booksave.append(" ");
        }
        booksave.append("&&&PAGESSTART ");
        for(int i = 0; i < this.pages.size(); i++) {
            booksave.append("&&&PAGESTART ");
            booksave.append(i);
            booksave.append(" ");
            booksave.append(this.pages.get(i).getMainText());
            booksave.append(" ");
            for(IChoice choice : this.pages.get(i).getChoices()) {
                booksave.append("&&&CHOICE ");
                booksave.append(choice.getDescription());
                booksave.append(" ");
                booksave.append("&&&PAGELINK ");
                booksave.append(choice.getDestination().getPageNumber());
            }
        }
        booksave.append("&&&END ");
    }

    @Override
    public void load(String location) throws FileNotFoundException {
        this.pages = new ArrayList<IPage>();
        this.authors = new ArrayList<>();
        ArrayList<ArrayList<String>> choicesdescriptions =  new ArrayList<>();
        ArrayList<ArrayList<Integer>> choicespagenumbers = new ArrayList<>();
        StringBuilder currenttext = new StringBuilder();
        Scanner sc;

        sc = new Scanner(new FileInputStream(location));
        String nextpartofbook = "";
        String currentsection = "";
        int count = 0;
        while (sc.hasNext() && !currentsection.equalsIgnoreCase("&&&END") ) {
            nextpartofbook = sc.next();
            switch (nextpartofbook) {
                case "&&&START": {
                    choicesdescriptions.add( new ArrayList<>());
                    choicespagenumbers.add(new ArrayList<>());
                    currentsection = nextpartofbook;
                    count = 0;
                    currenttext = new StringBuilder();
                    break;
                }
                case "&&&CHOICE": {
                    if(currentsection.equalsIgnoreCase("&&&PAGESTART")) {
                        this.pages.get(pages.size() - 1).setMainText(currenttext.toString());
                    } else {
                        choicesdescriptions.get(pages.size() - 1).add(currenttext.toString());
                    }
                    currentsection = nextpartofbook;
                    count = 0;
                    currenttext = new StringBuilder();
                    break;
                }
                case "&&&PAGESTART": {
                    currentsection = nextpartofbook;
                    count = 0;
                    this.pages.add(new SimplePage(""));
                    break;
                }
                case "&&&PAGESSTART": {
                    currentsection = nextpartofbook;
                    count = 0;
                    break;
                }
                case "&&&PAGELINK": {
                    currentsection = nextpartofbook;
                    count = 0;
                    break;
                }
                default :
                    switch (currentsection) {
                        case "&&&START": {
                            if(count == 1) {
                                this.title = nextpartofbook;
                            } else {
                                this.authors.add(nextpartofbook);
                            }
                            break;
                        }
                        case "&&&CHOICE": {
                            if(count == 1) {
                            } else {

                            }
                            break;
                        }
                        case "&&&PAGESTART": {
                            if(count == 1) {
                            } else {
                               // this.pages.get(pages.size() - 1).setMainText();
                            }
                            break;
                        }
                        case "&&&PAGESSTART": {

                            break;
                        }
                        case "&&&PAGELINK": {

                            break;
                        }
                    }
            }
            count++;
        }
    }

    @Override
    public void export(String location) throws FileNotFoundException {

    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String newtitle) {
        this.title = newtitle;
    }

    @Override
    public ArrayList<IPage> getPages() {
        return this.pages;
    }

    @Override
    public void removePage(int pg) {
        IPage removedpage = pages.remove(pg);
        for(IPage page: this.pages ) {
            page.removeChoice(removedpage);
        }
    }

    @Override
    public IPage getPage(int pagenumber) {
        return pages.get(pagenumber);
    }

    @Override
    public ArrayList<Integer> getUnlinkedPageNumbers() {
        ArrayList<Integer> unlinkedpgs = new ArrayList<>();
        HashSet<IPage> seenpages = new HashSet<>();
        if(this.pages.size() > 0) {
            this.seeAllPages(this.pages.get(0), seenpages);
            for (int i = 1; i < this.pages.size(); i++) {
                if (!seenpages.contains(pages.get(i))) {
                    unlinkedpgs.add(i);
                }
            }
        }
        return  unlinkedpgs;
    }

    /*
    * helper method that adds all pages seen down the chain of pages from given page
     * to given hashset.
    */
    protected void seeAllPages(IPage page, HashSet<IPage> seenpages) {
        int beforesize = seenpages.size();
        seenpages.add(page);
        if(beforesize != seenpages.size()) {
            for(IChoice choice : page.getChoices()) {
                this.seeAllPages(choice.getDestination(), seenpages);
            }
        }
    }


    @Override
    public void randomizePages() {
        for(int i = 0; i < pages.size(); i++ ) {
            pages.add((int)(Math.random() * pages.size()), pages.remove(i));
        }
    }

    @Override
    public void addPage() {
        this.pages.add(new SimplePage(pages.size()));
    }

    @Override
    public void addPageAsChoice(int indexofpagebeinglinked, int indexoflinkingpage, String description) throws  IllegalArgumentException {
        if (indexoflinkingpage < 0 || indexoflinkingpage > this.pages.size() - 1) {
            throw new IllegalArgumentException("please put in a valid index");
        }
        pages.get(indexoflinkingpage).addChoice(new SimpleChoice(description, this.pages.get(indexofpagebeinglinked)));
    }

    @Override
    public void addNewPageAsChoice(int index) throws IllegalArgumentException {
        if (index < 0 || index > this.pages.size() - 1) {
            throw new IllegalArgumentException("please put in a valid index");
        }
        IPage newpage = new SimplePage(pages.size());
        this.pages.get(index).addChoice(new SimpleChoice("", newpage));
        this.pages.add(newpage);
    }

    @Override
    public void deleteChoice(int pageindex, int choiceindex) throws IllegalArgumentException {
        pages.get(pageindex).removeChoice(choiceindex);
    }

    @Override
    public int getDestinationChoice(int pageindex, int choiceindex) throws IllegalArgumentException {
        return pages.get(pageindex).getChoices().get(choiceindex).getDestination().getPageNumber();
    }

    @Override
    public void linkChoiceToPage(int pagenumberofchoice, int choicenumber, int linkedpagenumber) {
        this.pages.get(pagenumberofchoice).getChoices().get(choicenumber).setDestination(this.pages.get(linkedpagenumber));
    }

    @Override
    public ArrayList<String> getPagesStartingText() {
        ArrayList<String> pagestext = new ArrayList();
        for (IPage page: pages) {
            if(page.getMainText().length() < 40)
                pagestext.add(page.getMainText());
            else {
                pagestext.add(page.getMainText().substring(0, 40));
            }
        }
        return pagestext;
    }

    @Override
    public ArrayList<String> getChoicesText(int pg) {
        return this.pages.get(pg).getChoicesText();
    }

    @Override
    public void setChoicesText(int pg, ArrayList<String> newtext) {
        this.pages.get(pg).setChoicesText(newtext);
    }
}
