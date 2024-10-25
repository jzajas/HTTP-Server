import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

//import java.io.*;
//import java.net.*;


public class Main {
  public static void main(String[] args) {
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");

//     Uncomment this block to pass the first stage

     try {
         ServerSocket serverSocket = new ServerSocket(4221);

         // Since the tester restarts your program quite often, setting SO_REUSEADDR
         // ensures that we don't run into 'Address already in use' errors
         serverSocket.setReuseAddress(true);
         Socket clientSocket = serverSocket.accept(); // Wait for connection from client.

         OutputStream output = clientSocket.getOutputStream();
         output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());

         BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
         String requestLine = input.readLine();

         String[] requestLineArray = requestLine.split("\\s+");
         String filePath = requestLineArray[1];

         if (filePath.equals("/")) {
             output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
         } else {
             output.write("HTTP/1.1 404 Not Found\r\n\r\n".getBytes());
         }

         System.out.println("accepted new connection");

     } catch (IOException e) {
       System.out.println("IOException: " + e.getMessage());
     }
  }
}
