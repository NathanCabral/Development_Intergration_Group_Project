package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.plaf.synth.SynthMenuBarUI;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;
import java.util.stream.Stream;


public class Client extends Frame
{
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter networkOut = null;
    private static BufferedReader networkIn = null;

    //we can read this from the user too
    public  static String SERVER_ADDRESS = "localhost";
    public  static int    SERVER_PORT = 16789;
    public String symbol;
    public static GridPane pane = new GridPane();
    public Stage primaryStage;
    public String key;

    public Client(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        try
        {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        }
        catch(UnknownHostException e)
        {
            System.err.println("Unknown host: "+SERVER_ADDRESS);
        }
        catch (IOException e)
        {
            System.err.println("IOEXception while connecting to server: "+SERVER_ADDRESS);
        }

        if (socket == null) {
            System.err.println("socket is null");
        }

        try
        {
            networkOut = new PrintWriter(socket.getOutputStream(),  true);
            networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e)
        {
            System.err.println("IOEXception while opening a read/write connection");
        }
        generateBoard(pane);
        getSymbol();

    }
    public GridPane getPane()
    {
        return pane;
    }
    public void getSymbol()
    {
        try
        {
            symbol = networkIn.readLine();
            if(symbol.equals("1"))
            {
                symbol = "X";
                key = "1";
            }
            else
            {
                symbol = "O";
                key ="2";
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    public void generateBoard(GridPane pane)
    {
        javafx.scene.control.Button block1 = new javafx.scene.control.Button("");
        block1.setMinWidth(200);
        block1.setMinHeight(200);

        block1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                checkStatus(block1,0,0);
            }
        });

        javafx.scene.control.Button block2 = new javafx.scene.control.Button("");
        block2.setMinWidth(200);
        block2.setMinHeight(200);

        block2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                checkStatus(block2,1,0);
            }
        });

        javafx.scene.control.Button block3 = new javafx.scene.control.Button("");
        block3.setMinWidth(200);
        block3.setMinHeight(200);

        block3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                checkStatus(block3,2,0);
            }
        });

        javafx.scene.control.Button block4 = new javafx.scene.control.Button("");
        block4.setMinWidth(200);
        block4.setMinHeight(200);

        block4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                checkStatus(block4,0,1);
            }
        });

        javafx.scene.control.Button block5 = new javafx.scene.control.Button("");
        block5.setMinWidth(200);
        block5.setMinHeight(200);

        block5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                checkStatus(block5,1,1);
            }
        });

        javafx.scene.control.Button block6 = new javafx.scene.control.Button("");
        block6.setMinWidth(200);
        block6.setMinHeight(200);

        block6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                checkStatus(block6,2,1);
            }
        });

        javafx.scene.control.Button block7 = new javafx.scene.control.Button("");
        block7.setMinWidth(200);
        block7.setMinHeight(200);

        block7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                checkStatus(block7,0,2);
            }
        });

        javafx.scene.control.Button block8 = new javafx.scene.control.Button("");
        block8.setMinWidth(200);
        block8.setMinHeight(200);

        block8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                checkStatus(block8,1,2);
            }
        });

        javafx.scene.control.Button block9 = new javafx.scene.control.Button("");
        block9.setMinWidth(200);
        block9.setMinHeight(200);

        block9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                checkStatus(block9,2,2);
            }
        });
        pane.add(block1,0,0);
        pane.add(block2,0,1);
        pane.add(block3,0,2);
        pane.add(block4,1,0);
        pane.add(block5,1,1);
        pane.add(block6,1,2);
        pane.add(block7,2,0);
        pane.add(block8,2,1);
        pane.add(block9,2,2);

    }
    public void checkStatus(Button block, int column, int row)
    {
        if(block.getText().equals(""))
        {
            block.setText(symbol);
            networkOut.flush();
            networkOut.println("MOVE: " + block + "," + column + "," + row);
        }
        else
        {
            System.out.println("ALREADY TAKEN");
        }
    }
}

