
package test.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {

        try {
            int port = Server.UDP_PORT;
            String hostname = "192.168.6.72";

            byte[] buf = "hello, udp```".getBytes();
            InetSocketAddress stunServer = new InetSocketAddress(hostname, port);
            DatagramPacket packet = new DatagramPacket(buf, buf.length,
                    stunServer.getAddress(), stunServer.getPort());
            DatagramSocket datagramSocket = new DatagramSocket(56789);
            datagramSocket.send(packet);

            //
            byte[] buf2 = new byte[1000];
            packet = new DatagramPacket(buf2, buf2.length);
            datagramSocket.receive(packet);
            System.out.println(new String(packet.getData()));

            datagramSocket.receive(packet);
            System.out.println(new String(packet.getData()));

            datagramSocket.receive(packet);
            System.out.println(new String(packet.getData()));
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            int port = Server.TCP_PORT;
            String hostname = "192.168.6.72";
            Socket socket = new Socket(hostname, port);
            socket.getOutputStream().write("hello, tcp```".getBytes());

            byte[] buf2 = new byte[1000];
            socket.getInputStream().read(buf2);
            System.out.println(new String(buf2));
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
