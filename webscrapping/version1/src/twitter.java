import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class twitter {
    public static void main(String[] args) {
        try {


            ConfigurationBuilder cf = new ConfigurationBuilder();
            cf.setDebugEnabled(true)
                    .setOAuthConsumerKey("7FwMi7SDspcGN8WUekeYkPr9W")
                    .setOAuthConsumerSecret("xyN3fWJ3nKVb7Gm0fethneTMlY1a0khodouDSBMYdX2sY7BCtL")
                    .setOAuthAccessToken("1134985944-5q4xhtIpD5dph4cvWYH56e9SIGJUiztwjhbY28r")
                    .setOAuthAccessTokenSecret("5eFPuAVp6dHml0R8rOgNeifjjGSKdilSC4ANhY59OsEMF");

            TwitterFactory twitterFactory = new TwitterFactory(cf.build());
            Twitter twitter = twitterFactory.getInstance();
            FileWriter outputFile = new FileWriter("twitter.csv");
            PrintWriter out = new PrintWriter(outputFile);

            try {
                Query patrimonios1 = new Query(" Castelo de Beja -filter:retweets");

                Query patrimonios2 = new Query("Museu Regional de Beja -filter:retweets");

                Query patrimonios3 = new Query("Teatro Pax Julia -filter:retweets");
                Query patrimonios4 = new Query("Núcleo Museológico de Beja -filter:retweets");
                Query patrimonios5 = new Query("Igreja da Sé de Beja -filter:retweets");
                Query patrimonios6 = new Query("Capela de Nossa Senhora do Rosário -filter:retweets");
                Query patrimonios7 = new Query("Ermida De Santo André de Beja -filter:retweets");
                Query patrimonios8 = new Query("Igreja dos Prazeres de Beja -filter:retweets");



                QueryResult result1;
                QueryResult result2;
                QueryResult result3;
                QueryResult result4;
                QueryResult result5;
                QueryResult result6;
                QueryResult result7;
                QueryResult result8;
                do {
                    result1 = twitter.search(patrimonios1);
                    System.out.println("Tweet1 ----------------------------------------------");


                    List<Status> tweets1 = result1.getTweets();


                    for (Status tweet1 : tweets1) {

                        System.out.println("@" + tweet1.getUser().getScreenName() + " ; " + tweet1.getText());
                    }


                   // TimeUnit.SECONDS.sleep(5);


                } while ((patrimonios1 = result1.nextQuery()) != null );
                TimeUnit.SECONDS.sleep(5);


                do {
                    result2 = twitter.search(patrimonios2);
                    System.out.println("Tweet2 ----------------------------------------------");

                    List<Status> tweets2 = result2.getTweets();


                    for (Status emma : tweets2) {

                        System.out.println("@" + emma.getUser().getScreenName() + " ; " + emma.getText());
                    }

                    // TimeUnit.SECONDS.sleep(5);


                } while ((patrimonios2 = result2.nextQuery()) != null );
                TimeUnit.SECONDS.sleep(5);

                do {
                    result3 = twitter.search(patrimonios3);
                    System.out.println("Tweet2 ----------------------------------------------");

                    List<Status> tweets3 = result3.getTweets();


                    for (Status emma : tweets3) {

                        System.out.println("@" + emma.getUser().getScreenName() + " ; " + emma.getText());
                    }

                    // TimeUnit.SECONDS.sleep(5);


                } while ((patrimonios3 = result3.nextQuery()) != null );
                TimeUnit.SECONDS.sleep(5);

                do {
                    result4 = twitter.search(patrimonios4);
                    System.out.println("Tweet2 ----------------------------------------------");

                    List<Status> tweets4 = result4.getTweets();


                    for (Status emma : tweets4) {

                        System.out.println("@" + emma.getUser().getScreenName() + " ; " + emma.getText());
                    }

                    // TimeUnit.SECONDS.sleep(5);


                } while ((patrimonios4 = result4.nextQuery()) != null );

                TimeUnit.SECONDS.sleep(5);

                do {
                    result5 = twitter.search(patrimonios5);
                    System.out.println("Tweet2 ----------------------------------------------");

                    List<Status> tweets5 = result5.getTweets();


                    for (Status emma : tweets5) {

                        System.out.println("@" + emma.getUser().getScreenName() + " ; " + emma.getText());
                    }

                    // TimeUnit.SECONDS.sleep(5);


                } while ((patrimonios5 = result5.nextQuery()) != null );
                TimeUnit.SECONDS.sleep(5);

                do {
                    result6 = twitter.search(patrimonios6);
                    System.out.println("Tweet2 ----------------------------------------------");

                    List<Status> tweets6 = result6.getTweets();


                    for (Status emma : tweets6) {

                        System.out.println("@" + emma.getUser().getScreenName() + " ; " + emma.getText());
                    }

                    // TimeUnit.SECONDS.sleep(5);


                } while ((patrimonios6 = result6.nextQuery()) != null );
                TimeUnit.SECONDS.sleep(5);

                do {
                    result7 = twitter.search(patrimonios7);
                    System.out.println("Tweet2 ----------------------------------------------");

                    List<Status> tweets7 = result7.getTweets();


                    for (Status emma : tweets7) {

                        System.out.println("@" + emma.getUser().getScreenName() + " ; " + emma.getText());
                    }

                    // TimeUnit.SECONDS.sleep(5);


                } while ((patrimonios7 = result7.nextQuery()) != null );
                TimeUnit.SECONDS.sleep(5);


                do {
                    result8 = twitter.search(patrimonios8);
                    System.out.println("Tweet2 ----------------------------------------------");

                    List<Status> tweets8 = result8.getTweets();


                    for (Status emma : tweets8) {

                        System.out.println("@" + emma.getUser().getScreenName() + " ; " + emma.getText());
                    }

                    // TimeUnit.SECONDS.sleep(5);


                } while ((patrimonios8 = result8.nextQuery()) != null );
                TimeUnit.SECONDS.sleep(5);


                System.exit(0);
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
