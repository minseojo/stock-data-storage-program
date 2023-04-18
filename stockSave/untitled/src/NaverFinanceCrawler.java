import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NaverFinanceCrawler {
    private ArrayList<Stock> stocks = new ArrayList<>(); // 크롤링 할 주식들
    private ArrayList<String> result = new ArrayList<>(); // 크롤링 결과

    String fileName = "";
    private FileWriter fw = null;
    private PrintWriter out = null;
    NaverFinanceCrawler(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }


    public void exe() {
        try {
            getFile();
            crawling();
            System.out.println("크롤링 성공");
        } catch (Exception e) {
            System.out.println("크롤링 실패: " + e);
        }
    }
    private void getFile() {
        SimpleDateFormat today = new SimpleDateFormat("yyyyMMdd");
        String date = today.format(new Date());
        try {
            fileName = "D:/stock/files/" + date + ".txt";
            fw = new FileWriter(fileName, true); // 추가모드
            out = new PrintWriter(fw, true);
        } catch (Exception e) {
            System.out.println("파일 가져오기 실패: " + e);
        }
    }
    public ArrayList<String> crawling () {
        for(Stock stock : stocks) {
            String 이름 = stock.getName(); // 주식 이름
            String 코드 = stock.getCode(); // 주식 코드
            String url = "https://finance.naver.com/item/sise.naver?code=" + 코드;

            try {
                Document doc = Jsoup.connect(url).get();
                Elements element = doc.select("div.rate_info > table.no_info > tbody > tr");
                String[] elements = element.text().split(" ");
                String 전일종가 = elements[0] + "종가: " + elements[1];
                String 고가 = elements[3] + ": " + elements[4].substring(0, elements[4].length()/2);
                String 상한가 = elements[5].substring(1) + " " + elements[6].substring(0, elements[6].length()/2);
                String 거래량 = elements[8] + ": " + elements[9];
                String 시가 = elements[11] + ": " + elements[12].substring(0, elements[12].length()/2);
                String 저가 = elements[13] + ": " + elements[14].substring(0, elements[14].length()/2);
                String 하한가 = elements[15].substring(1) + ": " + elements[16];
                String 거래대금 = elements[18] + ": " + elements[19] + " " + elements[21];
                /*System.out.println(이름);
                System.out.println(전일종가);
                System.out.println(고가);
                System.out.println(상한가);
                System.out.println(거래량);
                System.out.println(시가);
                System.out.println(저가);
                System.out.println(하한가);
                System.out.println(거래대금);
                */
                String str = 이름 + "\n" +
                        전일종가 + "\n" +
                        고가 + "\n" +
                        상한가 + "\n" +
                        거래량 + "\n" +
                        시가 + "\n" +
                        저가 + "\n" +
                        하한가 + "\n" +
                        거래대금 + "\n";
                
                out.println(str); // 크롤링 하고 파일에 쓰기
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}