
package test.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    public static final int TCP_PORT = 51111;
    public static final int UDP_PORT = TCP_PORT + 1;

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            new UdpListener(UDP_PORT);
            new TcpListener(TCP_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static class UdpListener extends Thread {
        private DatagramSocket socket;

        public UdpListener(int ServerPort) throws IOException {
            try {
                socket = new DatagramSocket(ServerPort);
            } catch (SocketException e) {
                throw new IOException("Can't create DatagramSocket:  "
                        + e.getMessage());
            }

            synchronized (this) {
                start();

                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
        }

        public void run() {
            synchronized (this) {
                notifyAll();
            }

            while (true) {
                try {
                    byte[] buf = new byte[10000];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    processUDPRequest(socket, packet);
                } catch (IOException e) {
                }
            }
        }

        public void processUDPRequest(DatagramSocket socket,
                DatagramPacket packet) {

            byte[] request = packet.getData();

            System.out.println("processUDPRequest: " + new String(request));
            try {
                packet.setData("echo from udp server 1".getBytes());
                socket.send(packet);
                packet.setData("echo from udp server 2".getBytes());
                socket.send(packet);
                packet.setData("echo from udp server 3".getBytes());
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class TcpListener extends Thread {

        private ServerSocket serverSocket;

        public TcpListener(int ServerPort) throws IOException {
            try {
                serverSocket = new ServerSocket(ServerPort);
            } catch (SocketException e) {
                throw new IOException("Can't create ServerSocket:  "
                        + e.getMessage());
            }

            synchronized (this) {
                start();

                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
        }

        public void run() {

            synchronized (this) {
                notifyAll();
            }

            while (true) {
                try {
                    Socket socket = serverSocket.accept();

                    processTCPRequest(socket);
                } catch (IOException e) {
                }
            }
        }

        public void processTCPRequest(Socket socket) {

            try {
                DataInputStream input = new DataInputStream(
                        socket.getInputStream());

                byte[] request = new byte[1000];

                input.read(request); // read from socket

                System.out.println("processTCPRequest: " + new String(request));
                socket.getOutputStream().write("echo from TCP server 1".getBytes());
                socket.getOutputStream().write("echo from TCP server 2".getBytes());
                socket.getOutputStream().write("echo from TCP server 3".getBytes());

            } catch (IOException e) {
            }
        }
    }

}
