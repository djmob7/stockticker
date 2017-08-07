package net.djtek.stockticker.marketdata;

import java.util.List;

public interface MarketDataProviderHandler {
     void onUpdate(List<MarketDataProviderUpdate> updates);
}
