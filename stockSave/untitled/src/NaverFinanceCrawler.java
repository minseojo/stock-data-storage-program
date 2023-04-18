import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NaverFinanceCrawler {

    private static final String FILE_PATH_FORMAT = "C:/Users/user/Desktop/stock-data-storage-program/files/%s.txt";
    private static final String STOCK_URL_FORMAT = "https://finance.naver.com/item/sise.naver?code=%s";

    public static void exe(final List<Stock> stocks) {
        try (
                FileWriter fileWriter = new FileWriter(String.format(FILE_PATH_FORMAT, LocalDate.now()), false); // 파일 있으면, 처음부터 쓰기
                PrintWriter printWriter = new PrintWriter(fileWriter, true)
        ) {
            printWriter.println(String.join("\n", crawling(stocks)));
            System.out.println("크롤링 성공");
        } catch (Exception e) {
            System.out.println("크롤링 실패: " + e);
        }
    }

    private static List<String> crawling(final List<Stock> stocks) {
        return stocks.stream()
                .map(NaverFinanceCrawler::toString)
                .collect(Collectors.toList());
    }

    private static String toString(final Stock stock) {
        try {
            Document doc = Jsoup.connect(String.format(STOCK_URL_FORMAT, stock.getCode())).get();
            return changeStockToFilePrintFormat(stock);
        } catch (IOException e) {
            throw new RuntimeException("크롤링 실패 name:" + stock.getName() + " code: " + stock.getCode());
        }
    }

    private static String changeStockToFilePrintFormat(final Stock stock) throws IOException {
        Document doc = Jsoup.connect(String.format(STOCK_URL_FORMAT, stock.getCode())).get();

        // 현재가격, 전일대비증가가격, 등락율
        Elements today = doc.select("div.rate_info > div.today");
        String[] elements = today.text().split(" ");
        String 현재가격 = "현재가격: " + elements[0];
        String 전일대비증가가격 = "전일대비증가가격: " + elements[4] + "원 " + elements[3];
        String 등락율 = "등락율: " + elements[7] + " " + elements[8] + "%";

        // 전일종가, 고가, 상한가, 거래량, 시가, 저가, 하한가, 거래대금
        Elements element = doc.select("div.rate_info > table.no_info > tbody > tr");
        elements = element.text().split(" ");
        String 전일종가 = elements[0] + "종가: " + elements[1];
        String 고가 = elements[3] + ": " + elements[4].substring(0, elements[4].length()/2);
        String 상한가 = elements[5].substring(1) + " " + elements[6].substring(0, elements[6].length()/2);
        String 거래량 = elements[8] + ": " + elements[9];
        String 시가 = elements[11] + ": " + elements[12].substring(0, elements[12].length()/2);
        String 저가 = elements[13] + ": " + elements[14].substring(0, elements[14].length()/2);
        String 하한가 = elements[15].substring(1) + ": " + elements[16];
        String 거래대금 = elements[18] + ": " + elements[19] + " " + elements[21];

        return stock.getName() + "\n" +
                현재가격 + "\n" +
                전일대비증가가격 + "\n" +
                등락율 + "\n" +
                전일종가 + "\n" +
                시가 + "\n" +
                저가 + "\n" +
                고가 + "\n" +
                하한가 + "\n" +
                상한가 + "\n" +
                거래량 + "\n" +
                거래대금 + "\n";
    }

}