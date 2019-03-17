import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HamletParserTest {
    private String hamletText;
    private HamletParser hamletParser;

    @Before
    public void setUp() {
        this.hamletParser = new HamletParser();
        this.hamletText = hamletParser.getHamletData();
    }
    @Test
    public void testChangeHamletToLeonFromFile() {
        HamletParser parser = new HamletParser();
        int beforeNumberofHamlet = parser.getNumberOfMatches("hamlet");
        int beforeNumberofLeon = parser.getNumberOfMatches("leon");
        parser.replaceAll("hamlet", "Leon");
        int afterNumberofHamlet = parser.getNumberOfMatches("hamlet");
        int afterNumberofLeon = parser.getNumberOfMatches("Leon");

        Assert.assertEquals(afterNumberofHamlet, 0);
        Assert.assertEquals(afterNumberofLeon, beforeNumberofHamlet + beforeNumberofLeon);
    }


    @Test
    public void testChangeHoratioToTariqFromFile() {
        String expected = "Tariq something else";
        HamletParser parser = new HamletParser();
        int beforeNumberofHoratio = parser.getNumberOfMatches("horatio");
        int beforeNumberofTariq = parser.getNumberOfMatches("tariq");
        parser.replaceAll("horatio", "Tariq");
        int afterNumberofHoratio = parser.getNumberOfMatches("horatio");
        int afterNumberofTariq = parser.getNumberOfMatches("Tariq");

        Assert.assertEquals(afterNumberofHoratio, 0);
        Assert.assertEquals(afterNumberofTariq, beforeNumberofHoratio + beforeNumberofTariq);
    }

    @Test
    public void testChangeHamletToLeon() {
        String expected = "Leon something else";
        HamletParser parser = new HamletParser("Hamlet something else");
        parser.replaceAll("hamlet", "Leon");
        Assert.assertEquals(parser.getHamletData(), expected);

    }
    @Test
    public void testChangeHamletToLeonSubsequentMultiple() {
        String expected = "Leon  ,\nLeon\n else";
        HamletParser parser = new HamletParser("Hamlet  ,\nhamlet\n else");
        int num = parser.getNumberOfMatches("hamlet");
        parser.replaceAll("hamlet", "Leon");
        Assert.assertEquals(parser.getHamletData(), expected);

    }

    @Test
    public void testChangeHoratioToTariq() {
        String expected = "Tariq something else";
        HamletParser parser = new HamletParser("Horatio something else");
        parser.replaceAll("horatio", "Tariq");
        Assert.assertEquals(parser.getHamletData(), expected);
    }
    @Test
    public void testChangeHoratioToTariqMultiple() {
        String expected = "Tariq something Tariq else";
        HamletParser parser = new HamletParser("Horatio something horatio else");
        parser.replaceAll("horatio", "Tariq");
        Assert.assertEquals(parser.getHamletData(), expected);
    }
    @Test
    public void testFindHoratioSimple() {
        HamletParser parser = new HamletParser("Horatio");
        boolean retrievedFound = parser.find("horatio");
        Assert.assertTrue(retrievedFound);
    }
    @Test
    public void testReplaceHoratioFullCaps() {
        HamletParser parser = new HamletParser("HORATIO jhsgdjhsgdjhsg");
        parser.replaceAll("HORATIO", "LEON");
        Assert.assertEquals(parser.getHamletData(), "LEON jhsgdjhsgdjhsg");
    }
    @Test
    public void testFindHoratio() {
        HamletParser parser = new HamletParser(" Horatio.");
        boolean retrievedFound = parser.find("horatio");
        Assert.assertTrue(retrievedFound);
    }
    @Test
    public void testFindHoratioMiddleFalse() {
        HamletParser parser = new HamletParser(" fHoratiof.");
        boolean retrievedFound = parser.find("horatio");
        Assert.assertFalse(retrievedFound);
    }
    @Test
    public void testFindHoratioFalse() {
        HamletParser parser = new HamletParser(" fHoyytof.");
        boolean retrievedFound = parser.find("horatio");
        Assert.assertFalse(retrievedFound);
    }
    @Test
    public void testFindHamletSimple() {
        HamletParser parser = new HamletParser("Hamlet");
        boolean retrievedFound = parser.find("hamlet");
        Assert.assertTrue(retrievedFound);
    }
    @Test
    public void testFindHamlet() {
        HamletParser parser = new HamletParser(" Hamlet.");
        boolean retrievedFound = parser.find("haMlet");
        Assert.assertTrue(retrievedFound);
    }
    @Test
    public void testFindHamletMiddleFalse() {
        HamletParser parser = new HamletParser(" fHamletf.");
        boolean retrievedFound = parser.find("hamlet");
        Assert.assertFalse(retrievedFound);
    }
    @Test
    public void testFindHamletFalse() {
        HamletParser parser = new HamletParser(" fHoyytof.");
        boolean retrievedFound = parser.find("horatio");
        Assert.assertFalse(retrievedFound);
    }

}