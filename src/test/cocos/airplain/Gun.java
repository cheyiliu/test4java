package test.cocos.airplain;

import java.util.ArrayList;

/**
 * 装配好的枪，配有限量的子弹，功能就一个，发射子弹
 */
public class Gun {
	public enum GunType {
		GunType1, // 发射一排子弹的枪
		GunType2, // 发射两排子弹的枪
		GunType3 // 发射炸全屏的枪
	}

	private ArrayList<Bullet> mBullets;

	public Gun(ArrayList<Bullet> bullets) {
		mBullets = bullets;
	}

	public Bullet fire() {
		if (mBullets != null && mBullets.size() > 0) {
			return mBullets.remove(0);
		}
		return null;
	}
}
