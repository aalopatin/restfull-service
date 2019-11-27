package ru.watchlist.domain;

public enum TypePeriod {
    DATE(""),
    MONTH(""),
    TREEMONTHS("3M"),
    SIXMONTHS("6M"),
    NINEMONTHS("9M"),
    TWELVEMONTHS("12M"),
    QUARTER("Q"),
    HALFYEAR("2H");

    private String symbol;

    TypePeriod(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }
}
