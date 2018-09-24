package webScraper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;

public class PartNameFilter implements Filters {
    ArrayList<SubiPart> subiList;
    public PartNameFilter(ArrayList<SubiPart> subiList){
        this.subiList = subiList;
    }

    /**
     * Method that writes the desired data to a text file hard coded as subiParts.txt
     * @params none
     */
    public void writeToFile(){
        boolean hasMatches = false;

        //catch exceptions
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("subiParts.txt", true));
            //Time stamp
            writer.write(new Date().toString().toUpperCase() + "\n");
            writer.write("Parts containing Part description\n");
            writer.write("-----------------------\n");

            //loop through and write each part and price
            for(SubiPart s: subiList) {
                if(s.getPartName().matches("(.*)Equal(.*)")) {
                    writer.write("Part Name: " + s.getPartName());
                    writer.newLine();
                    writer.write("Price: " + s.getPrice());
                    writer.newLine();
                    writer.write("Found on page: " + s.getPageNumber() + "\n");
                    writer.newLine();
                    hasMatches = true;
                }
            }
            //No matches
            if(!hasMatches){
                writer.write("No Matches...");
            }
            writer.close();

            //reset isMatches boolean
            hasMatches = false;

        }catch (Exception e){System.out.println(e.getMessage());}
    }
}
