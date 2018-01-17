package View;

import Controller.IActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SimpleView extends JFrame implements ViewCYOAOperations {
    private ActionListener actionListener;
    private CardLayout screens;
    private JPanel panel;
    private JPanel mainscreen;
    private JPanel editingscreen;
    private JToolBar toolBar;
    private JComboBox pages;
    private JTextArea maintext;
    private JLabel pagenumber;
    private ArrayList<JTextArea> choices;
    private JPanel choicespane;

    public SimpleView(IActionListener actionListener) {

        super("826 CYOA");

        setSize(800, 800);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ActionListener listen = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                actionListener.action(e.getActionCommand());
            }
        };
        this.actionListener = listen;

        this.screens = new CardLayout();

        screens = new CardLayout();
        panel = new JPanel();

        panel.setLayout(screens);

        mainscreen = new JPanel();
        editingscreen = new JPanel();
        toolBar = new JToolBar("");

        maintext = new JTextArea("write on this page");
        maintext.setLineWrap(true);
        maintext.setFont(new Font("", 1, 22));
        pagenumber = new JLabel("Page 0");


        mainscreen.setLayout(new GridLayout(0, 2));
        editingscreen.setLayout(new BorderLayout());

        // adding screens to main panel
        panel.add(mainscreen,"Main Screen");
        panel.add(editingscreen,"Editing Screen");

        mainscreen.add(this.createButton("Start A New Choose Your Own Adventure Story",
                "NewStory"));
        mainscreen.add(this.createButton("Load Saved Choose Your Own Adventure Story",
                "LoadStory"));
        toolBar.add(this.createButton("Quit",
                "Quit"));
        toolBar.add(this.createButton("Save",
                "Save"));
        toolBar.add(this.createButton("MAKE BOOK",
                "MakeBook"));
        toolBar.add(this.createButton("Delete This Page",
                "DeletePage"));
        toolBar.add(pagenumber);
        editingscreen.add(toolBar,
                BorderLayout.PAGE_START);
        editingscreen.add(new JScrollPane(maintext),
                BorderLayout.CENTER);
        this.setVisible(true);
        this.add(panel);
        screens.show(panel, "Main Screen");
        choicespane = new JPanel();
        JPanel holder = new JPanel();

        editingscreen.add(holder,
                BorderLayout.LINE_END);
        pages = new JComboBox();
        pages.addActionListener(this.actionListener);
        this.setPages(1);
        holder.setLayout(new BorderLayout());
        JPanel pageselector = new JPanel();
        pageselector.add(pages);
        pageselector.add(this.createButton("Go To Selected Page",
                "Go"));
        holder.add(pageselector, BorderLayout.PAGE_START);
        holder.add(this.choicespane);
        this.setChoicesText(new ArrayList<String>());
    }

    private JButton createButton(String displaytext, String actiontext) {
        JButton button = new JButton();
        button.setText(displaytext);
        button.setActionCommand(actiontext);
        button.addActionListener(this.actionListener);

        return button;
    }


    @Override
    public void setMaintext(String mainText) {
        this.maintext.setText(mainText);
    }

    @Override
    public String getMaintext() {
        return this.maintext.getText();
    }

    @Override
    public void setChoicesText(ArrayList<String> choices) {
        choicespane.removeAll();
        this.choices = new ArrayList<>();
        choicespane.setLayout(new GridLayout(0, 2));
        choicespane.add(this.createButton("Link a Choice with a Page",
                "Link"));
        choicespane.add(this.createButton("Add new Choice",
                "AddChoice"));
        for(int i = 0; i < choices.size(); i ++) {
            System.out.println(choices.size());
            JPanel buttonholder = new JPanel();
            buttonholder.setLayout(new BorderLayout());
            buttonholder.add(this.createButton("Delete Choice " + i, "Choice " + i),
                    BorderLayout.PAGE_START);
            buttonholder.add(this.createButton("Go to Linked Page", "Page " + i),
                    BorderLayout.CENTER);
            choicespane.add(buttonholder);
            JTextArea newchoice;
            if(choices.get(i).length() == 0) {
                newchoice = new JTextArea("Type the choice you  want here");
            }
            else {
                newchoice = new JTextArea(choices.get(i));
            }
            newchoice.setLineWrap(true);
            newchoice.setBorder(new LineBorder(Color.BLACK));
            choicespane.add(newchoice);
            this.choices.add(newchoice);
        }
        this.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public ArrayList<String> getChoicesText() {
        ArrayList<String> output = new ArrayList<>();
        for( JTextArea jTextArea : this.choices) {
            output.add(jTextArea.getText());
        }
        return output;
    }

    @Override
    public void setPages(int numpages) {
        pages.removeAllItems();
        for(int i = 0; i < numpages; i++) {
            pages.addItem("Page " + i);
        }
    }

    @Override
    public int getSelectedPage() {
        return pages.getSelectedIndex();
    }

    @Override
    public void setPageNumber(int pgnum) {
        this.pagenumber.setText("          The Current Page is " + String.valueOf(pgnum));
    }

    @Override
    public void displayMainScreen() {
        this.screens.show(panel, "Main Screen");
    }

    @Override
    public void displayEditingScreen() {
        this.screens.show(panel, "Editing Screen");
    }
}
