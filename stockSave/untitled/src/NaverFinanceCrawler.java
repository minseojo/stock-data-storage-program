import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NaverFinanceCrawler {
    private static final String FILE_PATH_FORMAT = "D:/stock/files/%s.txt";
    private static final String STOCK_URL_FORMAT = "https://finance.naver.com/item/sise.naver?code=%s";

    public static void exe(final List<Stock> stocks) {
        try (
                FileWriter fileWriter = new FileWriter(String.format(FILE_PATH_FORMAT, LocalDate.now()), true);
                PrintWriter printWriter = new PrintWriter(fileWriter, true)
        ) {
            printWriter.println(String.join("\n", crawling(stocks)));
            System.out.println("크롤링 성공");
        } catch (Exception e) {
            System.out.println("파일 가져오기 실패: " + e);
        }
    }

    private static List<String> crawling(final List<Stock> stocks) {
        return stocks.stream()
                .map(NaverFinanceCrawler::toString)
                .toList();
    }

    private static String toString(final Stock stock) {
        try {
            Document doc = Jsoup.connect(String.format(STOCK_URL_FORMAT, stock.getCode())).get();
            Elements element = doc.select("div.rate_info > table.no_info > tbody > tr");
            return changeStockToFilePrintFormat(stock, element);
        } catch (IOException e) {
            throw new RuntimeException("크롤링 실패 name:" + stock.getName() + " code: " + stock.getCode());
        }
    }

    private static String changeStockToFilePrintFormat(final Stock stock, final Elements element) {
        String[] elements = element.text().split(" ");
        String 전일종가 = elements[0] + "종가: " + elements[1];
        String 고가 = elements[3] + ": " + elements[4].substring(0, elements[4].length() / 2);
        String 상한가 = elements[5].substring(1) + " " + elements[6].substring(0, elements[6].length() / 2);
        String 거래량 = elements[8] + ": " + elements[9];
        String 시가 = elements[11] + ": " + elements[12].substring(0, elements[12].length() / 2);
        String 저가 = elements[13] + ": " + elements[14].substring(0, elements[14].length() / 2);
        String 하한가 = elements[15].substring(1) + ": " + elements[16];
        String 거래대금 = elements[18] + ": " + elements[19] + " " + elements[21];

        return stock.getName() + "\n" +
                전일종가 + "\n" +
                고가 + "\n" +
                상한가 + "\n" +
                거래량 + "\n" +
                시가 + "\n" +
                저가 + "\n" +
                하한가 + "\n" +
                거래대금 + "\n";
    }

}
