package net.djtek.stockticker.marketdata;

public final class MarketDataProviderUpdate {
    private String symbol;
    private Double bid;

    private MarketDataProviderUpdate(MarketDataUpdateBuilder builder){
        this.symbol = builder.symbol;
        this.bid = builder.bid;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getBid() {
        return bid;
    }

    public static class MarketDataUpdateBuilder{
        private String symbol;
        private Double bid;

        public MarketDataUpdateBuilder setSymbol(String symbol){
            this.symbol = symbol;
            return this;
        }

        public MarketDataUpdateBuilder setBid(Double bid){
            this.bid = bid;
            return this;
        }

        public MarketDataProviderUpdate build(){
            return new MarketDataProviderUpdate(this);
        }
    }
}
