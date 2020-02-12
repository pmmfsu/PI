import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class trip {
    public static void main(String[] args) {
        try {





                String url = "https://www.tripadvisor.pt/Attraction_Review-g189102-d536521-Reviews-Castelo_de_Beja-Beja_Beja_District_Alentejo.html";
                Document dcUrls = Jsoup.connect(url).get();

            Elements insideScroll = dcUrls.select("div.ui_column");
                //  Elements method = dcUrls.getElementsByClass("#REVIEWS");
                Elements commentElement = insideScroll.select("ui_column > ");


            for (Element el:commentElement   ) {

                String rating = el.text();
                System.out.println(rating);
            }








        /*
            for (Element e: commentElement){
                comments.add(e.text());
            }
            for (String s: comments) {
                csv1.println(s + "----");

            }

         */
            /*



             */

            /*
            for (int i = 0; i < url.length(); i++) {
                //Document dcUrls = Jsoup.connect(urls[i]).get();
                Elements links = dcUrls.select("#review_summary");
                System.out.println(links.text());
            }
             */


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
