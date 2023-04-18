import java.util.ArrayList;

public class Controller {
    public static ArrayList<Stock> stocks = new ArrayList<>();
    public static ArrayList<String> result = new ArrayList<>();

    public static void main(String[] args) {
        ManagerNaverStock managerNaverStock = new ManagerNaverStock(stocks);
        stocks = managerNaverStock.addAllStocks();

        NaverFinanceCrawler naverFinanceCrawler = new NaverFinanceCrawler(stocks, result);
        naverFinanceCrawler.exe();

    }
}
