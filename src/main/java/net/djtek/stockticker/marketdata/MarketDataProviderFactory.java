package net.djtek.stockticker.marketdata;

import net.djtek.stockticker.marketdata.yahoo.YahooMarketDataProvider;

public class MarketDataProviderFactory {
    public static MarketDataProvider create(){
        return new YahooMarketDataProvider();
    }
}
