import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
            for (int i = 0; i < urls.length; i++) {
                Document dcUrls = Jsoup.connect(urls[i]).get();
                Elements links = dcUrls.select("#review_summary");

                for (Element spans : links) {
                    System.out.println(spans.getElementsByTag("strong").text());
                }
                //System.out.println(links.text());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
