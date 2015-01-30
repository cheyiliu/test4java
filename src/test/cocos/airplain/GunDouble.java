package test.cocos.airplain;

import java.util.ArrayList;

import test.cocos.airplain.Bullet.BulletType;

/**
 * 双排子弹的枪，一次发射两枚子弹
 */
public class GunDouble extends Gun {
	private ArrayList<Bullet> mBullets;

	public GunDouble() {
		mBullets = Bullet.createBullets(BulletType.BulletTypeBlue, 20);
	}

	@Override
	public boolean fire(GameLayer layer, int x, int y) {
		int gap = 10;

		int size = mBullets.size();
		if (size > 0) {
			// fire 1
			layer.addChild(mBullets.remove(size - 1), x - gap, y);

			--size;
			if (size > 0) {
				// fire 2
				layer.addChild(mBullets.remove(size - 1), x + gap, y);
			}
			return true;
		}
		return false;
	}
}
