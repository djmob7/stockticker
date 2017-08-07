package net.djtek.stockticker.marketdata;

import java.util.List;

public interface MarketDataProvider {
    /**
     * Subscribe to market data updates.  The call to subscribe() starts the
     * the market data provider.
     * @param handler The callback handler for updates
     * @param symbols The stock symbols to subscribe to
     * @param interval The update interval seconds
     * @return true on success, false otherwise
     */
    boolean subscribe (MarketDataProviderHandler handler, List<String> symbols, int interval);

    /**
     * Shutdown the market data provider.  The call to shutdown() stops and does any
     * cleanup of the market data provider.
     */
    void shutdown();
}
