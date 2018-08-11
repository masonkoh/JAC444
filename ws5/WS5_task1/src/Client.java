import java.net.Socket;
import java.util.Scanner;
import java.io.*;
import java.net.*;


public class Client implements Runnable{

    Socket socketConnection;
    DataOutputStream outToServer; 
    DataInputStream din;


    Client() throws UnknownHostException, IOException {

            socketConnection = new Socket("127.0.0.1", 8000);
            outToServer = new DataOutputStream(socketConnection.getOutputStream());
            din = new DataInputStream(socketConnection.getInputStream());


            Thread thread;
            thread = new Thread(this);
            thread.start();

            BufferedReader br = null;
            String ClientName = null;
            Scanner input = new Scanner(System.in);
            String SQL = "";
            try {
                    System.out.print("Enter you name: ");
                    ClientName = input.next();
                    ClientName += ": ";
                    // passing query

                    br = new BufferedReader(new InputStreamReader(System.in));
                    while (!SQL.equalsIgnoreCase("exit")) {
                            System.out.println();
                            System.out.print(ClientName);

                            SQL = br.readLine();
                            //SQL = input.next();
                            outToServer.writeUTF(ClientName + SQL);
                            //outToServer.flush();
                            //System.out.println(din.readUTF());

                    }

            } catch (Exception e) {
                    System.out.println(e);
            }
    }

    public static void main(String[] arg) throws UnknownHostException, IOException {
            Client client = new Client();
    }


    public void run() {
            while (true) {
                    try {
                            System.out.println("\n" + din.readUTF());

                    } catch (IOException e) {
                            e.printStackTrace();
                    }

            }
    }
}