package net.djtek.stockticker.gui;

import net.djtek.stockticker.marketdata.MarketDataProviderUpdate;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class GUIMarketDataProviderUpdate {
    private SimpleStringProperty symbol;
    private SimpleDoubleProperty bid;

    public GUIMarketDataProviderUpdate (MarketDataProviderUpdate update) {
        this.symbol.set(update.getSymbol());
        this.bid.set(update.getBid());
    }

    public String getSymbol() {
        return symbol.get();
    }

    public double getBid() {
        return bid.get();
    }
}