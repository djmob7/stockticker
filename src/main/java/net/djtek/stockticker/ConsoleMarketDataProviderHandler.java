package net.djtek.stockticker;

import net.djtek.stockticker.marketdata.MarketDataProviderHandler;
import net.djtek.stockticker.marketdata.MarketDataProviderUpdate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by danmobley on 8/7/17.
 */
public class ConsoleMarketDataProviderHandler implements MarketDataProviderHandler {
    @Override
    public void onUpdate(List<MarketDataProviderUpdate> updates) {
        for (MarketDataProviderUpdate update : updates){
            System.out.println(String.format("%s %s %s",
                    new SimpleDateFormat("hh:mm:ss").format(new Date()), update.getSymbol(), getFormattedDoubleStr(update.getBid())));
        }

        if (updates.size() > 1) {
            System.out.println();
        }
    }

    private String getFormattedDoubleStr(Double d){
        String s = "-";
        if (d != null){
            s = String.format("%,.3f", d);
        }
        return s;
    }
}
