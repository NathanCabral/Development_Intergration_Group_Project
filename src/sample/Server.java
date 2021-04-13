package sample;
import java.io.*;
import java.net.*;

public class Server
{
    protected Socket clientSocket = null;
    protected ServerSocket serverSocket = null;
    protected ClientConnectionHandler[] threads = null;
    protected int numClients = 0;

    public static int SERVER_PORT = 16789;
    public static int MAX_CLIENTS = 2;

    public Server()
    {
        try
        {
            serverSocket = new ServerSocket(SERVER_PORT);
            threads = new ClientConnectionHandler[MAX_CLIENTS];
            System.out.println("-------------------------");
            System.out.println("---Server Has Started----");
            System.out.println("-------------------------");
            System.out.println("Listening to Port: "+ SERVER_PORT);
            while(true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client #"+(numClients+1)+" connected.");
                threads[numClients] = new ClientConnectionHandler(clientSocket);
                threads[numClients].setClientNumber(numClients+1);
                threads[numClients].start();
                numClients++;
            }
        }
        catch(IOException e)
        {
            System.err.println("IOException while creating server connection");
        }
    }
    public static void main(String[] args) {
        Server server = new Server();
    }
}
