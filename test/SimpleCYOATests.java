import Model.CYOAModelOperations;
import Model.IPage;
import Model.SimpleCYOAModel;
import Model.SimplePage;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
* A test class designed to test the simple version of the CYOA model.

public class SimpleCYOATests {
    protected IPage testpage;
    protected IPage testpage2;
    protected IPage testpage3;
    protected IPage testpage4;
    protected CYOAModelOperations testcyoamodel;

    /*
    * A method that initializes data for testing.

    protected void init() {
        testcyoamodel = new SimpleCYOAModel("simple model");
        testpage = new SimplePage();
        testpage2 = new SimplePage();
        testpage3 = new SimplePage();
        testpage4 = new SimplePage();

    }

    //test constructors are initializing data properly
    @Test
    public void constructorTest() {
        this.init();
        assertEquals(this.testcyoamodel.getTitle(), "simple model");
        assertEquals(this.testpage.getMainText(), "");
        assertEquals(this.testpage2.getMainText(), "");
    }

    //test that pages are added correctly in the correct order
    @Test
    public void addPagesTest() {
        this.init();
        //testcyoamodel.addPage(testpage);
        assertEquals(this.testcyoamodel.getPages().size(), 1);
        assertEquals(this.testcyoamodel.getPages().get(0), testpage);
        //testcyoamodel.addPage(testpage2);
        assertEquals(this.testcyoamodel.getPages().get(0), testpage);
        assertEquals(this.testcyoamodel.getPages().size(), 2);
        assertEquals(this.testcyoamodel.getPages().get(1), testpage2);
    }

    //test that pages are added correctly in the correct location when the location is specified
    @Test
    public void addPagesAtIndexTest() {
        this.init();
        //testcyoamodel.addPage(testpage);
        //testcyoamodel.addPage(testpage2);
      //  testcyoamodel.addPageAsChoice(testpage3, 0, "this is page 3");
        assertEquals(this.testcyoamodel.getPages().get(0), testpage);
        assertEquals(this.testcyoamodel.getPages().size(), 3);
        assertEquals(this.testcyoamodel.getPages().get(1), testpage2);
        assertEquals(this.testpage.getChoices().get(0).getDestination(), testpage3);
       // testcyoamodel.addPageAsChoice(testpage4, 0, "this is page 4");
        assertEquals(this.testpage.getChoices().get(1).getDestination(), testpage4);
    }

    //test that the model can correctly identify which indexs are not linked with the rest
    // in other words testing the getUnlinkedPageNumbers function

    @Test
    public void linkCheckerTesting() {
        this.init();
        assertEquals(testcyoamodel.getUnlinkedPageNumbers().size(), 0);
       // testcyoamodel.addPage(testpage);
        assertEquals(testcyoamodel.getUnlinkedPageNumbers().size(), 0);
       // testcyoamodel.addPage(testpage2);
        assertEquals(testcyoamodel.getUnlinkedPageNumbers().size(), 1);
        assertEquals((int)testcyoamodel.getUnlinkedPageNumbers().get(0), 1);
       // testcyoamodel.addPageAsChoice(testpage3, 0, "this is page 3");
        assertEquals(testcyoamodel.getUnlinkedPageNumbers().size(), 1);
        assertEquals((int)testcyoamodel.getUnlinkedPageNumbers().get(0), 1);
       // testcyoamodel.addPageAsChoice(testpage4, 0, "this is page 4");
        assertEquals(testcyoamodel.getUnlinkedPageNumbers().size(), 1);
        assertEquals((int)testcyoamodel.getUnlinkedPageNumbers().get(0), 1);
        this.testpage.removeChoice(1);
        assertEquals(testcyoamodel.getUnlinkedPageNumbers().size(), 2);
        assertEquals((int)testcyoamodel.getUnlinkedPageNumbers().get(0), 1);
        assertEquals((int)testcyoamodel.getUnlinkedPageNumbers().get(1), 3);
    }
}
*/
