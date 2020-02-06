import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Page;
import com.restfb.types.Post;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class facebook {
    public static void main(String[] args) {
        try {
            String accessToken = "EAAHLGg9YXgkBAG88jTet49AfYYMKOBRynEea1BWzpHw8Xb0pbmWJDHoyP4YKV3KGshNgMjBCGgBaSPLVrB3uf8hBxjNDc6EQXdsfwZCeM66ZCzwEm0DDJPRZAbiHMi4JwK7b4IgSIl67YwXyvqSDneKAgzewODV1UvpuT898fCxC15Rj6PqXvZCZBeZCSGgMseZA187JsmlDjj69qCrZCm1lLv7Jc1DZApO0x9zuV9dKHpQZDZD";
            FacebookClient fbClient = new DefaultFacebookClient(accessToken);

            Connection<Page> allPages = fbClient.fetchConnection("search", Page.class,
                    Parameter.with("q", "name"),
                    Parameter.with("type", "beja"),
                    Parameter.with("limit", "limit"),
                    Parameter.with("fields", "fan_count,name,username,picture,url,link"));

            Iterator<List<Page>> it = allPages.iterator();
            boolean hasnext = it.hasNext();
            int n = 0;
            while (hasnext)  {
                List<Page> pageList = it.next();

                // up to limit results per pagination. Get only the first 'limit'
                // for (List<Page> pages: allPages) {
                for (int i = 0; i < pageList.size(); i++) {
                    Page p = allPages.getData().get(i);
                    if (p == null) {
                        hasnext = it.hasNext();
                        continue;
                    }

                    String pageName = p.getName();
                    String pageId   = p.getId();
                    String pageImg  = p.getPicture().getUrl();
                    String pageUserName = p.getUsername();
                    String pageUrl  = p.getLink();

                    ++n;
                    System.out.printf("Found page %d: %s / %s / %d\n", n, pageName, pageUserName);
                }

                // check for another element (another results page)
                hasnext = it.hasNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
