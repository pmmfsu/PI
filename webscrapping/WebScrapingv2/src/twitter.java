import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class twitter {
    public static void main(String[] args) {
        try {

            FileInputStream serviceAccount =
                    new FileInputStream("./ServiceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://projetointegrado-71528.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);


            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());

            String [] palavras = new String[]{"Castelo de Beja -filter:retweets", "Museu Regional de Beja -filter:retweets",
                    "Núcleo Museológico de Beja -filter:retweets","Igreja Matriz de santa maria -filter:retweets","Igreja da Sé -filter:retweets","capela de nossa senhora do rosario -filter:retweets",
                    "ermida de santo andre -filter:retweets", "igreja de nossa senhora dos prazeres -filter:retweets"};
            ConfigurationBuilder cf = new ConfigurationBuilder();
            cf.setDebugEnabled(true)
                    .setOAuthConsumerKey("7FwMi7SDspcGN8WUekeYkPr9W")
                    .setOAuthConsumerSecret("xyN3fWJ3nKVb7Gm0fethneTMlY1a0khodouDSBMYdX2sY7BCtL")
                    .setOAuthAccessToken("1134985944-5q4xhtIpD5dph4cvWYH56e9SIGJUiztwjhbY28r")
                    .setOAuthAccessTokenSecret("5eFPuAVp6dHml0R8rOgNeifjjGSKdilSC4ANhY59OsEMF");

            TwitterFactory twitterFactory = new TwitterFactory(cf.build());
            Twitter twitter = twitterFactory.getInstance();
            FileWriter outputFile = new FileWriter("twitter_" +time.getTime() +".csv");
            PrintWriter out = new PrintWriter(outputFile);

            try {

                for (int i = 0; i < palavras.length ; i++) {

                    Query patrimonios = new Query(palavras[i]);
                    QueryResult result;
                    do {
                         result = twitter.search(patrimonios);

                        List<Status> tweets1 = result.getTweets();


                        for (Status tweet1 : tweets1) {
                            int k = i+1;
                            String s = tweet1.getText().replaceAll("([\\r\\n])", "");
                            s = s.replaceAll(";", ",");
                            Date d = tweet1.getCreatedAt();
                            Timestamp t = new Timestamp(d.getTime());
                            out.println("@" + tweet1.getUser().getScreenName() + " ; " + s + ";" +t.getTime()+ ";"+k);
                        }

                    } while ((patrimonios = result.nextQuery()) != null );
                    TimeUnit.MINUTES.sleep(5);
                }



                out.close();
                outputFile.close();
            } catch (TwitterException te) {
                te.printStackTrace();
                System.out.println("Failed to search tweets: " + te.getMessage());
                System.exit(-1);
            }


           /* Query query = new Query("Castelo de Beja");
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                System.out.println(status.getText() + " - " + status.getCreatedAt());
                System.out.println();
            }*/

/*
            Query query = new Query();
            query.setCount(DEFAULT_QUERY_COUNT);
            query.setLang("en");
// set the bounding dates
            query.setSince(sdf.format(startDate));
            query.setUntil(sdf.format(endDate));

            QueryResult result = searchWithRetry(twitter, query); // searchWithRetry is my function that deals with rate limits

            while (result.getTweets().size() != 0) {

                List<Status> tweets = result.getTweets();
                System.out.print("# Tweets:\t" + tweets.size());
                Long minId = Long.MAX_VALUE;

                for (Status tweet : tweets) {
                    // do stuff here
                    if (tweet.getId() < minId)
                        minId = tweet.getId();
                }
                query.setMaxId(minId-1);
                result = searchWithRetry(twitter, query);*/
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
             /*   String url = "https://twitter.com/search?q=Portugal";
                Document dcUrls = Jsoup.connect(url).get();

            //
            Elements commentElement = dcUrls.select("div.css-1dbjc4n");


            for (Element el: commentElement) {
                String content = el.select("span.css-901oao").text();


                System.out.println(content);
            }
*/




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
