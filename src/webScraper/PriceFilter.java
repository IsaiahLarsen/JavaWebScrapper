package webScraper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Price filter finds any products with a price of $100 or above up to ten thousands
 * example to show use of regex
 * TODO make price range dynamic
 */

public class PriceFilter implements Filters {
    ArrayList<SubiPart> subiList;
    public PriceFilter(ArrayList<SubiPart> subiList){
        this.subiList = subiList;
    }


    /**
     * Method that writes the desired data to a text file hard coded as subiParts.txt
     * @params none
     */
    public void writeToFile(){
        int pageNumber = 1;
        boolean isMatches = false;

        //catch exceptions
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("subiParts.txt", true));
            //Time stamp
            writer.write(new Date().toString().toUpperCase() + "\n");
            writer.write("Parts with price $100.00 or above\n".toUpperCase());
            writer.write("-----------------------\n");

            //loop through and write each part and price
            for(SubiPart s: subiList) {
                if(s.getPrice().matches("\\$[1-9][0-9]{2,4}\\.\\d{2}")) {
                    writer.write("Part Name: " + s.getPartName());
                    writer.newLine();
                    writer.write("Price: " + s.getPrice());
                    writer.newLine();
                    writer.write("Found on page: " + s.getPageNumber() + "\n");
                    writer.newLine();
                    pageNumber++;
                    isMatches = true;
                }
            }
            //No matches
            if(!isMatches){
                writer.write("No Matches...");
            }
            writer.close();

            //reset isMatches boolean
            isMatches = false;

        }catch (Exception e){System.out.println(e.getMessage());}
    }
}
