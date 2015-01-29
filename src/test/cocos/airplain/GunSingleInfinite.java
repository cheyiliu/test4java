package test.cocos.airplain;

import test.cocos.airplain.Bullet.BulletType;

public class GunSingleInfinite extends Gun {

	@Override
	public boolean fire(GamLayer layer, int x, int y) {
		layer.addChild(Bullet.createOneBullet(BulletType.BulletTypeYellow), x, y);
		return true;
	}

}
