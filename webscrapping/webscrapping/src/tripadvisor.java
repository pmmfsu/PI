import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class tripadvisor {
    public static void main(String[] args) {
        try {

            String[] urls = new String[]{"https://www.tripadvisor.pt/Attraction_Review-g189102-d536521-Reviews-Castelo_de_Beja-Beja_Beja_District_Alentejo.html",
                    "https://www.tripadvisor.pt/Attraction_Review-g189102-d3912127-Reviews-Museu_Regional_de_Beja_Museu_Rainha_D_Leonor-Beja_Beja_District_Alentejo.html",
                    "https://www.tripadvisor.pt/Attraction_Review-g189102-d3906702-Reviews-Nucleo_Museologico-Beja_Beja_District_Alentejo.html", "https://www.tripadvisor.pt/Attraction_Review-g189109-d7225303-Reviews-Igreja_de_Santa_Maria-Serpa_Beja_District_Alentejo.html",
                    "https://www.tripadvisor.pt/ShowUserReviews-g189102-d13869129-r687459465-Se_Catedral_de_Beja_Igreja_de_Sao_Tiago-Beja_Beja_District_Alentejo.html",
                    "https://www.tripadvisor.pt/Attraction_Review-g315901-d15033926-Reviews-Capela_de_Nossa_Senhora_do_Rosario-Chaves_Vila_Real_District_Northern_Portugal.html", "https://www.tripadvisor.pt/Attraction_Review-g189102-d13867513-Reviews-Ermida_de_Santo_Andre-Beja_Beja_District_Alentejo.html",
                    "https://www.tripadvisor.pt/Attraction_Review-g189102-d5986623-Reviews-Igreja_de_Nossa_Senhora_Dos_Prazeres_E_Museu_Episcopal-Beja_Beja_District_Alentej.html"};


            String[] patrimonio = new String[]{"Castelo de Beja", "Museu Regional de Beja",
                    "Núcleo Museológico de Beja","Igreja Matriz de santa maria","Igreja da Sé","capela de nossa senhora do rosario",
                    "ermida de santo andre", "igreja de nossa-senhora dos prazeres"};

/*
            for (String url : urls) {
                System.out.println(url);
            }


 */
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            for (int i = 0; i < urls.length; i++) {

                //String url = "https://www.tripadvisor.pt/Attraction_Review-g189102-d536521-Reviews-Castelo_de_Beja-Beja_Beja_District_Alentejo.html";
                Document dcUrls = Jsoup.connect(urls[i]).get();

                String commentSelector = "div#REVIEWS";
                Elements commentElement = dcUrls.select(commentSelector);
                ArrayList<String> comments = new ArrayList<>();
                Elements elements = dcUrls.select("span.ratingDate");
                FileWriter outputFile = new FileWriter("tripAdvisor_" + time.getTime() +".csv");
                PrintWriter out = new PrintWriter(outputFile);

                Elements date_of_exp = dcUrls.select("div.prw_rup");

                Elements commentText = dcUrls.select("p.partial_entry");


                for (Element elem : elements) {

                /*out.append(elem.text());
                out.append("\r\n");

                 */
                    for (Element date_ex : date_of_exp) {


                        for (Element comment : commentText) {
              /* out.append(comment.text());
                out.append("\n");

               */


                            out.println(comment.text() + ";" + elem.text() + ";" + date_ex.text() + ";" + patrimonio[i]);
                        }
                    }
                }


                out.flush();
                out.close();
                outputFile.close();

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
