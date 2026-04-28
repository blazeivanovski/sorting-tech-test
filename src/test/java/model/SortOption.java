package model;

public enum SortOption {

    NEWEST("newest"),
    OLDEST("oldest"),
    PRICE_ASC("£ → £££"),
    PRICE_DESC("£££ → £"),
    UPCOMING("upcoming"),
    RELEVANCE("relevance");

    private final String value;

    SortOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
