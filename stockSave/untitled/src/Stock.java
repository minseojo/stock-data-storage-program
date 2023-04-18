import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum Stock {
    EV_ADVANCED_MATERIALS("이브이첨단소재", "131400"),
    CRYSTAL_NEW_MATERIAL("크리스탈신소재 ", "900250"),
    POSCO_M_TECH("포스코엠텍 ", "009520"),
    EID("이아이디 ", "093230"),
    URBAN_LITHIUM("어반리튬 ", "073570"),
    ECO_PRO("에코프로 ", "086520"),
    MOBASE("모베이스 ", "101330"),
    TSI("티에스아이 ", "277880 "),
    DONGKUK_INDUSTRY("동국산업 ", "005160");

    private final String name;
    private final String code;

    Stock(final String name, final String code) {
        this.name = name;
        this.code = code;
    }

    public static List<Stock> sortedValues() {
        Stock[] values = Stock.values();
        Arrays.sort(values, Comparator.comparing(o -> o.name));
        return List.of(values);
    }
    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }
    
}
