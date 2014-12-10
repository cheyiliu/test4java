package test.Transient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4000080487117604395L;

	private Date mDate;
	private String mName;
	private transient String mPwd;// NOT do serialize of pwd

	public Login(String name, String pwd) {
		mName = name;
		mPwd = pwd;
		mDate = new Date();
	}

	public String toString() {
		return "Login info:\n username " + mName + "\n date: " + mDate
				+ "\n password: " + mPwd;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Login login = new Login("coolBoy", "123*");
		System.out.println(login.toString());

		try {
			String outName = "src/test/Transient/login.obj";
			// 序列化到文件
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new FileOutputStream(outName));
			objectOutputStream.writeObject(login);
			objectOutputStream.close();

			TimeUnit.SECONDS.sleep(1);

			// 反序列化
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new FileInputStream(outName));
			Login log = (Login) objectInputStream.readObject();
			objectInputStream.close();
			System.out.println(log.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
