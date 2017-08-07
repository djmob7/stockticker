package net.djtek.stockticker.marketdata.yahoo;

import net.djtek.stockticker.marketdata.MarketDataProvider;
import net.djtek.stockticker.marketdata.MarketDataProviderHandler;
import net.djtek.stockticker.marketdata.MarketDataProviderUpdate;
import net.djtek.stockticker.utils.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class YahooMarketDataProvider implements MarketDataProvider {
    private MarketDataProviderHandler handler;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private Future<?> future;

    private class YahooMarketDataProviderRunnable implements Runnable {
        private URL url;

        public YahooMarketDataProviderRunnable(URL url){
            this.url = url;
        }

        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                List<MarketDataProviderUpdate> updates = new ArrayList<>();

                String line;
                while ((line = reader.readLine()) != null) {
                    MarketDataProviderUpdate update = createUpdate(line);
                    if (update != null) {
                        updates.add(update);
                    }
                }

                if (handler != null) {
                    handler.onUpdate(updates);
                }
            } catch (IOException e){
                // TODO
            }
        }

        private MarketDataProviderUpdate createUpdate(String line){
            MarketDataProviderUpdate update = null;

            String[] parts = line.split(",");
            if (parts.length >= 2){
                MarketDataProviderUpdate.MarketDataUpdateBuilder builder
                        = new MarketDataProviderUpdate.MarketDataUpdateBuilder();
                builder.setSymbol(parseSymbol(parts[0]));
                builder.setBid(parseBid(parts[1]));
                update = builder.build();
            } else {
                //TODO
            }

            return update;
        }

        private String parseSymbol(String s){
            return StringUtils.trimDoubleQuotes(s);
        }

        private Double parseBid(String s){
            Double d = null;

            if (!s.equalsIgnoreCase("N/A")) {
                try {
                    d = Double.parseDouble(s);
                } catch (NumberFormatException e) {
                    // TODO
                }
            }

            return d;
        }
    }

    public boolean subscribe(MarketDataProviderHandler handler, List<String> symbols, int interval) {
        boolean result = false;

        this.handler = handler;

        // stop the currently running task
        if (future != null){
            future.cancel(false);
            future = null;
        }

        try {
            URL url = new URL(createUrlString(symbols));
            future = scheduler.scheduleWithFixedDelay(new YahooMarketDataProviderRunnable(url), 0, interval, SECONDS);
            result = true;
        } catch (MalformedURLException e){
            // TODO
        }

        return result;
    }

    public void shutdown() {
        scheduler.shutdown();
    }

    private String createUrlString(List<String> symbols){
        StringBuilder sb = new StringBuilder("http://download.finance.yahoo.com/d/quotes.csv");
        if (symbols.size() > 0) {
            sb.append("?s=");
            for (int i = 0; i < symbols.size(); ++i){
                if (i > 0){
                    sb.append("+");
                }
                sb.append(symbols.get(i));
            }

            // subscribe to symbol and bid
            sb.append("&f=sb");
        }
        return sb.toString();
    }
}