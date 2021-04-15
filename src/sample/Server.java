package sample;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Server
{
    protected Socket clientSocket = null;
    protected ServerSocket serverSocket = null;
    protected static ClientConnectionHandler[] threads = null;
    protected int numClients = 0;

    public static int SERVER_PORT = 16789;
    public static int MAX_CLIENTS = 2;
    public static String[][] tile = new String[3][3];
    public Server()
    {
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                tile[i][j] = "-";
            }
        }
        try
        {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("-------------------------");
            System.out.println("---Server Has Started----");
            System.out.println("-------------------------");
            System.out.println("Listening to Port: "+ SERVER_PORT);
        }
        catch(IOException e)
        {
            System.err.println("IOException while creating server connection");
        }
    }
    public void acceptConnection(){
        try {
            threads = new ClientConnectionHandler[MAX_CLIENTS];
            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client #" + (numClients + 1) + " connected.");
                threads[numClients] = new ClientConnectionHandler(clientSocket);
                threads[numClients].setClientNumber(numClients + 1);
                threads[numClients].start();
                numClients++;
                if (numClients == 2) {
                    break;
                }
            }
            System.out.println("No longer accepting connections");
        } catch (IOException e){
            System.out.println("IOException from AcceptConnection");
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.acceptConnection();
    }

    public static void winner(String message,String title)
    {
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);

    }
    public static void update()
    {
        Client.generateBoard();
    }

}
