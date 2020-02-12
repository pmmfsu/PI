import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class guiadacidade {
    public static void main(String[] args) {
        try{

            String[] urls = new String[]{"https://www.guiadacidade.pt/pt/poi-castelo-de-beja-13941", "https://www.guiadacidade.pt/pt/poi-convento-de-nossa-senhora-da-conceicao-museu-rainha-dona-leonor-285349",
                    "https://www.guiadacidade.pt/pt/poi-nucleo-museologico-da-rua-do-sembrano-285651","https://www.guiadacidade.pt/pt/poi-igreja-matriz-de-santa-maria-285346","https://maisbeja.blogs.sapo.pt/capela-de-nossa-senhora-do-rosario-158584",
                    "https://www.guiadacidade.pt/pt/poi-ermida-de-santo-andre-285345", "https://www.guiadacidade.pt/pt/poi-igreja-de-nossa-senhora-dos-prazeres-285347"};

            /*
            for (String url: urls) {
                System.out.println(url);
            }

             */
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
          //  String time = new SimpleDateFormat("mm:HH:dd:MM:yyyy'.txt'").format(new Date());
            FileWriter outputFile = new FileWriter( "castelo" + time.getTime() + ".csv");
            FileWriter outputFile2 = new FileWriter( "as" + time.getTime() + ".csv");
            FileWriter outputFile3 = new FileWriter( "12" + time.getTime() + ".csv");
            FileWriter outputFile4 = new FileWriter( "34" + time.getTime() + ".csv");
            FileWriter outputFile5 = new FileWriter( "56" + time.getTime() + ".csv");
            FileWriter outputFile6 = new FileWriter( "12" + time.getTime() + ".csv");
            FileWriter outputFile7 = new FileWriter( "guiadacidade_" + time.getTime() + ".csv");
            FileWriter outputFile8 = new FileWriter( "guiadacidade_" + time.getTime() + ".csv");
            PrintWriter out = new PrintWriter(outputFile);
            PrintWriter out2 = new PrintWriter(outputFile2);
            PrintWriter out3 = new PrintWriter(outputFile3);
            PrintWriter out4 = new PrintWriter(outputFile4);
            PrintWriter out5 = new PrintWriter(outputFile5);
            PrintWriter out6 = new PrintWriter(outputFile6);
            PrintWriter out7 = new PrintWriter(outputFile7);
            PrintWriter out8 = new PrintWriter(outputFile8);

            for (int i = 0; i < urls.length; i++) {
                Document dcUrls = Jsoup.connect(urls[i]).get();
                Elements links = dcUrls.select("#review_summary");

                for (Element spans : links) {
                    String rating = spans.getElementsByTag("strong").text();
                    String classificacao = spans.getElementsByTag("em").text();
                    String nr_votos = spans.getElementsByTag("small").text();
                    out.println(rating  + ";" + classificacao + ";" + nr_votos + ";");
                }
                //System.out.println(links.text());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
