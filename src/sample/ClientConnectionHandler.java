package sample;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Pattern;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Pattern;

public class ClientConnectionHandler extends Thread
{
    protected Socket socket       = null;
    protected PrintWriter out     = null;
    protected BufferedReader in   = null;

    public int counter = 0;

    protected static boolean updateRequired = false;

    public ClientConnectionHandler(Socket socket)
    {
        super();
        this.socket = socket;
        try
        {
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(IOException e)
        {
            System.err.println("IOEXception while opening a read/write connection");
        }
    }
    public void setClientNumber(int number)
    {
        out.println(number);
    }

    public void run()
    {
        boolean endOfSession = false;

        while(!endOfSession) {
            endOfSession = processCommand();
        }
        try {
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    protected boolean processCommand() {
        String message = null;

        try {
            message = in.readLine();
        } catch (IOException e) {
            System.err.println("Error reading command from socket.");
            return true;
        }
        if (message == null) {
            return true;
        }
        StringTokenizer st = new StringTokenizer(message);
        String command = st.nextToken();
        String args = null;
        if (st.hasMoreTokens()) {
            args = message.substring(command.length()+1, message.length());
        }
        return processCommand(command, args);
    }
    protected boolean processCommand(String command, String arguments) {

        // these are the other possible commands
        if (command.equalsIgnoreCase("MOVE:"))
        {
            String[] values = arguments.split("'");
            String player = values[1];
            values = arguments.split(",");
            int column = Integer.valueOf(values[1]);
            int row = Integer.valueOf(values[2]);
            if(Server.tile[column][row].equals("-"))
                Server.tile[column][row] = player;
            else{
                System.out.println("ERRRROR");
            }
            out.println(Arrays.deepToString(Server.tile));
            if(checkWin(player))
            {
                String message = "Player " + player + " has won the Game";
                Server.winner(message,"Winner");
                return true;
            }
            if(counter == 9)
            {
                String message = "Game Ended In A Draw";
                Server.winner(message,"Draw");
                return true;
            }

        }
        counter++;
        if (command.equalsIgnoreCase("UPDATE")){
            out.println(Arrays.deepToString(Server.tile));
        }
        return false;
    }
    protected boolean checkRowWin(String symbol)
    {
        int winFound = 0;
        for(int i = 0; i < 3; i++)
        {
            winFound = 0;
            for(int j = 0; j < 3; j++)
            {
                String value = Server.tile[i][j];
                if(symbol.equals(value))
                {
                    winFound++;
                }
            }
            if(winFound == 3)
            {
                return true;
            }
        }
        return false;
    }
    protected boolean checkColumnWin(String symbol)
    {
        int winFound = 0;
        for(int i = 0; i < 3; i++)
        {
            winFound = 0;
            for(int j = 0; j < 3; j++)
            {
                String value = Server.tile[j][i];
                if(symbol.equals(value))
                {
                    winFound++;
                }
            }
            if(winFound == 3)
            {
                return true;
            }
        }
        return false;
    }
    protected boolean checkDiagonalWinRight(String symbol)
    {
        int winFound = 0;
        for(int i = 0; i < 3; i++)
        {
            String value = Server.tile[i][i];
            if(symbol.equals(value))
            {
                winFound++;
            }
        }
        if(winFound == 3)
        {
            return true;
        }
        return false;
    }
    protected boolean checkDiagonalWinLeft(String symbol)
    {
        int i = 2;
        int j = 0;
        int winFound = 0;
        while(i>= 0)
        {
            String value = Server.tile[j][i];
            if(symbol.equals(value))
            {
                winFound++;
            }
            i--;
            j++;
        }
        if(winFound == 3)
        {
            return true;
        }
        return false;
    }
    protected boolean checkWin(String symbol)
    {
        if(checkRowWin(symbol)||checkColumnWin(symbol)||checkDiagonalWinRight(symbol)||checkDiagonalWinLeft(symbol))
        {
            return true;
        }
        return false;
    }

}