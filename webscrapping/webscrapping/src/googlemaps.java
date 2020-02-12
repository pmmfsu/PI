import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class googlemaps {
    public static void main(String[] args) {
        try{
 
            String[] urls = new String[]{"https://www.guiadacidade.pt/pt/poi-castelo-de-beja-13941", "https://www.guiadacidade.pt/pt/poi-convento-de-nossa-senhora-da-conceicao-museu-rainha-dona-leonor-285349",
                    "https://www.guiadacidade.pt/pt/poi-nucleo-museologico-da-rua-do-sembrano-285651","https://www.guiadacidade.pt/pt/poi-igreja-matriz-de-santa-maria-285346","https://maisbeja.blogs.sapo.pt/capela-de-nossa-senhora-do-rosario-158584",
                    "https://www.guiadacidade.pt/pt/poi-ermida-de-santo-andre-285345", "https://www.guiadacidade.pt/pt/poi-igreja-de-nossa-senhora-dos-prazeres-285347"};




                String url = "https://www.google.pt/maps/place/Castelo+de+Beja/@38.0173848,-7.8676554,17z/data=!4m7!3m6!1s0xd1a748422a39999:0x3e462a2401c78209!8m2!3d38.0173806!4d-7.8654667!9m1!1b1";
                Document dcUrls = Jsoup.connect(url).get();
                Elements method = dcUrls.select(".ripple-container");
                System.out.println(method);


                for (Element inside : method) {
                    String content = inside.select(".section-review-review-content").text();
                    System.out.println(content);
                }


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
