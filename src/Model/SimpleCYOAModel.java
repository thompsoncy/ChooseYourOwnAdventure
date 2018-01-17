package Model;

import Model.CYOAModelOperations;
import Model.IChoice;
import Model.IPage;
import Model.SimpleChoice;

import java.util.ArrayList;
import java.util.HashSet;

/*
* represents a simple model of a choose your own adventure that has basic functionality
*/
public class SimpleCYOAModel implements CYOAModelOperations {
    private String title;
    private ArrayList<IPage> pages;


    /*
     * a basic constructor that initializes data
    */
    public SimpleCYOAModel(String title) {
        this.title = title;
        this.pages = new ArrayList<>();
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
