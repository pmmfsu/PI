import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class TripAdvisor {
    public static void main(String[] args) {
        try {

            String[] urls = new String[]{"https://www.tripadvisor.pt/Attraction_Review-g189102-d536521-Reviews-Castelo_de_Beja-Beja_Beja_District_Alentejo.html",
                    "https://www.tripadvisor.pt/Attraction_Review-g189102-d3912127-Reviews-Museu_Regional_de_Beja_Museu_Rainha_D_Leonor-Beja_Beja_District_Alentejo.html",
                    "https://www.tripadvisor.pt/Attraction_Review-g189102-d3906702-Reviews-Nucleo_Museologico-Beja_Beja_District_Alentejo.html", "https://www.tripadvisor.pt/Attraction_Review-g189109-d7225303-Reviews-Igreja_de_Santa_Maria-Serpa_Beja_District_Alentejo.html",
                    "https://www.tripadvisor.pt/ShowUserReviews-g189102-d13869129-r687459465-Se_Catedral_de_Beja_Igreja_de_Sao_Tiago-Beja_Beja_District_Alentejo.html",
                    "https://www.tripadvisor.pt/Attraction_Review-g315901-d15033926-Reviews-Capela_de_Nossa_Senhora_do_Rosario-Chaves_Vila_Real_District_Northern_Portugal.html", "https://www.tripadvisor.pt/Attraction_Review-g189102-d13867513-Reviews-Ermida_de_Santo_Andre-Beja_Beja_District_Alentejo.html",
                    "https://www.tripadvisor.pt/Attraction_Review-g189102-d5986623-Reviews-Igreja_de_Nossa_Senhora_Dos_Prazeres_E_Museu_Episcopal-Beja_Beja_District_Alentej.html"};


            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            FileWriter outputFile = new FileWriter("tripAdvisor_" + time.getTime() +".csv");
            PrintWriter out = new PrintWriter(outputFile);


            for (int i = 0; i < urls.length; i++) {
                Document dcUrls = Jsoup.connect(urls[i]).get();

                Elements elements = dcUrls.getElementsByClass("social-member-event-MemberEventOnObjectBlock__event_type--3njyv");
                Elements pages = dcUrls.getElementsByClass("ui_pagination is-centered");


                String[] usernamePlusDate = new String[elements.size()];

                for (int j = 0; j < elements.size(); j++) {
                    usernamePlusDate[j] = elements.get(j).text().replaceAll(";", ",").replaceAll("([\\r\\n])", "");
                }

                elements = dcUrls.getElementsByClass("location-review-review-list-parts-ReviewTitle__reviewTitleText--2tFRT");
                String[] titulo = new String[elements.size()];

                for (int j = 0; j < elements.size(); j++) {
                    titulo[j] = elements.get(j).text().replaceAll(";", ",").replaceAll("([\\r\\n])", "");
                }

                elements = dcUrls.getElementsByClass("location-review-review-list-parts-ExpandableReview__reviewText--gOmRC");
                String[] comment = new String[elements.size()];

                for (int j = 0; j < elements.size(); j++) {
                    comment[j] = elements.get(j).text().replaceAll(";", ",").replaceAll("([\\r\\n])", "");
                }


                for (int j = 0; j < usernamePlusDate.length; j++) {
                    int p = i + 1;
                    out.println(usernamePlusDate[j] + ";" + titulo[j] + ";" + comment[j] + ";" + p);
                }
                if (pages.size() > 0) {

                    Elements pageNum = dcUrls.getElementsByClass("pageNum");
                    String s = pageNum.get(pageNum.size() - 1).text();
                    int maxPag = Integer.parseInt(s);

                    for (int k = 1; k < maxPag; k++) {
                        String[] tr = urls[i].split("Reviews");


                        dcUrls = Jsoup.connect(tr[0] + "Reviews-or" + k * 5 + tr[1] + "#REVIEWS").get();

                        elements = dcUrls.getElementsByClass("social-member-event-MemberEventOnObjectBlock__event_type--3njyv");
                        pages = dcUrls.getElementsByClass("ui_pagination is-centered");


                        usernamePlusDate = new String[elements.size()];

                        for (int j = 0; j < elements.size(); j++) {
                            usernamePlusDate[j] = elements.get(j).text().replaceAll(";", ",").replaceAll("([\\r\\n])", "");
                        }

                        elements = dcUrls.getElementsByClass("location-review-review-list-parts-ReviewTitle__reviewTitleText--2tFRT");
                        titulo = new String[elements.size()];

                        for (int j = 0; j < elements.size(); j++) {
                            titulo[j] = elements.get(j).text().replaceAll(";", ",").replaceAll("([\\r\\n])", "");
                        }

                        elements = dcUrls.getElementsByClass("location-review-review-list-parts-ExpandableReview__reviewText--gOmRC");
                        comment = new String[elements.size()];

                        for (int j = 0; j < elements.size(); j++) {
                            comment[j] = elements.get(j).text().replaceAll(";", ",").replaceAll("([\\r\\n])", "");
                        }



                        for (int j = 0; j < usernamePlusDate.length; j++) {
                            int p = i + 1;
                            out.println(usernamePlusDate[j] + ";" + titulo[j] + ";" + comment[j] + ";" + p);
                        }

                    }

                }
            }
            out.flush();
            out.close();
            outputFile.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
