package net.djtek.stockticker.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.djtek.stockticker.marketdata.MarketDataProviderUpdate;

import java.util.List;

public class GUIMarketDataProviderHandler {
    void onUpdate(List<MarketDataProviderUpdate> updates){
        ObservableList<GUIMarketDataProviderUpdate> guiUpdates = FXCollections.observableArrayList();
        updates.forEach(update -> guiUpdates.add(new GUIMarketDataProviderUpdate(update)));
        // TODO
    }
}
