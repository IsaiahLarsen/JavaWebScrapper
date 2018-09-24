package webScraper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList<SubiPart> subiList = new ArrayList();

        //TODO get pages to search and part category from user
        int numPagesToSearch = 1;
        String partCategory = "exhaust";

        //loop through multiple pages
        while (numPagesToSearch < 6) {
            try {
                final Document doc = Jsoup.connect("http://www.subispeed.com/2015-subaru-sti/" + partCategory + "?p=" + numPagesToSearch).get();

            //add a subipart for each product
            for (Element e : doc.select("ul.products-grid li")) {
                final String partName = e.select(".product-name").text().trim();
                String price = e.select(".price").text().trim();
                subiList.add(new SubiPart(partName, price, Integer.toString(numPagesToSearch)));
            }
            numPagesToSearch++;
            }catch (Exception e){
                System.out.println(e.getMessage());
                numPagesToSearch++;
            }
        }

        //Add price filter
        PriceFilter priceFilter = new PriceFilter(subiList);

        //function to write to text file
        priceFilter.writeToFile();

        //Add part name filter
        PartNameFilter partNameFilter = new PartNameFilter(subiList);
        partNameFilter.writeToFile();

    }
}
