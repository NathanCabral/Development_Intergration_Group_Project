package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {
    public static String symbol;
    public static GridPane pane = new GridPane();
    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(Arrays.deepToString(Server.tile));
        Client player = new Client(primaryStage);
        Scene scene = new Scene(player.getPane(), 600, 643);
        primaryStage.setTitle("TIC TAC TOE GAME");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("root.css").toExternalForm());

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
