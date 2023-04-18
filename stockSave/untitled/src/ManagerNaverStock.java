import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

class ManagerNaverStock {
    private ArrayList<Stock> stocks = new ArrayList<>();
    ManagerNaverStock(ArrayList<Stock> stocks) {
        stocks = this.stocks;
    }

    ArrayList<Stock> addAllStocks() {
        Stock 이브이첨단소재 = new Stock("이브이첨단소재", "131400");
        Stock 크리스탈신소재 = new Stock("크리스탈신소재 ", "900250");
        Stock 포스코엠텍 = new Stock("포스코엠텍 ", "009520");
        Stock 이아이디 = new Stock("이아이디 ", "093230");
        Stock 어반리튬 = new Stock("어반리튬 ", "073570");
        Stock 에코프로 = new Stock("에코프로 ", "086520");
        Stock 모베이스 = new Stock("모베이스 ", "101330");
        Stock 티에스아이 = new Stock("티에스아이 ", "277880 ");
        Stock 동국산업 = new Stock("동국산업 ", "005160");

        stocks.add(이브이첨단소재);
        stocks.add(크리스탈신소재);
        stocks.add(포스코엠텍);
        stocks.add(이아이디);
        stocks.add(어반리튬);
        stocks.add(에코프로);
        stocks.add(모베이스);
        stocks.add(티에스아이);
        stocks.add(동국산업);
        stocks.sort((ns1, ns2) -> ns1.getName().compareTo(ns2.getName())); // 이름순 정렬

        return stocks;
    }
}
