package net.djtek.stockticker.console;

import net.djtek.stockticker.marketdata.MarketDataProvider;
import net.djtek.stockticker.marketdata.MarketDataProviderFactory;
import net.djtek.stockticker.marketdata.MarketDataProviderHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {
    private static final int INTERVAL = 10;

    public static void main(String[] args){
        MarketDataProvider provider = MarketDataProviderFactory.create();
        MarketDataProviderHandler handler = new ConsoleMarketDataProviderHandler();

        if (args.length > 0) {
            provider.subscribe(handler, Arrays.asList(args), INTERVAL);
        }

        // main loop
        while (true){
            String input = System.console().readLine().trim();
            if (input.equals("exit")){
                break;
            } else if (input.startsWith("sub")) {
                provider.subscribe(handler, parseSymbols(input), INTERVAL);
            } else {
                help();
            }
        }

        provider.shutdown();
    }

    private static List<String> parseSymbols(String input){
        List<String> symbols = new ArrayList<>();
        String[] parts = input.split("\\s+");
        if (parts.length > 1) {
            for (int i = 1; i < parts.length; ++i) {
                symbols.add(parts[i]);
            }
        }
        return symbols;
    }

    private static void help(){
        System.out.println("Valid commands are as follows:");
        System.out.println("  sub <symbols> - Subscribe to stock symbol market data");
        System.out.println("  exit          - Exit program");
        System.out.println("");
    }
}