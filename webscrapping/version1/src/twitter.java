import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class twitter {
    public static void main(String[] args) {
        try {
/*
            String[] urls = new String[]{"https://www.tripadvisor.pt/Attraction_Review-g189102-d536521-Reviews-Castelo_de_Beja-Beja_Beja_District_Alentejo.html", "https://www.tripadvisor.pt/Attraction_Review-g189102-d3912127-Reviews-Museu_Regional_de_Beja_Museu_Rainha_D_Leonor-Beja_Beja_District_Alentejo.html",
                    "https://www.tripadvisor.pt/Attraction_Review-g189102-d3906702-Reviews-Nucleo_Museologico-Beja_Beja_District_Alentejo.html", "https://www.guiadacidade.pt/pt/poi-igreja-matriz-de-santa-maria-285346", "https://www.tripadvisor.pt/Attraction_Review-g189158-d246152-Reviews-Se_de_Lisboa_Igreja_de_Santa_Maria_Maior-Lisbon_Lisbon_District_Central_Portugal.html",
                    "https://www.tripadvisor.pt/Attraction_Review-g315901-d15033926-Reviews-Capela_de_Nossa_Senhora_do_Rosario-Chaves_Vila_Real_District_Northern_Portugal.html", "https://www.tripadvisor.pt/Attraction_Review-g189102-d13867513-Reviews-Ermida_de_Santo_Andre-Beja_Beja_District_Alentejo.html",
                    "https://www.tripadvisor.pt/Attraction_Review-g189102-d5986623-Reviews-Igreja_de_Nossa_Senhora_Dos_Prazeres_E_Museu_Episcopal-Beja_Beja_District_Alentej.html"};


 */
/*
            for (String url : urls) {
                System.out.println(url);
            }

 */

            //id div-total = react-root
                String url = "https://twitter.com/search?q=Portugal&src=trend_click&f=live";
                Document dcUrls = Jsoup.connect(url).get();

            //
            Elements commentElement = dcUrls.select("div.css-1dbjc4n");


            for (Element el: commentElement) {
                String content = el.select("span.css-901oao").text();


                System.out.println(content);
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
