package ru.watchlist.domain.enums;

public enum TypePeriod {
    DATE(""),
    MONTH(""),
    QUARTER("Q"),
    HALFYEAR("H"),
    NINEMONTHS("9M"),
    TWELVEMONTHS("Y");

    private String symbol;

    TypePeriod(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }
}
