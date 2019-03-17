import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thook on 10/7/15.
 */
public class HamletParser {

    private String hamletData;

    private final boolean isCaseInSensitive = true;

    public HamletParser(){
        this.hamletData = loadFile();
    }

    public HamletParser(String inputString){
        this.hamletData = inputString;
    }

    private String loadFile(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("hamlet.txt").getFile());
        StringBuilder result = new StringBuilder("");
        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
            scanner.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return result.toString();
    }

    public void replaceAll(String toReplace, String newString){
        Matcher matcher = getMatcher(toReplace);
        //hamletData = matcher.replaceAll( newString );
       hamletData = matcher.replaceAll( "$1" + newString + "$3" );

    }

    public boolean find(String toFind){
        Matcher matcher = getMatcher(toFind);
        boolean isFound = matcher.find();
        return isFound;
    }

    private Matcher getMatcher(String toMatch){

        Pattern pattern = null;

        //String toMatchPattern = "\\b("+ toMatch + ")\\b"; // using word boundary
        //String toMatchPattern = "(?m)(^|\\W+?)("+ toMatch + ")(\\W+?|$)";

        String toMatchPattern = "(?m)(^|\\W{1})("+ toMatch + ")(\\W{1}|$)";

        if(isCaseInSensitive)
            pattern = Pattern.compile(toMatchPattern, Pattern.CASE_INSENSITIVE);
        else
            pattern = Pattern.compile(toMatchPattern);

        Matcher matcher = pattern.matcher(hamletData);

        return matcher;
    }

    public int getNumberOfMatches(String toFind){
        Matcher matcher = getMatcher(toFind);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public String getHamletData(){
        return hamletData;
    }
}
