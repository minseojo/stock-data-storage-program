class Stock {
    private String name;
    private String code;

    Stock(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "주식이름: " + name + "\n" +
                "주식코드: " + code;
    }
}
