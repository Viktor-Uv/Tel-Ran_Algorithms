package lesson24; // Date 13.09.2023

/*
https://i0.wp.com/javaconceptoftheday.com/wp-content/uploads/2022/04/ByteStreamVsCharacterStreamInJava.png
 */

import java.io.*;
import java.net.Socket;

/**
 * A socket in Java is an object that represents a communication endpoint between two applications
 * over a network. A socket can be used to send and receive data, as well as to establish a connection
 * with another socket. There are two types of sockets in Java: TCP sockets and UDP sockets.
 * TCP sockets are connection-oriented, meaning they require a handshake before exchanging data,
 * and they guarantee reliable and ordered delivery of data. UDP sockets are connection-less,
 * meaning they do not need a handshake, and they do not guarantee reliability or order of data.
 */

public class A_Socket {
    public static void main(String[] args) {
        // http://colormind.io/list is a website that generates color palettes
        // ipv4 0.0.0.0-255.255.255.255 is the range of IP addresses in version 4
        // ipv6 is the newer version of IP addresses with a larger range
        // dns www.whitehouse.gov -> 192.0.66.168 is an example of domain name resolution
        // 80/tcp -> http is the default port and protocol for web servers
        // 25/tcp -> smtp is the default port and protocol for email servers
        // 53/udp -> dns is the default port and protocol for domain name servers

        String server = "colormind.io"; // the server name to connect to
        int port = 80; // the port number to use
        String service = "/list"; // the service path to request
        try (
                Socket socket = new Socket(server, port); // a socket object to communicate with the server
                InputStream is = socket.getInputStream(); // get the input stream to read data from the server
                OutputStream os = socket.getOutputStream(); // get the output stream to write data to the server
                InputStreamReader isr = new InputStreamReader(is); // wrap "is" with a reader to read characters
                BufferedReader br = new BufferedReader(isr); // wrap the reader with a buffer to read lines
                OutputStreamWriter osw = new OutputStreamWriter(os); // wrap "os" with a writer to write chars
        ) {
            osw.write("GET http://colormind.io/list/ HTTP/1.0\n\n"); // write HTTP request line to server
            osw.flush(); // сбросить буферы в поток (flush the writer), чтобы отправить данные сразу
            br.lines()
                    .forEach(System.out::println); // read and print each line from the server response
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
