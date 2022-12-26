package Jsoup.practice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class reptile {

    public static void main(String[] args) throws IOException, ParseException {
        List<Prop> list= new ArrayList<>();
        // 進行爬蟲
        URL url = new URL("https://www.booking.com/searchresults.zh-tw.html?aid=304142&label=gen173nr-1FCAEoggI46AdIMFgEaOcBiAEBmAEwuAEHyAEM2AEB6AEB-AECiAIBqAIDuALzoqSdBsACAdICJDc3YzMxN2UzLTc1YmYtNGI3NS04ZjgxLWMxMDg0NGVmZTdmY9gCBeACAQ&sid=35b265348ca5948f76259a7946f80bad&sb=1&src=hotel&src_elem=sb&error_url=https%3A%2F%2Fwww.booking.com%2Fhotel%2Ftw%2Fmeng-ye-ken-ding-bao-dong-min-su.zh-tw.html%3Faid%3D304142%26label%3Dgen173nr-1FCAEoggI46AdIMFgEaOcBiAEBmAEwuAEHyAEM2AEB6AEB-AECiAIBqAIDuALzoqSdBsACAdICJDc3YzMxN2UzLTc1YmYtNGI3NS04ZjgxLWMxMDg0NGVmZTdmY9gCBeACAQ%26sid%3D35b265348ca5948f76259a7946f80bad%26dest_id%3D-2632489%3Bdest_type%3Dcity%3Bdist%3D0%3Bgroup_adults%3D2%3Bgroup_children%3D0%3Bhapos%3D1%3Bhpos%3D1%3Bno_rooms%3D1%3Breq_adults%3D2%3Breq_children%3D0%3Broom1%3DA%252CA%3Bsb_price_type%3Dtotal%3Bsr_order%3Dpopularity%3Bsrepoch%3D1672024444%3Bsrpvid%3Df07e16bd36e100d1%3Btype%3Dtotal%3Bucfs%3D1%26%26&highlighted_hotels=9237878&hp_sbox=1&ss=%E5%A2%BE%E4%B8%81&is_ski_area=0&ssne=%E5%A2%BE%E4%B8%81&ssne_untouched=%E5%A2%BE%E4%B8%81&dest_id=-2632489&dest_type=city&checkin_year=2023&checkin_month=1&checkin_monthday=5&checkout_year=2023&checkout_month=1&checkout_monthday=6&group_adults=2&group_children=0&no_rooms=1&from_sf=146&offset=0");
        Document document =Jsoup.parse(url,10000);

        //取得房源總數
        String total = document.select(".d3a14d00da").text().split(" ")[1];

        //取得資料
        List<Element> name = document.select("div[data-testid='property-card'] div[data-testid='title']");
        List<Element> cost = document.select("div[data-testid='property-card'] span[data-testid='price-and-discounted-price']");

        for(int i =0;i<name.size();i++){
            list.add(new Prop(name.get(i).text(),cost.get(i).text()));
        }

        for(int j=25;j<=Integer.parseInt(total);){
            url = new URL(url+"&offset="+j);
            document =Jsoup.parse(url,10000);
            name = document.select("div[data-testid='property-card'] div[data-testid='title']");
            cost = document.select("div[data-testid='property-card'] span[data-testid='price-and-discounted-price']");
            for(int i =0;i<name.size();i++){
                // 去除重複的旅館
                if(name.get(i).text().equals("夢也．墾丁包棟民宿")){
                    continue;
                }
                else {
                    list.add(new Prop(name.get(i).text(),cost.get(i).text()));
                }
            }

            if(Integer.parseInt(total)-25<0){
                j=Integer.parseInt(total);
            }
            else{
                j+=25;
            }
        }
        System.out.println(list);
        System.out.println(list.size());
   }
}
