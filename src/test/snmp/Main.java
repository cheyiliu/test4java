
package test.snmp;

public class Main {

    // 修改自 https://github.com/cheyiliu/Android-SNMP manager部分
    // snmp4j command line tool(clt)是收费的， 故自己简单实现了个clt
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        new ManagedDevice("192.168.11.36").updateBatteryLevel();
        new ManagedDevice("192.168.11.36").updateVersionName();
        new ManagedDevice("192.168.11.36").sendMessage("say hi to agent, from manager");
        System.out.println("wait for 5s...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("exit...");
    }

}
