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


            String[] patrimonio = new String[]{"Castelo de Beja", "Museu Regional de Beja",
                    "Igreja Matriz de santa maria","Igreja da Se","capela de nossa senhora do rosario",
                    "ermida de santo andre", "igreja de nossa senhora dos prazeres"};

            /*
            for (String url: urls) {
                System.out.println(url);
            }

             */
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
          //  String time = new SimpleDateFormat("mm:HH:dd:MM:yyyy'.txt'").format(new Date());
            FileWriter outputFile = new FileWriter( "guiaDaCidade_" + time.getTime() + ".csv");

            PrintWriter out = new PrintWriter(outputFile);


            for (int i = 0; i < urls.length; i++) {
                Document dcUrls = Jsoup.connect(urls[i]).get();
                Elements links = dcUrls.select("#review_summary");

                for (Element spans : links) {
                    String rating = spans.getElementsByTag("strong").text();
                    String classificacao = spans.getElementsByTag("em").text();
                    String nr_votos = spans.getElementsByTag("small").text();
                    out.println(rating  + ";" + classificacao + ";" + nr_votos + ";" + patrimonio[i]);
                }
                //System.out.println(links.text());
            }

            out.flush();
            out.close();
            outputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
