package test.cocos.airplain;

import java.util.ArrayList;

public class Bullet {
	public enum BulletType {
		BulletTypeRed, BulletTypeBlue
	}

	int mForce;

	public void kill(Role target) {
		target.gotAttacked(mForce);
		// after kill, bullet die
	}

	public static ArrayList<Bullet> createBullet(BulletType type, int num) {
		if (BulletType.BulletTypeBlue == type) {
			ArrayList<Bullet> bulltes = new ArrayList<Bullet>(num);
			for (int i = 0; i < num; i++) {
				bulltes.add(new BulletBlue());
			}
		} else if (BulletType.BulletTypeRed == type) {
			ArrayList<Bullet> bulltes = new ArrayList<Bullet>(num);
			for (int i = 0; i < num; i++) {
				bulltes.add(new BulletYellow());
			}
		}
		return null;
	}
}
