
package test.java.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestTransientObjectOutputStream {
    public static class People implements Serializable {
        private static final long serialVersionUID = 8294180014912103005L;

        /**
         * 用户名
         */
        private String username;
        /**
         * 密码 transient
         */
        private transient String password;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static void main(String[] args) throws Exception {

        People p = new People();
        p.setUsername("snowolf");
        p.setPassword("123456");

        System.err.println("------操作前------");
        System.err.println("username: " + p.getUsername());
        System.err.println("password: " + p.getPassword());

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                "people.txt"));
        oos.writeObject(p);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                "people.txt"));
        p = (People) ois.readObject();

        ois.close();

        System.err.println("------操作后------");
        System.err.println("username: " + p.getUsername());
        System.err.println("password: " + p.getPassword());

    }

}
