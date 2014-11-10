
package com.sun.stun;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Main {

    // stun code from
    // http://code.google.com/p/openwonderland-jvoicebridge/source/browse/branches/jp/stun/src/com/sun/stun/?r=345
    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            int port = 37026;
            String hostname = "218.205.48.254";// huawei server
            InetSocketAddress stunServer = new InetSocketAddress(hostname, port);
            DatagramSocket datagramSocket = new DatagramSocket(56789);
            StunClient stunClient = new StunClient(stunServer, datagramSocket);
            System.out.println(stunClient.getMappedAddress());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
