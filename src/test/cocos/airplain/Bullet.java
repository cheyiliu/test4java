package test.cocos.airplain;

import java.util.ArrayList;

public class Bullet {
	public enum BulletType {
		BulletTypeYellow, BulletTypeBlue
	}

	protected int mForce;
	protected int mRes;

	public Bullet(int mForce, int mRes) {
		this.mForce = mForce;
		this.mRes = mRes;
	}

	public void kill(Role target) {
		target.gotAttacked(mForce);
		// after kill, bullet die
	}

	public static ArrayList<Bullet> createBullets(BulletType type, int num) {
		if (BulletType.BulletTypeBlue == type) {
			ArrayList<Bullet> bulltes = new ArrayList<Bullet>(num);
			for (int i = 0; i < num; i++) {
				bulltes.add(new BulletBlue());
			}
		} else if (BulletType.BulletTypeYellow == type) {
			ArrayList<Bullet> bulltes = new ArrayList<Bullet>(num);
			for (int i = 0; i < num; i++) {
				bulltes.add(new BulletYellow());
			}
		}
		return null;
	}

	public static Bullet createOneBullet(BulletType type) {
		if (BulletType.BulletTypeBlue == type) {
			return new BulletBlue();
		} else if (BulletType.BulletTypeYellow == type) {
			return new BulletYellow();
		}
		return null;
	}
}
