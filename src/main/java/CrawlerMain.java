import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;


public class CrawlerMain {
    public static void main(String[] args) throws IOException {
        testProxy();
    }

    private static void testProxy() {
        System.out.println("entry test proxy");
        //System.setProperty("socksProxyHost", "199.101.97.183"); // set proxy server
        //System.setProperty("socksProxyPort", "61336"); // set proxy port
        String test_url = "https://sfbay.craigslist.org/d/apts-housing-for-rent/search/apa";
        try {
            Document doc = Jsoup.connect(test_url).get();
            System.out.println("entry test proxy step1:");
            //body > section.articles-section > div > div > div > div.col-md-8.display-flex > div > div.table-responsive > table > tbody > tr:nth-child(1) > td:nth-child(2) > strong
            Elements items = doc.select("div.content > ul.rows > li.result-row"); //get used IP.
            //System.out.println(items);
            for (Element item : items) {
                System.out.println(item.select("a.result-image").first().attr("href"));
                System.out.println(item.select("span.result-price").first().text());
                System.out.println(item.select("p > a").first().text());
                System.out.println(item.select("p > time").first().attr("datetime"));
                Element housing=item.select("p > span.result-meta > span.housing").first();
                if(housing!=null)
                    System.out.println(housing.text());
                Element result_hood=item.select("p > span.result-meta > span.result-hood").first();
                if(result_hood!=null)
                    System.out.println(result_hood.text());
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
