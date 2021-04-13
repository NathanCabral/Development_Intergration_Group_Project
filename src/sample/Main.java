package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static String symbol;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Client player = new Client(primaryStage);
        primaryStage.setTitle("TIC TAC TOE GAME");
        primaryStage.setScene(new Scene(player.getPane(), 725, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
