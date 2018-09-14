package webScraper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class SubiPart {
    private String partName;
    private String price;
    private String pageNumber;

    public SubiPart(String partName, String price, String pageNumber){
        this.partName = partName;
        this.price = price;
        this.pageNumber = pageNumber;

    }

    public SubiPart() {
        partName = null;
        price = null;
        pageNumber = null;
    }

    public void wtiteToFile(ArrayList<SubiPart> subiParts){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("subiParts.txt", true));
            for(SubiPart s: subiParts) {
                    writer.write("Part Name: " + s.partName);
                    writer.newLine();
                    writer.write("Price: " + s.price);
                    writer.newLine();
                    writer.newLine();
            }
            writer.close();
        }catch (Exception e){System.out.println(e.getMessage());}

    }

    //GETTERS
    public String getPartName(){
        return partName;
    }

    public String getPageNumber(){return pageNumber;}

    public String getPrice(){
        return price;
    }
}
