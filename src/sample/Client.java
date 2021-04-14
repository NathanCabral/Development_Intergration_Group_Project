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
    private static BufferedReader in = null;
    private static PrintWriter networkOut = null;
    private static BufferedReader networkIn = null;

    //we can read this from the user too
    public  static String SERVER_ADDRESS = "localhost";
    public  static int    SERVER_PORT = 16789;
    public static String symbol;
    public static GridPane pane = new GridPane();
    public Stage primaryStage;
    public static String key;
    public static int counter = 0;

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
        generateBoard(pane,new String[3][3]);
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
    public static void generateBoard(GridPane pane,String tile[][])
    {
        if(counter == 0)
        {
            for(int i = 0; i < 3; i++)
            {
                for(int j = 0; j< 3; j++)
                {
                    Button block = new Button(tile[i][j]);
                    block.setMinWidth(200);
                    block.setMinHeight(200);
                    pane.add(block,i,j);
                    int x = i;
                    int y = j;
                    block.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            checkStatus(block,x,y);
                        }
                    });
                }
            }
        }
        else
        {
            Node result = null;
            ObservableList<Node> childrens = pane.getChildren();
            for(int i = 0; i < 3; i++)
            {
                for(int j = 0; j< 3; j++)
                {
                    for (Node node : childrens) {
                        if(pane.getRowIndex(node) == j && pane.getColumnIndex(node) == i) {
                            result = node;
                            break;
                        }
                    }
                    result.setAccessibleText(symbol);
                }
            }
        }
    }
    public static void checkStatus(Button block, int column, int row)
    {
        System.out.println(block.getText());
        if(block.getText()==null)
        {
            block.setText(symbol);
            networkOut.flush();
            networkOut.println("MOVE: " + block + "," + column + "," + row);
        }
        else
        {
            if(key.equals("1"))
            {
                block.setText("O");
            }
            else
            {
                block.setText("X");
            }
        }
    }
}

