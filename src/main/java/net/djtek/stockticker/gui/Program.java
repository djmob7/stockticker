package net.djtek.stockticker.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Program extends Application
{
    public static void main(String... args){
        launch(args);
    }

    public void start(Stage stage){
        stage.setTitle("Stock Ticker");

        FlowPane rootNode = new FlowPane();

        Scene scene = new Scene(rootNode, 400, 600);

        stage.setScene(scene);

        stage.show();
    }
}
